package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Data
@Entity
@Table(name = FamCompetitionTeam.TABLE_NAME)
@NamedQueries({
               @NamedQuery(name = "FamCompetitionTeam.findAll",
                           query = "SELECT f FROM FamCompetitionTeam f"),
               @NamedQuery(name = "FamCompetitionTeam.findByIdCompetitionTeam",
                           query = "SELECT f FROM FamCompetitionTeam f WHERE f.idCompetitionTeam = :idCompetitionTeam")
})
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

}
