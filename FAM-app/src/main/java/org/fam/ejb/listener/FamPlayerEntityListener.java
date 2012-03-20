/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author gbougear
 */
public class FamPlayerEntityListener {

    //    @EJB
//    private FamTypEventFacade ejbTypEvent;
//
    @PrePersist
    @PreUpdate
    void setProfileChart(final Object entity) {
//        LogUtil.log("FamPlayerEntityListener::buildChart", Level.INFO, null);
//        String url = ChartUtil.radarProfile(( (FamPlayer) entity ).getCurrentProfile());
//        ( (FamPlayer) entity ).setProfileChartUrl(url);
//        ( (FamPlayer) entity ).getCurrentProfile().setProfileChartUrl(url);
    }
//
//    @PreUpdate
//    void beforeUpdate(final Object entity) {
//        ((FamMatch) entity).getFamEvent().setFamTypEvent(ejbTypEvent.getMatch());
//    }
//
//    @PostPersist
//    @PostUpdate
//    void afterPersist(final Object entity) {
//    }
//
//    public void setEjbTypEvent(FamTypEventFacade ejbTypEvent) {
//        this.ejbTypEvent = ejbTypEvent;
//    }
}
