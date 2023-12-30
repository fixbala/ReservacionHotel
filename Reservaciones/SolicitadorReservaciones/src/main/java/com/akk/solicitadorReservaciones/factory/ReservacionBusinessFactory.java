package com.akk.solicitadorReservaciones.factory;

import com.akk.solicitadorReservaciones.business.Business;

public interface ReservacionBusinessFactory {

    Business getBusiness(String name);
}
