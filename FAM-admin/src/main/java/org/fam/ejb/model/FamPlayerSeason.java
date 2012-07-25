package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "fam_player_season_category",
            joinColumns = {
                    @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = COL_ID_PLAYER),
                    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = COL_ID_SEASON)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = FamCategory.COL_ID)
            }
    )
    private List<FamCategory> famCategoryList;

    /**
     *
     */
    public FamPlayerSeason() {
        famPlayerProfile = new FamPlayerProfile();
        famPlayerStat = new FamPlayerStat();
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
