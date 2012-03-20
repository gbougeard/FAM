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
@Table(name = FamCity.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamCity.FIND_ALL,
                query = "SELECT f FROM FamCity f ORDER BY f.libUpper"),
        @NamedQuery(name = FamCity.FIND_BY_ID_CITY,
                query = "SELECT f FROM FamCity f WHERE f.idCity = :idCity"),
        @NamedQuery(name = FamCity.FIND_BY_LIB_CITY,
                query = "SELECT f FROM FamCity f WHERE f.libCity = :libCity"),
        @NamedQuery(name = FamCity.FIND_BY_COD_CITY,
                query = "SELECT f FROM FamCity f WHERE f.codCity = :codCity"),
        @NamedQuery(name = FamCity.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamCity f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamCity.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamCity f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamCity extends FamEntity implements Serializable {

    private static final long serialVersionUID = -104103917154788254L;
    public static final String TABLE_NAME = "fam_city";
    //
    public static final String FIND_ALL = "FamCity.findAll";
    public static final String FIND_BY_ID_CITY = "FamCity.findByIdCity";
    public static final String FIND_BY_LIB_CITY = "FamCity.findByLibCity";
    public static final String FIND_BY_COD_CITY = "FamCity.findByCodCity";
    public static final String FIND_BY_DT_CREAT = "FamCity.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamCity.findByDtModif";
    //
    public static final String PROP_ID = "idCity";
    public static final String COL_ID = "id_city";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idCity;

    @Override
    public Long getId() {
        return this.getIdCity();
    }

    //
    public static final String PROP_LIB = "libCity";
    public static final String COL_LIB = "lib_city";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libCity;
    //
    public static final String PROP_COD = "codCity";
    public static final String COL_COD = "cod_city";
    @Basic(optional = false)
    @NotNull(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codCity;
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
    public static final String COL_ID_PROVINCE = "id_province";
    public static final String PROP_PROVINCE = "famProvince";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_PROVINCE, referencedColumnName = FamProvince.COL_ID)
    private FamProvince famProvince;
    //
    /*public static final String PROP_CLUBS = "famClubList";
    @OneToMany(mappedBy = FamClub.PROP_CITY)
    private List<FamClub> famClubList;*/

    public FamCity() {
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

        FamCity famCity = (FamCity) o;

        return idCity.equals(famCity.idCity);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamCity");
        sb.append("{idCity=").append(idCity);
        sb.append(", libCity='").append(libCity).append('\'');
        sb.append(", codCity='").append(codCity).append('\'');
        sb.append(", libUpper='").append(libUpper).append('\'');
        sb.append(", libLower='").append(libLower).append('\'');
        sb.append(", famProvince=").append(famProvince.getLibProvince());
        sb.append('}');
        return sb.toString();
    }
}
