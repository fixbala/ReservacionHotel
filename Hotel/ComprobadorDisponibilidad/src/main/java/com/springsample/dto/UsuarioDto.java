/**
 * 
 */
package com.springsample.dto;

/**
 * @author tlopez
 *
 */
public class UsuarioDto {
    private String idReservacion;
    private String idHotel;
    private String idHabitacion;
    private String fechaReservacion;
    private String codigoRespuesta;
    private Double CostoHabitacion;
    /**
     * @return the idReservacion
     */
    public String getIdReservacion() {
        return idReservacion;
    }
    /**
     * @param idReservacion the idReservacion to set
     */
    public void setIdReservacion(String idReservacion) {
        this.idReservacion = idReservacion;
    }
    /**
     * @return the idHotel
     */
    public String getIdHotel() {
        return idHotel;
    }
    /**
     * @param idHotel the idHotel to set
     */
    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }
    /**
     * @return the idHabitacion
     */
    public String getIdHabitacion() {
        return idHabitacion;
    }
    /**
     * @param idHabitacion the idHabitacion to set
     */
    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
    /**
     * @return the fechaReservacion
     */
    public String getFechaReservacion() {
        return fechaReservacion;
    }
    /**
     * @param fechaReservacion the fechaReservacion to set
     */
    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }
    /**
     * @return the codigoRespuesta
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }
    /**
     * @param codigoRespuesta the codigoRespuesta to set
     */
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    /**
     * @return the costoHabitacion
     */
    public Double getCostoHabitacion() {
        return CostoHabitacion;
    }
    /**
     * @param costoHabitacion the costoHabitacion to set
     */
    public void setCostoHabitacion(Double costoHabitacion) {
        CostoHabitacion = costoHabitacion;
    }



    
}