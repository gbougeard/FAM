/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author mask_hot
 */
public class LogInterceptor {

    @AroundInvoke
    public Object writeJournal(InvocationContext ic) throws Exception {
        System.out.println("AVANT: " + ic.getMethod());
        Object o = ic.proceed();
        System.out.println("APRES: " + ic.getMethod());
        return o;
    }
}
