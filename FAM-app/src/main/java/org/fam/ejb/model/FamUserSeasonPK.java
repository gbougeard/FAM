package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 * @author gbougear
 */
public class FamUserSeasonPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long famUser;
    private Long famSeason;
//    private Long famClub;

    /**
     *
     */
    public FamUserSeasonPK() {
    }

    /**
     * @return
     */
    public Long getFamSeason() {
        return famSeason;
    }

    /**
     * @param famSeason
     */
    public void setFamSeason(Long famSeason) {
        this.famSeason = famSeason;
    }

    /**
     * @return
     */
    public Long getFamUser() {
        return famUser;
    }

    /**
     * @param famUser
     */
    public void setFamUser(Long famUser) {
        this.famUser = famUser;
    }

    //  /**
//     *
//     * @return
//     */
//    public Long getFamClub() {
//        return famClub;
//    }
//
//    /**
//     *
//     * @param famClub
//     */
//    public void setFamClub(Long famClub) {
//        this.famClub = famClub;
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamUserSeasonPK other = (FamUserSeasonPK) obj;
        if (this.famUser != other.famUser && (this.famUser == null || !this.famUser.equals(other.famUser))) {
            return false;
        }
        if (this.famSeason != other.famSeason && (this.famSeason == null || !this.famSeason.equals(other.famSeason))) {
            return false;
        }
//        if (this.famClub != other.famClub && (this.famClub == null || !this.famClub.equals(other.famClub))) {
//            return false;
//        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.famUser != null ? this.famUser.hashCode() : 0);
        hash = 17 * hash + (this.famSeason != null ? this.famSeason.hashCode() : 0);
//        hash = 17 * hash + (this.famClub != null ? this.famClub.hashCode() : 0);
        return hash;
    }


}
