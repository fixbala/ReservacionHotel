package com.akk.solicitadorReservaciones.business;

import java.rmi.RemoteException;
import java.sql.Time;

import org.apache.axis2.AxisFault;
import org.apache.xmlbeans.XmlException;

import com.akk.receptorValidadorConexiones.*;
import com.akk.receptorValidadorConexiones.RequestValidadorConexionesDocument.RequestValidadorConexiones;
import com.akk.receptorValidadorConexiones.ResponseValidadorConexionesDocument.ResponseValidadorConexiones;
import com.akk.solicitadorReservaciones.dao.ReservacionDao;
import com.akk.solicitadorReservaciones.dto.ReservacionDto;
import com.akk.solicitadorReservaciones.jms.JmsSender;
import com.akk.validarhabitacion.*;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument.RequestRealizarPagos;

import com.akk.generarReserva.*;

/**
 * Clase de negocio.
 * 
 * @author Alexis Aguirre.
 *
 */
public class ReservacionBusiness implements Business {
    /** Emisor de mensajes. */
    private JmsSender jmsSender;

    private ReservacionDao reservacionDao;

    @Override
    public void solicitarConfirmacionReservacion(String xml) {

        try {
            System.out.println("[SolicitadorReservaciones] Instancia de negocio: " + this);

            RequestRealizarPagosDocument docBanco = RequestRealizarPagosDocument.Factory.parse(xml);

            System.out.println("----------[SolicitadorReservaciones]-------------\n\n");
            System.out.println(docBanco.xmlText());
            System.out.println("----------[SolicitadorReservaciones]-------------\n\n");

            RequestRealizarPagos responseBanco = docBanco.getRequestRealizarPagos();
            int codigoRespuestaBanco = responseBanco.getCodigoRespuesta();
            if (codigoRespuestaBanco == 200) {
                System.out.println("IDReservacion: "+responseBanco.getIdReservacion());
                ReservacionDto reservacionDto = reservacionDao.ObtenerReservacion(responseBanco.getIdReservacion());
                reservacionDto.setEmail(reservacionDao.ObtenerEmailUsuario(reservacionDto));
                System.out.println("IDReservacionPost: "+reservacionDto.getIDReservacion());
                GenerarReservaServiceStub stubGenerarReservacionHabitacion = new GenerarReservaServiceStub(
                        "http://192.168.43.35:8080/axis2/services/GenerarReservaService");

                // Solicita confirmar la reservación en el hotel
                GenerarReservaServiceStub.RequestGenerar requestGenerar = new GenerarReservaServiceStub.RequestGenerar();
                requestGenerar.setIdReservacion(reservacionDto.getIDReservacion().toString());
                requestGenerar.setEmailUsuario(reservacionDto.getEmail());
                requestGenerar.setIdHotel(reservacionDto.getIDHotel().toString());
                requestGenerar.setIdHabitacion(reservacionDto.getIDHabitacion().toString());
                requestGenerar.setFechaReservacion(reservacionDto.getFECHA());

                // Verifica si se hizo la reservación en el hotel
                GenerarReservaServiceStub.ResponseGenerar responseGenerarReservacion = stubGenerarReservacionHabitacion
                        .generarReservaOperation(requestGenerar);
                int codigoRespuestaHotel = responseGenerarReservacion.getCodigoRespuesta();
                if (codigoRespuestaHotel == 200) {
                    reservacionDto.setEstatus("Confirmada");
                    reservacionDao.ActualizarEstatusReservacion(reservacionDto);
                    ResponderEstatus(codigoRespuestaHotel, reservacionDto.getIDReservacion().toString());
                }
                    
                else
                    ResponderEstatus(codigoRespuestaHotel, "");
            } else
                ResponderEstatus(codigoRespuestaBanco, "");
        } catch (RemoteException e) {
            ResponderEstatus(501, "");
            e.printStackTrace();
        } catch (XmlException e) {
            ResponderEstatus(501, "");
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

    /***
     * Devuelve respuesta al usuario inicial del sistema, escribiendo en una Queue
     * 
     * @param codigoRespuesta   El código HTTP devuelto al usuario
     * @param codigoReservacion Utilizado para generar la factura
     */
    private void ResponderEstatus(int codigoRespuesta, String codigoReservacion) {
        ResponseValidadorConexionesDocument docValidadorConexiones = ResponseValidadorConexionesDocument.Factory
                .newInstance();
        ResponseValidadorConexiones responseValidador = docValidadorConexiones.addNewResponseValidadorConexiones();

        responseValidador.setCodigoRespuesta(codigoRespuesta);
        if (codigoRespuesta == 200)
            responseValidador.setFactura("Se ha generado la factura " + codigoReservacion);

        System.out.println("****************[SolicitadorReservacionesResponse]*************************");
        System.out.println(responseValidador.xmlText());
        System.out.println("****************[SolicitadorReservacionesResponse]*************************");
        jmsSender.sendMessage("queue/ex", responseValidador.xmlText());
    }
}
