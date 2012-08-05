package org.fam.ejb.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 05/08/12
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */
@Data
@javax.persistence.Table(name = "v_rankings", schema = "", catalog = "fam")
@Entity
@NamedQueries({
               @NamedQuery(name = VRankings.FIND,
                           query = "SELECT f FROM VRankings f WHERE f.famSeasonCompetition = :famSeasonCompetition ORDER BY f.points DESC")
})
@IdClass(FamRankingPK.class)
public class VRankings {

    public static final String FIND = "VRankings.Find";

    @javax.persistence.Column(name = "points", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long points;


    @javax.persistence.Column(name = "lib_team", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    private String libTeam;


    @javax.persistence.Column(name = "victory", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long victory;


    @javax.persistence.Column(name = "defeat", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long defeat;


    @javax.persistence.Column(name = "draw", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long draw;


    @javax.persistence.Column(name = "goal_scored", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long goalScored;


    @javax.persistence.Column(name = "goal_shipped", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    @Basic
    private Long goalShipped;

    @javax.persistence.Column(name = "id_club", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    private Long idClub;

    @javax.persistence.Column(name = "played", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    private Long played;

//    @javax.persistence.Column(name = "id_team", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
//    @Basic
//    private Long idTeam;
//
//    @javax.persistence.Column(name = "id_season_competition", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
//    @Basic
//    private Long idSeasonCompetition;

    @Id
    @ManyToOne
    @JoinColumn(name = FamTeam.COL_ID, referencedColumnName = FamTeam.COL_ID)
    private FamTeam famTeam;

    @Id
    @ManyToOne
    @JoinColumn(name = FamSeasonCompetition.COL_ID, referencedColumnName = FamSeasonCompetition.COL_ID)
    private FamSeasonCompetition famSeasonCompetition;


}
