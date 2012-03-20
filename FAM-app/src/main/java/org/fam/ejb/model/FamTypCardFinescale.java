package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamTypCardFinescale.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamTypCardFinescale.findAll", query = "SELECT f FROM FamTypCardFinescale f")
})
public class FamTypCardFinescale extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_card_finescale";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypCardFinescale";
    /**
     *
     */
    public static final String COL_ID = "id_typ_card_finescale";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypCardFinescale;

    @Override
    public Long getId() {
        return this.getIdTypCardFinescale();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypCardFinescale";
    public static final String COL_LIB = "lib_typ_card_finescale";
    @Column(name = COL_LIB)
    @NotEmpty
    private String libTypCardFinescale;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypCardFinescale";
    public static final String COL_COD = "cod_typ_card_finescale";
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypCardFinescale;
    //
    /**
     *
     */
    public static final String COL_ID_TYP_CARD = "id_typ_card";
    /**
     *
     */
    public static final String PROP_TYP_CARD = "famTypCard";
    @ManyToOne
    @JoinColumn(name = COL_ID_TYP_CARD, referencedColumnName = FamTypCard.COL_ID)
    private FamTypCard famTypCard;
    //
    /**
     *
     */
    public static final String PROP_PRICE = "price";
    @Column(name = PROP_PRICE)
    @NotNull
    private BigDecimal price;
    //
//    @ManyToMany
//    @JoinTable(name = "fam_competition_finescale",
//    joinColumns = {
//        @JoinColumn(name = COL_ID)},
//    inverseJoinColumns = {
//        @JoinColumn(name = FamSeasonCompetition.COL_ID)})
//    private List<FamSeasonCompetition> famSeasonCompetitionList;

    /**
     *
     */
    public FamTypCardFinescale() {
    }

    /**
     * @return
     */
    public FamTypCard getFamTypCard() {
        return famTypCard;
    }

    /**
     * @param famTypCard
     */
    public void setFamTypCard(FamTypCard famTypCard) {
        this.famTypCard = famTypCard;
    }

    /**
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return
     */
    public String getCodTypCardFinescale() {
        return codTypCardFinescale;
    }

    /**
     * @param codTypCardFinescale
     */
    public void setCodTypCardFinescale(String codTypCardFinescale) {
        this.codTypCardFinescale = codTypCardFinescale;
    }

    /**
     * @return
     */
    public Long getIdTypCardFinescale() {
        return idTypCardFinescale;
    }

    /**
     * @param idTypCardFinescale
     */
    public void setIdTypCardFinescale(Long idTypCardFinescale) {
        this.idTypCardFinescale = idTypCardFinescale;
    }

    /**
     * @return
     */
    public String getLibTypCardFinescale() {
        return libTypCardFinescale;
    }

    /**
     * @param libTypCardFinescale
     */
    public void setLibTypCardFinescale(String libTypCardFinescale) {
        this.libTypCardFinescale = libTypCardFinescale;
    }

    /**
     *
     * @return
     */
//    public List<FamSeasonCompetition> getFamSeasonCompetitionList() {
//        return famSeasonCompetitionList;
//    }

    /**
     * @param famSeasonCompetitionList
     */
//    public void setFamSeasonCompetitionList(List<FamSeasonCompetition> famSeasonCompetitionList) {
//        this.famSeasonCompetitionList = famSeasonCompetitionList;
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamTypCardFinescale other = (FamTypCardFinescale) obj;
        if (this.idTypCardFinescale != other.idTypCardFinescale && (this.idTypCardFinescale == null || !this.idTypCardFinescale.equals(other.idTypCardFinescale))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.idTypCardFinescale != null ? this.idTypCardFinescale.hashCode() : 0);
        return hash;
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
                LogUtil.log("Erreur!", Level.SEVERE, e);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
