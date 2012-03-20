package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamCountry.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamCountry.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamCountry.FIND_ALL,
                query = "SELECT f FROM FamCountry f ORDER BY f.libUpper ASC"),
        @NamedQuery(name = FamCountry.FIND_BY_ID_COUNTRY,
                query = "SELECT f FROM FamCountry f WHERE f.idCountry = :idCountry"),
        @NamedQuery(name = FamCountry.FIND_BY_LIB_COUNTRY,
                query = "SELECT f FROM FamCountry f WHERE f.libCountry = :libCountry"),
        @NamedQuery(name = FamCountry.FIND_BY_COD_COUNTRY,
                query = "SELECT f FROM FamCountry f WHERE f.codCountry = :codCountry"),
        @NamedQuery(name = FamCountry.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamCountry f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamCountry.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamCountry f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamCountry extends FamEntity implements Serializable {

    private static final long serialVersionUID = 2788282406796899472L;
    public static final String TABLE_NAME = "fam_country";
    //
    public static final String FIND_ALL = "FamCountry.findAll";
    public static final String FIND_BY_ID_COUNTRY = "FamCountry.findByIdCountry";
    public static final String FIND_BY_LIB_COUNTRY = "FamCountry.findByLibCountry";
    public static final String FIND_BY_COD_COUNTRY = "FamCountry.findByCodCountry";
    public static final String FIND_BY_DT_CREAT = "FamCountry.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamCountry.findByDtModif";
    //
    public static final String PROP_ID = "idCountry";
    public static final String COL_ID = "id_country";

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = COL_ID)
    private Long idCountry;

    @Override
    public Long getId() {
        return this.getIdCountry();
    }

    //
    public static final String PROP_LIB = "libCountry";
    public static final String COL_LIB = "lib_country";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libCountry;
    //
    public static final String PROP_COD = "codCountry";
    public static final String COL_COD = "cod_country";
    @Basic(optional = false)
    @NotNull(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codCountry;
    //
    public static final String PROP_LIB_UPPER = "libUpper";
    public static final String COL_LIB_UPPER = "lib_upper";
    @Basic(optional = true)
    @Column(name = COL_LIB_UPPER)
    private String libUpper;
    //
    public static final String PROP_LIB_LOWER = "libLower";
    public static final String COL_LIB_LOWER = "lib_lower";
    @Basic(optional = true)
    @Column(name = COL_LIB_LOWER)
    private String libLower;
    //
//    public static final String PROP_STATES = "famStateList";
//    @OrderBy(FamState.PROP_LIB + " ASC")
//    @OneToMany(mappedBy = FamState.PROP_COUNTRY, fetch = FetchType.EAGER)
//    private List<FamState> famStateList;
    //
//    public static final String PROP_CLUBS = "famClubList";
//    @OneToMany(mappedBy = FamClub.PROP_COUNTRY)
//    private List<FamClub> famClubList;

    public FamCountry() {
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

        FamCountry that = (FamCountry) o;

        return idCountry.equals(that.idCountry);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCountry.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamCountry");
        sb.append("{idCountry=").append(idCountry);
        sb.append(", libCountry='").append(libCountry).append('\'');
        sb.append(", codCountry='").append(codCountry).append('\'');
        sb.append(", libUpper='").append(libUpper).append('\'');
        sb.append(", libLower='").append(libLower).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
