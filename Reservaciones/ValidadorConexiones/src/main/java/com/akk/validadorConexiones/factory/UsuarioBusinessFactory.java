package com.akk.validadorConexiones.factory;

import com.akk.validadorConexiones.business.Business;

public interface UsuarioBusinessFactory {

    Business getBusiness(String name);
}
