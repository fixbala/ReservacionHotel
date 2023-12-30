package com.akk.validadorConexiones.business;

import java.rmi.RemoteException;
import java.sql.Time;

import org.apache.axis2.AxisFault;
import org.apache.xmlbeans.XmlException;

import com.akk.receptorValidadorConexiones.*;
import com.akk.receptorValidadorConexiones.ResponseValidadorConexionesDocument.ResponseValidadorConexiones;
import com.akk.validadorConexiones.dao.ReservacionDao;
import com.akk.validadorConexiones.dao.UsuarioDao;
import com.akk.validadorConexiones.dto.ReservacionDto;
import com.akk.validadorConexiones.dto.UsuarioDto;
import com.akk.validadorConexiones.jms.JmsSender;

import com.akk.validarhabitacion.*;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument.RequestRealizarPagos;

/**
 * Clase de negocio.
 * 
 * @author tlopez.
 *
 */
public class ReservacionBusiness implements Business {
    /** Emisor de mensajes. */
    private JmsSender jmsSender;

    private ReservacionDao reservacionDao;

    @Override
    public void agregarReservacion(String xml) {

        try {
            System.out.println("[ValidadorConexiones] Instancia de negocio: " + this);

            RequestValidadorConexionesDocument docValidadorConexiones = RequestValidadorConexionesDocument.Factory
                    .parse(xml);
            ReservacionDto reservacionDto = new ReservacionDto();

            System.out.println("----------[ValidadorConexiones]-------------\n\n");
            System.out.println(xml);
            System.out.println("----------[ValidadorConexiones]-------------\n\n");

            // Crea el registro de la reservación en BD
            AgendarReservacion(reservacionDto, docValidadorConexiones);
            reservacionDto.setIDReservacion(reservacionDao.ObtenerIDReservacion(reservacionDto));

            // Conecta al hotel para ver si hay disponibilidad de habitaciones para esa
            // fecha
            ValidarHabitacionServiceStub stubValidarHabitacion = new ValidarHabitacionServiceStub(
                    "http://192.168.43.35:8082/axis2/services/ValidarHabitacionService/");

            ValidarHabitacionServiceStub.RequestValidar requestValidarDisponibilidadHabitacion = new ValidarHabitacionServiceStub.RequestValidar();
            requestValidarDisponibilidadHabitacion.setIdReservacion(reservacionDto.getIDReservacion().toString());
            requestValidarDisponibilidadHabitacion.setIdHotel(reservacionDto.getIDHotel().toString());
            requestValidarDisponibilidadHabitacion.setFechaReservacion(reservacionDto.getFECHA());

            ValidarHabitacionServiceStub.ResponseValidar response = stubValidarHabitacion
                    .validarHabitacionOperation(requestValidarDisponibilidadHabitacion);
            if (response.getCodigoRespuesta() == 200) {

                reservacionDto.setMONTO(response.getCosto());
                reservacionDto.setIDHabitacion(Integer.parseInt(response.getIdHabitacion()));
                reservacionDto.setIDReservacion(Integer.parseInt(response.getIdReservacion()));

                reservacionDao.ActualizarReservacion(reservacionDto);

                // hace petición al banco para realizar el pago
                RequestRealizarPagosDocument docBanco = RequestRealizarPagosDocument.Factory.newInstance();
                RequestRealizarPagos requestBanco = docBanco.addNewRequestRealizarPagos();

                requestBanco.setIdReservacion(reservacionDto.getIDReservacion().toString());
                requestBanco.setEmailUsuario(reservacionDto.getEmail());
                requestBanco.setCosto(reservacionDto.getMONTO());
                requestBanco.setCodigoRespuesta(0);

                System.out.println("****************[ValidadorConexionesResponse]*************************");
                System.out.println(docBanco.xmlText());
                System.out.println("****************[ValidadorConexionesResponse]*************************");
                jmsSender.sendMessage("queue/C", docBanco.xmlText());
            } else {
                reservacionDto.setEstatus("Cancelada");
                reservacionDao.ActualizarEstatusReservacion(reservacionDto);
                ResponderEstatus(response.getCodigoRespuesta());
            }

        } catch (XmlException e) {
            ResponderEstatus(501);
            e.printStackTrace();

        } catch (RemoteException e) {
            ResponderEstatus(501);
            e.printStackTrace();
        }
    }

    /**
     * @param jmsSender the jmsSender to set
     */
    public final void setJmsSender(JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public final void setReservacionDao(ReservacionDao reservacionDao) {
        this.reservacionDao = reservacionDao;
    }

    private void AgendarReservacion(ReservacionDto reservacionDto, RequestValidadorConexionesDocument doc) {
        reservacionDto.setIDReservacion(null);
        reservacionDto.setEmail(doc.getRequestValidadorConexiones().getEmailUsuario());
        reservacionDto.setFECHA(doc.getRequestValidadorConexiones().getFechaReservacion());
        reservacionDto.setIDHotel(Integer.parseInt(doc.getRequestValidadorConexiones().getIdHotel()));
        reservacionDto.InicializarReservacion();
        reservacionDto.setIDUsuario(reservacionDao.ObtenerIDUsuario(reservacionDto));
        reservacionDao.AgregarReservacion(reservacionDto);
    }

    private void ResponderEstatus(int codigoRespuesta) {
        ResponseValidadorConexionesDocument docValidadorConexiones = ResponseValidadorConexionesDocument.Factory
                .newInstance();
        ResponseValidadorConexiones responseValidador = docValidadorConexiones.addNewResponseValidadorConexiones();

        responseValidador.setCodigoRespuesta(codigoRespuesta);

        System.out.println("****************[ValidadorConexionesResponse]*************************");
        System.out.println(responseValidador.xmlText());
        System.out.println("****************[ValidadorConexionesResponse]*************************");
        jmsSender.sendMessage("queue/ex", responseValidador.xmlText());
    }
}
