package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamFixture.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamFixture.findAll", query = "SELECT f FROM FamFixture f"),
        @NamedQuery(name = "FamFixture.findByIdFixture", query = "SELECT f FROM FamFixture f WHERE f.idFixture = :idFixture"),
        @NamedQuery(name = "FamFixture.findByLibFixture", query = "SELECT f FROM FamFixture f WHERE f.libFixture = :libFixture"),
        @NamedQuery(name = "FamFixture.findByCompetition", query = "SELECT f FROM FamFixture f WHERE f.famSeasonCompetition = :famSeasonCompetition ORDER BY f.libFixture ASC"),
        @NamedQuery(name = "FamFixture.findByLibAndCompetition", query = "SELECT f FROM FamFixture f WHERE f.libFixture = :libFixture AND f.famSeasonCompetition = :famSeasonCompetition ORDER BY f.libFixture ASC"),
        @NamedQuery(name = "FamFixture.findByDtCreat", query = "SELECT f FROM FamFixture f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamFixture.findByDtModif", query = "SELECT f FROM FamFixture f WHERE f.dtModif = :dtModif")
})
public class FamFixture extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_fixture";
    //
    /**
     *
     */
    public static final String PROP_ID = "idFixture";
    /**
     *
     */
    public static final String COL_ID = "id_fixture";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idFixture;

    @Override
    public Long getId() {
        return this.getIdFixture();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libFixture";
    public static final String COL_LIB = "lib_fixture";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libFixture;
    //
    /**
     *
     */
    public static final String PROP_DT_FIXTURE = "dtFixture";
    public static final String COL_DT_FIXTURE = "dt_fixture";
    @Basic(optional = false)
    @Column(name = COL_DT_FIXTURE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFixture;
    //
    /**
     *
     */
    public static final String COL_ID_SEASON_COMPETITION = "id_season_competition";
    /**
     *
     */
    public static final String PROP_SEASON_COMPETITION = "famSeasonCompetition";
    @JoinColumn(name = COL_ID_SEASON_COMPETITION, referencedColumnName = FamSeasonCompetition.COL_ID)
    @ManyToOne//(optional = false)
    @NotNull
    private FamSeasonCompetition famSeasonCompetition;
    //
    /**
     *
     */
    public static final String PROP_MATCHS = "famMatchList";
    @OneToMany(mappedBy = FamMatch.PROP_FIXTURE, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FamMatch> famMatchList;
    //

    /**
     *
     */
    public FamFixture() {
    }

    /**
     * @param idFixture
     */
    public FamFixture(Long idFixture) {
        this.idFixture = idFixture;
    }

    /**
     * @return
     */
    public Long getIdFixture() {
        return idFixture;
    }

    /**
     * @param idFixture
     */
    public void setIdFixture(Long idFixture) {
        this.idFixture = idFixture;
    }

    /**
     * @return
     */
    public String getLibFixture() {
        return libFixture;
    }

    /**
     * @param libFixture
     */
    public void setLibFixture(String libFixture) {
        this.libFixture = libFixture;
    }

    /**
     * @return
     */
    public Date getDtFixture() {
        return dtFixture;
    }

    /**
     * @param dtFixture
     */
    public void setDtFixture(Date dtFixture) {
        this.dtFixture = dtFixture;
    }

    /**
     * @return
     */
    public FamSeasonCompetition getFamSeasonCompetition() {
        return famSeasonCompetition;
    }

    /**
     * @param famSeasonCompetition
     */
    public void setFamSeasonCompetition(FamSeasonCompetition famSeasonCompetition) {
        this.famSeasonCompetition = famSeasonCompetition;
    }

    /**
     * @return
     */
    public List<FamMatch> getFamMatchList() {
        return famMatchList;
    }

    /**
     * @param famMatchList
     */
    public void setFamMatchList(List<FamMatch> famMatchList) {
        this.famMatchList = famMatchList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFixture != null ? idFixture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamFixture)) {
            return false;
        }
        FamFixture other = (FamFixture) object;
        if ((this.idFixture == null && other.idFixture != null) || (this.idFixture != null && !this.idFixture.equals(other.idFixture))) {
            return false;
        }
        return true;
    }

    //    @PreUpdate
//    void preUpdate() {
//        if (this.getDtFixture() != null) {
////
//            // Pour tous les matches de la journée
//            for (FamMatch match : this.getFamMatchList()) {
//                match.getFamEvent().setDtEvent(this.getDtFixture());
//            }
//        }
//    }
}
