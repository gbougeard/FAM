/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
public class AuditInterceptor {

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
    public Object profile(InvocationContext ic) throws Exception {
        long initTime = System.currentTimeMillis();
        try {
            return ic.proceed();
        }
        finally {
            long diffTime = System.currentTimeMillis() - initTime;
            logger.log(Level.FINE, "{0} took {1} milliseconds.", new Object[]{ic.getMethod(), diffTime});
        }
    }
}
