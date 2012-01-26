package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = FamPlayerPosition.TABLE_NAME)
//@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "FamPlayerPosition.findAll", query = "SELECT f FROM FamPlayerPosition f ORDER BY f.numOrder"),
    @NamedQuery(name = "FamPlayerPosition.findByPosition", query = "SELECT f FROM FamPlayerPosition f WHERE f.famPosition = :famPosition ORDER BY f.numOrder"),
    @NamedQuery(name = "FamPlayerPosition.findByPlayer", query = "SELECT f FROM FamPlayerPosition f WHERE f.famPlayer = :famPlayer ORDER BY f.numOrder"),
    @NamedQuery(name = "FamPlayerPosition.findByPlayerAndPosition", query = "SELECT f FROM FamPlayerPosition f WHERE f.famPosition = :famPosition AND f.famPlayer = :famPlayer ORDER BY f.numOrder"),
    @NamedQuery(name = "FamPlayerPosition.findByNotPosition", query = "SELECT f FROM FamPlayerPosition f WHERE f.famPosition != :famPosition ORDER BY f.numOrder")
})
@IdClass(FamPlayerPositionPK.class)
public class FamPlayerPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_player_position";
    //
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

    /**
     *
     * @return
     */
    public FamPlayer getFamPlayer() {
        return famPlayer;
    }

    /**
     * 
     * @param famPlayer
     */
    public void setFamPlayer(FamPlayer famPlayer) {
        this.famPlayer = famPlayer;
    }


    /**
     * 
     * @return
     */
    public FamPosition getFamPosition() {
        return famPosition;
    }

    /**
     * 
     * @param famPosition
     */
    public void setFamPosition(FamPosition famPosition) {
        this.famPosition = famPosition;
    }

    public Integer getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(Integer numOrder) {
        this.numOrder = numOrder;
    }




    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Class cls = this.getClass();
        int ii = 0;
        builder.append(this.getClass()).append(" [");
        for (Field f : cls.getDeclaredFields()) {
            String str = "null";
            if (f.getName().equals(PROP_POSITION)) {

                if (this.getFamPosition() != null) {
                    str = this.getFamPosition().getLibPosition();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
            } else if (f.getName().equals(PROP_PLAYER)) {

                if (this.getFamPlayer() != null) {
                    str = this.getFamPlayer().getDisplayName();
                }
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(str);
            } else {
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
        }
        builder.append("\n]");
        return builder.toString();
    }
}
