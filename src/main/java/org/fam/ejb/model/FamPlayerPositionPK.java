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
public class FamPlayerPositionPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long famPlayer;
    private Long famPosition;
    private Integer numOrder;

    /**
     * 
     */
    public FamPlayerPositionPK() {
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

    public Integer getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(Integer numOrder) {
        this.numOrder = numOrder;
    }

    public Long getFamPosition() {
        return famPosition;
    }

    public void setFamPosition(Long famPosition) {
        this.famPosition = famPosition;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamPlayerPositionPK other = (FamPlayerPositionPK) obj;
        if (this.famPlayer != other.famPlayer && ( this.famPlayer == null || !this.famPlayer.equals(other.famPlayer) )) {
            return false;
        }
        if (this.famPosition != other.famPosition && ( this.famPosition == null || !this.famPosition.equals(other.famPosition) )) {
            return false;
        }
        if (this.numOrder != other.numOrder && ( this.numOrder == null || !this.numOrder.equals(other.numOrder) )) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + ( this.famPlayer != null ? this.famPlayer.hashCode() : 0 );
        hash = 41 * hash + ( this.famPosition != null ? this.famPosition.hashCode() : 0 );
        hash = 41 * hash + ( this.numOrder != null ? this.numOrder.hashCode() : 0 );
        return hash;
    }
    
    

}
