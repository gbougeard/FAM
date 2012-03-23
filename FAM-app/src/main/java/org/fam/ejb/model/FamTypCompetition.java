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
import java.util.List;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamTypCompetition.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamTypCompetition.findAll", query = "SELECT f FROM FamTypCompetition f"),
        @NamedQuery(name = "FamTypCompetition.findByIdTypCompetition", query = "SELECT f FROM FamTypCompetition f WHERE f.idTypCompetition = :idTypCompetition"),
        @NamedQuery(name = "FamTypCompetition.findByLibTypCompetition", query = "SELECT f FROM FamTypCompetition f WHERE f.libTypCompetition = :libTypCompetition"),
        @NamedQuery(name = "FamTypCompetition.findByDtCreat", query = "SELECT f FROM FamTypCompetition f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypCompetition.findByDtModif", query = "SELECT f FROM FamTypCompetition f WHERE f.dtModif = :dtModif"),
        @NamedQuery(name = "FamTypCompetition.findByCodTypCompetition", query = "SELECT f FROM FamTypCompetition f WHERE f.codTypCompetition = :codTypCompetition")
})
public class FamTypCompetition extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_competition";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypCompetition";
    /**
     *
     */
    public static final String COL_ID = "id_typ_competition";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypCompetition;


    @Override
    public Long getId() {
        return this.getIdTypCompetition();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libCompetition";
    public static final String COL_LIB = "lib_typ_competition";
    @Basic(optional = false)
    @NotEmpty(message = "Libelle obligatoire")
    @Column(name = COL_LIB)
    private String libTypCompetition;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypCompetition";
    public static final String COL_COD = "cod_typ_competition";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codTypCompetition;
    //
    /**
     *
     */
    public static final String PROP_CHAMP = "championship";
    @Basic(optional = false)
    @Column(name = PROP_CHAMP)
    private Boolean isChampionship;
    //
    /**
     *
     */
    public static final String PROP_SEASONS = "famSeasonList";
    @ManyToMany
    @JoinTable(name = FamSeasonCompetition.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamSeason.COL_ID)})
    private List<FamSeason> famSeasonList;
    //
    /**
     *
     */
    public static final String COL_ID_TYP_MATCH = "id_typ_match";
    /**
     *
     */
    public static final String PROP_TYP_MATCH = "famTypMatch";
    @ManyToOne
    @JoinColumn(name = COL_ID_TYP_MATCH, referencedColumnName = FamTypMatch.COL_ID)
    @NotNull
    private FamTypMatch famTypMatch;

    /**
     *
     */
    public FamTypCompetition() {
    }

    /**
     * @param idCompetition
     */
    public FamTypCompetition(Long idCompetition) {
        this.idTypCompetition = idCompetition;
    }

    /**
     * @return
     */
    public Long getIdTypCompetition() {
        return idTypCompetition;
    }

    /**
     * @param idCompetition
     */
    public void setIdTypCompetition(Long idCompetition) {
        this.idTypCompetition = idCompetition;
    }

    /**
     * @return
     */
    public String getLibTypCompetition() {
        return libTypCompetition;
    }

    /**
     * @param libCompetition
     */
    public void setLibTypCompetition(String libCompetition) {
        this.libTypCompetition = libCompetition;
    }

    /**
     * @return
     */
    public String getCodTypCompetition() {
        return codTypCompetition;
    }

    /**
     * @param codCompetition
     */
    public void setCodTypCompetition(String codCompetition) {
        this.codTypCompetition = codCompetition;
    }

    /**
     * @return
     */
    public List<FamSeason> getFamSeasonList() {
        return famSeasonList;
    }

    /**
     * @param famSeasonList
     */
    public void setFamSeasonList(List<FamSeason> famSeasonList) {
        this.famSeasonList = famSeasonList;
    }

    /**
     * @return
     */
    public Boolean getIsChampionship() {
        return isChampionship;
    }

    /**
     * @param isChampionship
     */
    public void setIsChampionship(Boolean isChampionship) {
        this.isChampionship = isChampionship;
    }

    /**
     * @return
     */
    public FamTypMatch getFamTypMatch() {
        return famTypMatch;
    }

    /**
     * @param famTypMatch
     */
    public void setFamTypMatch(FamTypMatch famTypMatch) {
        this.famTypMatch = famTypMatch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypCompetition != null ? idTypCompetition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamTypCompetition)) {
            return false;
        }
        FamTypCompetition other = (FamTypCompetition) object;
        if ((this.idTypCompetition == null && other.idTypCompetition != null) || (this.idTypCompetition != null && !this.idTypCompetition.equals(other.idTypCompetition))) {
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
