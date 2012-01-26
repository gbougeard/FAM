/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gbougear
 */
public class LogUtil {

    private static final Logger logger;

    static {
        logger = Logger.getLogger("FAM-org.fam.ejb");
        logger.setLevel(Level.ALL);
    }

    /**
     * 
     * @param info
     * @param level
     * @param ex
     */
    public static void log(String info, Level level, Throwable ex) {
        logger.log(level, info, ex);

    }

    /**
     * 
     * @return
     */
    public static Logger getLogger() {
        return logger;
    }

}
