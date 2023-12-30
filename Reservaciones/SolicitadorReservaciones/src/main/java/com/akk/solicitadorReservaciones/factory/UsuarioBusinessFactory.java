package com.akk.solicitadorReservaciones.factory;

import com.akk.solicitadorReservaciones.business.Business;

public interface UsuarioBusinessFactory {

    Business getBusiness(String name);
}
