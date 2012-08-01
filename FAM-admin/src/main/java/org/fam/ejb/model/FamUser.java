package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Data;
import org.fam.ejb.util.Util;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p/>
 *
 * @author gbougear
 */
@Data
@Entity
@Table(name = FamUser.TABLE_NAME,
       uniqueConstraints = {
                            @UniqueConstraint(columnNames = {FamUser.COL_EMAIL})
       })
@NamedQueries({
               @NamedQuery(name = FamUser.FIND_ALL,
                           query = "SELECT f FROM FamUser f"),
               @NamedQuery(name = FamUser.FIND_BY_ID_USER,
                           query = "SELECT f FROM FamUser f WHERE f.idUser = :idUser"),
               @NamedQuery(name = FamUser.FIND_BY_EMAIL_AND_OPENID,
                           query = "SELECT f FROM FamUser f WHERE f.email = :email AND f.openid = :openid"),
               @NamedQuery(name = FamUser.FIND_BY_EMAIL,
                           query = "SELECT f FROM FamUser f WHERE f.email = :email"),
               @NamedQuery(name = FamUser.LOGIN,
                           query = "SELECT f FROM FamUser f WHERE f.email = :email AND f.password = :password"),
               @NamedQuery(name = FamUser.FIND_BY_DT_CREAT,
                           query = "SELECT f FROM FamUser f WHERE f.dtCreat = :dtCreat"),
               @NamedQuery(name = FamUser.FIND_BY_DT_MODIF,
                           query = "SELECT f FROM FamUser f WHERE f.dtModif = :dtModif")
})
public class FamUser extends FamEntity implements Serializable {

    private static final long serialVersionUID = -1395568979877326735L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_user";
    //
    public static final String FIND_ALL = "FamUser.findAll";
    public static final String FIND_BY_ID_USER = "FamUser.findByIdUser";
    public static final String FIND_BY_EMAIL_AND_OPENID = "FamUser.findByEmailAndOpenid";
    public static final String FIND_BY_EMAIL = "FamUser.findByEmail";
    public static final String LOGIN = "FamUser.login";
    public static final String FIND_BY_DT_CREAT = "FamUser.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamUser.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idUser";
    public static final String COL_ID = "id_user";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idUser;

    @Override
    public Long getId() {
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
    public static final String COL_ID_CURRENT_CLUB = "id_current_club";
    public static final String PROP_CURRENT_CLUB = "currentClub";

    @OneToOne//(optional = false)
    @JoinColumn(name = COL_ID_CURRENT_CLUB, referencedColumnName = FamClub.COL_ID)
//    @NotNull
    private FamClub currentClub;
    //
    @ManyToMany
    @JoinTable(name = FamUserSeason.TABLE_NAME,
               joinColumns = {@JoinColumn(name = COL_ID)},
               inverseJoinColumns = {@JoinColumn(name = FamSeason.COL_ID)})
    private List<FamSeason> famSeasonList;

    //bi-directional many-to-many association to Group
    @ManyToMany
    @JoinTable(name = "fam_user_group",
               joinColumns = {@JoinColumn(name = COL_ID)},
               inverseJoinColumns = {@JoinColumn(name = FamGroup.COL_ID)}
    )
    private List<FamGroup> groups;

    /**
     *
     */
    public FamUser() {
    }

    public void setPassword(String password) {
        this.password = Util.hash(password);
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

}
