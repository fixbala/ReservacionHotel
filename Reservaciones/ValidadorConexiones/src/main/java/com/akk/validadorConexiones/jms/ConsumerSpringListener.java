package com.akk.validadorConexiones.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.akk.validadorConexiones.business.ReservacionBusiness;
import com.akk.validadorConexiones.factory.ReservacionBusinessFactory;
import com.akk.validadorConexiones.factory.UsuarioBusinessFactory;

public class ConsumerSpringListener implements MessageListener {
    /**
     * Objeto de ejecución de negocio.
     */
    //private UsuarioBusiness usuarioBusiness;
    private ReservacionBusiness reservacionBusiness;

    //private UsuarioBusinessFactory usuarioBusinessFactory;
    private ReservacionBusinessFactory reservacionBusinessFactory;

    // private ApplicationContext appContext;

    @Override
    public void onMessage(Message arg0) {
        String mensaje = "";
        
        System.out.println("[ValidadorConexiones] OnMessage:" +this);
        try {
            TextMessage txtMsg = (TextMessage) arg0;
            
            mensaje = txtMsg.getText();
            
            reservacionBusiness = (ReservacionBusiness) reservacionBusinessFactory.getBusiness("reservacionBusiness");
            
            reservacionBusiness.agregarReservacion(mensaje);
            System.out.println("[ValidadorConexiones] Ya volví");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param usuarioBusiness the usuarioBusiness to set
     */
    /*public final void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }*/
    
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
    /*public final void setUsuarioBusinessFactory(UsuarioBusinessFactory usuarioBusinessFactory) {
        this.usuarioBusinessFactory = usuarioBusinessFactory;
    }*/
    
    public final void setReservacionBusinessFactory(ReservacionBusinessFactory reservacionBusinessFactory) {
        this.reservacionBusinessFactory = reservacionBusinessFactory;
    }
}
