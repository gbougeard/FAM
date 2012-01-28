/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author gbougear
 */
public class LoggingInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * @param ic
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
//        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        LOGGER.trace("ENTRY {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()});
//        logger.log(Level.INFO, "enter Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});
        try {
            return ic.proceed();
        } finally {
            LOGGER.trace("RETURN {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()});
//            logger.log(Level.INFO, "exit Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});

//            logger.exiting(ic.getMethod().toString(), ic.getMethod().getName());
        }
    }
}
