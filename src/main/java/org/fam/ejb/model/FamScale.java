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
 *
 * @author gbougear
 */
@Entity
@Table(name = FamScale.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamScale.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamScale.findAll", query = "SELECT f FROM FamScale f"),
    @NamedQuery(name = "FamScale.findByIdScale", query = "SELECT f FROM FamScale f WHERE f.idScale = :idScale"),
    @NamedQuery(name = "FamScale.findByLibScale", query = "SELECT f FROM FamScale f WHERE f.libScale = :libScale"),
    @NamedQuery(name = "FamScale.findByDtCreat", query = "SELECT f FROM FamScale f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamScale.findByDtModif", query = "SELECT f FROM FamScale f WHERE f.dtModif = :dtModif")})
public class FamScale extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_scale";
    //
    /**
     *
     */
    public static final String PROP_ID = "idScale";
    /**
     *
     */
    public static final String COL_ID = "id_scale";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idScale;


    @Override
    public Long getId(){
        return this.getIdScale();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libScale";
    public static final String COL_LIB = "lib_scale";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libScale;
    //
    /**
     *
     */
    public static final String PROP_COD = "codScale";
    public static final String COL_COD = "cod_scale";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codScale;
    //
    /**
     *
     */
    public static final String PROP_PTS_VICTORY = "ptsVictory";
    public static final String COL_PTS_VICTORY = "pts_victory";
    @Basic(optional = false)
    @NotNull(message = "Valeur obligatoire")
    @Column(name = COL_PTS_VICTORY)
    private Integer ptsVictory;
    //
    /**
     *
     */
    public static final String PROP_PTS_DRAW = "ptsDraw";
    public static final String COL_PTS_DRAW = "pts_draw";
    @Basic(optional = false)
    @NotNull(message = "Valeur obligatoire")
    @Column(name = COL_PTS_DRAW)
    private Integer ptsDraw;
    //
    /**
     *
     */
    public static final String PROP_PTS_DEFEAT = "ptsDefeat";
    public static final String COL_PTS_DEFEAT = "pts_defeat";
    @Basic(optional = false)
    @NotNull(message = "Valeur obligatoire")
    @Column(name = COL_PTS_DEFEAT)
    private Integer ptsDefeat;
    //
    public static final String PROP_SEASONCOMPETITIONS = "famSeasonCompetitionList";
    @OneToMany(mappedBy = FamSeasonCompetition.PROP_SCALE)
    private List<FamSeasonCompetition> famSeasonCompetitionList;
    //

    /**
     *
     */
    public FamScale() {
    }

    /**
     *
     * @param idScale
     */
    public FamScale(Long idScale) {
        this.idScale = idScale;
    }

    /**
     *
     * @return
     */
    public Long getIdScale() {
        return idScale;
    }

    /**
     *
     * @param idScale
     */
    public void setIdScale(Long idScale) {
        this.idScale = idScale;
    }

    /**
     *
     * @return
     */
    public String getLibScale() {
        return libScale;
    }

    /**
     *
     * @param libScale
     */
    public void setLibScale(String libScale) {
        this.libScale = libScale;
    }

    /**
     *
     * @return
     */
    public String getCodScale() {
        return codScale;
    }

    /**
     *
     * @param codScale
     */
    public void setCodScale(String codScale) {
        this.codScale = codScale;
    }

    /**
     *
     * @return
     */
    public Integer getPtsDefeat() {
        return ptsDefeat;
    }

    /**
     *
     * @param ptsDefeat
     */
    public void setPtsDefeat(Integer ptsDefeat) {
        this.ptsDefeat = ptsDefeat;
    }

    /**
     *
     * @return
     */
    public Integer getPtsDraw() {
        return ptsDraw;
    }

    /**
     *
     * @param ptsDraw
     */
    public void setPtsDraw(Integer ptsDraw) {
        this.ptsDraw = ptsDraw;
    }

    /**
     *
     * @return
     */
    public Integer getPtsVictory() {
        return ptsVictory;
    }

    /**
     *
     * @param ptsVictory
     */
    public void setPtsVictory(Integer ptsVictory) {
        this.ptsVictory = ptsVictory;
    }

    /**
     *
     * @return
     */
    public List<FamSeasonCompetition> getFamSeasonCompetitionList() {
        return famSeasonCompetitionList;
    }

    /**
     * 
     * @param famSeasonCompetitionList
     */
    public void setFamSeasonCompetitionList(List<FamSeasonCompetition> famSeasonCompetitionList) {
        this.famSeasonCompetitionList = famSeasonCompetitionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idScale != null ? idScale.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamScale)) {
            return false;
        }
        FamScale other = (FamScale) object;
        if (( this.idScale == null && other.idScale != null ) || ( this.idScale != null && !this.idScale.equals(other.idScale) )) {
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
