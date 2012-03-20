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
 * @author gbougear
 */
@Entity
@Table(name = FamTypPlace.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTypPlace.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamTypPlace.findAll", query = "SELECT f FROM FamTypPlace f"),
        @NamedQuery(name = "FamTypPlace.findByIdTypPlace", query = "SELECT f FROM FamTypPlace f WHERE f.idTypPlace = :idTypPlace"),
        @NamedQuery(name = "FamTypPlace.findByLibTypPlace", query = "SELECT f FROM FamTypPlace f WHERE f.libTypPlace = :libTypPlace"),
        @NamedQuery(name = "FamTypPlace.findByDtCreat", query = "SELECT f FROM FamTypPlace f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypPlace.findByDtModif", query = "SELECT f FROM FamTypPlace f WHERE f.dtModif = :dtModif")})
public class FamTypPlace extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_place";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypPlace";
    /**
     *
     */
    public static final String COL_ID = "id_typ_place";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypPlace;

    @Override
    public Long getId() {
        return this.getIdTypPlace();
    }

    /**
     *
     */
    public static final String COL_LIB = "lib_typ_pPlace";
    public static final String PROP_LIB = "libTypPlace";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libTypPlace;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypPlace";
    public static final String COL_COD = "cod_typ_place";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codTypPlace;
    //
    public static final String PROP_PLACES = "famPlaceList";
    @OneToMany(mappedBy = FamPlace.PROP_TYP)
    private List<FamPlace> famPlaceList;

    /**
     *
     */
    public FamTypPlace() {
    }

    /**
     * @param idTypPlace
     */
    public FamTypPlace(Long idTypPlace) {
        this.idTypPlace = idTypPlace;
    }

    /**
     * @return
     */
    public Long getIdTypPlace() {
        return idTypPlace;
    }

    /**
     * @param idTypPlace
     */
    public void setIdTypPlace(Long idTypPlace) {
        this.idTypPlace = idTypPlace;
    }

    /**
     * @return
     */
    public String getLibTypPlace() {
        return libTypPlace;
    }

    /**
     * @param libTypPlace
     */
    public void setLibTypPlace(String libTypPlace) {
        this.libTypPlace = libTypPlace;
    }

    /**
     * @return
     */
    public String getCodTypPlace() {
        return codTypPlace;
    }

    /**
     * @param codTypPlace
     */
    public void setCodTypPlace(String codTypPlace) {
        this.codTypPlace = codTypPlace;
    }

    /**
     * @return
     */
    public List<FamPlace> getFamPlaceList() {
        return famPlaceList;
    }

    /**
     * @param famPlaceList
     */
    public void setFamPlaceList(List<FamPlace> famPlaceList) {
        this.famPlaceList = famPlaceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypPlace != null ? idTypPlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamTypPlace)) {
            return false;
        }
        FamTypPlace other = (FamTypPlace) object;
        if ((this.idTypPlace == null && other.idTypPlace != null) || (this.idTypPlace != null && !this.idTypPlace.equals(other.idTypPlace))) {
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
