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
@Table(name = FamCity.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamCity.findAll", query = "SELECT f FROM FamCity f ORDER BY f.libUpper"),
        @NamedQuery(name = "FamCity.findByIdCity", query = "SELECT f FROM FamCity f WHERE f.idCity = :idCity"),
        @NamedQuery(name = "FamCity.findByLibCity", query = "SELECT f FROM FamCity f WHERE f.libCity = :libCity"),
        @NamedQuery(name = "FamCity.findByCodCity", query = "SELECT f FROM FamCity f WHERE f.codCity = :codCity"),
        @NamedQuery(name = "FamCity.findByDtCreat", query = "SELECT f FROM FamCity f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamCity.findByDtModif", query = "SELECT f FROM FamCity f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamCity extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_city";
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
    public static final String PROP_CLUBS = "famClubList";
    @OneToMany(mappedBy = FamClub.PROP_CITY)
    private List<FamClub> famClubList;

    public FamCity() {
    }

    public FamCity(Long idCity) {
        this.idCity = idCity;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getLibCity() {
        return libCity;
    }

    public void setLibCity(String libCity) {
        this.libCity = libCity;
    }

    public String getCodCity() {
        return codCity;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
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

    public FamProvince getFamProvince() {
        return famProvince;
    }

    public void setFamProvince(FamProvince famProvince) {
        this.famProvince = famProvince;
    }

    public List<FamClub> getFamClubList() {
        return famClubList;
    }

    public void setFamClubList(List<FamClub> famClubList) {
        this.famClubList = famClubList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCity != null ? idCity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamCity)) {
            return false;
        }
        FamCity other = (FamCity) object;
        if ((this.idCity == null && other.idCity != null) || (this.idCity != null && !this.idCity.equals(other.idCity))) {
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
            if ((f.getName().equals(PROP_PROVINCE) == false)
                    && (f.getName().equals(PROP_CLUBS) == false)) {
                try {
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                } catch (IllegalArgumentException e) {
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                } catch (IllegalAccessException e) {
                    LogUtil.log("Erreur!", Level.SEVERE, e);
                }
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
