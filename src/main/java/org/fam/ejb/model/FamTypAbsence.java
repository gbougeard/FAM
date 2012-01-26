package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamTypAbsence.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamTypAbsence.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamTypAbsence.findAll", query = "SELECT f FROM FamTypAbsence f"),
    @NamedQuery(name = "FamTypAbsence.findByIdTypAbsence", query = "SELECT f FROM FamTypAbsence f WHERE f.idTypAbsence = :idTypAbsence"),
    @NamedQuery(name = "FamTypAbsence.findByLibTypAbsence", query = "SELECT f FROM FamTypAbsence f WHERE f.libTypAbsence = :libTypAbsence"),
    @NamedQuery(name = "FamTypAbsence.findByDtCreat", query = "SELECT f FROM FamTypAbsence f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamTypAbsence.findByDtModif", query = "SELECT f FROM FamTypAbsence f WHERE f.dtModif = :dtModif")})
public class FamTypAbsence extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_absence";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypAbsence";
    /**
     *
     */
    public static final String COL_ID = "id_typ_absence";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypAbsence;

    @Override
    public Long getId() {
        return this.getIdTypAbsence();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypAbsence";
    public static final String COL_LIB = "lib_typ_absence";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libTypAbsence;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypAbsence";
    public static final String COL_COD = "cod_typ_absence";
    @Basic(optional = false)
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypAbsence;
    //
    public static final String PROP_ABSENCES = "famAbsenceList";
    @OneToMany(mappedBy = FamAbsence.PROP_TYP_ABSENCE)
    private List<FamAbsence> famAbsenceList;

    /**
     *
     */
    public FamTypAbsence() {
    }

    /**
     *
     * @param idTypAbsence
     */
    public FamTypAbsence(Long idTypAbsence) {
        this.idTypAbsence = idTypAbsence;
    }

    /**
     *
     * @return
     */
    public Long getIdTypAbsence() {
        return idTypAbsence;
    }

    /**
     *
     * @param idTypAbsence
     */
    public void setIdTypAbsence(Long idTypAbsence) {
        this.idTypAbsence = idTypAbsence;
    }

    /**
     *
     * @return
     */
    public String getLibTypAbsence() {
        return libTypAbsence;
    }

    /**
     *
     * @param libTypAbsence
     */
    public void setLibTypAbsence(String libTypAbsence) {
        this.libTypAbsence = libTypAbsence;
    }

    /**
     *
     * @return
     */
    public String getCodTypAbsence() {
        return codTypAbsence;
    }

    /**
     *
     * @param codTypAbsence
     */
    public void setCodTypAbsence(String codTypAbsence) {
        this.codTypAbsence = codTypAbsence;
    }

    /**
     *
     * @return
     */
    public List<FamAbsence> getFamAbsenceList() {
        return famAbsenceList;
    }

    /**
     * 
     * @param famAbsenceList
     */
    public void setFamAbsenceList(List<FamAbsence> famAbsenceList) {
        this.famAbsenceList = famAbsenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTypAbsence != null ? idTypAbsence.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamTypAbsence)) {
            return false;
        }
        FamTypAbsence other = (FamTypAbsence) object;
        if (( this.idTypAbsence == null && other.idTypAbsence != null ) || ( this.idTypAbsence != null && !this.idTypAbsence.equals(other.idTypAbsence) )) {
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
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
