package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamCard.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamCard.FIND_ALL,
                query = "SELECT f FROM FamCard f"),
        @NamedQuery(name = FamCard.FIND_BY_ID_CARD,
                query = "SELECT f FROM FamCard f WHERE f.idCard = :idCard"),
        @NamedQuery(name = FamCard.FIND_BY_CARD_TIME,
                query = "SELECT f FROM FamCard f WHERE f.cardTime = :cardTime"),
        @NamedQuery(name = FamCard.FIND_BY_MATCH_AND_TEAM,
                query = "SELECT f FROM FamCard f WHERE f.famMatchPlayer.famMatchTeam.famMatch = :famMatch AND f.famMatchPlayer.famMatchTeam.famTeam = :famTeam"),
        @NamedQuery(name = FamCard.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamCard f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamCard.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamCard f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamCard extends FamEntity implements Serializable {

    private static final long serialVersionUID = -4902339674970875475L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_card";
    //
    public static final String FIND_ALL = "FamCard.findAll";
    public static final String FIND_BY_ID_CARD = "FamCard.findByIdCard";
    public static final String FIND_BY_CARD_TIME = "FamCard.findByCardTime";
    public static final String FIND_BY_MATCH_AND_TEAM = "FamCard.findByMatchAndTeam";
    public static final String FIND_BY_DT_CREAT = "FamCard.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamCard.findByDtModif";
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
    public Long getId() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FamCard famCard = (FamCard) o;

        return idCard.equals(famCard.idCard);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCard.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamCard");
        sb.append("{idCard=").append(idCard);
        sb.append(", cardTime='").append(cardTime).append('\'');
        sb.append(", famTypCard=").append(famTypCard.getLibTypCard());
        sb.append(", famPlayer=").append(famMatchPlayer.getFamPlayer().getDisplayName());
        sb.append(", famMatch=").append(famMatchPlayer.getFamMatchTeam().getFamMatch().getFamEvent().getLibEvent());
        sb.append('}');
        return sb.toString();
    }
}