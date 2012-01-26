package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;

/**
 *
 * @author gbougear
 */
public class FamPlayerSeasonPK implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long famPlayer;
    private Long famSeason;
//    private Long famClub;

    /**
     * 
     */
    public FamPlayerSeasonPK() {
    }

    /**
     * 
     * @return
     */
    public Long getFamSeason() {
        return famSeason;
    }

    /**
     * 
     * @param famSeason
     */
    public void setFamSeason(Long famSeason) {
        this.famSeason = famSeason;
    }

    /**
     * 
     * @return
     */
    public Long getFamPlayer() {
        return famPlayer;
    }

    /**
     * 
     * @param famPlayer
     */
    public void setFamPlayer(Long famPlayer) {
        this.famPlayer = famPlayer;
    }

//    public Long getFamClub() {
//        return famClub;
//    }
//
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
        final FamPlayerSeasonPK other = (FamPlayerSeasonPK) obj;
        if (this.famPlayer != other.famPlayer && (this.famPlayer == null || !this.famPlayer.equals(other.famPlayer))) {
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
        int hash = 5;
        hash = 29 * hash + (this.famPlayer != null ? this.famPlayer.hashCode() : 0);
        hash = 29 * hash + (this.famSeason != null ? this.famSeason.hashCode() : 0);
//        hash = 29 * hash + (this.famClub != null ? this.famClub.hashCode() : 0);
        return hash;
    }
    
    

}
