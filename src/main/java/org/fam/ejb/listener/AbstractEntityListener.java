/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
public class AbstractEntityListener {

    /**
     * 
     * @param entity
     */
    @PrePersist
    public void doPrePersist(final Object entity) {
        LogUtil.log("doPrePersist " + entity, Level.FINEST, null);
        ((FamEntity) entity).setDtCreat(new Date());
    }

    /**
     * 
     * @param entity
     */
    @PostPersist
    public void doPostPersist(Object entity) {
        LogUtil.log("doPostPersist " + entity, Level.FINEST, null);
    }

    /**
     * 
     * @param entity
     */
    @PreRemove
    public void doPreRemove(final Object entity) {
        LogUtil.log("doPreRemove " + entity, Level.FINEST, null);
    }

    /**
     * 
     * @param entity
     */
    @PostRemove
    public void doPostRemove(final Object entity) {
        LogUtil.log("doPostRemove " + entity, Level.FINEST, null);
    }

    /**
     * 
     * @param entity
     */
    @PreUpdate
    public void doPreUpdate(final Object entity) {
        LogUtil.log("doPreUpdate " + entity, Level.FINEST, null);
        ((FamEntity) entity).setDtModif(new Date());
    }

    /**
     * 
     * @param entity
     */
    @PostUpdate
    public void doPostUpdate(final Object entity) {
        LogUtil.log("doPostUpdate " + entity, Level.FINEST, null);
       
    }

    /**
     * 
     * @param entity
     */
    @PostLoad
    public void doPostLoad(final Object entity) {
        LogUtil.log("doPostLoad ", Level.FINEST, null);
//        LogUtil.log("doPostLoad " + entity, Level.FINEST, null);
    }
}
