package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Entity
@Table(name = FamFormation.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamFormation.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamFormation.findAll", query = "SELECT f FROM FamFormation f"),
    @NamedQuery(name = "FamFormation.findByIdFormation", query = "SELECT f FROM FamFormation f WHERE f.idFormation = :idFormation"),
    @NamedQuery(name = "FamFormation.findByLibFormation", query = "SELECT f FROM FamFormation f WHERE f.libFormation = :libFormation"),
    @NamedQuery(name = "FamFormation.findByDtCreat", query = "SELECT f FROM FamFormation f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamFormation.findByDtModif", query = "SELECT f FROM FamFormation f WHERE f.dtModif = :dtModif")})
public class FamFormation extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_formation";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idFormation";
    /**
     * 
     */
    public static final String COL_ID = "id_formation";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idFormation;
    
    @Override
    public Long getId(){
        return this.getIdFormation();
    }
    
    /**
     * 
     */
    public static final String PROP_LIB = "libFormation";
    public static final String COL_LIB = "lib_formation";
    @Basic(optional = false)
    @NotEmpty(message = "Libelle obligatoire")
    @Size(max = 30)
    @Column(name = COL_LIB)
    private String libFormation;
    //
    /**
     * 
     */
    public static final String PROP_COD = "codFormation";
    public static final String COL_COD = "cod_formation";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    @Size(max = 3)
    private String codFormation;
    //
    /**
     * 
     */
    public static final String PROP_DEFAULT = "byDefault";
    @Basic(optional = false)
    @Column(name = PROP_DEFAULT)
    private Boolean byDefault;
    //
    /**
     * 
     */
    public static final String COL_ID_TYP_MATCH = "id_typ_match";
    /**
     * 
     */
    public static final String PROP_TYP_MATCH = "famTypMatch";
    @JoinColumn(name = COL_ID_TYP_MATCH, referencedColumnName = FamTypMatch.COL_ID)
    @ManyToOne//(optional = false)
    @NotNull
    private FamTypMatch famTypMatch;
    //
    @OneToMany(cascade = CascadeType.ALL, mappedBy = FamFormationItem.PROP_FORMATION)
    @OrderBy(value=FamFormationItem.PROP_NUM)
    private List<FamFormationItem> famFormationItemList;

    /**
     *
     */
    public FamFormation() {
    }

    /**
     *
     * @param idFormation
     */
    public FamFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    /**
     *
     * @return
     */
    public Long getIdFormation() {
        return idFormation;
    }

    /**
     *
     * @param idFormation
     */
    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    /**
     *
     * @return
     */
    public String getLibFormation() {
        return libFormation;
    }

    /**
     *
     * @param libFormation
     */
    public void setLibFormation(String libFormation) {
        this.libFormation = libFormation;
    }

    /**
     *
     * @return
     */
    public Boolean getByDefault() {
        return byDefault;
    }

    /**
     *
     * @param byDefault
     */
    public void setByDefault(Boolean byDefault) {
        this.byDefault = byDefault;
    }

    /**
     *
     * @return
     */
    public List<FamFormationItem> getFamFormationItemList() {
        return famFormationItemList;
    }

    /**
     *
     * @param famFormationItemList
     */
    public void setFamFormationItemList(List<FamFormationItem> famFormationItemList) {
        this.famFormationItemList = famFormationItemList;
    }

    /**
     *
     * @return
     */
    public String getCodFormation() {
        return codFormation;
    }

    /**
     *
     * @param codeFormation
     */
    public void setCodFormation(String codeFormation) {
        this.codFormation = codeFormation;
    }

    /**
     *
     * @return
     */
    public FamTypMatch getFamTypMatch() {
        return famTypMatch;
    }

    /**
     *
     * @param famTypMatch
     */
    public void setFamTypMatch(FamTypMatch famTypMatch) {
        this.famTypMatch = famTypMatch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idFormation != null ? idFormation.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamFormation)) {
            return false;
        }
        FamFormation other = (FamFormation) object;
        if (( this.idFormation == null && other.idFormation != null ) || ( this.idFormation != null && !this.idFormation.equals(other.idFormation) )) {
            return false;
        }
        return true;
    }

    @PrePersist
    void prePersist() {
        // Ajout du gardien par défaut
        famFormationItemList = new ArrayList<FamFormationItem>();
        for (int i = 1; i <= 11; i++) {
            FamFormationItem itemG = new FamFormationItem();
            itemG.setNumItem(i);
            itemG.setCoord(i);
            famFormationItemList.add(itemG);
        }
    }

    @PostPersist
    void postPerssist() {
        for (FamFormationItem item : famFormationItemList) {
            item.setFamFormation(this);
        }
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
