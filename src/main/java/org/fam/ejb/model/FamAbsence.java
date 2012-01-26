package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.ejb.common.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamAbsence.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamAbsence.findAll", query = "SELECT f FROM FamAbsence f"),
    @NamedQuery(name = "FamAbsence.findByIdAbsence", query = "SELECT f FROM FamAbsence f WHERE f.idAbsence = :idAbsence"),
    @NamedQuery(name = "FamAbsence.findByDtBegAbsence", query = "SELECT f FROM FamAbsence f WHERE f.dtBegAbsence = :dtBegAbsence"),
    @NamedQuery(name = "FamAbsence.findByDtEndAbsence", query = "SELECT f FROM FamAbsence f WHERE f.dtEndAbsence = :dtEndAbsence"),
    @NamedQuery(name = "FamAbsence.findByDtCreat", query = "SELECT f FROM FamAbsence f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamAbsence.findByDtModif", query = "SELECT f FROM FamAbsence f WHERE f.dtModif = :dtModif")})
public class FamAbsence extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_absence";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idAbsence";
    /**
     * 
     */
    public static final String COL_ID = "id_absence";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idAbsence;
    
    @Override
    public Long getId(){
        return this.getIdAbsence();
    }
    //
    /**
     * 
     */
    public static final String PROP_COMMENTS = "comments";
    @Lob
    @Column(name = PROP_COMMENTS)
    private String comments;
    //
    /**
     * 
     */
    public static final String PROP_DT_BEG = "dtBegAbsence";
    public static final String COL_DT_BEG = "dt_beg_absence";
    @Basic(optional = false)
    @Column(name = COL_DT_BEG)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtBegAbsence;
    //
    /**
     * 
     */
    public static final String PROP_DT_END = "dtEndAbsence";
    public static final String COL_DT_END = "dt_end_absence";
    @Basic(optional = false)
    @Column(name = COL_DT_END)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtEndAbsence;
    //
    /**
     * 
     */
    public static final String COL_ID_TYP_ABSENCE = "id_typ_absence";
    /**
     * 
     */
    public static final String PROP_TYP_ABSENCE = "famTypAbsence";
    @JoinColumn(name = COL_ID_TYP_ABSENCE, referencedColumnName = FamTypAbsence.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamTypAbsence famTypAbsence;

    /**
     * 
     */
    public FamAbsence() {
    }

    /**
     * 
     * @param idAbsence
     */
    public FamAbsence(Long idAbsence) {
        this.idAbsence = idAbsence;
    }

    /**
     * 
     * @return
     */
    public Long getIdAbsence() {
        return idAbsence;
    }

    /**
     * 
     * @param idAbsence
     */
    public void setIdAbsence(Long idAbsence) {
        this.idAbsence = idAbsence;
    }

    /**
     * 
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     */
    public Date getDtBegAbsence() {
        return dtBegAbsence;
    }

    /**
     * 
     * @param dtBegAbsence
     */
    public void setDtBegAbsence(Date dtBegAbsence) {
        this.dtBegAbsence = dtBegAbsence;
    }

    /**
     * 
     * @return
     */
    public Date getDtEndAbsence() {
        return dtEndAbsence;
    }

    /**
     * 
     * @param dtEndAbsence
     */
    public void setDtEndAbsence(Date dtEndAbsence) {
        this.dtEndAbsence = dtEndAbsence;
    }

    public FamTypAbsence getFamTypAbsence() {
        return famTypAbsence;
    }

    public void setFamTypAbsence(FamTypAbsence famTypAbsence) {
        this.famTypAbsence = famTypAbsence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idAbsence != null ? idAbsence.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamAbsence)) {
            return false;
        }
        FamAbsence other = (FamAbsence) object;
        if (( this.idAbsence == null && other.idAbsence != null ) || ( this.idAbsence != null && !this.idAbsence.equals(other.idAbsence) )) {
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
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
            catch (IllegalAccessException e) {
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
