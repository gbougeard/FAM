/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.model.FamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gbougear
 */
public class AbstractEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityListener.class);

    /**
     * @param entity
     */
    @PrePersist
    public void doPrePersist(final Object entity) {
//        LOGGER.debug("doPrePersist " + entity);
        ((FamEntity) entity).setDtCreat(new Date());
    }

    /**
     * @param entity
     */
    @PostPersist
    public void doPostPersist(Object entity) {
//        LOGGER.debug("doPostPersist " + entity);
    }

    /**
     * @param entity
     */
    @PreRemove
    public void doPreRemove(final Object entity) {
        LOGGER.debug("doPreRemove " + entity);
    }

    /**
     * @param entity
     */
    @PostRemove
    public void doPostRemove(final Object entity) {
        LOGGER.debug("doPostRemove " + entity);
    }

    /**
     * @param entity
     */
    @PreUpdate
    public void doPreUpdate(final Object entity) {
//        LOGGER.debug("doPreUpdate " + entity);
        ((FamEntity) entity).setDtModif(new Date());
    }

    /**
     * @param entity
     */
    @PostUpdate
    public void doPostUpdate(final Object entity) {
//        LOGGER.debug("doPostUpdate " + entity);

    }

    /**
     * @param entity
     */
    @PostLoad
    public void doPostLoad(final Object entity) {
//        LOGGER.debug("doPostLoad ", Level.FINEST, null);
    }
}
