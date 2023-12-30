package com.akk.validadorConexiones.dao;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.akk.validadorConexiones.dto.ReservacionDto;

/**
 * @author tlopez
 */
public class ReservacionDao {

	private String IDReservacion = "idReservacion";

    private String IDUsuario = "idUsuario";

    private String IDHotel = "idHotel";
    
    private String IDHabitacion = "idHabitacion";
    
    private String FECHA="Fecha";
    
    private String MONTO="Monto";
    
    private String Estatus="Estatus";

	
    protected JdbcTemplate jdbcTemplate;

    /**
     * Establece el template.
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public int ObtenerIDUsuario(ReservacionDto reservacionDto) {
        String sql = "SELECT idUsuario FROM Usuarios WHERE Email = ?";
        return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{reservacionDto.getEmail()}, 
                (rs, rowNum) -> rs.getInt("idUsuario"));
    }
    
    public void AgregarReservacion(ReservacionDto reservacionDto) {
        String sql = "INSERT INTO Reservaciones(idUsuario,idHotel,Fecha,Monto,Estatus,idHabitacion) VALUES("
        +reservacionDto.getIDUsuario()+","+reservacionDto.getIDHotel()+",'"+reservacionDto.getFECHA()+"',"
        +reservacionDto.getMONTO()+",'"+reservacionDto.getEstatus()+"',"+reservacionDto.getIDHabitacion()+")";
        jdbcTemplate.execute(sql);
    }
    
    public void ActualizarEstatusReservacion(ReservacionDto reservacionDto) {
        jdbcTemplate.execute("UPDATE Reservaciones SET Estatus = '"
                + reservacionDto.getEstatus()+"' "
                + "WHERE idReservacion = " 
                + reservacionDto.getIDReservacion() + "");
    }
    
    public void ActualizarReservacion(ReservacionDto reservacionDto) {
        jdbcTemplate.execute("UPDATE Reservaciones SET idUsuario = "
                + reservacionDto.getIDUsuario() + ","
                +"idHotel ="+ reservacionDto.getIDHotel()+","
                +"Fecha ='"+ reservacionDto.getFECHA()+"',"
                + "Monto = "+reservacionDto.getMONTO()+","
                +"Estatus ='" +reservacionDto.getEstatus()+"',"
                +"idHabitacion = "+ reservacionDto.getIDHabitacion()+" "
                + "WHERE idReservacion = " 
                + reservacionDto.getIDReservacion() + "");
    }
    
    public int ObtenerIDReservacion(ReservacionDto reservacionDto) {
        String sql = "select idReservacion from Reservaciones where idUsuario=? ORDER BY idReservacion DESC LIMIT 1";
        return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{reservacionDto.getIDUsuario()}, 
                (rs, rowNum) -> rs.getInt("idReservacion"));
    }
}