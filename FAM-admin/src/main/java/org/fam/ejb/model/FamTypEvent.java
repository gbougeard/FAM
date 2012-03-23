package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamTypEvent.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTypEvent.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamTypEvent.findAll", query = "SELECT f FROM FamTypEvent f"),
        @NamedQuery(name = "FamTypEvent.findByIdTypEvent", query = "SELECT f FROM FamTypEvent f WHERE f.idTypEvent = :idTypEvent"),
        @NamedQuery(name = "FamTypEvent.findByLibTypEvent", query = "SELECT f FROM FamTypEvent f WHERE f.libTypEvent = :libTypEvent"),
        @NamedQuery(name = "FamTypEvent.findByCodTypEvent", query = "SELECT f FROM FamTypEvent f WHERE f.codTypEvent = :codTypEvent"),
        @NamedQuery(name = "FamTypEvent.findByDtCreat", query = "SELECT f FROM FamTypEvent f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypEvent.findByDtModif", query = "SELECT f FROM FamTypEvent f WHERE f.dtModif = :dtModif")})
public class FamTypEvent extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_event";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypEvent";
    /**
     *
     */
    public static final String COL_ID = "id_typ_event";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypEvent;

    @Override
    public Long getId() {
        return this.getIdTypEvent();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypEvent";
    public static final String COL_LIB = "lib_typ_event";
    @Column(name = COL_LIB)
    @NotEmpty
    private String libTypEvent;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypEvent";
    public static final String COL_COD = "cod_typ_event";
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypEvent;
    //
    public static final String PROP_EVENTS = "famEventList";
    @OneToMany(mappedBy = FamEvent.PROP_TYP)
    private List<FamEvent> famEventList;

    /**
     *
     */
    public FamTypEvent() {
    }

    /**
     * @param idTypEvent
     */
    public FamTypEvent(Long idTypEvent) {
        this.idTypEvent = idTypEvent;
    }

    /**
     * @return
     */
    public Long getIdTypEvent() {
        return idTypEvent;
    }

    /**
     * @param idTypEvent
     */
    public void setIdTypEvent(Long idTypEvent) {
        this.idTypEvent = idTypEvent;
    }

    /**
     * @return
     */
    public String getLibTypEvent() {
        return libTypEvent;
    }

    /**
     * @param libTypEvent
     */
    public void setLibTypEvent(String libTypEvent) {
        this.libTypEvent = libTypEvent;
    }

    /**
     * @return
     */
    public String getCodTypEvent() {
        return codTypEvent;
    }

    /**
     * @param codTypEvent
     */
    public void setCodTypEvent(String codTypEvent) {
        this.codTypEvent = codTypEvent;
    }

    /**
     * @return
     */
    public List<FamEvent> getFamEventList() {
        return famEventList;
    }

    /**
     * @param famEventList
     */
    public void setFamEventList(List<FamEvent> famEventList) {
        this.famEventList = famEventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypEvent != null ? idTypEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamTypEvent)) {
            return false;
        }
        FamTypEvent other = (FamTypEvent) object;
        if ((this.idTypEvent == null && other.idTypEvent != null) || (this.idTypEvent != null && !this.idTypEvent.equals(other.idTypEvent))) {
            return false;
        }
        return true;
    }

}
