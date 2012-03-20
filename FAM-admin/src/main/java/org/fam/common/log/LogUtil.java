/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * @author gbougear
 */
public class LogUtil {

    //    private static final java.util.logging.Logger logger;
    private static final Logger LOGGER = LoggerFactory.getLogger("FAM");

//    static {
//        logger =  java.util.logging.Logger.getLogger("FAM-org.fam.ejb");
//        logger.setLevel(Level.ALL);
//    }

    /**
     * @param info
     * @param level
     * @param ex
     */
    public static void log(String info, Level level, Throwable ex) {
//        logger.log(level, info, ex);
        LOGGER.info(info, ex);

    }

    /**
     * @return
     */
    public static Logger getLogger() {
        return LOGGER;
    }

}
