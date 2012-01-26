/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamPlayerProfile;
import org.fam.ejb.util.ChartUtil;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
public class FamPlayerProfileEntityListener {

//    @EJB
//    private FamTypEventFacade ejbTypEvent;
//
    @PrePersist
    @PreUpdate
    void buildChart(final Object entity) {
        LogUtil.log("FamPlayerProfileEntityListener::buildChart", Level.INFO, null);
        
        FamPlayerProfile profile = null;
        
        if (entity.getClass() == FamPlayerProfile.class) {
            profile = (FamPlayerProfile) entity;

        } else if (entity.getClass() == FamPlayer.class) {
            profile = ( (FamPlayer) entity ).getCurrentProfile();

        }

        String url = ChartUtil.radarProfile(profile);
        
        if (entity.getClass() == FamPlayerProfile.class) {
           ((FamPlayerProfile) entity).setProfileChartUrl(url);

        } else if (entity.getClass() == FamPlayer.class) {
            ( (FamPlayer) entity ).getCurrentProfile().setProfileChartUrl(url);

        }

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
