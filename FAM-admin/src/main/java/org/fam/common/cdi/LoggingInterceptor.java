package org.fam.common.cdi;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 23/03/12
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
@Loggable
@Interceptor
public class LoggingInterceptor implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private transient Logger logger;

    // ======================================
    // =          Business methods          =
    // ======================================

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
