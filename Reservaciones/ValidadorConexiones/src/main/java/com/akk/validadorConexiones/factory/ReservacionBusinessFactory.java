package com.akk.validadorConexiones.factory;

import com.akk.validadorConexiones.business.Business;

public interface ReservacionBusinessFactory {

    Business getBusiness(String name);
}
