package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.ejb.common.LogUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

/**
 * <p/>
 * @author gbougear
 */
@Entity
@Table(name = FamUser.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamUser.COL_EMAIL})
})
@NamedQueries({
    @NamedQuery(name = "FamUser.findAll", query = "SELECT f FROM FamUser f"),
    @NamedQuery(name = "FamUser.findByIdUser", query = "SELECT f FROM FamUser f WHERE f.idUser = :idUser"),
    @NamedQuery(name = "FamUser.findByEmailAndOpenid", query = "SELECT f FROM FamUser f WHERE f.email = :email AND f.openid = :openid"),
    @NamedQuery(name = "FamUser.login", query = "SELECT f FROM FamUser f WHERE f.email = :email AND f.password = :password"),
    @NamedQuery(name = "FamUser.findByDtCreat", query = "SELECT f FROM FamUser f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamUser.findByDtModif", query = "SELECT f FROM FamUser f WHERE f.dtModif = :dtModif")})
public class FamUser extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_user";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idUser";
    /**
     * 
     */
    public static final String COL_ID = "id_user";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idUser;

    @Override
    public Long getId(){
        return this.getIdUser();
    }
    
    /**
     * 
     */
    public static final String PROP_PWD = "password";
    @Basic(optional = false)
    @Column(name = PROP_PWD)
    private String password;
    //
    /**
     * 
     */
    public static final String PROP_FIRST_NAME = "firstName";
    public static final String COL_FIRST_NAME = "first_name";
    @Basic(optional = false)
    @NotEmpty(message = "Prenom obligatoire")
    @Column(name = COL_FIRST_NAME)
    private String firstName;
    //
    /**
     * 
     */
    public static final String PROP_LAST_NAME = "lastName";
    public static final String COL_LAST_NAME = "last_name";
    @Basic(optional = false)
    @NotEmpty(message = "Nom obligatoire")
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
    @Column(name = PROP_EMAIL)
    @Email
    @NotEmpty
    private String email;
    // 
    /**
     * 
     */
    public static final String PROP_OPENID = "openid";
    @Column(name = PROP_OPENID)
    @NotNull
    Boolean openid = Boolean.FALSE;
    //
    /**
     * 
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     * 
     */
    public static final String PROP_PLAYER = "famPlayer";
    @OneToOne//(optional = false)
    @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = FamPlayer.COL_ID)
//    @NotNull
    private FamPlayer famPlayer;
    //
    @ManyToMany
    @JoinTable(name = FamUserSeason.TABLE_NAME,
    joinColumns = {
        @JoinColumn(name = COL_ID)},
    inverseJoinColumns = {
        @JoinColumn(name = FamSeason.COL_ID)})
    private List<FamSeason> famSeasonList;

    /**
     *
     */
    public FamUser() {
    }

    /**
     *
     * @param idUser
     */
    public FamUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public List<FamSeason> getFamSeasonList() {
        return famSeasonList;
    }

    /**
     *
     * @param famSeasonList
     */
    public void setFamSeasonList(List<FamSeason> famSeasonList) {
        this.famSeasonList = famSeasonList;
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
    public Boolean getOpenid() {
        return openid;
    }

    /**
     *
     * @param openid
     */
    public void setOpenid(Boolean openid) {
        this.openid = openid;
    }

    /**
     *
     * @return
     */
    public FamPlayer getFamPlayer() {
        return famPlayer;
    }

    /**
     *
     * @param famPlayer
     */
    public void setFamPlayer(FamPlayer famPlayer) {
        this.famPlayer = famPlayer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idUser != null ? idUser.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamUser)) {
            return false;
        }
        FamUser other = (FamUser) object;
        if (( this.idUser == null && other.idUser != null ) || ( this.idUser != null && !this.idUser.equals(other.idUser) )) {
            return false;
        }
        return true;
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
