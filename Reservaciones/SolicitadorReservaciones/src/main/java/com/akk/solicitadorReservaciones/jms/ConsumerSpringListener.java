package com.akk.solicitadorReservaciones.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.akk.solicitadorReservaciones.business.ReservacionBusiness;
import com.akk.solicitadorReservaciones.factory.ReservacionBusinessFactory;
import com.akk.solicitadorReservaciones.factory.UsuarioBusinessFactory;

public class ConsumerSpringListener implements MessageListener {
    /**
     * Objeto de ejecución de negocio.
     */
    // private UsuarioBusiness usuarioBusiness;
    private ReservacionBusiness reservacionBusiness;

    // private UsuarioBusinessFactory usuarioBusinessFactory;
    private ReservacionBusinessFactory reservacionBusinessFactory;

    // private ApplicationContext appContext;

    @Override
    public void onMessage(Message arg0) {
        String mensaje = "";

        System.out.println("[SolicitadorReservaciones] OnMessage :" + this);
        try {
            TextMessage txtMsg = (TextMessage) arg0;

            mensaje = txtMsg.getText();

            reservacionBusiness = (ReservacionBusiness) reservacionBusinessFactory.getBusiness("reservacionBusiness");

            reservacionBusiness.solicitarConfirmacionReservacion(mensaje);

            System.out.println("[SolicitadorReservaciones] Ya volví");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param reservacionBusiness the reservacionBusiness to set
     */
    public final void setReservacionBusiness(ReservacionBusiness reservacionBusiness) {
        this.reservacionBusiness = reservacionBusiness;
    }

    // @Override
    // public void setApplicationContext(ApplicationContext applicationContext)
    // throws BeansException {
    // this.appContext = applicationContext;
    // }

    /**
     * @param usuarioBusinessFactory the usuarioBusinessFactory to set
     */
    /*
     * public final void setUsuarioBusinessFactory(UsuarioBusinessFactory
     * usuarioBusinessFactory) { this.usuarioBusinessFactory =
     * usuarioBusinessFactory; }
     */

    public final void setReservacionBusinessFactory(ReservacionBusinessFactory reservacionBusinessFactory) {
        this.reservacionBusinessFactory = reservacionBusinessFactory;
    }
}
