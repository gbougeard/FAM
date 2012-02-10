/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author arungupta
 */
@Interceptor
@LoggingInterceptor
public class LoggingInterceptorImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger("FAM-jsf");

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
//        System.out.println("BEFORE: " + context.getMethod().getName());
//        Object response = context.proceed();
//        System.out.println("AFTER: " + context.getMethod().getName());

        LOGGER.trace(ic.getTarget().toString() + ic.getMethod().getName());
        Object response = null;
        try {
            response = ic.proceed();
        } finally {
            LOGGER.trace(ic.getTarget().toString() + ic.getMethod().getName());
        }


        return response;
    }
}
