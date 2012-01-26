package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamTypMatch.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamTypMatch.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamTypMatch.findAll", query = "SELECT f FROM FamTypMatch f"),
    @NamedQuery(name = "FamTypMatch.findByIdTypMatch", query = "SELECT f FROM FamTypMatch f WHERE f.idTypMatch = :idTypMatch"),
    @NamedQuery(name = "FamTypMatch.findByLibTypMatch", query = "SELECT f FROM FamTypMatch f WHERE f.libTypMatch = :libTypMatch"),
    @NamedQuery(name = "FamTypMatch.findByDtCreat", query = "SELECT f FROM FamTypMatch f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamTypMatch.findByDtModif", query = "SELECT f FROM FamTypMatch f WHERE f.dtModif = :dtModif")})
public class FamTypMatch extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_typ_match";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idTypMatch";
    /**
     * 
     */
    public static final String COL_ID = "id_typ_match";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypMatch;
    
    @Override
    public Long getId(){
        return this.getIdTypMatch();
    }
    
    /**
     * 
     */
    public static final String PROP_LIB = "libTypMatch";
    public static final String COL_LIB = "lib_typ_match";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libTypMatch;
    //
    /**
     * 
     */
    public static final String PROP_COD = "codTypMatch";
    public static final String COL_COD = "cod_typ_match";
    @Basic(optional = false)
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypMatch;
    //
    /**
     * 
     */
    public static final String COL_NB_PLAYER = "nb_player";
    public static final String PROP_NB_PLAYER = "nbPlayer";
    @Basic(optional = false)
    @Column(name = COL_NB_PLAYER)
    @NotNull
    private Integer nbPlayer;
    //
    /**
     * 
     */
    public static final String COL_NB_SUBSTITUTE = "nb_substitute";
    public static final String PROP_NB_SUBSTITUTE = "nbSubstitute";
    @Basic(optional = false)
    @Column(name = COL_NB_SUBSTITUTE)
    @NotNull
    private Integer nbSubstitute;
    //
    /**
     * 
     */
    public static final String COL_PERIOD_DURATION = "period_duration";
    public static final String PROP_PERIOD_DURATION = "periodDuration";
    @Basic(optional = false)
    @Column(name = COL_PERIOD_DURATION)
    @NotNull
    private Integer periodDuration;
    //
    /**
     * 
     */
    public static final String PROP_NB_SUBSTITUTION = "nbSubstitution";
    public static final String COL_NB_SUBSTITUTION = "nb_substitution";
    @Basic(optional = false)
    @Column(name = COL_NB_SUBSTITUTION)
    @NotNull
    private Integer nbSubstitution;
    //
    /**
     * 
     */
    public static final String PROP_INFINITE_SUBS = "infiniteSubs";
    public static final String COL_INFINITE_SUBS = "infinite_subs";
    @Basic(optional = false)
    @Column(name = COL_INFINITE_SUBS)
    private Boolean infiniteSubs;
    //
    /**
     * 
     */
    public static final String PROP_EXTRA_TIME = "extraTime";
    public static final String COL_EXTRA_TIME = "extra_time";
    @Basic(optional = false)
    @Column(name = COL_EXTRA_TIME)
    private Boolean extraTime;
    //
    /**
     * 
     */
    public static final String PROP_EXTRA_TIME_DURATION = "extraTimeDuration";
    public static final String COL_EXTRA_TIME_DURATION = "extra_time_duration";
    @Basic(optional = false)
    @Column(name = COL_EXTRA_TIME_DURATION)
    @NotNull
    private Integer extraTimeDuration;
    //
    /**
     * 
     */
    public static final String PROP_PENALTIES = "penalties";
    @Basic(optional = false)
    @Column(name = PROP_PENALTIES)
    private Boolean penalties;
    //
    /**
     * 
     */
    public static final String COL_NB_PENALTIES = "nb_penalties";
    public static final String PROP_NB_PENALTIES = "nbPenalties";
    @Basic(optional = false)
    @Column(name = COL_NB_PENALTIES)
    @Max(value = 5)
    @NotNull
    private Integer nbPenalties;

    //
    /**
     * 
     */
    public FamTypMatch() {
        // valeurs par defaut
        this.extraTime = false;
        this.extraTimeDuration = 15;
        this.infiniteSubs = false;
        this.nbPenalties = 5;
        this.nbPlayer = 11;
        this.nbSubstitute = 5;
        this.nbSubstitution = 3;
        this.penalties = false;
        this.periodDuration = 45;
    }

    /**
     * 
     * @param idTypMatch
     */
    public FamTypMatch(Long idTypMatch) {
        this.idTypMatch = idTypMatch;
    }

    /**
     * 
     * @return
     */
    public Long getIdTypMatch() {
        return idTypMatch;
    }

    /**
     * 
     * @param idTypMatch
     */
    public void setIdTypMatch(Long idTypMatch) {
        this.idTypMatch = idTypMatch;
    }

    /**
     * 
     * @return
     */
    public String getLibTypMatch() {
        return libTypMatch;
    }

    /**
     * 
     * @param libTypMatch
     */
    public void setLibTypMatch(String libTypMatch) {
        this.libTypMatch = libTypMatch;
    }

    /**
     * 
     * @return
     */
    public String getCodTypMatch() {
        return codTypMatch;
    }

    /**
     * 
     * @param codTypMatch
     */
    public void setCodTypMatch(String codTypMatch) {
        this.codTypMatch = codTypMatch;
    }

    /**
     * 
     * @return
     */
    public Boolean getInfiniteSubs() {
        return infiniteSubs;
    }

    /**
     * 
     * @param infiniteSubs
     */
    public void setInfiniteSubs(Boolean infiniteSubs) {
        this.infiniteSubs = infiniteSubs;
    }

    /**
     * 
     * @return
     */
    public Integer getNbPlayer() {
        return nbPlayer;
    }

    /**
     * 
     * @param nbPlayer
     */
    public void setNbPlayer(Integer nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    /**
     * 
     * @return
     */
    public Integer getNbSubstitute() {
        return nbSubstitute;
    }

    /**
     * 
     * @param nbSubstitute
     */
    public void setNbSubstitute(Integer nbSubstitute) {
        this.nbSubstitute = nbSubstitute;
    }

    /**
     * 
     * @return
     */
    public Integer getPeriodDuration() {
        return periodDuration;
    }

    /**
     * 
     * @param periodDuration
     */
    public void setPeriodDuration(Integer periodDuration) {
        this.periodDuration = periodDuration;
    }

    /**
     * 
     * @return
     */
    public Boolean getExtraTime() {
        return extraTime;
    }

    /**
     * 
     * @param extraTime
     */
    public void setExtraTime(Boolean extraTime) {
        this.extraTime = extraTime;
    }

    /**
     * 
     * @return
     */
    public Integer getExtraTimeDuration() {
        return extraTimeDuration;
    }

    /**
     * 
     * @param extraTimeDuration
     */
    public void setExtraTimeDuration(Integer extraTimeDuration) {
        this.extraTimeDuration = extraTimeDuration;
    }

    /**
     * 
     * @return
     */
    public Integer getNbPenalties() {
        return nbPenalties;
    }

    /**
     * 
     * @param nbPenalties
     */
    public void setNbPenalties(Integer nbPenalties) {
        this.nbPenalties = nbPenalties;
    }

    /**
     * 
     * @return
     */
    public Boolean getPenalties() {
        return penalties;
    }

    /**
     * 
     * @param penalties
     */
    public void setPenalties(Boolean penalties) {
        this.penalties = penalties;
    }

    /**
     * 
     * @return
     */
    public Integer getNbSubstitution() {
        return nbSubstitution;
    }

    /**
     * 
     * @param nbSubstitution
     */
    public void setNbSubstitution(Integer nbSubstitution) {
        this.nbSubstitution = nbSubstitution;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTypMatch != null ? idTypMatch.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamTypMatch)) {
            return false;
        }
        FamTypMatch other = (FamTypMatch) object;
        if (( this.idTypMatch == null && other.idTypMatch != null ) || ( this.idTypMatch != null && !this.idTypMatch.equals(other.idTypMatch) )) {
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
