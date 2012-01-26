/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * @author arungupta
 */
@Interceptor
@LoggingInterceptor
public class LoggingInterceptorImpl {

    private Logger logger = Logger.getLogger("FAM-jsf");

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
//        System.out.println("BEFORE: " + context.getMethod().getName());
//        Object response = context.proceed();
//        System.out.println("AFTER: " + context.getMethod().getName());

        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        Object response = null;
        try {
            response = ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }


        return response;
    }
}
