package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Entity
@Table(name = FamFormationItem.TABLE_NAME //,uniqueConstraints = {
//    @UniqueConstraint(columnNames = {FamFormationItem.PROP_ID_FORMATION,
//        FamFormationItem.PROP_NUM})
//}
)
@NamedQueries({
    @NamedQuery(name = "FamFormationItem.findAll", query = "SELECT f FROM FamFormationItem f"),
    @NamedQuery(name = "FamFormationItem.findByIdFormationItem", query = "SELECT f FROM FamFormationItem f WHERE f.idFormationItem = :idFormationItem"),
    @NamedQuery(name = "FamFormationItem.findByFormation", query = "SELECT f FROM FamFormationItem f WHERE f.famFormation = :famFormation"),
    @NamedQuery(name = "FamFormationItem.findByDtCreat", query = "SELECT f FROM FamFormationItem f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamFormationItem.findByDtModif", query = "SELECT f FROM FamFormationItem f WHERE f.dtModif = :dtModif")})
public class FamFormationItem extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_formation_item";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idFormationItem";
    /**
     * 
     */
    public static final String COL_ID = "id_formation_item";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idFormationItem;
    //
    /**
     * 
     */
    public static final String PROP_NUM = "numItem";
    public static final String COL_NUM = "num_item";
    @Basic(optional = false)
    @NotNull(message = "Numero obligatoire")
    @Column(name = COL_NUM)
    private Integer numItem;
    //
    /**
     * 
     */
    public static final String PROP_COORD = "coord";
    @Basic(optional = false)
    @Column(name = PROP_COORD)
    private Integer coord;
    //
    /**
     * 
     */
    public static final String COL_ID_FORMATION = "id_formation";
    /**
     * 
     */
    public static final String PROP_FORMATION = "famFormation";
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = COL_ID_FORMATION)
    private FamFormation famFormation;

    /**
     *
     */
    public FamFormationItem() {
    }

    /**
     *
     * @param idFormationItem
     */
    public FamFormationItem(Long idFormationItem) {
        this.idFormationItem = idFormationItem;
    }

    /**
     *
     * @return
     */
    public Long getIdFormationItem() {
        return idFormationItem;
    }

    /**
     *
     * @param idFormationItem
     */
    public void setIdFormationItem(Long idFormationItem) {
        this.idFormationItem = idFormationItem;
    }

    /**
     *
     * @return
     */
    public FamFormation getFamFormation() {
        return famFormation;
    }

    /**
     *
     * @param famFormation
     */
    public void setFamFormation(FamFormation famFormation) {
        this.famFormation = famFormation;
    }

    /**
     * 
     * @return
     */
    public Integer getNumItem() {
        return numItem;
    }

    /**
     * 
     * @param numItem
     */
    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
    }

    /**
     * 
     * @return
     */
    public Integer getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     */
    public void setCoord(Integer coord) {
        this.coord = coord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idFormationItem != null ? idFormationItem.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamFormationItem)) {
            return false;
        }
        FamFormationItem other = (FamFormationItem) object;
        if (( this.idFormationItem == null && other.idFormationItem != null ) || ( this.idFormationItem != null && !this.idFormationItem.equals(other.idFormationItem) )) {
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
                if (f.getName().equals(PROP_FORMATION)){
                    if (this.getFamFormation() == null){
                        builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append("null");
                    } else {
                        builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(this.getFamFormation().getLibFormation());
                    }
                } else {
                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
                }
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
