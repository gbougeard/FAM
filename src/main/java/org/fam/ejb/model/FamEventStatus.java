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
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Entity
@Table(name = FamEventStatus.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamEventStatus.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamEventStatus.findAll", query = "SELECT f FROM FamEventStatus f"),
    @NamedQuery(name = "FamEventStatus.findByIdEventStatus", query = "SELECT f FROM FamEventStatus f WHERE f.idEventStatus = :idEventStatus"),
    @NamedQuery(name = "FamEventStatus.findByLibEventStatus", query = "SELECT f FROM FamEventStatus f WHERE f.libEventStatus = :libEventStatus"),
    @NamedQuery(name = "FamEventStatus.findByCodEventStatus", query = "SELECT f FROM FamEventStatus f WHERE f.codEventStatus = :codEventStatus"),
    @NamedQuery(name = "FamEventStatus.findByDtCreat", query = "SELECT f FROM FamEventStatus f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamEventStatus.findByDtModif", query = "SELECT f FROM FamEventStatus f WHERE f.dtModif = :dtModif")})
public class FamEventStatus extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_event_status";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idEventStatus";
    /**
     * 
     */
    public static final String COL_ID = "id_event_status";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = COL_ID)
    private Long idEventStatus;

    @Override
    public Long getId() {
        return this.getIdEventStatus();
    }
    /**
     * 
     */
    public static final String PROP_LIB = "libEventStatus";
    public static final String COL_LIB = "lib_event_status";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libEventStatus;
    //
    /**
     * 
     */
    public static final String PROP_COD = "codEventStatus";
    public static final String COL_COD = "cod_event_status";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codEventStatus;
    //
    public static final String PROP_EVENTS = "famEventList";
    @OneToMany(mappedBy = FamEvent.PROP_STATUS)
    private List<FamEvent> famEventList;

    /**
     * 
     */
    public FamEventStatus() {
    }

    /**
     * 
     * @param idEventStatus
     */
    public FamEventStatus(Long idEventStatus) {
        this.idEventStatus = idEventStatus;
    }

    /**
     * 
     * @return
     */
    public Long getIdEventStatus() {
        return idEventStatus;
    }

    /**
     * 
     * @param idEventStatus
     */
    public void setIdEventStatus(Long idEventStatus) {
        this.idEventStatus = idEventStatus;
    }

    /**
     * 
     * @return
     */
    public String getLibEventStatus() {
        return libEventStatus;
    }

    /**
     * 
     * @param libEventStatus
     */
    public void setLibEventStatus(String libEventStatus) {
        this.libEventStatus = libEventStatus;
    }

    /**
     * 
     * @return
     */
    public String getCodEventStatus() {
        return codEventStatus;
    }

    /**
     * 
     * @param codEventStatus
     */
    public void setCodEventStatus(String codEventStatus) {
        this.codEventStatus = codEventStatus;
    }

    /**
     * 
     * @return
     */
    public List<FamEvent> getFamEventList() {
        return famEventList;
    }

    /**
     * 
     * @param famEventList
     */
    public void setFamEventList(List<FamEvent> famEventList) {
        this.famEventList = famEventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idEventStatus != null ? idEventStatus.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamEventStatus)) {
            return false;
        }
        FamEventStatus other = (FamEventStatus) object;
        if (( this.idEventStatus == null && other.idEventStatus != null ) || ( this.idEventStatus != null && !this.idEventStatus.equals(other.idEventStatus) )) {
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
