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
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamUserSeason.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamUserSeason.findAll", query = "SELECT f FROM FamUserSeason f"),
    @NamedQuery(name = "FamUserSeason.findByUser", query = "SELECT f FROM FamUserSeason f WHERE f.famUser = :famUser"),
    @NamedQuery(name = "FamUserSeason.findBySeason", query = "SELECT f FROM FamUserSeason f WHERE f.famSeason = :famSeason"),
    @NamedQuery(name = "FamUserSeason.findByClub", query = "SELECT f FROM FamUserSeason f WHERE f.famClub = :famClub")
})
@IdClass(FamUserSeasonPK.class)
public class FamUserSeason implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_user_season";
    //
    /**
     *
     */
    public static final String COL_ID_USER = FamUser.COL_ID;
    /**
     *
     */
    public static final String PROP_USER = "famUser";
    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_USER, referencedColumnName = FamUser.COL_ID)
    @NotNull
    private FamUser famUser;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON = FamSeason.COL_ID;
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
    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID)
    @NotNull
    private FamClub famClub;
    //
    @ManyToMany
    @JoinTable(name = "fam_staff",
    joinColumns = {
        @JoinColumn(name = COL_ID_USER, referencedColumnName = COL_ID_USER),
        @JoinColumn(name = COL_ID_SEASON, referencedColumnName = COL_ID_SEASON),
        @JoinColumn(name = COL_ID_CLUB, referencedColumnName = COL_ID_CLUB)},
    inverseJoinColumns = {
        @JoinColumn(name = FamStaffFunction.COL_ID)})
    private List<FamStaffFunction> famStaffFunctionList;
    //
    /**
     *
     */
    public static final String PROP_IS_ADMIN = "isAdmin";
    @NotNull
    @Column(name = PROP_IS_ADMIN)
    private Boolean isAdmin = false;
    //
    /**
     *
     */
    public static final String PROP_IS_PLAYER = "isPlayer";
    @NotNull
    @Column(name = PROP_IS_PLAYER)
    private Boolean isPlayer = false;
    //
    /**
     *
     */
    public static final String PROP_IS_COACH = "isCoach";
    @NotNull
    @Column(name = PROP_IS_COACH)
    private Boolean isCoach = false;
    //
    /**
     *
     */
    public static final String PROP_IS_REPORTER = "isReporter";
    @NotNull
    @Column(name = PROP_IS_REPORTER)
    private Boolean isReporter = false;
    //
    /**
     *
     */
    public static final String PROP_CONFIRMED = "confirmed";
    @NotNull
    @Column(name = PROP_CONFIRMED)
    private Boolean confirmed = false;

    /**
     *
     */
    public FamUserSeason() {
    }

    /**
     *
     * @return
     */
    public List<FamStaffFunction> getFamStaffFunctionList() {
        return famStaffFunctionList;
    }

    /**
     *
     * @param famStaffFunctionList
     */
    public void setFamStaffFunctionList(List<FamStaffFunction> famStaffFunctionList) {
        this.famStaffFunctionList = famStaffFunctionList;
    }

    /**
     *
     * @return
     */
    public FamUser getFamUser() {
        return famUser;
    }

    /**
     *
     * @param famUser
     */
    public void setFamUser(FamUser famUser) {
        this.famUser = famUser;
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
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param isAdmin
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @return
     */
    public Boolean getIsCoach() {
        return isCoach;
    }

    /**
     *
     * @param isCoach
     */
    public void setIsCoach(Boolean isCoach) {
        this.isCoach = isCoach;
    }

    /**
     *
     * @return
     */
    public Boolean getIsPlayer() {
        return isPlayer;
    }

    /**
     *
     * @param isPlayer
     */
    public void setIsPlayer(Boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    /**
     *
     * @return
     */
    public Boolean getIsReporter() {
        return isReporter;
    }

    /**
     *
     * @param isReporter
     */
    public void setIsReporter(Boolean isReporter) {
        this.isReporter = isReporter;
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
    public Boolean getConfirmed() {
        return confirmed;
    }

    /**
     * 
     * @param confirmed
     */
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamUserSeason other = (FamUserSeason) obj;
        if (this.famUser != other.famUser && ( this.famUser == null || !this.famUser.equals(other.famUser) )) {
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
        int hash = 7;
        hash = 37 * hash + ( this.famUser != null ? this.famUser.hashCode() : 0 );
        hash = 37 * hash + ( this.famSeason != null ? this.famSeason.hashCode() : 0 );
        hash = 37 * hash + ( this.famClub != null ? this.famClub.hashCode() : 0 );
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
        builder.append("\n]");
        return builder.toString();
    }
}
