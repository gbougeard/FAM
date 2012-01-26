package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Entity
@Table(name = FamMatchTeam.TABLE_NAME)
@IdClass(FamMatchTeamPK.class)
public class FamMatchTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_match_team";
    //
    /**
     * 
     */
    public static final String COL_ID_MATCH = "id_match";
    /**
     * 
     */
    public static final String PROP_MATCH = "famMatch";
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatch.COL_ID)
    @NotNull
    private FamMatch famMatch;
    //
    /**
     * 
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     * 
     */
    public static final String PROP_TEAM = "famTeam";
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamTeam.COL_ID)
    @NotNull
    private FamTeam famTeam;
    //
    /**
     * 
     */
    public static final String PROP_HOME = "home";
    @Column(name = PROP_HOME)
    private Boolean home;
    //
    /**
     * 
     */
    public static final String PROP_POINTS = "points";
    @Basic(optional = false)
    @Column(name = PROP_POINTS)
    private Integer points;
    //
    /**
     * 
     */
    public static final String PROP_VICTORY = "victory";
    @Basic(optional = false)
    @Column(name = PROP_VICTORY)
    private Integer victory;
    //
    /**
     * 
     */
    public static final String PROP_DRAW = "draw";
    @Basic(optional = false)
    @Column(name = PROP_DRAW)
    private Integer draw;
    //
    /**
     * 
     */
    public static final String PROP_DEFEAT = "defeat";
    @Basic(optional = false)
    @Column(name = PROP_DEFEAT)
    private Integer defeat;
    //
    /**
     * 
     */
    public static final String PROP_GOAL_SCORED = "goal_scored";
    @Column(name = PROP_GOAL_SCORED)
    private Integer goalScored;
    //
    /**
     * 
     */
    public static final String PROP_GOAL_SHIPPED = "goal_shipped";
    @Column(name = PROP_GOAL_SHIPPED)
    private Integer goalShipped;
    //
    @OneToMany(mappedBy = FamMatchPlayer.PROP_MATCH_TEAM)
    @OrderBy(FamMatchPlayer.PROP_NUM)
    private List<FamMatchPlayer> famMatchPlayerList;
    //
    /**
     *
     */
    public static final String COL_ID_FORMATION = "id_formation";
    /**
     *
     */
    public static final String PROP_FORMATION = "famFormation";
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_FORMATION, referencedColumnName = FamFormation.COL_ID)
    private FamFormation famFormation;
    //
    /**
     *
     */
    public static final String PROP_DRAFT = "draft";
    @Column(name = PROP_DRAFT)
    private Boolean draft;

    /**
     *
     */
    public FamMatchTeam() {
    }

    /**
     *
     * @return
     */
    public FamMatch getFamMatch() {
        return famMatch;
    }

    /**
     *
     * @param famMatch
     */
    public void setFamMatch(FamMatch famMatch) {
        this.famMatch = famMatch;
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
        if (famTeam != null) {
            this.famTeam = famTeam;
        }
    }

    /**
     *
     * @return
     */
    public Integer getDefeat() {
        return defeat;
    }

    /**
     *
     * @param defeat
     */
    public void setDefeat(Integer defeat) {
        this.defeat = defeat;
    }

    /**
     *
     * @return
     */
    public Integer getDraw() {
        return draw;
    }

    /**
     *
     * @param draw
     */
    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    /**
     *
     * @return
     */
    public List<FamMatchPlayer> getFamMatchPlayerList() {
        return famMatchPlayerList;
    }

    /**
     *
     * @param famMatchPlayerList
     */
    public void setFamMatchPlayerList(List<FamMatchPlayer> famMatchPlayerList) {
        this.famMatchPlayerList = famMatchPlayerList;
    }

    /**
     * 
     * @return
     */
    public Integer getGoalScored() {
        return goalScored;
    }

    /**
     * 
     * @param goalScored
     */
    public void setGoalScored(Integer goalScored) {
        this.goalScored = goalScored;
    }

    /**
     * 
     * @return
     */
    public Integer getGoalShipped() {
        return goalShipped;
    }

    /**
     * 
     * @param goalShipped
     */
    public void setGoalShipped(Integer goalShipped) {
        this.goalShipped = goalShipped;
    }

    /**
     * 
     * @return
     */
    public Boolean getHome() {
        return home;
    }

    /**
     * 
     * @param home
     */
    public void setHome(Boolean home) {
        this.home = home;
    }

    /**
     * 
     * @return
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 
     * @return
     */
    public Integer getVictory() {
        return victory;
    }

    /**
     * 
     * @param victory
     */
    public void setVictory(Integer victory) {
        this.victory = victory;
    }

    /**
     * 
     * @return
     */
    public Boolean getDraft() {
        return draft;
    }

    /**
     * 
     * @param draft
     */
    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    /**
     * 
     * @return
     */
    public FamFormation getFamFormation() {
        return famFormation;
    }

    /**
     * 
     * @param famFormation
     */
    public void setFamFormation(FamFormation famFormation) {
        this.famFormation = famFormation;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamMatchTeam other = (FamMatchTeam) obj;
        if (this.famMatch != other.famMatch && (this.famMatch == null || !this.famMatch.equals(other.famMatch))) {
            return false;
        }
        if (this.famTeam != other.famTeam && (this.famTeam == null || !this.famTeam.equals(other.famTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.famMatch != null ? this.famMatch.hashCode() : 0);
        hash = 97 * hash + (this.famTeam != null ? this.famTeam.hashCode() : 0);
        return hash;
    }
//    @PrePersist
//    private void addEvent() {
////        LogUtil.log("addEvent", Level.WARNING, null);
//
//        // Event
//        FamEvent event = null;
//        if (famMatch.getFamEvent() == null) {
//            event = new FamEvent();
//        } else {
//            event = famMatch.getFamEvent();
//        }
//
//        event.setFamSeason(getFamMatch().getFamSeasonCompetition().getFamSeason());
//        event.setDuration(90);
//
//        FamTeam team = getFamTeam();
//        if (getHome() == true) {
//            event.setFamPlace(team.getFamPlace());
//        }
//
//        if (event.getFamTeamList() == null) {
//            event.setFamTeamList(new ArrayList<FamTeam>());
//        }
//        event.getFamTeamList().add(team);
//
//        String lib = team.getLibTeam();
//        if (event.getFamTeamList().size() == 2) {
//            if (getHome() == true) {
//                lib = team.getLibTeam() + " - " + event.getLibEvent();
//            } else {
//                lib = event.getLibEvent() + " - " + team.getLibTeam();
//            }
//        }
//        event.setLibEvent(lib);
//
//
//        famMatch.setFamEvent(event);
//    }
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//
//        Class cls = this.getClass();
//
//        int ii = 0;
//        builder.append(this.getClass()).append(" [");
//
//        for (Field f : cls.getDeclaredFields()) {
//            try {
//                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
//
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                 LogUtil.log("Erreur!", Level.SEVERE, e);
//
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                 LogUtil.log("Erreur!", Level.SEVERE, e);
//            }
//        }
//        builder.append("\n]");
//        return builder.toString();
//    }
}
