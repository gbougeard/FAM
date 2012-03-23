package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamState.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamState.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamState.findAll", query = "SELECT f FROM FamState f ORDER BY f.libUpper"),
        @NamedQuery(name = "FamState.findByIdState", query = "SELECT f FROM FamState f WHERE f.idState = :idState"),
        @NamedQuery(name = "FamState.findByLibState", query = "SELECT f FROM FamState f WHERE f.libState = :libState"),
        @NamedQuery(name = "FamState.findByCodState", query = "SELECT f FROM FamState f WHERE f.codState = :codState"),
        @NamedQuery(name = "FamState.findByDtCreat", query = "SELECT f FROM FamState f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamState.findByDtModif", query = "SELECT f FROM FamState f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamState extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_state";
    //
    public static final String PROP_ID = "idState";
    public static final String COL_ID = "id_state";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idState;

    @Override
    public Long getId() {
        return this.getIdState();
    }


    //
    public static final String PROP_LIB = "libState";
    public static final String COL_LIB = "lib_state";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libState;
    //
    public static final String PROP_COD = "codState";
    public static final String COL_COD = "cod_state";
    @Basic(optional = false)
    @NotNull(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codState;
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
    public static final String COL_ID_COUNTRY = "id_country";
    public static final String PROP_COUNTRY = "famCountry";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_COUNTRY, referencedColumnName = FamCountry.COL_ID)
    private FamCountry famCountry;
    //
//    public static final String PROP_PROVINCES = "famProvinceList";
//    @OneToMany(mappedBy = FamProvince.PROP_STATE, fetch = FetchType.EAGER)
//    @OrderBy(FamProvince.PROP_COD + " ASC")
//    private List<FamProvince> famProvinceList;
    //
    public static final String PROP_CLUBS = "famClubList";
    @OneToMany(mappedBy = FamClub.PROP_STATE)
    private List<FamClub> famClubList;

    public FamState() {
    }

    public FamState(Long idState) {
        this.idState = idState;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public String getLibState() {
        return libState;
    }

    public void setLibState(String libState) {
        this.libState = libState;
    }

    public String getCodState() {
        return codState;
    }

    public void setCodState(String codState) {
        this.codState = codState;
    }

    public String getLibLower() {
        return libLower;
    }

    public void setLibLower(String libLower) {
        this.libLower = libLower;
    }

    public String getLibUpper() {
        return libUpper;
    }

    public void setLibUpper(String libUpper) {
        this.libUpper = libUpper;
    }

    public FamCountry getFamCountry() {
        return famCountry;
    }

    public void setFamCountry(FamCountry famCountry) {
        this.famCountry = famCountry;
    }

//    public List<FamProvince> getFamProvinceList() {
//        return famProvinceList;
//    }
//
//    public void setFamProvinceList(List<FamProvince> famProvinceList) {
//        this.famProvinceList = famProvinceList;
//    }

    public List<FamClub> getFamClubList() {
        return famClubList;
    }

    public void setFamClubList(List<FamClub> famClubList) {
        this.famClubList = famClubList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idState != null ? idState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamState)) {
            return false;
        }
        FamState other = (FamState) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState))) {
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
//            if (( f.getName().equals(PROP_PROVINCES) == false )
//                    && 
            if ((f.getName().equals(PROP_CLUBS) == false)) {
                try {
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                } catch (IllegalArgumentException e) {

                } catch (IllegalAccessException e) {

                }
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
