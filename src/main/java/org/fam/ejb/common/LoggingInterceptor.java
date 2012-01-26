/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.common;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gbougear
 */
public class LoggingInterceptor {

    private static final Logger logger;

    static {
        logger = Logger.getLogger("FAM-org.fam.ejb");
        logger.setLevel(Level.ALL);
    }

    /**
     * 
     * @param ic
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {

//        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        logger.log(Level.INFO, "ENTRY {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()});
//        logger.log(Level.INFO, "enter Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});
        try {
            return ic.proceed();
        } finally {
            logger.log(Level.INFO, "RETURN {0}::{1}", new Object[]{ic.getMethod(), ic.getMethod().getName()});
//            logger.log(Level.INFO, "exit Target {0} {1}", new Object[]{ic.getTarget(), ic.getTarget().toString()});

//            logger.exiting(ic.getMethod().toString(), ic.getMethod().getName());
        }
    }
}
