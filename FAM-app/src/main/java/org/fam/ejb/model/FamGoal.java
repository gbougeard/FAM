package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamGoal.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamGoal.FIND_ALL,
                query = "SELECT f FROM FamGoal f"),
        @NamedQuery(name = FamGoal.FIND_BY_ID_GOAL,
                query = "SELECT f FROM FamGoal f WHERE f.idGoal = :idGoal"),
        @NamedQuery(name = FamGoal.FIND_BY_GOAL_TIME,
                query = "SELECT f FROM FamGoal f WHERE f.goalTime = :goalTime"),
        @NamedQuery(name = FamGoal.FIND_BY_CSC,
                query = "SELECT f FROM FamGoal f WHERE f.csc = :csc"),
        @NamedQuery(name = FamGoal.FIND_BY_PENALTY,
                query = "SELECT f FROM FamGoal f WHERE f.penalty = :penalty"),
        @NamedQuery(name = FamGoal.FIND_BY_STRIKER,
                query = "SELECT f FROM FamGoal f WHERE f.famMatchPlayerStriker.famPlayer = :famMatchPlayerStriker"),
        @NamedQuery(name = FamGoal.FIND_BY_ASSIST,
                query = "SELECT f FROM FamGoal f WHERE f.famMatchPlayerAssist.famPlayer = :famMatchPlayerAssist"),
        @NamedQuery(name = FamGoal.FIND_BY_MATCH_AND_TEAM,
                query = "SELECT f FROM FamGoal f WHERE f.famMatchPlayerStriker.famMatchTeam.famMatch = :famMatch AND f.famMatchPlayerStriker.famMatchTeam.famTeam = :famTeam"),
        @NamedQuery(name = FamGoal.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamGoal f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamGoal.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamGoal f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamGoal extends FamEntity implements Serializable {

    private static final long serialVersionUID = 6952842318358454966L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_goal";
    //
    public static final String FIND_ALL = "FamGoal.findAll";
    public static final String FIND_BY_ID_GOAL = "FamGoal.findByIdGoal";
    public static final String FIND_BY_GOAL_TIME = "FamGoal.findByGoalTime";
    public static final String FIND_BY_CSC = "FamGoal.findByCsc";
    public static final String FIND_BY_PENALTY = "FamGoal.findByPenalty";
    public static final String FIND_BY_MATCH_AND_TEAM = "FamGoal.findByMatchAndTeam";
    public static final String FIND_BY_STRIKER = "FamGoal.findByStriker";
    public static final String FIND_BY_ASSIST = "FamGoal.findByAssist";
    public static final String FIND_BY_DT_CREAT = "FamGoal.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamGoal.findByDtModif";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FamGoal famGoal = (FamGoal) o;

        return idGoal.equals(famGoal.idGoal);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idGoal.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamGoal");
        sb.append("{idGoal=").append(idGoal);
        sb.append(", goalTime='").append(goalTime).append('\'');
        sb.append(", csc=").append(csc);
        sb.append(", penalty=").append(penalty);
        sb.append(", famMatch=").append(famMatchPlayerStriker.getFamMatchTeam().getFamMatch().getFamEvent().getLibEvent());
        sb.append(", famMatchPlayerStriker=").append(famMatchPlayerStriker.getFamPlayer().getDisplayName());
        sb.append(", famMatchPlayerAssist=").append(famMatchPlayerAssist.getFamPlayer().getDisplayName());
        sb.append('}');
        return sb.toString();
    }
}
