package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamSeason.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamSeason.findAll", query = "SELECT f FROM FamSeason f"),
        @NamedQuery(name = "FamSeason.findByIdSeason", query = "SELECT f FROM FamSeason f WHERE f.idSeason = :idSeason"),
        @NamedQuery(name = "FamSeason.findByLibSeason", query = "SELECT f FROM FamSeason f WHERE f.libSeason = :libSeason"),
        @NamedQuery(name = "FamSeason.findCurrent", query = "SELECT f FROM FamSeason f WHERE f.currentSeason = 1"),
        @NamedQuery(name = "FamSeason.findByDtCreat", query = "SELECT f FROM FamSeason f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamSeason.findByDtModif", query = "SELECT f FROM FamSeason f WHERE f.dtModif = :dtModif")
})
public class FamSeason extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static String TABLE_NAME = "fam_season";
    //
    public final static String COL_ID = "id_season";
    public final static String PROP_ID = "idSeason";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idSeason;

    @Override
    public Long getId() {
        return this.getIdSeason();
    }

    public final static String PROP_LIB = "libSeason";
    public final static String COL_LIB = "lib_season";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libSeason;
    //
    public final static String PROP_CURRENT = "currentSeason";
    public final static String COL_CURRENT = "current_season";
    @Basic(optional = false)
    @Column(name = COL_CURRENT)
    private Boolean currentSeason;
    //
    public final static String PROP_COMPETITIONS = "famCompetitionList";
    @ManyToMany
    @JoinTable(name = FamSeasonCompetition.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamTypCompetition.COL_ID)})
    private List<FamTypCompetition> famCompetitionList;
    //
    public final static String PROP_PLAYERS = "famPlayerList";
    @ManyToMany
    @JoinTable(name = FamPlayerSeason.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamPlayer.COL_ID)})
    private List<FamPlayer> famPlayerList;
    //
    public final static String PROP_USERS = "famUserList";
    @ManyToMany
    @JoinTable(name = FamUserSeason.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamUser.COL_ID)})
    private List<FamUser> famUserList;

    public FamSeason() {
    }

    public FamSeason(Long idSeason) {
        this.idSeason = idSeason;
    }

    public Long getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(Long idSeason) {
        this.idSeason = idSeason;
    }

    public String getLibSeason() {
        return libSeason;
    }

    public void setLibSeason(String libSeason) {
        this.libSeason = libSeason;
    }

    public Boolean getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Boolean currentSeason) {
        this.currentSeason = currentSeason;
    }

    public List<FamTypCompetition> getFamCompetitionList() {
        return famCompetitionList;
    }

    public void setFamCompetitionList(List<FamTypCompetition> famCompetitionList) {
        this.famCompetitionList = famCompetitionList;
    }

    public List<FamPlayer> getFamPlayerList() {
        return famPlayerList;
    }

    public void setFamPlayerList(List<FamPlayer> famPlayerList) {
        this.famPlayerList = famPlayerList;
    }

    public List<FamUser> getFamUserList() {
        return famUserList;
    }

    public void setFamUserList(List<FamUser> famUserList) {
        this.famUserList = famUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeason != null ? idSeason.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamSeason)) {
            return false;
        }
        FamSeason other = (FamSeason) object;
        if ((this.idSeason == null && other.idSeason != null) || (this.idSeason != null && !this.idSeason.equals(other.idSeason))) {
            return false;
        }
        return true;
    }

}
