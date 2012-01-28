package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamOrganization.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamOrganization.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamOrganization.findAll", query = "SELECT f FROM FamOrganization f"),
    @NamedQuery(name = "FamOrganization.findById", query = "SELECT f FROM FamOrganization f WHERE f.idOrganization = :idOrganization"),
    @NamedQuery(name = "FamOrganization.findByLib", query = "SELECT f FROM FamOrganization f WHERE f.libOrganization = :libOrganization"),
    @NamedQuery(name = "FamOrganization.findByCod", query = "SELECT f FROM FamOrganization f WHERE f.codOrganization = :codOrganization"),
    @NamedQuery(name = "FamOrganization.findByDtCreat", query = "SELECT f FROM FamOrganization f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamOrganization.findByDtModif", query = "SELECT f FROM FamOrganization f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamOrganization extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_organization";
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

    public FamOrganization(Long idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Long getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Long idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getLibOrganization() {
        return libOrganization;
    }

    public void setLibOrganization(String libOrganization) {
        this.libOrganization = libOrganization;
    }

    public String getCodOrganization() {
        return codOrganization;
    }

    public void setCodOrganization(String codeOrganization) {
        this.codOrganization = codeOrganization;
    }

    public FamOrganization getParent() {
        return parent;
    }

    public void setParent(FamOrganization parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idOrganization != null ? idOrganization.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamOrganization)) {
            return false;
        }
        FamOrganization other = (FamOrganization) object;
        if (( this.idOrganization == null && other.idOrganization != null ) || ( this.idOrganization != null && !this.idOrganization.equals(other.idOrganization) )) {
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
