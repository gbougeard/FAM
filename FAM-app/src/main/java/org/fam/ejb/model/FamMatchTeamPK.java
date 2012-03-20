package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 * @author gbougear
 */
public class FamMatchTeamPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long famMatch;
    private Long famTeam;

    /**
     *
     */
    public FamMatchTeamPK() {
    }

    /**
     * @return
     */
    public Long getFamMatch() {
        return famMatch;
    }

    /**
     * @param famMatch
     */
    public void setFamMatch(Long famMatch) {
        this.famMatch = famMatch;
    }

    /**
     * @return
     */
    public Long getFamTeam() {
        return famTeam;
    }

    /**
     * @param famTeam
     */
    public void setFamTeam(Long famTeam) {
        this.famTeam = famTeam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamMatchTeamPK other = (FamMatchTeamPK) obj;
        if (this.famMatch != other.famMatch && (this.famMatch == null || !this.famMatch.equals(other.famMatch))) {
            return false;
        }
        if (this.famTeam != other.famTeam && (this.famTeam == null || !this.famTeam.equals(other.famTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.famMatch != null ? this.famMatch.hashCode() : 0);
        hash = 29 * hash + (this.famTeam != null ? this.famTeam.hashCode() : 0);
        return hash;
    }

}
