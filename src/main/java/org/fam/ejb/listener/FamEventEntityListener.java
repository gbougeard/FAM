/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.event.FamEventUpdated;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.session.FamEventStatusFacade;
import org.fam.ejb.session.FamSeasonFacade;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author gbougear
 */
public class FamEventEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamEventEntityListener.class);

    @EJB
    private FamEventStatusFacade ejbEventStatus;
    @EJB
    private FamSeasonFacade ejbSeason;
    @Inject
    Event<FamEventUpdated> event;

    @PrePersist
    void beforeCreate(final Object entity) {
        LOGGER.trace("FamEventEntityListener::beforeCreate");
        if (((FamEvent) entity).getFamEventStatus() == null) {
            ((FamEvent) entity).setFamEventStatus(ejbEventStatus.getScheduled());
        }
        if (((FamEvent) entity).getFamSeason() == null) {
            ((FamEvent) entity).setFamSeason(ejbSeason.getCurrentSeason());
        }
        if (((FamEvent) entity).getAllDay() == null) {
            ((FamEvent) entity).setAllDay(Boolean.FALSE);
        }
        DateTime dtStart = new DateTime(((FamEvent) entity).getDtEvent());
        ((FamEvent) entity).setDtEnd((dtStart.plusMinutes(((FamEvent) entity).getDuration())).toDate());
        LOGGER.trace(((FamEvent) entity).toString());
    }

    @PreUpdate
    void beforeUpdate(final Object entity) {
        DateTime dtStart = new DateTime(((FamEvent) entity).getDtEvent());
        ((FamEvent) entity).setDtEnd((dtStart.plusMinutes(((FamEvent) entity).getDuration())).toDate());
    }

    @PostPersist
    @PostUpdate
    void afterPersist(final Object entity) {
        LOGGER.trace("fire FamEventUpdated");
//        event.fire(new FamEventUpdated());
    }

    /**
     * @return
     */
    public FamEventStatusFacade getEjbEventStatus() {
        return ejbEventStatus;
    }

    /**
     * @param ejbEventStatus
     */
    public void setEjbEventStatus(FamEventStatusFacade ejbEventStatus) {
        this.ejbEventStatus = ejbEventStatus;
    }

    /**
     * @return
     */
    public FamSeasonFacade getEjbSeason() {
        return ejbSeason;
    }

    /**
     * @param ejbSeason
     */
    public void setEjbSeason(FamSeasonFacade ejbSeason) {
        this.ejbSeason = ejbSeason;
    }

    /**
     * @return
     */
    public Event<FamEventUpdated> getEvent() {
        return event;
    }

    /**
     * @param event
     */
    public void setEvent(Event<FamEventUpdated> event) {
        this.event = event;
    }

}
