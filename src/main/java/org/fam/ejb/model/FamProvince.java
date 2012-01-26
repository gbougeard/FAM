package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;
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
@Table(name = FamProvince.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamProvince.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamProvince.findAll", query = "SELECT f FROM FamProvince f ORDER BY f.codProvince"),
    @NamedQuery(name = "FamProvince.findByIdProvince", query = "SELECT f FROM FamProvince f WHERE f.idProvince = :idProvince"),
    @NamedQuery(name = "FamProvince.findByLibProvince", query = "SELECT f FROM FamProvince f WHERE f.libProvince = :libProvince"),
    @NamedQuery(name = "FamProvince.findByCodProvince", query = "SELECT f FROM FamProvince f WHERE f.codProvince = :codProvince"),
    @NamedQuery(name = "FamProvince.findByDtCreat", query = "SELECT f FROM FamProvince f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamProvince.findByDtModif", query = "SELECT f FROM FamProvince f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamProvince extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_province";
    //
    public static final String PROP_ID = "idProvince";
    public static final String COL_ID = "id_province";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idProvince;
    
    @Override
    public Long getId(){
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

    public FamProvince(Long idProvince) {
        this.idProvince = idProvince;
    }

    public Long getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Long idProvince) {
        this.idProvince = idProvince;
    }

    public String getLibProvince() {
        return libProvince;
    }

    public void setLibProvince(String libProvince) {
        this.libProvince = libProvince;
    }

    public String getCodProvince() {
        return codProvince;
    }

    public void setCodProvince(String codProvince) {
        this.codProvince = codProvince;
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

    public FamState getFamState() {
        return famState;
    }

    public void setFamState(FamState famState) {
        this.famState = famState;
    }

//    public List<FamCity> getFamCityList() {
//        return famCityList;
//    }
//
//    public void setFamCityList(List<FamCity> famCityList) {
//        this.famCityList = famCityList;
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
        hash += ( idProvince != null ? idProvince.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamProvince)) {
            return false;
        }
        FamProvince other = (FamProvince) object;
        if (( this.idProvince == null && other.idProvince != null ) || ( this.idProvince != null && !this.idProvince.equals(other.idProvince) )) {
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
//            if (( f.getName().equals(PROP_CITIES) == false )
//                    && 
                 if   (( f.getName().equals(PROP_CLUBS) == false )) {
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
