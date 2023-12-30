package com.akk.solicitadorReservaciones.dao;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.akk.solicitadorReservaciones.dto.ReservacionDto;

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
        System.out.println(sql);
        return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{reservacionDto.getEmail()}, 
                (rs, rowNum) -> rs.getInt("idUsuario"));
    }
    
    public void AgregarReservacion(ReservacionDto reservacionDto) {
        System.out.println("Usuario: "+reservacionDto.getIDUsuario());
        String sql = "INSERT INTO Reservaciones(idUsuario,idHotel,Fecha,Monto,Estatus,idHabitacion) VALUES("
        +reservacionDto.getIDUsuario()+","+reservacionDto.getIDHotel()+",'"+reservacionDto.getFECHA()+"',"
        +reservacionDto.getMONTO()+",'"+reservacionDto.getEstatus()+"',"+reservacionDto.getIDHabitacion()+")";
        jdbcTemplate.execute(sql);
    }
    
    public int ObtenerIDReservacion(ReservacionDto reservacionDto) {
        String sql = "select idReservacion from Reservaciones where idUsuario=? ORDER BY idReservacion DESC LIMIT 1";
        System.out.println(sql);
        return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{reservacionDto.getIDUsuario()}, 
                (rs, rowNum) -> rs.getInt("idReservacion"));
    }
    
    public String ObtenerEmailUsuario(ReservacionDto reservacionDto) {
        String sql = "select Email from Usuarios where idUsuario=?";
        System.out.println(sql);
        return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{reservacionDto.getIDUsuario()}, 
                (rs, rowNum) -> rs.getString("Email"));
    }
    
    public ReservacionDto ObtenerReservacion(String idReservacion) {
        String sql = "SELECT * FROM Reservaciones where idReservacion=?";
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, new Object[]{idReservacion}, (rs, rowNum) ->
        new ReservacionDto(
                rs.getInt("idReservacion"),
                rs.getInt("idUsuario"),
                rs.getInt("idHotel"),
                rs.getString("Fecha"),
                rs.getDouble("Monto"),
                rs.getString("Estatus"),
                rs.getInt("idHabitacion")
        ));
    }
    
    public void ActualizarEstatusReservacion(ReservacionDto reservacionDto) {
        jdbcTemplate.execute("UPDATE Reservaciones SET Estatus = '"
                + reservacionDto.getEstatus()+"' "
                + "WHERE idReservacion = " 
                + reservacionDto.getIDReservacion() + "");
    }
}