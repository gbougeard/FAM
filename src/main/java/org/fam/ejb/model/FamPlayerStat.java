package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author gbougear
 */
@Embeddable
@Access(AccessType.FIELD)
@Data
@EqualsAndHashCode(callSuper=false)
public class FamPlayerStat implements Serializable {

    private static final long serialVersionUID = 1L;
    //
    /**
     * 
     */
    public static final String PROP_NB_MATCH = "nbMatch";
    public static final String COL_NB_MATCH = "nb_match";
    @Basic(optional = false)
    @Column(name = COL_NB_MATCH)
    @Max(value = 100)
    @Min(value = 0)
    private Integer nbMatch;
    /**
     * 
     */
    public static final String PROP_NB_WORKOUT = "nbWorkout";
    public static final String COL_NB_WORKOUT = "nb_workout";
    @Basic(optional = false)
    @Column(name = COL_NB_WORKOUT)
    @Max(value = 100)
    @Min(value = 0)
    private Integer nbWorkout;
    /**
     * 
     */
    public static final String PROP_NB_GOAL = "nbGoal";
    public static final String COL_NB_GOAL = "nb_goal";
    @Basic(optional = false)
    @Column(name = COL_NB_GOAL)
    @Min(value = 0)
    private Integer nbGoal;
    /**
     * 
     */
    public static final String PROP_NB_ASSIST = "nbAssist";
    public static final String COL_NB_ASSIST = "nb_assist";
    @Basic(optional = false)
    @Column(name = COL_NB_ASSIST)
    @Min(value = 0)
    private Integer nbAssist;
    /**
     * 
     */
    public static final String PROP_NB_SUBSTITUTE = "nbSubstitute";
    public static final String COL_NB_SUBSTITUTE = "nb_substitute";
    @Basic(optional = false)
    @Column(name = COL_NB_SUBSTITUTE)
    @Min(value = 0)
    private Integer nbSubstitute;
    /**
     * 
     */
    public static final String PROP_TIME_PLAYED = "timePlayed";
    public static final String COL_TIME_PLAYED = "time_played";
    @Basic(optional = false)
    @Column(name = COL_TIME_PLAYED)
    @Min(value = 0)
    private Integer timePlayed;
    /**
     * 
     */
    public static final String PROP_AVG_NOTE = "avgNote";
    public static final String COL_AVG_NOTE = "avg_note";
    @Basic(optional = false)
    @Column(name = COL_AVG_NOTE)
    @Max(value = 10)
    @Min(value = 0)
    @Digits(integer = 1, fraction = 2)
    private BigDecimal avgNote;
    /**
     * 
     */
    public static final String PROP_AVG_GOAL_PER_MATCH = "avgGoalPerMatch";
    public static final String COL_AVG_GOAL_PER_MATCH = "avg_goal_per_match";
    @Basic(optional = false)
    @Column(name = COL_AVG_GOAL_PER_MATCH)
    @Min(value = 0)
    @Digits(integer = 1, fraction = 2)
    private BigDecimal avgGoalPerMatch;
    /**
     * 
     */
    public static final String PROP_AVG_ASSIST_PER_MATCH = "avgAssistPerMatch";
    public static final String COL_AVG_ASSIST_PER_MATCH = "avg_assist_per_match";
    @Basic(optional = false)
    @Column(name = COL_AVG_ASSIST_PER_MATCH)
    @Min(value = 0)
    @Digits(integer = 1, fraction = 2)
    private BigDecimal avgAssistPerMatch;
    //
    public static final String COL_DT_MODIF_STAT = "dt_modif_stat";
    public static final String PROP_DT_MODIF_STAT = "dtModifStat";
    @Column(name = COL_DT_MODIF_STAT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtModifStat;

    /**
     * 
     */
    public FamPlayerStat() {
        this.reset();
    }

    public void reset() {
        avgAssistPerMatch = new BigDecimal(0);
        avgGoalPerMatch = new BigDecimal(0);
        avgNote = new BigDecimal(0);
        nbAssist = 0;
        nbGoal = 0;
        nbMatch = 0;
        nbSubstitute = 0;
        nbWorkout = 0;
        timePlayed = 0;
    }

//    public BigDecimal getAvgAssistPerMatch() {
//        return avgAssistPerMatch;
//    }
//
//    public void setAvgAssistPerMatch(BigDecimal avgAssistPerMatch) {
//        this.avgAssistPerMatch = avgAssistPerMatch;
//    }
//
//    public BigDecimal getAvgGoalPerMatch() {
//        return avgGoalPerMatch;
//    }
//
//    public void setAvgGoalPerMatch(BigDecimal avgGoalPerMatch) {
//        this.avgGoalPerMatch = avgGoalPerMatch;
//    }
//
//    public BigDecimal getAvgNote() {
//        return avgNote;
//    }
//
//    public void setAvgNote(BigDecimal avgNote) {
//        this.avgNote = avgNote;
//    }
//
//    public Integer getNbAssist() {
//        return nbAssist;
//    }
//
//    public void setNbAssist(Integer nbAssist) {
//        this.nbAssist = nbAssist;
//    }
//
//    public Integer getNbGoal() {
//        return nbGoal;
//    }
//
//    public void setNbGoal(Integer nbGoal) {
//        this.nbGoal = nbGoal;
//    }
//
//    public Integer getNbMatch() {
//        return nbMatch;
//    }
//
//    public void setNbMatch(Integer nbMatch) {
//        this.nbMatch = nbMatch;
//    }
//
//    public Integer getNbSubstitute() {
//        return nbSubstitute;
//    }
//
//    public void setNbSubstitute(Integer nbSubstitute) {
//        this.nbSubstitute = nbSubstitute;
//    }
//
//    public Integer getNbWorkout() {
//        return nbWorkout;
//    }
//
//    public void setNbWorkout(Integer nbWorkout) {
//        this.nbWorkout = nbWorkout;
//    }
//
//    public Integer getTimePlayed() {
//        return timePlayed;
//    }
//
//    public void setTimePlayed(Integer timePlayed) {
//        this.timePlayed = timePlayed;
//    }
//    
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//
//        Class cls = this.getClass();
//        int ii = 0;
//        builder.append(this.getClass()).append(" [");
//        for (Field f : cls.getDeclaredFields()) {
//            try {
//                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
//            }
//            catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                LogUtil.log("Erreur!", Level.SEVERE, e);
//            }
//            catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                LogUtil.log("Erreur!", Level.SEVERE, e);
//            }
//        }
//        builder.append("\n]");
//        return builder.toString();
//    }
}
