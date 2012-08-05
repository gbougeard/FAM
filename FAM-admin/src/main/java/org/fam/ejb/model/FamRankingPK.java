package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author gbougear
 */
@Data
public class FamRankingPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long famTeam;
    private Long famSeasonCompetition;
//    private Long famClub;

    /**
     *
     */
    public FamRankingPK() {
    }

    /**
     * @return
     */
    public Long getFamSeasonCompetition() {
        return famSeasonCompetition;
    }

    /**
     * @param famSeason
     */
    public void setFamSeasonCompetition(Long famSeason) {
        this.famSeasonCompetition = famSeason;
    }

    /**
     * @return
     */
    public Long getFamUser() {
        return famTeam;
    }

    /**
     * @param famUser
     */
    public void setFamTeam(Long famUser) {
        this.famTeam = famUser;
    }


}
