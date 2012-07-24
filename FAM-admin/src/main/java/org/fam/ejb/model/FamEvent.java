package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.listener.FamEventEntityListener;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamEvent.TABLE_NAME)
@EntityListeners({FamEventEntityListener.class})
@NamedQueries({
        @NamedQuery(name = FamEvent.FIND_ALL,
                query = "SELECT f FROM FamEvent f"),
        @NamedQuery(name = FamEvent.FIND_BY_ID_EVENT,
                query = "SELECT f FROM FamEvent f WHERE f.idEvent = :idEvent"),
        @NamedQuery(name = FamEvent.FIND_BY_LIB_EVENT,
                query = "SELECT f FROM FamEvent f WHERE f.libEvent = :libEvent"),
        @NamedQuery(name = FamEvent.FIND_BY_DT_EVENT,
                query = "SELECT f FROM FamEvent f WHERE f.dtEvent = :dtEvent"),
        @NamedQuery(name = FamEvent.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamEvent f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamEvent.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamEvent f WHERE f.dtModif = :dtModif"),
        @NamedQuery(name = FamEvent.FIND_BETWEEN,
                query = "SELECT f FROM FamEvent f WHERE f.dtEvent BETWEEN :dtStart AND :dtEnd"),
        @NamedQuery(name = FamEvent.FIND_BETWEEN_BY_TEAM,
                query = "SELECT f FROM FamEvent f WHERE f.dtEvent BETWEEN :dtStart AND :dtEnd AND :team MEMBER OF f.famTeamList"),
        @NamedQuery(name = FamEvent.FIND_STATUS_BEFORE,
                query = "SELECT f FROM FamEvent f WHERE f.dtEvent <= :dtEvent AND f.famEventStatus = :famEventStatus")
})
@XmlRootElement
public class FamEvent extends FamEntity implements Serializable {

    //
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_event";
    //
    public static final String FIND_ALL = "FamEvent.findAll";
    public static final String FIND_BY_ID_EVENT = "FamEvent.findByIdEvent";
    public static final String FIND_BY_LIB_EVENT = "FamEvent.findByLibEvent";
    public static final String FIND_BY_DT_EVENT = "FamEvent.findByDtEvent";
    public static final String FIND_BY_DT_CREAT = "FamEvent.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamEvent.findByDtModif";
    public static final String FIND_BETWEEN = "FamEvent.findBetween";
    public static final String FIND_STATUS_BEFORE = "FamEvent.findStatusBefore";
    public static final String FIND_BETWEEN_BY_TEAM = "FamEvent.findBetweenByTeam";
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
    public Long getId() {
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

    @PostLoad
    @PostPersist
    @PostUpdate
    void afterLoad() {
        if ((dtEvent != null) && (duration != null)) {
            DateTime dt = new DateTime(dtEvent);
            dtEnd = dt.plusMinutes(duration).toDate();
        }
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

        FamEvent famEvent = (FamEvent) o;

        return idEvent.equals(famEvent.idEvent);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idEvent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamEvent");
        sb.append("{idEvent=").append(idEvent);
        sb.append(", libEvent='").append(libEvent).append('\'');
        sb.append(", dtEvent=").append(dtEvent);
        sb.append(", comments='").append(comments).append('\'');
        sb.append(", famTypEvent=").append(famTypEvent.getLibTypEvent());
        if (famPlace != null) {
            sb.append(", famPlace=").append(famPlace.getLibPlace());
        }
        sb.append(", famTeamList=").append(famTeamList);
        sb.append(", famEventStatus=").append(famEventStatus.getLibEventStatus());
        sb.append(", duration=").append(duration);
        sb.append(", allDay=").append(allDay);
        sb.append(", dtEnd=").append(dtEnd);
        sb.append(", famSeason=").append(famSeason.getLibSeason());
        sb.append('}');
        return sb.toString();
    }
}
