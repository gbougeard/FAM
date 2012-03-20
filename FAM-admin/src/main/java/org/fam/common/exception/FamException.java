/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.common.exception;

import javax.ejb.ApplicationException;

/**
 * @author mask_hot
 */
@ApplicationException(rollback = true)
public class FamException extends RuntimeException {

    /**
     * @param message
     */
    public FamException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public FamException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public FamException(String message, Throwable cause) {
        super(message, cause);
    }
}
