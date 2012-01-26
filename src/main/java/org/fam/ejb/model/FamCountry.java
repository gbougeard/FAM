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
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamCountry.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamCountry.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamCountry.findAll", query = "SELECT f FROM FamCountry f ORDER BY f.libUpper ASC"),
    @NamedQuery(name = "FamCountry.findByIdCountry", query = "SELECT f FROM FamCountry f WHERE f.idCountry = :idCountry"),
    @NamedQuery(name = "FamCountry.findByLibCountry", query = "SELECT f FROM FamCountry f WHERE f.libCountry = :libCountry"),
    @NamedQuery(name = "FamCountry.findByCodCountry", query = "SELECT f FROM FamCountry f WHERE f.codCountry = :codCountry"),
    @NamedQuery(name = "FamCountry.findByDtCreat", query = "SELECT f FROM FamCountry f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamCountry.findByDtModif", query = "SELECT f FROM FamCountry f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamCountry extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_country";
    //
    public static final String PROP_ID = "idCountry";
    public static final String COL_ID = "id_country";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(name = COL_ID)
    private Long idCountry;
    
    @Override
    public Long getId(){
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

    public FamCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getLibCountry() {
        return libCountry;
    }

    public void setLibCountry(String libCountry) {
        this.libCountry = libCountry;
    }

    public String getCodCountry() {
        return codCountry;
    }

    public void setCodCountry(String codCountry) {
        this.codCountry = codCountry;
    }

//    public List<FamState> getFamStateList() {
//        return famStateList;
//    }
//
//    public void setFamStateList(List<FamState> famStateList) {
//        this.famStateList = famStateList;
//    }

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

//    public List<FamClub> getFamClubList() {
//        return famClubList;
//    }
//
//    public void setFamClubList(List<FamClub> famClubList) {
//        this.famClubList = famClubList;
//    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCountry != null ? idCountry.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamCountry)) {
            return false;
        }
        FamCountry other = (FamCountry) object;
        if (( this.idCountry == null && other.idCountry != null ) || ( this.idCountry != null && !this.idCountry.equals(other.idCountry) )) {
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
//            if ((f.getName().equals(PROP_STATES) == false) ){
//            && (f.getName().equals(PROP_CLUBS) == false)){
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
//        }
        builder.append("\n]");
        return builder.toString();
    }
}
