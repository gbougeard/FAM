package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.fam.ejb.listener.FamWorkoutEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@EntityListeners({FamWorkoutEntityListener.class})
@Table(name = FamWorkout.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamWorkout.findAll", query = "SELECT f FROM FamWorkout f"),
    @NamedQuery(name = "FamWorkout.findByIdWorkout", query = "SELECT f FROM FamWorkout f WHERE f.idWorkout = :idWorkout"),
    @NamedQuery(name = "FamWorkout.findByEvent", query = "SELECT f FROM FamWorkout f WHERE f.famEvent = :famEvent"),
    @NamedQuery(name = "FamWorkout.findBySeason", query = "SELECT f FROM FamWorkout f WHERE f.famEvent.famSeason = :famSeason"),
    @NamedQuery(name = "FamWorkout.findByDtCreat", query = "SELECT f FROM FamWorkout f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamWorkout.findByDtModif", query = "SELECT f FROM FamWorkout f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamWorkout extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_workout";
    //
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
    public Long getId(){
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
     * 
     * @param idWorkout
     */
    public FamWorkout(Long idWorkout) {
        this.idWorkout = idWorkout;
    }

    /**
     * 
     * @return
     */
    public Long getIdWorkout() {
        return idWorkout;
    }

    /**
     * 
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
        hash += ( idWorkout != null ? idWorkout.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamWorkout)) {
            return false;
        }
        FamWorkout other = (FamWorkout) object;
        if (( this.idWorkout == null && other.idWorkout != null ) || ( this.idWorkout != null && !this.idWorkout.equals(other.idWorkout) )) {
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
            if (f.getName().equals(PROP_PLAYERS) == false) {
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
        }
        builder.append("\n]");
        return builder.toString();
    }
}
