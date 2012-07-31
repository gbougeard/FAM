package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamClub.TABLE_NAME,
       uniqueConstraints = {
                            @UniqueConstraint(columnNames = {FamClub.COL_COD})
       })
@NamedQueries({
               @NamedQuery(name = FamClub.FIND_ALL,
                           query = "SELECT f FROM FamClub f"),
               @NamedQuery(name = FamClub.FIND_BY_ID_CLUB,
                           query = "SELECT f FROM FamClub f WHERE f.idClub = :idClub"),
               @NamedQuery(name = FamClub.FIND_BY_LIB_CLUB,
                           query = "SELECT f FROM FamClub f WHERE f.libClub = :libClub"),
               @NamedQuery(name = FamClub.FIND_BY_CODE_FFF,
                           query = "SELECT f FROM FamClub f WHERE f.codeFff = :codeFff"),
               @NamedQuery(name = FamClub.FIND_BY_COUNTRY,
                           query = "SELECT f FROM FamClub f WHERE f.famCountry = :famCountry"),
               @NamedQuery(name = FamClub.FIND_BY_PROVINCE,
                           query = "SELECT f FROM FamClub f WHERE f.famProvince = :famProvince"),
               @NamedQuery(name = FamClub.FIND_BY_STATE,
                           query = "SELECT f FROM FamClub f WHERE f.famState = :famState"),
               @NamedQuery(name = FamClub.FIND_BY_CITY,
                           query = "SELECT f FROM FamClub f WHERE f.famCity = :famCity"),
               @NamedQuery(name = FamClub.FIND_BY_DT_CREAT,
                           query = "SELECT f FROM FamClub f WHERE f.dtCreat = :dtCreat"),
               @NamedQuery(name = FamClub.FIND_BY_DT_MODIF,
                           query = "SELECT f FROM FamClub f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamClub extends FamEntity implements Serializable {

    private static final long serialVersionUID = -8074401386168281045L;
    public static final String TABLE_NAME = "fam_club";
    //
    public static final String FIND_ALL = "FamClub.findAll";
    public static final String FIND_BY_ID_CLUB = "FamClub.findByIdClub";
    public static final String FIND_BY_LIB_CLUB = "FamClub.findByLibClub";
    public static final String FIND_BY_CODE_FFF = "FamClub.findByCodeFff";
    public static final String FIND_BY_COUNTRY = "FamClub.findByCountry";
    public static final String FIND_BY_PROVINCE = "FamClub.findByProvince";
    public static final String FIND_BY_STATE = "FamClub.findByState";
    public static final String FIND_BY_CITY = "FamClub.findByCity";
    public static final String FIND_BY_DT_CREAT = "FamClub.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamClub.findByDtModif";
    //
    public static final String PROP_ID = "idClub";
    public static final String COL_ID = "id_club";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idClub;


    @Override
    public Long getId() {
        return this.getIdClub();
    }

    //
    public static final String PROP_LIB = "libClub";
    public static final String COL_LIB = "lib_club";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libClub;
    //
    public static final String PROP_COD = "codeFff";
    public static final String COL_COD = "code_fff";
    @Basic(optional = false)
    @NotNull(message = "Code FFF obligatoire")
    @Column(name = COL_COD)
    private Integer codeFff;
    //
    public static final String PROP_COUNTRY = "famCountry";
    public static final String COL_ID_COUNTRY = "id_country";
    @ManyToOne
    @JoinColumn(name = FamCountry.COL_ID)
    private FamCountry famCountry;
    //
    public static final String PROP_STATE = "famState";
    public static final String COL_ID_STATE = "id_state";
    @ManyToOne
    @JoinColumn(name = FamState.COL_ID)
    private FamState famState;
    //
    public static final String PROP_PROVINCE = "famProvince";
    public static final String COL_ID_PROVINCE = "id_province";
    @ManyToOne
    @JoinColumn(name = FamProvince.COL_ID)
    private FamProvince famProvince;
    //
    public static final String PROP_CITY = "famCity";
    public static final String COL_ID_CITY = "id_city";
    @ManyToOne
    @JoinColumn(name = FamCity.COL_ID)
    private FamCity famCity;
    //
    public static final String PROP_COMMENTS = "comments";
    public static final String COL_COMMENTS = "comments";
    @Lob
    @Column(name = COL_COMMENTS)
    private String comments;
    //
    public static final String PROP_TEAMS = "famTeamList";
    @OneToMany(mappedBy = FamTeam.PROP_CLUB, fetch = FetchType.EAGER)
    private List<FamTeam> famTeamList;

    public FamClub() {
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

        FamClub famClub = (FamClub) o;

        return idClub.equals(famClub.idClub);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idClub.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamClub");
        sb.append("{idClub=").append(idClub);
        sb.append(", libClub='").append(libClub).append('\'');
        sb.append(", codeFff=").append(codeFff);
        sb.append(", famCountry=").append(famCountry != null ? famCountry.getLibCountry() : null);
        sb.append(", famState=").append(famState != null ? famState.getLibState() : null);
        sb.append(", famProvince=").append(famProvince != null ? famProvince.getLibProvince() : null);
        sb.append(", famCity=").append(famCity != null ? famCity.getLibCity() : null);
        sb.append(", comments='").append(comments).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
