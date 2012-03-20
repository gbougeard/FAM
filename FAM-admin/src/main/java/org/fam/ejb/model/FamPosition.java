package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamPosition.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamPosition.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamPosition.FIND_ALL,
                query = "SELECT f FROM FamPosition f"),
        @NamedQuery(name = FamPosition.FIND_BY_ID_POSITION,
                query = "SELECT f FROM FamPosition f WHERE f.idPosition = :idPosition"),
        @NamedQuery(name = FamPosition.FIND_BY_LIB_POSITION,
                query = "SELECT f FROM FamPosition f WHERE f.libPosition = :libPosition"),
        @NamedQuery(name = FamPosition.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamPosition f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamPosition.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamPosition f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamPosition extends FamEntity implements Serializable {

    private static final long serialVersionUID = -1277139423167212533L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_position";
    //
    public static final String FIND_ALL = "FamPosition.findAll";
    public static final String FIND_BY_ID_POSITION = "FamPosition.findByIdPosition";
    public static final String FIND_BY_LIB_POSITION = "FamPosition.findByLibPosition";
    public static final String FIND_BY_DT_CREAT = "FamPosition.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamPosition.findByDtModif";
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
    public Long getId() {
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

        FamPosition that = (FamPosition) o;

        return idPosition.equals(that.idPosition);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idPosition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamPosition");
        sb.append("{idPosition=").append(idPosition);
        sb.append(", libPosition='").append(libPosition).append('\'');
        sb.append(", codPosition='").append(codPosition).append('\'');
//        sb.append(", famPlayerPositionList=").append(famPlayerPositionList);
        sb.append('}');
        return sb.toString();
    }
}
