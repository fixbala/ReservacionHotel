package com.akk.solicitadorReservaciones.dto;

/**
 * @author tlopez
 *
 */
public class ReservacionDto {
    
    private Integer IDReservacion;
    private String Email;
    private Integer IDUsuario;
    private Integer IDHotel;
    private Integer IDHabitacion;
    private String FECHA;
    private Double MONTO;
    private String Estatus;
    
    public ReservacionDto() {
        
    }
    
    public ReservacionDto(int idReservacion,int idUsuario,int idHotel,String fecha, double monto, String estatus,int idHabitacion) {
        this.IDReservacion=idReservacion;
        this.IDHabitacion=idHabitacion;
        this.IDUsuario=idUsuario;
        this.IDHotel=idHotel;
        this.FECHA=fecha;
        this.MONTO=monto;
        this.Estatus=estatus;
    }
    /**
     * @return the iDReservacion
     */
    public Integer getIDReservacion() {
        return IDReservacion;
    }
    /**
     * @param iDReservacion the iDReservacion to set
     */
    public void setIDReservacion(Integer iDReservacion) {
        IDReservacion = iDReservacion;
    }
    /**
     * @return the iDUsuario
     */
    public Integer getIDUsuario() {
        return IDUsuario;
    }
    /**
     * @param iDUsuario the iDUsuario to set
     */
    public void setIDUsuario(Integer iDUsuario) {
        IDUsuario = iDUsuario;
    }
    /**
     * @return the iDHotel
     */
    public Integer getIDHotel() {
        return IDHotel;
    }
    /**
     * @param iDHotel the iDHotel to set
     */
    public void setIDHotel(Integer iDHotel) {
        IDHotel = iDHotel;
    }
    /**
     * @return the iDHabitacion
     */
    public Integer getIDHabitacion() {
        return IDHabitacion;
    }
    /**
     * @param iDHabitacion the iDHabitacion to set
     */
    public void setIDHabitacion(Integer iDHabitacion) {
        IDHabitacion = iDHabitacion;
    }
    /**
     * @return the fECHA
     */
    public String getFECHA() {
        return FECHA;
    }
    /**
     * @param fECHA the fECHA to set
     */
    public void setFECHA(String fECHA) {
        FECHA = fECHA;
    }
    /**
     * @return the mONTO
     */
    public Double getMONTO() {
        return MONTO;
    }
    /**
     * @param mONTO the mONTO to set
     */
    public void setMONTO(Double mONTO) {
        MONTO = mONTO;
    }
    /**
     * @return the estatus
     */
    public String getEstatus() {
        return Estatus;
    }
    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        Estatus = estatus;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    
    public void InicializarReservacion(){
        this.MONTO=0d;
        this.Estatus="En Proceso";
        this.IDHabitacion=0;
    }
    
}
