package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamGroup.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamGroup.COL_LOGIN})
})
@NamedQueries({
    @NamedQuery(name = "FamGroup.findAll", query = "SELECT f FROM FamGroup f"),
    @NamedQuery(name = "FamGroup.findByIdGroup", query = "SELECT f FROM FamGroup f WHERE f.idGroup = :idGroup"),
    @NamedQuery(name = "FamGroup.findByGroupname", query = "SELECT f FROM FamGroup f WHERE f.groupname = :groupname"),
    @NamedQuery(name = "FamGroup.findByLogin", query = "SELECT f FROM FamGroup f WHERE f.login = :login"),
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
    /**
     * 
     */
    public static final String COL_ID = "id_group";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idGroup;
    //
    /**
     * 
     */
    public static final String PROP_LIB = "groupname";
    /**
     * 
     */
    public static final String COL_LIB = "groupname";
    @Basic(optional = false)
    @NotNull(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String groupname;
    //
    /**
     * 
     */
    public static final String PROP_LOGIN = "login";
    /**
     * 
     */
    public static final String COL_LOGIN = "login";
    @Basic(optional = false)
    @NotNull(message = "Login obligatoire")
    @Column(name = COL_LOGIN)
    private String login;
    //

    /**
     * 
     */
    public FamGroup() {
    }

    /**
     * 
     * @param idGroup
     */
    public FamGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    /**
     * 
     * @return
     */
    public Long getIdGroup() {
        return idGroup;
    }

    /**
     * 
     * @param idGroup
     */
    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    /**
     * 
     * @return
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * 
     * @param groupname
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * 
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idGroup != null ? idGroup.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamGroup)) {
            return false;
        }
        FamGroup other = (FamGroup) object;
        if (( this.idGroup == null && other.idGroup != null ) || ( this.idGroup != null && !this.idGroup.equals(other.idGroup) )) {
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
