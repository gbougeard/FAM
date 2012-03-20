package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamOrganization.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamOrganization.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamOrganization.FIND_ALL,
                query = "SELECT f FROM FamOrganization f"),
        @NamedQuery(name = FamOrganization.FIND_BY_ID,
                query = "SELECT f FROM FamOrganization f WHERE f.idOrganization = :idOrganization"),
        @NamedQuery(name = FamOrganization.FIND_BY_LIB,
                query = "SELECT f FROM FamOrganization f WHERE f.libOrganization = :libOrganization"),
        @NamedQuery(name = FamOrganization.FIND_BY_COD,
                query = "SELECT f FROM FamOrganization f WHERE f.codOrganization = :codOrganization"),
        @NamedQuery(name = FamOrganization.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamOrganization f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamOrganization.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamOrganization f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamOrganization extends FamEntity implements Serializable {

    private static final long serialVersionUID = 3906912906499817113L;
    public static final String TABLE_NAME = "fam_organization";
    //
    public static final String FIND_ALL = "FamOrganization.findAll";
    public static final String FIND_BY_ID = "FamOrganization.findById";
    public static final String FIND_BY_LIB = "FamOrganization.findByLib";
    public static final String FIND_BY_COD = "FamOrganization.findByCod";
    public static final String FIND_BY_DT_CREAT = "FamOrganization.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamOrganization.findByDtModif";
    //
    public static final String PROP_ID = "idOrganization";
    public static final String COL_ID = "id_organization";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idOrganization;

    @Override
    public Long getId() {
        return this.getIdOrganization();
    }

    public static final String PROP_LIB = "libOrganization";
    public static final String COL_LIB = "lib_organization";
    @Basic(optional = false)
    @NotEmpty(message = "Libelle obligatoire")
    @Size(max = 255, message = "Taille maxi : {max} caractères.")
    @Column(name = COL_LIB)
    private String libOrganization;
    //
    public static final String PROP_COD = "codOrganization";
    public static final String COL_COD = "cod_organization";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Size(max = 10, min = 3, message = "Taille maxi : {max}, mini {min} caracteres.")
    @Column(name = COL_COD)
    private String codOrganization;
    //
    public static final String COL_ID_PARENT = "id_parent";
    @ManyToOne
    @JoinColumn(name = COL_ID_PARENT, referencedColumnName = FamOrganization.COL_ID)
    private FamOrganization parent;

    public FamOrganization() {
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

        FamOrganization that = (FamOrganization) o;

        return idOrganization.equals(that.idOrganization);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idOrganization.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamOrganization");
        sb.append("{idOrganization=").append(idOrganization);
        sb.append(", libOrganization='").append(libOrganization).append('\'');
        sb.append(", codOrganization='").append(codOrganization).append('\'');
        sb.append(", parent=").append(parent.getLibOrganization());
        sb.append('}');
        return sb.toString();
    }
}
