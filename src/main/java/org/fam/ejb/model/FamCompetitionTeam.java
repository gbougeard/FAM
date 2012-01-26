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
@Table(name = FamCompetitionTeam.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamCompetitionTeam.findAll", query = "SELECT f FROM FamCompetitionTeam f"),
    @NamedQuery(name = "FamCompetitionTeam.findByIdCompetitionTeam", query = "SELECT f FROM FamCompetitionTeam f WHERE f.idCompetitionTeam = :idCompetitionTeam")})
public class FamCompetitionTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_competition_team";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idCompetitionTeam";
    /**
     * 
     */
    public static final String COL_ID = "id_competition_team";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idCompetitionTeam;
    //
    /**
     * 
     */
    public static final String COL_ID_SEASON = "id_season_competition";
    /**
     * 
     */
    public static final String PROP_SEASON = "famSeasonCompetition";
    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = FamSeasonCompetition.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamSeasonCompetition famSeasonCompetition;
    //
    /**
     * 
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     * 
     */
    public static final String PROP_TEAM = "famTeam";
    @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamTeam.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamTeam famTeam;

    /**
     * 
     */
    public FamCompetitionTeam() {
    }

    /**
     * 
     * @param idCompetitionTeam
     */
    public FamCompetitionTeam(Long idCompetitionTeam) {
        this.idCompetitionTeam = idCompetitionTeam;
    }

    /**
     * 
     * @return
     */
    public Long getIdCompetitionTeam() {
        return idCompetitionTeam;
    }

    /**
     * 
     * @param idCompetitionTeam
     */
    public void setIdCompetitionTeam(Long idCompetitionTeam) {
        this.idCompetitionTeam = idCompetitionTeam;
    }

    /**
     * 
     * @return
     */
    public FamSeasonCompetition getFamSeasonCompetition() {
        return famSeasonCompetition;
    }

    /**
     * 
     * @param famSeasonCompetition
     */
    public void setFamSeasonCompetition(FamSeasonCompetition famSeasonCompetition) {
        this.famSeasonCompetition = famSeasonCompetition;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCompetitionTeam != null ? idCompetitionTeam.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamCompetitionTeam)) {
            return false;
        }
        FamCompetitionTeam other = (FamCompetitionTeam) object;
        if (( this.idCompetitionTeam == null && other.idCompetitionTeam != null ) || ( this.idCompetitionTeam != null && !this.idCompetitionTeam.equals(other.idCompetitionTeam) )) {
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
