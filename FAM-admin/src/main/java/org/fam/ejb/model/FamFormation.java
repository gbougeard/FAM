package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamFormation.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamFormation.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamFormation.FIND_ALL,
                query = "SELECT f FROM FamFormation f"),
        @NamedQuery(name = FamFormation.FIND_BY_ID_FORMATION,
                query = "SELECT f FROM FamFormation f WHERE f.idFormation = :idFormation"),
        @NamedQuery(name = FamFormation.FIND_BY_LIB_FORMATION,
                query = "SELECT f FROM FamFormation f WHERE f.libFormation = :libFormation"),
        @NamedQuery(name = FamFormation.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamFormation f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamFormation.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamFormation f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamFormation extends FamEntity implements Serializable {

    private static final long serialVersionUID = 4691531573158264565L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_formation";
    //
    public static final String FIND_ALL = "FamFormation.findAll";
    public static final String FIND_BY_ID_FORMATION = "FamFormation.findByIdFormation";
    public static final String FIND_BY_LIB_FORMATION = "FamFormation.findByLibFormation";
    public static final String FIND_BY_DT_CREAT = "FamFormation.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamFormation.findByDtModif";
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
    public Long getId() {
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
    @OrderBy(value = FamFormationItem.PROP_NUM)
    private List<FamFormationItem> famFormationItemList;

    /**
     *
     */
    public FamFormation() {
    }


    @PrePersist
    void prePersist() {
        // Ajout du gardien par défaut
        famFormationItemList = new ArrayList<FamFormationItem>();
        for (int i = 1;
             i <= 11;
             i++) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FamFormation that = (FamFormation) o;

        if (!codFormation.equals(that.codFormation)) {
            return false;
        }
        if (idFormation != null ? !idFormation.equals(that.idFormation) : that.idFormation != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idFormation != null ? idFormation.hashCode() : 0);
        result = 31 * result + codFormation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamFormation");
        sb.append("{idFormation=").append(idFormation);
        sb.append(", libFormation='").append(libFormation).append('\'');
        sb.append(", codFormation='").append(codFormation).append('\'');
        sb.append(", byDefault=").append(byDefault);
        sb.append(", famTypMatch=").append(famTypMatch.getLibTypMatch());
        if (famFormationItemList != null) {
            sb.append(", famFormationItemList=");
            for (FamFormationItem famFormationItem : famFormationItemList) {
                sb.append(famFormationItem).append('\'');
            }
        }
//        sb.append(", famFormationItemList=").append(famFormationItemList);
        sb.append('}');
        return sb.toString();
    }
}
