package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamFormationItem.TABLE_NAME
        , uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                FamFormationItem.PROP_FORMATION,
                FamFormationItem.PROP_NUM
        })
}
)
@NamedQueries({
        @NamedQuery(name = FamFormationItem.FIND_ALL,
                query = "SELECT f FROM FamFormationItem f"),
        @NamedQuery(name = FamFormationItem.FIND_BY_ID_FORMATION_ITEM,
                query = "SELECT f FROM FamFormationItem f WHERE f.idFormationItem = :idFormationItem"),
        @NamedQuery(name = FamFormationItem.FIND_BY_FORMATION,
                query = "SELECT f FROM FamFormationItem f WHERE f.famFormation = :famFormation"),
        @NamedQuery(name = FamFormationItem.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamFormationItem f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamFormationItem.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamFormationItem f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamFormationItem extends FamEntity implements Serializable {

    private static final long serialVersionUID = 3003578524600054258L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_formation_item";
    //
    public static final String FIND_ALL = "FamFormationItem.findAll";
    public static final String FIND_BY_ID_FORMATION_ITEM = "FamFormationItem.findByIdFormationItem";
    public static final String FIND_BY_FORMATION = "FamFormationItem.findByFormation";
    public static final String FIND_BY_DT_CREAT = "FamFormationItem.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamFormationItem.findByDtModif";
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_FORMATION)
    private FamFormation famFormation;

    /**
     *
     */
    public FamFormationItem() {
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

        FamFormationItem that = (FamFormationItem) o;

        if (idFormationItem != null ? !idFormationItem.equals(that.idFormationItem) : that.idFormationItem != null) {
            return false;
        }
        if (!numItem.equals(that.numItem)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idFormationItem != null ? idFormationItem.hashCode() : 0);
        result = 31 * result + numItem.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamFormationItem");
        sb.append("{idFormationItem=").append(idFormationItem);
        sb.append(", numItem=").append(numItem);
        sb.append(", coord=").append(coord);
        if (famFormation != null) {
            sb.append(", famFormation=").append(famFormation.getLibFormation());
        }
        sb.append('}');
        return sb.toString();
    }
}
