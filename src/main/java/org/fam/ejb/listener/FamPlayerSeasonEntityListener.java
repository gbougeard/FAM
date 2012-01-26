/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.listener;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamPlayerSeason;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
public class FamPlayerSeasonEntityListener {


    @PrePersist
    void beforeCreate(final FamPlayerSeason entity) {
        LogUtil.log("FamPlayerSeasonEntityListener::beforeCreate", Level.OFF, null);
        setClubAndTeam(entity);
    }

    @PreUpdate
    void beforeUpdate(final FamPlayerSeason entity) {
         LogUtil.log("FamPlayerSeasonEntityListener::beforeUpdate", Level.OFF, null);
         setClubAndTeam(entity);
    }

    private void setClubAndTeam(FamPlayerSeason entity){
//        entity.setFamClub(entity.getFamPlayer().getFamClub());
//        entity.setFamTeam(entity.getFamPlayer().getFamDefaultTeam());
    }
}
