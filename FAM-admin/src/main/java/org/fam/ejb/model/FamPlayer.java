package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamPlayer.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamPlayer.FIND_ALL,
                query = "SELECT f FROM FamPlayer f"),
        @NamedQuery(name = FamPlayer.FIND_BY_ID_PLAYER,
                query = "SELECT f FROM FamPlayer f WHERE f.idPlayer = :idPlayer"),
        @NamedQuery(name = FamPlayer.FIND_POSSIBLE_PLAYERS,
                query = "SELECT f FROM FamPlayer f WHERE (f.email = :email) OR (f.lastName = :lastName AND f.firstName = :firstName)"),
        @NamedQuery(name = FamPlayer.FIND_BY_FAM_USER,
                query = "SELECT f FROM FamPlayer f WHERE f.famUser = :famUser"),
        @NamedQuery(name = FamPlayer.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamPlayer f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamPlayer.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamPlayer f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamPlayer extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1122084647103216448L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_player";
    //
    public static final String FIND_ALL = "FamPlayer.findAll";
    public static final String FIND_BY_ID_PLAYER = "FamPlayer.findByIdPlayer";
    public static final String FIND_POSSIBLE_PLAYERS = "FamPlayer.findPossiblePlayers";
    public static final String FIND_BY_FAM_USER = "FamPlayer.findByFamUser";
    public static final String FIND_BY_DT_CREAT = "FamPlayer.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamPlayer.findByDtModif";
    /**
     *
     */
    public static final String COL_ID = "id_player";
    /**
     *
     */
    public static final String PROP_ID = "idPlayer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idPlayer;

    @Override
    public Long getId() {
        return this.getIdPlayer();
    }

    /**
     *
     */
    public static final String PROP_FIRST_NAME = "firstName";
    /**
     *
     */
    public static final String COL_FIRST_NAME = "first_name";
    @Basic(optional = false)
    @NotEmpty(message = "Prénom obligatoire")
//    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    @Column(name = COL_FIRST_NAME)
    private String firstName;
    //
    /**
     *
     */
    public static final String PROP_LAST_NAME = "lastName";
    /**
     *
     */
    public static final String COL_LAST_NAME = "last_name";
    @Basic(optional = false)
    @NotEmpty(message = "Nom obligatoire")
//    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    @Column(name = COL_LAST_NAME)
    private String lastName;
    //
    @Transient
    private String displayName;
    //
    /**
     *
     */
    public static final String PROP_EMAIL = "email";
    /**
     *
     */
    public static final String COL_EMAIL = "email";
    @Basic(optional = false)
    @Column(name = COL_EMAIL)
    @Email
    private String email;
    //
    /**
     *
     */
    public static final String PROP_NUM_LICENSE = "numLicense";
    /**
     *
     */
    public static final String COL_NUM_LICENSE = "num_license";
    @Basic(optional = false)
//    @NotNull(message = "Numero licence obligatoire")
    @Column(name = COL_NUM_LICENSE)
    private Long numLicense;
    //
    /**
     *
     */
    public static final String COL_ID_USER = "id_user";
    /**
     *
     */
    public static final String PROP_USER = "famUser";
    @OneToOne//(optional = false)
    @JoinColumn(name = COL_ID_USER, referencedColumnName = FamUser.COL_ID)
//    @NotNull
    private FamUser famUser;
    //
    /**
     *
     */
    public static final String PROP_DT_BIRTH = "dtBirth";
    /**
     *
     */
    public static final String COL_DT_BIRTH = "dt_birth";
    @Basic(optional = false)
    @Column(name = COL_DT_BIRTH)
    @Temporal(TemporalType.DATE)
    private Date dtBirth;
    //
    @Transient
    private Integer age;
    //
    /**
     *
     */
    public static final String PROP_DT_ARRIVAL = "dtArrival";
    /**
     *
     */
    public static final String COL_DT_ARRIVAL = "dt_arrival";
    @Basic(optional = false)
    @Column(name = COL_DT_ARRIVAL)
    @Temporal(TemporalType.DATE)
    private Date dtArrival;
    //
    @Transient
    private Integer seniority;
    //
    /**
     *
     */
    public static final String PROP_TEL = "tel";
    @Basic(optional = false)
    @Column(name = PROP_TEL)
//    @Digits(fraction = 0, integer = 10)
    private String tel;
    /**
     *
     */
    public static final String PROP_ADDRESS = "address";
    @Basic(optional = false)
    @Column(name = PROP_ADDRESS)
    private String address;
    /**
     *
     */
    public static final String PROP_ZIPCODE = "zipcode";
    @Basic(optional = false)
    @Column(name = PROP_ZIPCODE)
    private String zipcode;
    /**
     *
     */
    public static final String PROP_CITY = "city";
    @Basic(optional = false)
    @Column(name = PROP_CITY)
    private String city;
    /**
     *
     */
    public static final String PROP_ICE_CONTACT = "iceContact";
    public static final String COL_ICE_CONTACT = "ice_contact";
    @Basic(optional = true)
    @Column(name = COL_ICE_CONTACT)
    private String iceContact;
    /**
     *
     */
    public static final String PROP_ICE_TEL = "iceTel";
    public static final String COL_ICE_TEL = "ice_tel";
    @Basic(optional = false)
    @Column(name = COL_ICE_TEL)
    private String iceTel;
    /**
     *
     */
    public static final String PROP_ICE_COMMENT = "iceComment";
    public static final String COL_ICE_COMMENT = "ice_comment";
    @Lob
    @Column(name = COL_ICE_COMMENT)
    private String iceComment;
    //
//    @ManyToMany
//    @JoinTable(name = FamPlayerSeason.TABLE_NAME,
//    joinColumns = {
//        @JoinColumn(name = COL_ID)},
//    inverseJoinColumns = {
//        @JoinColumn(name = FamSeason.COL_ID)})
//    private List<FamSeason> famSeasonList;
    public static final String PROP_PLAYERSEASONS = "famPlayerSeasons";
    @OneToMany(mappedBy = FamPlayerSeason.PROP_PLAYER, cascade = CascadeType.ALL)
    private List<FamPlayerSeason> famPlayerSeasons;
    //
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.)
//    @JoinTable(name = "fam_player_position",
//    joinColumns = {
//        @JoinColumn(name = COL_ID)},
//    inverseJoinColumns = {
//        @JoinColumn(name = FamPosition.COL_ID)})
    public static final String PROP_POSITIONS = "famPlayerPositionList";
    @OneToMany(mappedBy = FamPlayerPosition.PROP_PLAYER, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(FamPlayerPosition.PROP_NUM_ORDER)
    private List<FamPlayerPosition> famPlayerPositionList;
    /**
     *
     */
    public static final String PROP_MATCHES = "famMatchTeamList";
    @OneToMany(mappedBy = FamMatchPlayer.PROP_PLAYER)
    private List<FamMatchPlayer> famMatchPlayer;
//    /**
//     *
//     */
//    public static final String PROP_CLUB = "famClub";
//    /**
//     *
//     */
//    public static final String COL_ID_CLUB = "id_club";
//    @ManyToOne
//    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID)
//    private FamClub famClub;
//    //
//    /**
//     *
//     */
//    public static final String PROP_DEFAULT_TEAM = "famDefaultTeam";
//    /**
//     *
//     */
//    public static final String COL_ID_DEFAULT_TEAM = "id_default_team";
//    @ManyToOne
//    @JoinColumn(name = COL_ID_DEFAULT_TEAM, referencedColumnName = FamTeam.COL_ID)
//    private FamTeam famDefaultTeam;

    /**
     *
     */
    public FamPlayer() {
    }

    public FamPlayerProfile getCurrentProfile() {
        return (getCurrentPlayerSeason() == null) ? null : getCurrentPlayerSeason().getFamPlayerProfile();
    }

    public FamPlayerStat getCurrentStat() {
        return (getCurrentPlayerSeason() == null) ? null : getCurrentPlayerSeason().getFamPlayerStat();
    }

    public FamPlayerSeason getCurrentPlayerSeason() {
        if (famPlayerSeasons != null) {
            for (FamPlayerSeason item : famPlayerSeasons) {
                if (item.getFamSeason().getCurrentSeason()) {
                    return item;
                }
            }
        }
        return null;
    }

    public FamClub getCurrentClub() {
        if (getCurrentPlayerSeason() == null) {
            return null;
        } else {
            return getCurrentPlayerSeason().getFamClub();
        }
    }

    public void setCurrentClub(FamClub club) {
        if (getCurrentPlayerSeason() != null) {
            getCurrentPlayerSeason().setFamClub(club);
        }
    }

    public FamClub getClubForSeason(FamSeason season) {
        if (famPlayerSeasons != null) {
            for (FamPlayerSeason item : famPlayerSeasons) {
                if (item.getFamSeason().equals(season)) {
                    return item.getFamClub();
                }
            }
        }
        return null;
    }

    public FamTeam getCurrentTeam() {
        if (getCurrentPlayerSeason() == null) {
            return null;
        } else {
            return getCurrentPlayerSeason().getFamTeam();
        }
    }

    public void setCurrentTeam(FamTeam team) {
        if (getCurrentPlayerSeason() != null) {
            getCurrentPlayerSeason().setFamTeam(team);
        }
    }


    public FamTeam getTeamForSeason(FamSeason season) {
        if (famPlayerSeasons != null) {
            for (FamPlayerSeason item : famPlayerSeasons) {
                if (item.getFamSeason().equals(season)) {
                    return item.getFamTeam();
                }
            }
        }
        return null;
    }

    public FamPosition getPrincipalPosition() {
        if (famPlayerPositionList == null || famPlayerPositionList.isEmpty()) {
            return null;
        } else {
            return famPlayerPositionList.get(0).getFamPosition();
        }
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    void afterLoad() {
        StringBuilder builder = new StringBuilder();
        if (firstName != null) {
            builder.append(firstName);
        }
        if (lastName != null) {
            builder.append(" ").append(lastName);
        }
        displayName = builder.toString();

        if (this.getDtBirth() != null) {
            DateMidnight date = new DateMidnight(this.getDtBirth());
            DateTime now = new DateTime();
            Years y = Years.yearsBetween(date, now);
            age = y.getYears();
        }
        if (this.getDtArrival() != null) {
            DateMidnight date = new DateMidnight(this.getDtArrival());
            DateTime now = new DateTime();
            Years y = Years.yearsBetween(date, now);
            seniority = y.getYears();
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

        FamPlayer famPlayer = (FamPlayer) o;

        return idPlayer.equals(famPlayer.idPlayer);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idPlayer != null ? idPlayer.hashCode() : 0);
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamPlayer");
        sb.append("{idPlayer=").append(idPlayer);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", numLicense=").append(numLicense);
        if (famUser != null) {
            sb.append(", famUser=").append(famUser.getDisplayName());
        }
        sb.append(", dtBirth=").append(dtBirth);
        sb.append(", age=").append(age);
        sb.append(", dtArrival=").append(dtArrival);
        sb.append(", seniority=").append(seniority);
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", iceContact='").append(iceContact).append('\'');
        sb.append(", iceTel='").append(iceTel).append('\'');
        sb.append(", iceComment='").append(iceComment).append('\'');
//        sb.append(", famPlayerSeasons=").append(famPlayerSeasons);
//        sb.append(", famPlayerPositionList=").append(famPlayerPositionList);
//        sb.append(", famMatchPlayer=").append(famMatchPlayer);
        sb.append('}');
        return sb.toString();
    }
}
