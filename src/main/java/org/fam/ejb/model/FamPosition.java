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
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamPosition.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamPosition.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamPosition.findAll", query = "SELECT f FROM FamPosition f"),
    @NamedQuery(name = "FamPosition.findByIdPosition", query = "SELECT f FROM FamPosition f WHERE f.idPosition = :idPosition"),
    @NamedQuery(name = "FamPosition.findByLibPosition", query = "SELECT f FROM FamPosition f WHERE f.libPosition = :libPosition"),
    @NamedQuery(name = "FamPosition.findByDtCreat", query = "SELECT f FROM FamPosition f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamPosition.findByDtModif", query = "SELECT f FROM FamPosition f WHERE f.dtModif = :dtModif")})
public class FamPosition extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_position";
    //
    /**
     *
     */
    public static final String PROP_ID = "idPosition";
    /**
     *
     */
    public static final String COL_ID = "id_position";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idPosition;


    @Override
    public Long getId(){
        return this.getIdPosition();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libPosition";
    public static final String COL_LIB = "lib_position";
    @Basic(optional = false)
    @NotEmpty(message = "Libelle obligatoire")
    @Column(name = COL_LIB)
    private String libPosition;
    //
    /**
     *
     */
    public static final String PROP_COD = "codPosition";
    public static final String COL_COD = "cod_position";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codPosition;
    //
//    @ManyToMany
//    @JoinTable(name = "fam_player_position",
//    joinColumns = {
//        @JoinColumn(name = COL_ID)},
//    inverseJoinColumns = {
//        @JoinColumn(name = FamPlayer.COL_ID)})
    public static final String PROP_PLAYERPOSITIONS = "famPlayerPositionList";
    @OneToMany(mappedBy = FamPlayerPosition.PROP_POSITION, cascade = CascadeType.ALL)
    private List<FamPlayerPosition> famPlayerPositionList;

    /**
     *
     */
    public FamPosition() {
    }

    /**
     *
     * @param idPosition
     */
    public FamPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    /**
     *
     * @return
     */
    public Long getIdPosition() {
        return idPosition;
    }

    /**
     *
     * @param idPosition
     */
    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    /**
     *
     * @return
     */
    public String getLibPosition() {
        return libPosition;
    }

    /**
     *
     * @param libPosition
     */
    public void setLibPosition(String libPosition) {
        this.libPosition = libPosition;
    }

    public List<FamPlayerPosition> getFamPlayerPositionList() {
        return famPlayerPositionList;
    }

    public void setFamPlayerPositionList(List<FamPlayerPosition> famPlayerPositionList) {
        this.famPlayerPositionList = famPlayerPositionList;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idPosition != null ? idPosition.hashCode() : 0 );
        return hash;
    }

    /**
     * 
     * @return
     */
    public String getCodPosition() {
        return codPosition;
    }

    /**
     * 
     * @param codPosition
     */
    public void setCodPosition(String codPosition) {
        this.codPosition = codPosition;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamPosition)) {
            return false;
        }
        FamPosition other = (FamPosition) object;
        if (( this.idPosition == null && other.idPosition != null ) || ( this.idPosition != null && !this.idPosition.equals(other.idPosition) )) {
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
