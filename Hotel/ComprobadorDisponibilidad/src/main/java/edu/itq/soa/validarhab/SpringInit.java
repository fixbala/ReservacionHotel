/**
 * 
 */
package edu.itq.soa.validarhab;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tlopez
 *
 */
public class SpringInit implements ServiceLifeCycle {

    @Override
    public void startUp(ConfigurationContext configctx, AxisService service) {
        ClassLoader classLoader = service.getClassLoader();
        ClassPathXmlApplicationContext appCtx = new
            ClassPathXmlApplicationContext(new String[] 
                    {"applicationContext.xml"}, false);
        appCtx.setClassLoader(classLoader);
        appCtx.refresh();
    }

    @Override
    public void shutDown(ConfigurationContext configctx, AxisService service) {
        // TODO Auto-generated method stub
        
    }

}
