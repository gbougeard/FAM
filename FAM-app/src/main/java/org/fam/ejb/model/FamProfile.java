package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamProfile.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamProfile.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamProfile.findAll", query = "SELECT f FROM FamProfile f"),
        @NamedQuery(name = "FamProfile.findByIdProfile", query = "SELECT f FROM FamProfile f WHERE f.idProfile = :idProfile"),
        @NamedQuery(name = "FamProfile.findByLibProfile", query = "SELECT f FROM FamProfile f WHERE f.libProfile = :libProfile"),
        @NamedQuery(name = "FamProfile.findByDtCreat", query = "SELECT f FROM FamProfile f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamProfile.findByDtModif", query = "SELECT f FROM FamProfile f WHERE f.dtModif = :dtModif")})
public class FamProfile extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_profile";
    //
    /**
     *
     */
    public static final String PROP_ID = "idProfile";
    /**
     *
     */
    public static final String COL_ID = "id_profile";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idProfile;
    //
    /**
     *
     */
    public static final String PROP_LIB = "libProfile";
    public static final String COL_LIB = "lib_profile";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libProfile;
    //
    /**
     *
     */
    public static final String PROP_COD = "codProfile";
    public static final String COL_COD = "cod_profile";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codProfile;

    /**
     *
     */
    public FamProfile() {
    }

    /**
     * @param idProfile
     */
    public FamProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    /**
     * @return
     */
    public Long getIdProfile() {
        return idProfile;
    }

    /**
     * @param idProfile
     */
    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    /**
     * @return
     */
    public String getLibProfile() {
        return libProfile;
    }

    /**
     * @param libProfile
     */
    public void setLibProfile(String libProfile) {
        this.libProfile = libProfile;
    }

    /**
     * @return
     */
    public String getCodProfile() {
        return codProfile;
    }

    /**
     * @param codProfile
     */
    public void setCodProfile(String codProfile) {
        this.codProfile = codProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfile != null ? idProfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamProfile)) {
            return false;
        }
        FamProfile other = (FamProfile) object;
        if ((this.idProfile == null && other.idProfile != null) || (this.idProfile != null && !this.idProfile.equals(other.idProfile))) {
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
            try {
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
