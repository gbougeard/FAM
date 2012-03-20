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
import java.util.List;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamProvince.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamProvince.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamProvince.FIND_ALL,
                query = "SELECT f FROM FamProvince f ORDER BY f.codProvince"),
        @NamedQuery(name = FamProvince.FIND_BY_ID_PROVINCE,
                query = "SELECT f FROM FamProvince f WHERE f.idProvince = :idProvince"),
        @NamedQuery(name = FamProvince.FIND_BY_LIB_PROVINCE,
                query = "SELECT f FROM FamProvince f WHERE f.libProvince = :libProvince"),
        @NamedQuery(name = FamProvince.FIND_BY_COD_PROVINCE,
                query = "SELECT f FROM FamProvince f WHERE f.codProvince = :codProvince"),
        @NamedQuery(name = FamProvince.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamProvince f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamProvince.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamProvince f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamProvince extends FamEntity implements Serializable {

    private static final long serialVersionUID = -6069022001817094462L;
    public static final String TABLE_NAME = "fam_province";
    //
    public static final String FIND_ALL = "FamProvince.findAll";
    public static final String FIND_BY_ID_PROVINCE = "FamProvince.findByIdProvince";
    public static final String FIND_BY_LIB_PROVINCE = "FamProvince.findByLibProvince";
    public static final String FIND_BY_COD_PROVINCE = "FamProvince.findByCodProvince";
    public static final String FIND_BY_DT_CREAT = "FamProvince.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamProvince.findByDtModif";
    //
    public static final String PROP_ID = "idProvince";
    public static final String COL_ID = "id_province";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idProvince;

    @Override
    public Long getId() {
        return this.getIdProvince();
    }

    public static final String PROP_LIB = "libProvince";
    public static final String COL_LIB = "lib_province";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libProvince;
    //
    public static final String PROP_COD = "codProvince";
    public static final String COL_COD = "cod_province";
    @Basic(optional = false)
    @NotNull(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codProvince;
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
    /**
     *
     */
    public static final String COL_ID_STATE = "id_state";
    public static final String PROP_STATE = "famState";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_STATE, referencedColumnName = FamState.COL_ID)
    private FamState famState;
    //
//    public static final String PROP_CITIES = "famCityList";
//    @OneToMany(mappedBy = FamCity.PROP_PROVINCE, fetch = FetchType.EAGER)
//    @OrderBy(FamCity.PROP_LIB + " ASC")
//    private List<FamCity> famCityList;
    //
    public static final String PROP_CLUBS = "famClubList";
    @OneToMany(mappedBy = FamClub.PROP_PROVINCE)
    private List<FamClub> famClubList;

    public FamProvince() {
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

        FamProvince that = (FamProvince) o;

        return idProvince.equals(that.idProvince);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idProvince.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamProvince");
        sb.append("{idProvince=").append(idProvince);
        sb.append(", libProvince='").append(libProvince).append('\'');
        sb.append(", codProvince='").append(codProvince).append('\'');
        sb.append(", libUpper='").append(libUpper).append('\'');
        sb.append(", libLower='").append(libLower).append('\'');
        sb.append(", famState=").append(famState);
//        sb.append(", famClubList=").append(famClubList);
        sb.append('}');
        return sb.toString();
    }
}
