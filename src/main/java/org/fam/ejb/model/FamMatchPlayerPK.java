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
public class FamMatchPlayerPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private FamMatchTeamPK famMatchTeam;
    private Long famPlayer;

    /**
     * 
     */
    public FamMatchPlayerPK() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamMatchPlayerPK other = (FamMatchPlayerPK) obj;
        if (this.famMatchTeam != other.famMatchTeam && (this.famMatchTeam == null || !this.famMatchTeam.equals(other.famMatchTeam))) {
            return false;
        }
        if (this.famPlayer != other.famPlayer && (this.famPlayer == null || !this.famPlayer.equals(other.famPlayer))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.famMatchTeam != null ? this.famMatchTeam.hashCode() : 0);
        hash = 59 * hash + (this.famPlayer != null ? this.famPlayer.hashCode() : 0);
        return hash;
    }

}
