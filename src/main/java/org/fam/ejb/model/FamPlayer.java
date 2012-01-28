package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Entity
@Table(name = FamPlayer.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamPlayer.findAll", query = "SELECT f FROM FamPlayer f"),
    @NamedQuery(name = "FamPlayer.findByIdPlayer", query = "SELECT f FROM FamPlayer f WHERE f.idPlayer = :idPlayer"),
    @NamedQuery(name = "FamPlayer.findPossiblePlayers", query = "SELECT f FROM FamPlayer f WHERE (f.email = :email) OR (f.lastName = :lastName AND f.firstName = :firstName)"),
    @NamedQuery(name = "FamPlayer.findByDtCreat", query = "SELECT f FROM FamPlayer f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamPlayer.findByDtModif", query = "SELECT f FROM FamPlayer f WHERE f.dtModif = :dtModif")})
public class FamPlayer extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_player";
    //
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
    public Long getId(){
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
//    public static final String PROP_USER = "famUser";
//    @OneToOne//(optional = false)
//    @JoinColumn(name = COL_ID_USER, referencedColumnName = FamUser.COL_ID)
////    @NotNull
//    private FamUser famUser;
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

    /**
     *
     * @param idPlayer
     */
    public FamPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    /**
     *
     * @return
     */
    public Long getIdPlayer() {
        return idPlayer;
    }

    /**
     *
     * @param idPlayer
     */
    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    /**
     *
     * @return
     */
//    public FamUser getFamUser() {
//        return famUser;
//    }
    /**
     *
     * @param famUser
     */
//    public void setFamUser(FamUser famUser) {
//        this.famUser = famUser;
//    }
    /**
     *
     * @return
     */
    public Long getNumLicense() {
        return numLicense;
    }

    /**
     *
     * @param numLicense
     */
    public void setNumLicense(Long numLicense) {
        this.numLicense = numLicense;
    }

    public List<FamPlayerSeason> getFamPlayerSeasons() {
        return famPlayerSeasons;
    }

    public void setFamPlayerSeasons(List<FamPlayerSeason> famPlayerSeasons) {
        this.famPlayerSeasons = famPlayerSeasons;
    }

    public List<FamPlayerPosition> getFamPlayerPositionList() {
        return famPlayerPositionList;
    }

    public void setFamPlayerPositionList(List<FamPlayerPosition> famPlayerPositionList) {
        this.famPlayerPositionList = famPlayerPositionList;
    }

    /**
     *
     * @return
     */
    public List<FamMatchPlayer> getFamMatchPlayer() {
        return famMatchPlayer;
    }

    /**
     *
     * @param famMatchPlayer
     */
    public void setFamMatchPlayer(List<FamMatchPlayer> famMatchPlayer) {
        this.famMatchPlayer = famMatchPlayer;
    }

    /**
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 
     * @return
     */
    public Date getDtArrival() {
        return dtArrival;
    }

    /**
     * 
     * @param dtArrival
     */
    public void setDtArrival(Date dtArrival) {
        this.dtArrival = dtArrival;
    }

    /**
     * 
     * @return
     */
    public Date getDtBirth() {
        return dtBirth;
    }

    /**
     * 
     * @param dtBirth
     */
    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    /**
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     */
    public Integer getSeniority() {
        return seniority;
    }

    /**
     * 
     * @param seniority
     */
    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    /**
     * 
     * @return
     */
    public String getTel() {
        return tel;
    }

    /**
     * 
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 
     * @return
     */
    public String getIceComment() {
        return iceComment;
    }

    /**
     * 
     * @param iceComment
     */
    public void setIceComment(String iceComment) {
        this.iceComment = iceComment;
    }

    /**
     * 
     * @return
     */
    public String getIceContact() {
        return iceContact;
    }

    /**
     * 
     * @param iceContact
     */
    public void setIceContact(String iceContact) {
        this.iceContact = iceContact;
    }

    /**
     * 
     * @return
     */
    public String getIceTel() {
        return iceTel;
    }

    /**
     * 
     * @param iceTel
     */
    public void setIceTel(String iceTel) {
        this.iceTel = iceTel;
    }

    public FamPlayerProfile getCurrentProfile() {
        return ( getCurrentPlayerSeason() == null ) ? null : getCurrentPlayerSeason().getFamPlayerProfile();
    }

    public FamPlayerStat getCurrentStat() {
        return ( getCurrentPlayerSeason() == null ) ? null : getCurrentPlayerSeason().getFamPlayerStat();
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
    public int hashCode() {
        int hash = 0;
        hash += ( idPlayer != null ? idPlayer.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamPlayer)) {
            return false;
        }
        FamPlayer other = (FamPlayer) object;
        if (( this.idPlayer == null && other.idPlayer != null ) || ( this.idPlayer != null && !this.idPlayer.equals(other.idPlayer) )) {
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
            String str = "null";
//            if (f.getName().equals(PROP_CLUB)) {
//
//                if (this.getFamClub() != null) {
//                    str = this.getFamClub().getLibClub();
//                }
//                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
//            } else if (f.getName().equals(PROP_DEFAULT_TEAM)) {
//                str = "null";
//                if (this.getFamDefaultTeam() != null) {
//                    str = this.getFamDefaultTeam().getLibTeam();
//                }
//                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
//            } else 
            if (f.getName().equals(PROP_PLAYERSEASONS)) {
                str = "null";
                if (famPlayerSeasons != null) {
                    for (FamPlayerSeason item : famPlayerSeasons) {
                        str = item.getFamSeason().getLibSeason();
                        builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
                    }
                }
            } else {
                try {
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                }
                catch (IllegalArgumentException e) {
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                }
                catch (IllegalAccessException e) {
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                }
            }

        }
        builder.append("\n]");
        return builder.toString();
    }
}
