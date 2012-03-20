package org.fam.common.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 20/03/12
 * Time: 00:38
 * To change this template use File | Settings | File Templates.
 */
public class LogResource {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
