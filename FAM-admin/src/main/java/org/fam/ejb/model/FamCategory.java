package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamCategory.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamCategory.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamCategory.FIND_ALL,
                query = "SELECT f FROM FamCategory f ORDER BY f.codCategory ASC"),
        @NamedQuery(name = FamCategory.FIND_BY_ID_CLUB,
                query = "SELECT f FROM FamCategory f WHERE f.idCategory = :idCategory"),
        @NamedQuery(name = FamCategory.FIND_BY_LIB_CLUB,
                query = "SELECT f FROM FamCategory f WHERE f.libCategory = :libCategory"),
        @NamedQuery(name = FamCategory.FIND_BY_COD_CATEGORY,
                query = "SELECT f FROM FamCategory f WHERE f.codCategory = :codCategory"),
        @NamedQuery(name = FamCategory.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamCategory f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamCategory.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamCategory f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamCategory extends FamEntity implements Serializable {

    private static final long serialVersionUID = -8074401386168281045L;
    public static final String TABLE_NAME = "fam_category";
    //
    public static final String FIND_ALL = "FamCategory.findAll";
    public static final String FIND_BY_ID_CLUB = "FamCategory.findByIdCategory";
    public static final String FIND_BY_LIB_CLUB = "FamCategory.findByLibCategory";
    public static final String FIND_BY_COD_CATEGORY = "FamCategory.findByCodCategory";
    public static final String FIND_BY_DT_CREAT = "FamCategory.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamCategory.findByDtModif";
    //
    public static final String PROP_ID = "idCategory";
    public static final String COL_ID = "id_category";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idCategory;


    @Override
    public Long getId() {
        return this.getIdCategory();
    }

    //
    public static final String PROP_LIB = "libCategory";
    public static final String COL_LIB = "lib_category";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libCategory;
    //
    public static final String PROP_COD = "codCategory";
    public static final String COL_COD = "cod_category";
    @Basic(optional = false)
    @NotNull(message = "Code obligatoire")
    @Column(name = COL_COD)
    @Size(max = 5, message = "Taille maxi : {max} caractères.")
    private String codCategory;

    //
    public FamCategory() {
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

        FamCategory that = (FamCategory) o;

        if (!codCategory.equals(that.codCategory)) {
            return false;
        }
        if (idCategory != null ? !idCategory.equals(that.idCategory) : that.idCategory != null) {
            return false;
        }
        if (!libCategory.equals(that.libCategory)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idCategory != null ? idCategory.hashCode() : 0);
        result = 31 * result + libCategory.hashCode();
        result = 31 * result + codCategory.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamCategory");
        sb.append("{idCategory=").append(idCategory);
        sb.append(", libCategory='").append(libCategory).append('\'');
        sb.append(", codCategory='").append(codCategory).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
