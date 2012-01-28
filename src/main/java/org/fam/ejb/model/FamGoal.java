package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamGoal.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamGoal.findAll", query = "SELECT f FROM FamGoal f"),
    @NamedQuery(name = "FamGoal.findByIdGoal", query = "SELECT f FROM FamGoal f WHERE f.idGoal = :idGoal"),
    @NamedQuery(name = "FamGoal.findByGoalTime", query = "SELECT f FROM FamGoal f WHERE f.goalTime = :goalTime"),
    @NamedQuery(name = "FamGoal.findByCsc", query = "SELECT f FROM FamGoal f WHERE f.csc = :csc"),
    @NamedQuery(name = "FamGoal.findByPenalty", query = "SELECT f FROM FamGoal f WHERE f.penalty = :penalty"),
    @NamedQuery(name = "FamGoal.findByMatchAndTeam", query = "SELECT f FROM FamGoal f WHERE f.famMatchPlayerStriker.famMatchTeam.famMatch = :famMatch AND f.famMatchPlayerStriker.famMatchTeam.famTeam = :famTeam"),
    @NamedQuery(name = "FamGoal.findByDtCreat", query = "SELECT f FROM FamGoal f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamGoal.findByDtModif", query = "SELECT f FROM FamGoal f WHERE f.dtModif = :dtModif")})
public class FamGoal extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_goal";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idGoal";
    /**
     * 
     */
    public static final String COL_ID = "id_goal";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idGoal;

    @Override
    public Long getId() {
        return this.getIdGoal();
    }
    /**
     * 
     */
    public static final String PROP_TIME = "goalTime";
    public static final String COL_TIME = "goal_time";
    @Column(name = COL_TIME)
    private String goalTime;
    //
    /**
     * 
     */
    public static final String PROP_CSC = "csc";
    @Column(name = PROP_CSC)
    private Boolean csc;
    //
    /**
     * 
     */
    public static final String PROP_PENALTY = "penalty";
    @Column(name = PROP_PENALTY)
    private Boolean penalty;
    //
    /**
     * 
     */
    public static final String COL_ID_MATCH = "id_match";
    /**
     * 
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     * 
     */
    public static final String COL_ID_STRIKER = "id_striker";
    /**
     * 
     */
    public static final String PROP_STRIKER = "famMatchPlayerStriker";
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatchPlayer.COL_ID_MATCH),
        @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamMatchPlayer.COL_ID_TEAM),
        @JoinColumn(name = COL_ID_STRIKER, referencedColumnName = FamMatchPlayer.COL_ID_PLAYER)})
    private FamMatchPlayer famMatchPlayerStriker;
    //
    /**
     *
     */
    public static final String COL_ID_ASSIST = "id_assist";
    /**
     *
     */
    public static final String PROP_ASSIST = "famMatchPlayerAssist";
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatchPlayer.COL_ID_MATCH, insertable = false, updatable = false),
        @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamMatchPlayer.COL_ID_TEAM, insertable = false, updatable = false),
        @JoinColumn(name = COL_ID_ASSIST, referencedColumnName = FamMatchPlayer.COL_ID_PLAYER)})
    private FamMatchPlayer famMatchPlayerAssist;

    /**
     *
     */
    public FamGoal() {
        this.setFamMatchPlayerAssist(new FamMatchPlayer());
        this.setFamMatchPlayerStriker(new FamMatchPlayer());
    }

    /**
     *
     * @param idGoal
     */
    public FamGoal(Long idGoal) {
        this.idGoal = idGoal;
    }

    /**
     *
     * @return
     */
    public Long getIdGoal() {
        return idGoal;
    }

    /**
     *
     * @param idGoal
     */
    public void setIdGoal(Long idGoal) {
        this.idGoal = idGoal;
    }

    /**
     *
     * @return
     */
    public String getGoalTime() {
        return goalTime;
    }

    /**
     *
     * @param goalTime
     */
    public void setGoalTime(String goalTime) {
        this.goalTime = goalTime;
    }

    /**
     *
     * @return
     */
    public Boolean getCsc() {
        return csc;
    }

    /**
     *
     * @param csc
     */
    public void setCsc(Boolean csc) {
        this.csc = csc;
    }

    /**
     *
     * @return
     */
    public Boolean getPenalty() {
        return penalty;
    }

    /**
     *
     * @param penalty
     */
    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    /**
     *
     * @return
     */
    public FamMatchPlayer getFamMatchPlayerAssist() {
        return famMatchPlayerAssist;
    }

    /**
     *
     * @param famMatchPlayerAssist
     */
    public void setFamMatchPlayerAssist(FamMatchPlayer famMatchPlayerAssist) {
        this.famMatchPlayerAssist = famMatchPlayerAssist;
    }

    /**
     *
     * @return
     */
    public FamMatchPlayer getFamMatchPlayerStriker() {
        return famMatchPlayerStriker;
    }

    /**
     *
     * @param famMatchPlayerStriker
     */
    public void setFamMatchPlayerStriker(FamMatchPlayer famMatchPlayerStriker) {
        this.famMatchPlayerStriker = famMatchPlayerStriker;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idGoal != null ? idGoal.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamGoal)) {
            return false;
        }
        FamGoal other = (FamGoal) object;
        if (( this.idGoal == null && other.idGoal != null ) || ( this.idGoal != null && !this.idGoal.equals(other.idGoal) )) {
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
