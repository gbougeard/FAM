package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;

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
@Table(name = FamPlayerSeason.TABLE_NAME)
//@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "FamPlayerSeason.findAll", query = "SELECT f FROM FamPlayerSeason f"),
    @NamedQuery(name = "FamPlayerSeason.findBySeason", query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason"),
    @NamedQuery(name = "FamPlayerSeason.findByPlayer", query = "SELECT f FROM FamPlayerSeason f WHERE f.famPlayer = :famPlayer"),
    @NamedQuery(name = "FamPlayerSeason.findByClub", query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub"),
    @NamedQuery(name = "FamPlayerSeason.findByPlayerAndSeason", query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason AND f.famPlayer = :famPlayer"),
    @NamedQuery(name = "FamPlayerSeason.findByTeamAndSeason", query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason = :famSeason AND f.famTeam = :famTeam"),
    @NamedQuery(name = "FamPlayerSeason.findByPlayerAndClub", query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub AND f.famPlayer = :famPlayer"),
    @NamedQuery(name = "FamPlayerSeason.findByClubAndSeason", query = "SELECT f FROM FamPlayerSeason f WHERE f.famClub = :famClub AND f.famSeason = :famSeason"),
    @NamedQuery(name = "FamPlayerSeason.findByNotSeason", query = "SELECT f FROM FamPlayerSeason f WHERE f.famSeason != :famSeason")
})
@IdClass(FamPlayerSeasonPK.class)
public class FamPlayerSeason implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_player_season";
    //
    /**
     * 
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     * 
     */
    public static final String PROP_PLAYER = "famPlayer";
    @Id
    @ManyToOne()
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

    /**
     *
     * @return
     */
    public FamPlayer getFamPlayer() {
        return famPlayer;
    }

    /**
     * 
     * @param famPlayer
     */
    public void setFamPlayer(FamPlayer famPlayer) {
        this.famPlayer = famPlayer;
    }

    /**
     * 
     * @return
     */
    public FamPlayerProfile getFamPlayerProfile() {
        return famPlayerProfile;
    }

    /**
     * 
     * @param famPlayerProfile
     */
    public void setFamPlayerProfile(FamPlayerProfile famPlayerProfile) {
        this.famPlayerProfile = famPlayerProfile;
    }

    /**
     * 
     * @return
     */
    public FamSeason getFamSeason() {
        return famSeason;
    }

    /**
     * 
     * @param famSeason
     */
    public void setFamSeason(FamSeason famSeason) {
        this.famSeason = famSeason;
    }

    /**
     * 
     * @return
     */
    public FamClub getFamClub() {
        return famClub;
    }

    /**
     * 
     * @param famClub
     */
    public void setFamClub(FamClub famClub) {
        this.famClub = famClub;
    }

    /**
     * 
     * @return
     */
    public FamTeam getFamTeam() {
        return famTeam;
    }

    /**
     * 
     * @param famTeam
     */
    public void setFamTeam(FamTeam famTeam) {
        this.famTeam = famTeam;
    }

    public FamPlayerStat getFamPlayerStat() {
        return famPlayerStat;
    }

    public void setFamPlayerStat(FamPlayerStat famPlayerStat) {
        this.famPlayerStat = famPlayerStat;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamPlayerSeason other = (FamPlayerSeason) obj;
        if (this.famPlayer != other.famPlayer && ( this.famPlayer == null || !this.famPlayer.equals(other.famPlayer) )) {
            return false;
        }
        if (this.famSeason != other.famSeason && ( this.famSeason == null || !this.famSeason.equals(other.famSeason) )) {
            return false;
        }
        if (this.famClub != other.famClub && ( this.famClub == null || !this.famClub.equals(other.famClub) )) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + ( this.famPlayer != null ? this.famPlayer.hashCode() : 0 );
        hash = 23 * hash + ( this.famSeason != null ? this.famSeason.hashCode() : 0 );
        hash = 23 * hash + ( this.famClub != null ? this.famClub.hashCode() : 0 );
        return hash;
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

            } else if (f.getName().equals(PROP_TEAM)) {

                if (this.getFamTeam() != null) {
                    str = this.getFamTeam().getLibTeam();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
            } else if (f.getName().equals(PROP_SEASON)) {

                if (this.getFamSeason() != null) {
                    str = this.getFamSeason().getLibSeason();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
            } else if (f.getName().equals(PROP_PLAYER)) {

                if (this.getFamPlayer() != null) {
                    str = this.getFamPlayer().getDisplayName();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
            } else {
                try {
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                }
                catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                }
                catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                }
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
