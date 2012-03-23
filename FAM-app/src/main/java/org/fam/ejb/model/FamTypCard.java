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
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamTypCard.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTypCard.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamTypCard.findAll", query = "SELECT f FROM FamTypCard f"),
        @NamedQuery(name = "FamTypCard.findByIdTypCard", query = "SELECT f FROM FamTypCard f WHERE f.idTypCard = :idTypCard"),
        @NamedQuery(name = "FamTypCard.findByLibTypCard", query = "SELECT f FROM FamTypCard f WHERE f.libTypCard = :libTypCard"),
        @NamedQuery(name = "FamTypCard.findByCodTypCard", query = "SELECT f FROM FamTypCard f WHERE f.codTypCard = :codTypCard"),
        @NamedQuery(name = "FamTypCard.findByDtCreat", query = "SELECT f FROM FamTypCard f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypCard.findByDtModif", query = "SELECT f FROM FamTypCard f WHERE f.dtModif = :dtModif")})
public class FamTypCard extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_card";
    /**
     *
     */
    public static final String PROP_ID = "idTypCard";
    /**
     *
     */
    public static final String COL_ID = "id_typ_card";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypCard;

    @Override
    public Long getId() {
        return this.getIdTypCard();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypCard";
    public static final String COL_LIB = "lib_typ_card";
    @Column(name = COL_LIB)
    @NotEmpty
    private String libTypCard;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypCard";
    public static final String COL_COD = "cod_typ_card";
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypCard;

    /**
     *
     */
    public FamTypCard() {
    }

    /**
     * @param idTypCard
     */
    public FamTypCard(Long idTypCard) {
        this.idTypCard = idTypCard;
    }

    /**
     * @return
     */
    public Long getIdTypCard() {
        return idTypCard;
    }

    /**
     * @param idTypCard
     */
    public void setIdTypCard(Long idTypCard) {
        this.idTypCard = idTypCard;
    }

    /**
     * @return
     */
    public String getLibTypCard() {
        return libTypCard;
    }

    /**
     * @param libTypCard
     */
    public void setLibTypCard(String libTypCard) {
        this.libTypCard = libTypCard;
    }

    /**
     * @return
     */
    public String getCodTypCard() {
        return codTypCard;
    }

    /**
     * @param codTypCard
     */
    public void setCodTypCard(String codTypCard) {
        this.codTypCard = codTypCard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypCard != null ? idTypCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamTypCard)) {
            return false;
        }
        FamTypCard other = (FamTypCard) object;
        if ((this.idTypCard == null && other.idTypCard != null) || (this.idTypCard != null && !this.idTypCard.equals(other.idTypCard))) {
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
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block

            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block

            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
