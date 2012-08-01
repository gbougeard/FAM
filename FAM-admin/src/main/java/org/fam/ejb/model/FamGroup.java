package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Data
@Entity
@Table(name = FamGroup.TABLE_NAME,
       uniqueConstraints = {
                            @UniqueConstraint(columnNames = {FamGroup.COL_LIB})
       })
@NamedQueries({
               @NamedQuery(name = "FamGroup.findAll", query = "SELECT f FROM FamGroup f"),
               @NamedQuery(name = "FamGroup.findByIdGroup", query = "SELECT f FROM FamGroup f WHERE f.idGroup = :idGroup"),
               @NamedQuery(name = "FamGroup.findByLibGroup", query = "SELECT f FROM FamGroup f WHERE f.groupname = :libGroup"),
               @NamedQuery(name = "FamGroup.findByDtCreat", query = "SELECT f FROM FamGroup f WHERE f.dtCreat = :dtCreat"),
               @NamedQuery(name = "FamGroup.findByDtModif", query = "SELECT f FROM FamGroup f WHERE f.dtModif = :dtModif")})
@XmlRootElement
public class FamGroup extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_group";
    //
    /**
     *
     */
    public static final String PROP_ID = "idGroup";
    public static final String COL_ID = "id_group";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idGroup;
    //
    /**
     *
     */
    public static final String PROP_LIB = "libGroup";
    public static final String COL_LIB = "lib_group";
    @Basic(optional = false)
    @NotNull(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String groupname;
    //
    /**
     *
     */
    public static final String PROP_DESCRIPTION = "description";
    public static final String COL_DESCRIPTION = "description";
    @Lob
    @Basic(optional = false)
    @Column(name = COL_DESCRIPTION)
    private String description;
    //

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "groups")
    private List<FamUser> users;

    /**
     *
     */
    public FamGroup() {
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamGroup)) {
            return false;
        }
        FamGroup other = (FamGroup) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

}
