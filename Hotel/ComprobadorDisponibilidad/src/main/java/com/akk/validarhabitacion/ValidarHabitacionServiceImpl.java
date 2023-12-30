
/**
 * ValidarHabitacionServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package com.akk.validarhabitacion;

import java.util.List;

import com.springsample.dao.UsuarioDao;
import com.springsample.dto.UsuarioDto;

import edu.itq.soa.validarhab.RequestValidar;
import edu.itq.soa.validarhab.ResponseValidar;

/**
     *  ValidarHabitacionServiceSkeleton java skeleton for the axisService
     */
    public class ValidarHabitacionServiceImpl extends ValidarHabitacionServiceSkeleton{
        /** Objeto para acceso a datos. */
        private UsuarioDao usuarioDao;
         
        /**
         * Auto generated method signature
         * 
                                     * @param requestValidar 
             * @return responseValidar 
         */
        
                 public ResponseValidar validarHabitacionOperation(RequestValidar request) {
                     ResponseValidar response = new ResponseValidar();
                     UsuarioDto usuarioDto = new UsuarioDto();
                     response.setIdReservacion(request.getIdReservacion());
                     
                     try {
                         usuarioDto.setIdHotel(request.getIdHotel());
                         usuarioDto.setFechaReservacion(request.getFechaReservacion());
                     List<UsuarioDto> list = usuarioDao.consultaHabitacion(usuarioDto);
                     for (UsuarioDto usuarioDto3 : list) {
                         System.out.println(usuarioDto3.getIdHabitacion());
                         if (usuarioDto3.getIdHabitacion() != null ) {
                             response.setCodigoRespuesta(200);
                             response.setIdHabitacion(usuarioDto3.getIdHabitacion());
                             response.setCosto(usuarioDto3.getCostoHabitacion());
                         }else {
                             response.setCodigoRespuesta(204);
                             response.setIdHabitacion("null"); 
                         }
                        }
                     } catch (Exception e) {
                         System.out.println("Error en transacción: " + e.getMessage());
                         }
                     
                     return response;
                 }

        /**
         * @param usuarioDao the usuarioDao to set
         */
        public void setUsuarioDao(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }
     
    }
    