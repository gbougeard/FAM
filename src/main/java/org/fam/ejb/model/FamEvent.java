package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.fam.ejb.listener.FamEventEntityListener;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamEvent.TABLE_NAME)
@EntityListeners({FamEventEntityListener.class})
@NamedQueries({
    @NamedQuery(name = "FamEvent.findAll", query = "SELECT f FROM FamEvent f"),
    @NamedQuery(name = "FamEvent.findByIdEvent", query = "SELECT f FROM FamEvent f WHERE f.idEvent = :idEvent"),
    @NamedQuery(name = "FamEvent.findByLibEvent", query = "SELECT f FROM FamEvent f WHERE f.libEvent = :libEvent"),
    @NamedQuery(name = "FamEvent.findByDtEvent", query = "SELECT f FROM FamEvent f WHERE f.dtEvent = :dtEvent"),
    @NamedQuery(name = "FamEvent.findByDtCreat", query = "SELECT f FROM FamEvent f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamEvent.findByDtModif", query = "SELECT f FROM FamEvent f WHERE f.dtModif = :dtModif"),
    @NamedQuery(name = "FamEvent.findBetween", query = "SELECT f FROM FamEvent f WHERE f.dtEvent BETWEEN :dtStart AND :dtEnd"),
    @NamedQuery(name = "FamEvent.findStatusBefore", query = "SELECT f FROM FamEvent f WHERE f.dtEvent <= :dtEvent AND f.famEventStatus = :famEventStatus")})
public class FamEvent extends FamEntity implements Serializable {

    //
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_event";
    //
    /**
     *
     */
    public static final String PROP_ID = "idEvent";
    /**
     *
     */
    public static final String COL_ID = "id_event";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idEvent;

    @Override
    public Long getId(){
        return this.getIdEvent();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libEvent";
    public static final String COL_LIB = "lib_event";
    @Basic(optional = false)
    @Column(name = COL_LIB)
    @NotEmpty
    private String libEvent;
    //
    /**
     *
     */
    public static final String PROP_DT_EVENT = "dtEvent";
    /**
     *
     */
    public static final String COL_DT_EVENT = "dt_event";
    @Basic(optional = false)
    @Column(name = COL_DT_EVENT)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtEvent;
    //
    /**
     *
     */
    public static final String PROP_COMMENTS = "comments";
    @Lob
    @Basic(optional = false)
    @Column(name = PROP_COMMENTS)
    private String comments;
    //
    /**
     *
     */
    public static final String COL_ID_TYP = "id_typ_event";
    /**
     *
     */
    public static final String PROP_TYP = "famTypEvent";
    @JoinColumn(name = COL_ID_TYP, referencedColumnName = FamTypEvent.COL_ID)
    @ManyToOne
    @NotNull
    private FamTypEvent famTypEvent;
    //
    /**
     *
     */
    public static final String COL_ID_PLACE = "id_place";
    /**
     *
     */
    public static final String PROP_PLACE = "famPlace";
    @JoinColumn(name = COL_ID_PLACE, referencedColumnName = FamPlace.COL_ID)
    @ManyToOne
    private FamPlace famPlace;
    //
    @ManyToMany
    @JoinTable(name = "fam_event_team",
    joinColumns = {
        @JoinColumn(name = COL_ID)},
    inverseJoinColumns = {
        @JoinColumn(name = FamTeam.COL_ID)})
    private List<FamTeam> famTeamList;
    //
    /**
     *
     */
    public static final String COL_ID_STATUS = "id_eventStatus";
    /**
     *
     */
    public static final String PROP_STATUS = "famEventStatus";
    @JoinColumn(name = COL_ID_STATUS, referencedColumnName = FamEventStatus.COL_ID)
    @ManyToOne
    private FamEventStatus famEventStatus;
    //
    /**
     *
     */
    public static final String PROP_DURATION = "duration";
    @Column(name = PROP_DURATION)
    @NotNull
    private Integer duration;
    //
    /**
     *
     */
    public static final String PROP_ALL_DAY = "all_day";
    @Column(name = PROP_ALL_DAY)
    private Boolean allDay;
    //
    @Transient
    Date dtEnd;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON = "id_season";
    /**
     *
     */
    public static final String PROP_SEASON = "famSeason";
    @JoinColumn(name = COL_ID_SEASON, referencedColumnName = FamSeason.COL_ID)
    @ManyToOne
    @NotNull
    private FamSeason famSeason;

    /**
     *
     */
    public FamEvent() {
    }

    /**
     *
     * @param idEvent
     */
    public FamEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    /**
     *
     * @return
     */
    public Long getIdEvent() {
        return idEvent;
    }

    /**
     *
     * @param idEvent
     */
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    /**
     *
     * @return
     */
    public String getLibEvent() {
        return libEvent;
    }

    /**
     *
     * @param libEvent
     */
    public void setLibEvent(String libEvent) {
        this.libEvent = libEvent;
    }

    /**
     *
     * @return
     */
    public Date getDtEvent() {
        return dtEvent;
    }

    /**
     *
     * @param dtEvent
     */
    public void setDtEvent(Date dtEvent) {
        this.dtEvent = dtEvent;
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public FamPlace getFamPlace() {
        return famPlace;
    }

    /**
     *
     * @param famPlace
     */
    public void setFamPlace(FamPlace famPlace) {
        this.famPlace = famPlace;
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
    public FamTypEvent getFamTypEvent() {
        return famTypEvent;
    }

    /**
     *
     * @param famTypEvent
     */
    public void setFamTypEvent(FamTypEvent famTypEvent) {
        this.famTypEvent = famTypEvent;
    }

    /**
     *
     * @return
     */
    public FamEventStatus getFamEventStatus() {
        return famEventStatus;
    }

    /**
     *
     * @param famEventStatus
     */
    public void setFamEventStatus(FamEventStatus famEventStatus) {
        this.famEventStatus = famEventStatus;
    }

    /**
     *
     * @return
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
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
    public Boolean getAllDay() {
        return allDay;
    }

    /**
     * 
     * @param allDay
     */
    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * 
     * @return
     */
    public Date getDtEnd() {
        return dtEnd;
    }

    /**
     * 
     * @param dtEnd
     */
    public void setDtEnd(Date dtEnd) {
        this.dtEnd = dtEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idEvent != null ? idEvent.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamEvent)) {
            return false;
        }
        FamEvent other = (FamEvent) object;
        if (( this.idEvent == null && other.idEvent != null ) || ( this.idEvent != null && !this.idEvent.equals(other.idEvent) )) {
            return false;
        }
        return true;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    void afterLoad() {
        if (( dtEvent != null ) && ( duration != null )) {
            DateTime dt = new DateTime(dtEvent);
            dtEnd = dt.plusMinutes(duration).toDate();
        }
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
