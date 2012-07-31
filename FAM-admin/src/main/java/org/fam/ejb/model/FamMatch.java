package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamMatch.TABLE_NAME)
@NamedQueries({
               @NamedQuery(name = FamMatch.FIND_ALL,
                           query = "SELECT f FROM FamMatch f"),
               @NamedQuery(name = FamMatch.FIND_BY_ID_MATCH,
                           query = "SELECT f FROM FamMatch f WHERE f.idMatch = :idMatch"),
               @NamedQuery(name = FamMatch.FIND_BY_COMPETITION,
                           query = "SELECT f FROM FamMatch f WHERE f.famSeasonCompetition = :famSeasonCompetition"),
               @NamedQuery(name = FamMatch.FIND_BY_DT_CREAT,
                           query = "SELECT f FROM FamMatch f WHERE f.dtCreat = :dtCreat"),
               @NamedQuery(name = FamMatch.FIND_BY_DT_MODIF,
                           query = "SELECT f FROM FamMatch f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamMatch extends FamEntity implements Serializable {

    private static final long serialVersionUID = 3270056301876007323L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_match";
    //
    public static final String FIND_ALL = "FamMatch.findAll";
    public static final String FIND_BY_ID_MATCH = "FamMatch.findByIdMatch";
    public static final String FIND_BY_COMPETITION = "FamMatch.findByCompetition";
    public static final String FIND_BY_DT_CREAT = "FamMatch.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamMatch.findByDtModif";
    /**
     * TODO : interverser PROP & COL
     */
    public static final String PROP_ID = "id_match";
    public static final String COL_ID = "idMatch";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idMatch;

    @Override
    public Long getId() {
        return this.getIdMatch();
    }

    /**
     *
     */
    public static final String COL_ID_SEASON_COMPETITION = "id_season_competition";
    public static final String PROP_SEASON_COMPETITION = "famSeasonCompetition";
    @JoinColumn(name = COL_ID_SEASON_COMPETITION, referencedColumnName = FamSeasonCompetition.COL_ID)
    @ManyToOne//(optional = false)
    @NotNull
    private FamSeasonCompetition famSeasonCompetition;
    //
    /**
     *
     */
    public static final String COL_ID_EVENT = "id_event";
    public static final String PROP_EVENT = "famEvent";
    @JoinColumn(name = COL_ID_EVENT, referencedColumnName = FamEvent.COL_ID)
    @OneToOne(cascade = CascadeType.ALL)//(optional = false)
    @NotNull
    private FamEvent famEvent;
    //
    /**
     *
     */
    @OneToMany(mappedBy = FamMatchTeam.PROP_MATCH, cascade = CascadeType.ALL)
    private List<FamMatchTeam> famMatchTeamList;
    //
    /**
     *
     */
    public static final String COL_ID_FIXTURE = "id_fixture";
    public static final String PROP_FIXTURE = "famFixture";
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_FIXTURE, referencedColumnName = FamFixture.COL_ID)
    private FamFixture famFixture;

    // TODO : ajouter : arbirage, pelouse, météo, spectateurs, ...

    public FamMatchTeam getHomeTeam() {
        if (famMatchTeamList != null) {
            for (FamMatchTeam mt : famMatchTeamList) {
                if (mt.getHome()) {
                    return mt;
                }
            }
        }
        return null;
    }

    public FamMatchTeam getAwayTeam() {
        if (famMatchTeamList != null) {
            for (FamMatchTeam mt : famMatchTeamList) {
                if (!mt.getHome()) {
                    return mt;
                }
            }
        }
        return null;
    }

    /**
     *
     */
    public FamMatch() {
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

        FamMatch famMatch = (FamMatch) o;

        if (idMatch != null ? !idMatch.equals(famMatch.idMatch) : famMatch.idMatch != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idMatch != null ? idMatch.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamMatch");
        sb.append("{idMatch=").append(idMatch);
        sb.append(", famSeasonCompetition=").append(famSeasonCompetition.getDisplayName());
        sb.append(", famEvent=").append(famEvent.getLibEvent());
        //sb.append(", famMatchTeamList=").append(famMatchTeamList);
        if (famFixture != null) {
            sb.append(", famFixture=").append(famFixture.getLibFixture());
        }
        sb.append('}');
        return sb.toString();
    }
}
