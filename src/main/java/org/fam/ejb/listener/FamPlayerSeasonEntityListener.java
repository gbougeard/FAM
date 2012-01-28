/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.model.FamPlayerSeason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
public class FamPlayerSeasonEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamPlayerSeasonEntityListener.class);
    
    @PrePersist
    void beforeCreate(final FamPlayerSeason entity) {
       LOGGER.trace("FamPlayerSeasonEntityListener::beforeCreate");
        setClubAndTeam(entity);
    }

    @PreUpdate
    void beforeUpdate(final FamPlayerSeason entity) {
        LOGGER.trace("FamPlayerSeasonEntityListener::beforeUpdate");
         setClubAndTeam(entity);
    }

    private void setClubAndTeam(FamPlayerSeason entity){
//        entity.setFamClub(entity.getFamPlayer().getFamClub());
//        entity.setFamTeam(entity.getFamPlayer().getFamDefaultTeam());
    }
}
