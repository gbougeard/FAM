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
@Table(name = FamSeasonCompetition.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamSeasonCompetition.findAll", query = "SELECT f FROM FamSeasonCompetition f"),
    @NamedQuery(name = "FamSeasonCompetition.findByIdSeasonCompetition", query = "SELECT f FROM FamSeasonCompetition f WHERE f.idSeasonCompetition = :idSeasonCompetition")
})
public class FamSeasonCompetition extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_season_competition";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idSeasonCompetition";
    /**
     * 
     */
    public static final String COL_ID = "id_season_competition";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idSeasonCompetition;

    
    @Override
    public Long getId(){
        return this.getIdSeasonCompetition();
    }
    
    /**
     * 
     */
    public static final String COL_ID_COMPETITION = FamTypCompetition.COL_ID;
    /**
     *
     */
    public static final String PROP_COMPETITION = "famCompetition";
    @ManyToOne
    @JoinColumn(name = COL_ID_COMPETITION, referencedColumnName = FamTypCompetition.COL_ID)
    @NotNull
    private FamTypCompetition famTypCompetition;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON = FamSeason.COL_ID;
    /**
     *
     */
    public static final String PROP_SEASON = "famSeason";
    @ManyToOne
    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = FamSeason.COL_ID)
    @NotNull
    private FamSeason famSeason;
    //
    @Transient
    private String displayName;
    //
    @ManyToMany
    @JoinTable(name = FamCompetitionTeam.TABLE_NAME,
    joinColumns = {
        @JoinColumn(name = COL_ID)},
    inverseJoinColumns = {
        @JoinColumn(name = FamTeam.COL_ID)})
    private List<FamTeam> famTeamList;
    //
    /**
     *
     */
    public static final String COL_ID_SCALE = "id_scale";
    /**
     *
     */
    public static final String PROP_SCALE = "famScale";
    @ManyToOne
    @JoinColumn(name = COL_ID_SCALE, referencedColumnName = FamScale.COL_ID)
    private FamScale famScale;
    //
//    @ManyToMany
//    @JoinTable(name = "fam_competition_finescale",
//    joinColumns = {
//        @JoinColumn(name = COL_ID)},
//    inverseJoinColumns = {
//        @JoinColumn(name = FamTypCardFinescale.COL_ID)})
//    private List<FamTypCardFinescale> famTypCardFinescaleList;

    /**
     *
     */
    public FamSeasonCompetition() {
    }

    /**
     *
     * @param idSeasonCompetition
     */
    public FamSeasonCompetition(Long idSeasonCompetition) {
        this.idSeasonCompetition = idSeasonCompetition;
    }

    /**
     *
     * @return
     */
    public Long getIdSeasonCompetition() {
        return idSeasonCompetition;
    }

    /**
     *
     * @param idSeasonCompetition
     */
    public void setIdSeasonCompetition(Long idSeasonCompetition) {
        this.idSeasonCompetition = idSeasonCompetition;
    }

    /**
     *
     * @return
     */
    public FamTypCompetition getFamTypCompetition() {
        return famTypCompetition;
    }

    /**
     * 
     * @param famCompetition
     */
    public void setFamTypCompetition(FamTypCompetition famCompetition) {
        this.famTypCompetition = famCompetition;
    }

    /**
     * 
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    public List<FamTeam> getFamTeamList() {
        return famTeamList;
    }

    /**
     * 
     * @param famTeamList
     */
    public void setFamTeamList(List<FamTeam> famTeamList) {
        this.famTeamList = famTeamList;
    }

    /**
     * 
     * @return
     */
    public FamScale getFamScale() {
        return famScale;
    }

    /**
     * 
     * @param famScale
     */
    public void setFamScale(FamScale famScale) {
        this.famScale = famScale;
    }

    /**
     * 
     * @return
     */
//    public List<FamTypCardFinescale> getFamTypCardFinescaleList() {
//        return famTypCardFinescaleList;
//    }
    /**
     * 
     * @param famTypCardFinescaleList
     */
//    public void setFamTypCardFinescaleList(List<FamTypCardFinescale> famTypCardFinescaleList) {
//        this.famTypCardFinescaleList = famTypCardFinescaleList;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idSeasonCompetition != null ? idSeasonCompetition.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamSeasonCompetition)) {
            return false;
        }
        FamSeasonCompetition other = (FamSeasonCompetition) object;
        if (( this.idSeasonCompetition == null && other.idSeasonCompetition != null ) || ( this.idSeasonCompetition != null && !this.idSeasonCompetition.equals(other.idSeasonCompetition) )) {
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

    @PostLoad
    @PostPersist
    @PostUpdate
    void fillTransients() {
        StringBuilder builder = new StringBuilder();
        if (famSeason != null) {
            builder.append(famSeason.getLibSeason());
        }
        if (famTypCompetition != null) {
            builder.append(" ").append(famTypCompetition.getLibTypCompetition());
        }
        displayName = builder.toString();

    }
}
