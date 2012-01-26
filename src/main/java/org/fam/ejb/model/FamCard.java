package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamCard.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamCard.findAll", query = "SELECT f FROM FamCard f"),
    @NamedQuery(name = "FamCard.findByIdCard", query = "SELECT f FROM FamCard f WHERE f.idCard = :idCard"),
    @NamedQuery(name = "FamCard.findByCardTime", query = "SELECT f FROM FamCard f WHERE f.cardTime = :cardTime"),
    @NamedQuery(name = "FamCard.findByMatchAndTeam", query = "SELECT f FROM FamCard f WHERE f.famMatchPlayer.famMatchTeam.famMatch = :famMatch AND f.famMatchPlayer.famMatchTeam.famTeam = :famTeam"),
    @NamedQuery(name = "FamCard.findByDtCreat", query = "SELECT f FROM FamCard f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamCard.findByDtModif", query = "SELECT f FROM FamCard f WHERE f.dtModif = :dtModif")})
public class FamCard extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_card";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idCard";
    /**
     * 
     */
    public static final String COL_ID = "id_card";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idCard;

    
    @Override
    public Long getId(){
        return this.getIdCard();
    }
    /**
     * 
     */
    public static final String PROP_TIME = "cardTime";
    public static final String COL_TIME = "card_time";
    @Column(name = COL_TIME)
    private String cardTime;
    //
    /**
     * 
     */
    public static final String COL_ID_TYP = "id_typ_card";
    /**
     * 
     */
    public static final String PROP_TYP = "famTypCard";
    @JoinColumn(name = COL_ID_TYP, referencedColumnName = FamTypCard.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamTypCard famTypCard;
    //
    /**
     * 
     */
    public static final String COL_ID_MATCH = "id_match";
    /**
     * 
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     * 
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     * 
     */
    public static final String PROP_MATCH_PLAYER = "famMatchPlayer";
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatchPlayer.COL_ID_MATCH),
        @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamMatchPlayer.COL_ID_TEAM),
        @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = FamMatchPlayer.COL_ID_PLAYER)})
    @NotNull
    private FamMatchPlayer famMatchPlayer;
    //

    /**
     *
     */
    public FamCard() {
    }

    /**
     *
     * @param idCard
     */
    public FamCard(Long idCard) {
        this.idCard = idCard;
    }

    /**
     *
     * @return
     */
    public Long getIdCard() {
        return idCard;
    }

    /**
     *
     * @param idCard
     */
    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    /**
     *
     * @return
     */
    public String getCardTime() {
        return cardTime;
    }

    /**
     *
     * @param CardTime
     */
    public void setCardTime(String CardTime) {
        this.cardTime = CardTime;
    }

    /**
     *
     * @return
     */
    public FamMatchPlayer getFamMatchPlayer() {
        return famMatchPlayer;
    }

    /**
     *
     * @param famMatchPlayer
     */
    public void setFamMatchPlayer(FamMatchPlayer famMatchPlayer) {
        this.famMatchPlayer = famMatchPlayer;
    }

    /**
     * 
     * @return
     */
    public FamTypCard getFamTypCard() {
        return famTypCard;
    }

    /**
     * 
     * @param famTypCard
     */
    public void setFamTypCard(FamTypCard famTypCard) {
        this.famTypCard = famTypCard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCard != null ? idCard.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamCard)) {
            return false;
        }
        FamCard other = (FamCard) object;
        if (( this.idCard == null && other.idCard != null ) || ( this.idCard != null && !this.idCard.equals(other.idCard) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Class cls = this.getClass();
        int ii = 0;
        builder.append(this.getClass()).append(" [");
        for (Field f : cls.getDeclaredFields()) {
            try {
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
            }
            catch (IllegalArgumentException e) {
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
            catch (IllegalAccessException e) {
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
