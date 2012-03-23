package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.fam.common.log.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamPlayerSeason.TABLE_NAME)
//@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = FamPlayerSeason.FIND_ALL,
                query = "SELECT f FROM FamPlayerSeason f"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_SEASON,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_PLAYER,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famPlayer = :famPlayer"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_CLUB,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_PLAYER_AND_SEASON,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason AND f.famPlayer = :famPlayer"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_TEAM_AND_SEASON,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason AND f.famTeam = :famTeam"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_PLAYER_AND_CLUB,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub AND f.famPlayer = :famPlayer"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_CLUB_AND_SEASON,
                query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub AND f.famSeason = :famSeason"),
        @NamedQuery(name = FamPlayerSeason.FIND_BY_NOT_SEASON,
                query = "SELECT f FROM FamPlayerSeason f WHERE NOT f.famSeason = :famSeason")
})
@IdClass(FamPlayerSeasonPK.class)
@Getter
@Setter
public class FamPlayerSeason implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_player_season";
    //
    public static final String FIND_ALL = "FamPlayerSeason.findAll";
    public static final String FIND_BY_SEASON = "FamPlayerSeason.findBySeason";
    public static final String FIND_BY_PLAYER = "FamPlayerSeason.findByPlayer";
    public static final String FIND_BY_CLUB = "FamPlayerSeason.findByClub";
    public static final String FIND_BY_PLAYER_AND_SEASON = "FamPlayerSeason.findByPlayerAndSeason";
    public static final String FIND_BY_TEAM_AND_SEASON = "FamPlayerSeason.findByTeamAndSeason";
    public static final String FIND_BY_PLAYER_AND_CLUB = "FamPlayerSeason.findByPlayerAndClub";
    public static final String FIND_BY_CLUB_AND_SEASON = "FamPlayerSeason.findByClubAndSeason";
    public static final String FIND_BY_NOT_SEASON = "FamPlayerSeason.findByNotSeason";
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
    @NotNull
    private FamPlayer famPlayer;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON = "id_season";
    /**
     *
     */
    public static final String PROP_SEASON = "famSeason";
    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = FamSeason.COL_ID)
    @NotNull
    private FamSeason famSeason;
    //
    /**
     *
     */
    public static final String COL_ID_CLUB = FamClub.COL_ID;
    /**
     *
     */
    public static final String PROP_CLUB = "famClub";
    //    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID)
//    @NotNull
//    @OneToOne
    private FamClub famClub;
    //
    /**
     *
     */
    public static final String COL_ID_TEAM = FamTeam.COL_ID;
    /**
     *
     */
    public static final String PROP_TEAM = "famTeam";
    @ManyToOne
    @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamTeam.COL_ID)
//    @OneToOne
    private FamTeam famTeam;
    //
    @Embedded
    private FamPlayerProfile famPlayerProfile;
    @Embedded
    private FamPlayerStat famPlayerStat;

    /**
     *
     */
    public FamPlayerSeason() {
        famPlayerProfile = new FamPlayerProfile();
        famPlayerStat = new FamPlayerStat();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Class cls = this.getClass();
        int ii = 0;
        builder.append(this.getClass()).append(" [");
        for (Field f : cls.getDeclaredFields()) {
            String str = "null";
            if (f.getName().equals(PROP_CLUB)) {

                if (this.getFamClub() != null) {
                    str = this.getFamClub().getLibClub();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);

            } else {
                if (f.getName().equals(PROP_TEAM)) {

                    if (this.getFamTeam() != null) {
                        str = this.getFamTeam().getLibTeam();
                    }
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
                } else {
                    if (f.getName().equals(PROP_SEASON)) {

                        if (this.getFamSeason() != null) {
                            str = this.getFamSeason().getLibSeason();
                        }
                        builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
                    } else {
                        if (f.getName().equals(PROP_PLAYER)) {

                            if (this.getFamPlayer() != null) {
                                str = this.getFamPlayer().getDisplayName();
                            }
                            builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
                        } else {
                            try {
                                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block

                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block

                            }
                        }
                    }
                }
            }
        }
        builder.append("\n]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamPlayerSeason that = (FamPlayerSeason) o;

        return !(famPlayer != null ? !famPlayer.equals(that.famPlayer) : that.famPlayer != null) && !(famSeason != null ? !famSeason.equals(that.famSeason) : that.famSeason != null);

    }

    @Override
    public int hashCode() {
        int result = famPlayer != null ? famPlayer.hashCode() : 0;
        result = 31 * result + (famSeason != null ? famSeason.hashCode() : 0);
        return result;
    }


}
