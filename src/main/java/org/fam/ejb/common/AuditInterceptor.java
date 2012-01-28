/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author gbougear
 */
public class AuditInterceptor {

     private static final Logger LOGGER = LoggerFactory.getLogger(AuditInterceptor.class);

    /**
     * 
     * @param ic
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object profile(InvocationContext ic) throws Exception {
        long initTime = System.currentTimeMillis();
        try {
            return ic.proceed();
        }
        finally {
            long diffTime = System.currentTimeMillis() - initTime;

            LOGGER.trace("{0} took {1} milliseconds.", new Object[]{ic.getMethod(), diffTime});
        }
    }
}
