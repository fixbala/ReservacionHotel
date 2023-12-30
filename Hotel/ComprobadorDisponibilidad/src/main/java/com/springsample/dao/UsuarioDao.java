package com.springsample.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.springsample.dto.UsuarioDto;

/**
 * @author tlopez
 */
public class UsuarioDao {
    
    private String idReservacion;
    private String idHotel;
    private String idHabitacion;
    private String fechaReservacion;
    private String codigoRespuesta;
    private Double CostoHabitacion;
    private String costoS;
    protected JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
   /* public UsuarioDao() {

    }
    UsuarioDao (String a, String x, String b,String c,  int d)
    {
        codigoRespuesta=a;
        idHotel = x;
        idReservacion=b;
        idHabitacion=c;
        costoS=String.valueOf(d);
    } */
    
    public List<UsuarioDto> consultaHotel(UsuarioDto usuarioDto) {
        List<UsuarioDto> habitaciones = null;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "SELECT  habitaciones.idHabitacion, habitaciones.CostoHabitacion"
                + " FROM habitaciones"
                + " INNER JOIN reservaciones ON habitaciones.idHotel = reservaciones.idHotel"
                + " WHERE habitaciones.idHotel = " + usuarioDto.getIdHotel() 
                + " AND habitaciones.idhabitacion NOT IN (SELECT idHabitacion FROM reservaciones"
                + " WHERE FechaReservacion = '" + usuarioDto.getFechaReservacion() + "' AND idHotel = " + usuarioDto.getIdHotel() + ")");
        if (list.size() > 0) {
            habitaciones = new ArrayList<UsuarioDto>();
            for (Map<String, Object> map : list) {
                UsuarioDto dto = new UsuarioDto();
                dto.setIdHabitacion((String) map.get(idHabitacion));
                dto.setCostoHabitacion((Double) map.get(CostoHabitacion));
                habitaciones.add(dto);
            }
        }
        return habitaciones;
    }
    
    public List<UsuarioDto> consultaHabitacion(UsuarioDto usuarioDto) {
        List<UsuarioDto> resultList = jdbcTemplate.query(
                "SELECT  habitaciones.idHabitacion, habitaciones.CostoHabitacion"
                        + " FROM habitaciones"
                        + " INNER JOIN reservaciones ON habitaciones.idHotel = reservaciones.idHotel"
                        + " WHERE habitaciones.idHotel = " + usuarioDto.getIdHotel() 
                        + " AND habitaciones.idhabitacion NOT IN (SELECT idHabitacion FROM reservaciones"
                        + " WHERE FechaReservacion = '" + usuarioDto.getFechaReservacion() + "' AND idHotel = " + usuarioDto.getIdHotel() + ")"
                        + " UNION SELECT NULL, NULL FROM DUAL WHERE NOT EXISTS ("
                        + "SELECT  habitaciones.idHabitacion, habitaciones.CostoHabitacion "
                        + "FROM habitaciones "
                        + "INNER JOIN reservaciones ON habitaciones.idHotel = reservaciones.idHotel "
                        + "WHERE habitaciones.idHotel = " + usuarioDto.getIdHotel() 
                        + " AND habitaciones.idhabitacion NOT IN (SELECT idHabitacion FROM reservaciones"
                        + " WHERE FechaReservacion = '" + usuarioDto.getFechaReservacion() + "' AND idHotel = " + usuarioDto.getIdHotel() + "))" , 
                        new BeanPropertyRowMapper(UsuarioDto.class));
        return resultList;
    }
    
}






