package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamSeasonCompetition.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamSeasonCompetition.findAll", query = "SELECT f FROM FamSeasonCompetition f ORDER BY f.famSeason.libSeason DESC"),
        @NamedQuery(name = "FamSeasonCompetition.findByIdSeasonCompetition", query = "SELECT f FROM FamSeasonCompetition f WHERE f.idSeasonCompetition = :idSeasonCompetition")
})
public class FamSeasonCompetition extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_season_competition";
    //
    public static final String COL_ID = "id_season_competition";
    public static final String PROP_ID = "idSeasonCompetition";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idSeasonCompetition;


    @Override
    public Long getId() {
        return this.getIdSeasonCompetition();
    }

    /**
     *
     */
    public static final String COL_ID_COMPETITION = FamTypCompetition.COL_ID;
    public static final String PROP_COMPETITION = "famTypCompetition";
    @ManyToOne
    @JoinColumn(name = COL_ID_COMPETITION, referencedColumnName = FamTypCompetition.COL_ID)
    @NotNull
    private FamTypCompetition famTypCompetition;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON = FamSeason.COL_ID;
    public static final String PROP_SEASON = "famSeason";
    @ManyToOne
    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = FamSeason.COL_ID)
    @NotNull
    private FamSeason famSeason;

    public static final String COL_ID_CATEGORY = "id_category";
    public static final String PROP_CATEGORY = "famCategory";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_CATEGORY, referencedColumnName = FamCategory.COL_ID)
    private FamCategory famCategory;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeasonCompetition != null ? idSeasonCompetition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamSeasonCompetition)) {
            return false;
        }
        FamSeasonCompetition other = (FamSeasonCompetition) object;
        if ((this.idSeasonCompetition == null && other.idSeasonCompetition != null) || (this.idSeasonCompetition != null && !this.idSeasonCompetition.equals(other.idSeasonCompetition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FamSeasonCompetition{" +
                "idSeasonCompetition=" + idSeasonCompetition +
                ", famTypCompetition=" + famTypCompetition +
                ", famSeason=" + famSeason +
                ", famCategory=" + famCategory +
                ", displayName='" + displayName + '\'' +
                ", famTeamList=" + famTeamList +
                ", famScale=" + famScale +
                '}';
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
