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
 * @author mask_hot
 */
@Getter
@Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = FamPlayerPosition.TABLE_NAME)
//@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = FamPlayerPosition.FIND_ALL,
                query = "SELECT f FROM FamPlayerPosition f ORDER BY f.numOrder"),
        @NamedQuery(name = FamPlayerPosition.FIND_BY_POSITION,
                query = "SELECT f FROM FamPlayerPosition f WHERE f.famPosition = :famPosition ORDER BY f.numOrder"),
        @NamedQuery(name = FamPlayerPosition.FIND_BY_PLAYER,
                query = "SELECT f FROM FamPlayerPosition f WHERE f.famPlayer = :famPlayer ORDER BY f.numOrder"),
        @NamedQuery(name = FamPlayerPosition.FIND_BY_PLAYER_AND_POSITION,
                query = "SELECT f FROM FamPlayerPosition f WHERE f.famPosition = :famPosition AND f.famPlayer = :famPlayer ORDER BY f.numOrder"),
        @NamedQuery(name = FamPlayerPosition.FIND_BY_NOT_POSITION,
                query = "SELECT f FROM FamPlayerPosition f WHERE NOT f.famPosition = :famPosition ORDER BY f.numOrder")
})
@IdClass(FamPlayerPositionPK.class)
@XmlRootElement
public class FamPlayerPosition implements Serializable {

    private static final long serialVersionUID = 6558067003677187670L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_player_position";
    //
    public static final String FIND_ALL = "FamPlayerPosition.findAll";
    public static final String FIND_BY_POSITION = "FamPlayerPosition.findByPosition";
    public static final String FIND_BY_PLAYER = "FamPlayerPosition.findByPlayer";
    public static final String FIND_BY_PLAYER_AND_POSITION = "FamPlayerPosition.findByPlayerAndPosition";
    public static final String FIND_BY_NOT_POSITION = "FamPlayerPosition.findByNotPosition";
    /**
     *
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     *
     */
    public static final String PROP_PLAYER = "famPlayer";

    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = FamPlayer.COL_ID)
    @NotNull
    private FamPlayer famPlayer;
    //
    /**
     *
     */
    public static final String COL_ID_POSITION = "id_position";
    /**
     *
     */
    public static final String PROP_POSITION = "famPosition";
    @Id
    @ManyToOne
    @JoinColumn(name = COL_ID_POSITION, referencedColumnName = FamPosition.COL_ID)
    @NotNull
    private FamPosition famPosition;
    //
    /**
     *
     */
    public static final String COL_NUM_ORDER = "num_order";
    /**
     *
     */
    public static final String PROP_NUM_ORDER = "numOrder";
    @Id
    @NotNull
    @Column(name = COL_NUM_ORDER)
    private Integer numOrder;
    //

    /**
     *
     */
    public FamPlayerPosition() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamPlayerPosition that = (FamPlayerPosition) o;

        return famPlayer.equals(that.famPlayer) && famPosition.equals(that.famPosition) && numOrder.equals(that.numOrder);

    }

    @Override
    public int hashCode() {
        int result = famPlayer.hashCode();
        result = 31 * result + famPosition.hashCode();
        result = 31 * result + numOrder.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamPlayerPosition");
        sb.append("{famPlayer=").append(famPlayer.getDisplayName());
        sb.append(", famPosition=").append(famPosition.getLibPosition());
        sb.append(", numOrder=").append(numOrder);
        sb.append('}');
        return sb.toString();
    }
}
