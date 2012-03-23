package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.listener.FamWorkoutEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbougear
 */
@Entity
@EntityListeners({FamWorkoutEntityListener.class})
@Table(name = FamWorkout.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamWorkout.FIND_ALL,
                query = "SELECT f FROM FamWorkout f"),
        @NamedQuery(name = FamWorkout.FIND_BY_ID_WORKOUT,
                query = "SELECT f FROM FamWorkout f WHERE f.idWorkout = :idWorkout"),
        @NamedQuery(name = FamWorkout.FIND_BY_EVENT,
                query = "SELECT f FROM FamWorkout f WHERE f.famEvent = :famEvent"),
        @NamedQuery(name = FamWorkout.FIND_BY_SEASON,
                query = "SELECT f FROM FamWorkout f WHERE f.famEvent.famSeason = :famSeason"),
        @NamedQuery(name = FamWorkout.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamWorkout f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamWorkout.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamWorkout f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamWorkout extends FamEntity implements Serializable {

    private static final long serialVersionUID = -2684985602010470686L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_workout";
    //
    public static final String FIND_ALL = "FamWorkout.findAll";
    public static final String FIND_BY_ID_WORKOUT = "FamWorkout.findByIdWorkout";
    public static final String FIND_BY_EVENT = "FamWorkout.findByEvent";
    public static final String FIND_BY_SEASON = "FamWorkout.findBySeason";
    public static final String FIND_BY_DT_CREAT = "FamWorkout.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamWorkout.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idWorkout";
    /**
     *
     */
    public static final String COL_ID = "id_workout";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idWorkout;

    @Override
    public Long getId() {
        return this.getIdWorkout();
    }

    /**
     *
     */
    public static final String PROP_EVENT = "famEvent";
    /**
     *
     */
    public static final String COL_ID_EVENT = "id_event";
    @JoinColumn(name = COL_ID_EVENT, referencedColumnName = FamEvent.COL_ID)
    @OneToOne(cascade = CascadeType.ALL)//(optional = false)
    @NotNull
    private FamEvent famEvent;
    /**
     *
     */
    public static final String PROP_PLAYERS = "famPlayerList";
    @ManyToMany
    @JoinTable(name = "fam_workout_player",
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamPlayer.COL_ID)})
    private List<FamPlayer> famPlayerList;

    /**
     *
     */
    public FamWorkout() {
        this.famEvent = new FamEvent();
        this.famPlayerList = new ArrayList<FamPlayer>();
    }

    /**
     * @param idWorkout
     */
    public FamWorkout(Long idWorkout) {
        this.idWorkout = idWorkout;
    }

    /**
     * @return
     */
    public Long getIdWorkout() {
        return idWorkout;
    }

    /**
     * @param idWorkout
     */
    public void setIdWorkout(Long idWorkout) {
        this.idWorkout = idWorkout;
    }

    public FamEvent getFamEvent() {
        return famEvent;
    }

    public void setFamEvent(FamEvent famEvent) {
        this.famEvent = famEvent;
    }

    public List<FamPlayer> getFamPlayerList() {
        return famPlayerList;
    }

    public void setFamPlayerList(List<FamPlayer> famPlayer) {
        this.famPlayerList = famPlayer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkout != null ? idWorkout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamWorkout)) {
            return false;
        }
        FamWorkout other = (FamWorkout) object;
        return !((this.idWorkout == null && other.idWorkout != null) || (this.idWorkout != null && !this.idWorkout.equals(other.idWorkout)));
    }

}
