/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.common.interceptor;

import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.text.MessageFormat;

/**
 * @author gbougear
 */
public class LoggingInterceptor {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * @param ic
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {

//        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        LOGGER.debug(MessageFormat.format("ENTRY {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()}));
//        logger.log(Level.INFO, "enter Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});
        try {
            return ic.proceed();
        } finally {
            LOGGER.trace(MessageFormat.format("RETURN {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()}));
//            logger.log(Level.INFO, "exit Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});

//            logger.exiting(ic.getMethod().toString(), ic.getMethod().getName());
        }
    }
}
