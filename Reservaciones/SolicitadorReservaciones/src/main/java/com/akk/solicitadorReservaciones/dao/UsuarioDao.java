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
public class UsuarioDao {
    /** Campo id. */
	private static final String ID = "id";

    /** Campo login. */
    private static final String LOGIN = "login";

    /** Campo password. */
    private static final String PASSWORD = "password";

	/**
	 * Template para manejo de BD de Spring.
	 */
    protected JdbcTemplate jdbcTemplate;

    /**
     * Establece el template.
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Agrega un usuario en la BD.
     * @param usuarioDto Usuario a agregar.
     */
    /*
    public void add(UsuarioDto usuarioDto) {
        jdbcTemplate.execute("INSERT INTO Usuario(login, password) VALUES('"
                + usuarioDto.getLogin() + "', '" + usuarioDto.getPassword()
                + "')");
    }*/
    
    /**
     * Actualiza un usuario en la BD.
     * @param usuarioDto Usuario a agregar.
     */
    /*
    public void upd(UsuarioDto usuarioDto) {
        jdbcTemplate.execute("UPDATE Usuario SET password = '"
                + usuarioDto.getPassword() + "' WHERE login = '" 
                + usuarioDto.getLogin() + "'");
    }*/

    /**
     * Borra un usuario en la BD.
     * @param usuarioDto Usuario a agregar.
     */
    /*
    public void del(UsuarioDto usuarioDto) {
        jdbcTemplate.execute("DELETE FROM Usuario WHERE login = '" 
                + usuarioDto.getLogin() + "'");
    }*/

    /**
     * Consulta usuarios que coincidan con el criterio.
     * @param usuarioDto Usuario a agregar.
     */
//    public List<UsuarioDto> qry(UsuarioDto usuarioDto) {
//        List<UsuarioDto> resultado = null;
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(
//                "SELECT id, login, password FROM Usuario "
//                + "WHERE login = '" + usuarioDto.getLogin() + "'");
//        if (list.size() > 0) {
//            resultado = new ArrayList<UsuarioDto>();
//            for (LinkedHashMap<String, Object> map : list) {
//                UsuarioDto dto = new UsuarioDto();
//                dto.setId((Integer) map.get(ID));
//                dto.setLogin((String) map.get(LOGIN));
//                dto.setPassword((String) map.get(PASSWORD));
//                resultado.add(dto);
//            }
//        }
//        return resultado;
//    }


    /**
     * Consulta usuarios que coincidan con el criterio.
     * @param usuarioDto Usuario a agregar.
     */
    /*
    public List<UsuarioDto> queryRowMapper(UsuarioDto usuarioDto) {
        List<UsuarioDto> resultList = jdbcTemplate.query(
                "SELECT id, login, password FROM Usuario WHERE login = '"
                        + usuarioDto.getLogin() + "'", 
                        new BeanPropertyRowMapper(UsuarioDto.class));
        return resultList;
    }*/
}






