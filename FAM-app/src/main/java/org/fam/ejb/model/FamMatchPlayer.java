package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.fam.common.log.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamMatchPlayer.TABLE_NAME)
@NamedQueries(value = {
        @NamedQuery(name = "FamMatchPlayer.findAll", query = "SELECT f FROM FamMatchPlayer f"),
        @NamedQuery(name = "FamMatchPlayer.findByPlayer", query = "SELECT f FROM FamMatchPlayer f WHERE f.famPlayer = :famPlayer"),
        @NamedQuery(name = "FamMatchPlayer.findByPlayerAndSeason", query = "SELECT f FROM FamMatchPlayer f WHERE f.famPlayer = :famPlayer "
                + "AND f.famMatchTeam.famMatch.famSeasonCompetition.famSeason = :famSeason")
})
@IdClass(FamMatchPlayerPK.class)
@Getter
@Setter
public class FamMatchPlayer implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_match_player";
    /**
     *
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     *
     */
    public static final String COL_ID_MATCH = "id_match";
    /**
     *
     */
    public static final String PROP_MATCH_TEAM = "famMatchTeam";
    @Id
    @ManyToOne
    private FamMatchTeam famMatchTeam;
    /**
     *
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     *
     */
    public static final String PROP_PLAYER = "famPlayer";
    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = FamPlayer.COL_ID)
    private FamPlayer famPlayer;

    /**
     *
     */
    public static final String PROP_NUM = "num";
    public static final String COL_NUM = "num";
    @Column(name = COL_NUM)
    private Integer num;
    /**
     *
     */
    public static final String PROP_NOTE = "note";
    @Column(name = PROP_NOTE)
    @Digits(integer = 1, fraction = 1)
    private BigDecimal note;
    /**
     *
     */
    public static final String PROP_TIME_PLAYED = "timePlayed";
    public static final String COL_TIME_PLAYED = "time_played";
    @Column(name = COL_TIME_PLAYED)
    private Integer timePlayed;
    /**
     *
     */
    public static final String PROP_CAPTAIN = "captain";
    @Column(name = PROP_CAPTAIN)
    private Boolean captain;
    /**
     *
     */
    public static final String PROP_COMMENTS = "comments";
    @Lob
    @Column(name = PROP_COMMENTS)
    private String comments;
    //
    @OneToMany(cascade = CascadeType.ALL, mappedBy = FamGoal.PROP_STRIKER)
    private List<FamGoal> famGoalList;
    //
    @OneToMany(cascade = CascadeType.ALL, mappedBy = FamCard.PROP_MATCH_PLAYER)
    private List<FamCard> famCardList;

    /**
     *
     */
    public FamMatchPlayer() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamMatchPlayer other = (FamMatchPlayer) obj;
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
        int hash = 7;
        hash = 83 * hash + (this.famMatchTeam != null ? this.famMatchTeam.hashCode() : 0);
        hash = 83 * hash + (this.famPlayer != null ? this.famPlayer.hashCode() : 0);
        return hash;
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
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
