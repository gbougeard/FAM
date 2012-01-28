package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.fam.ejb.listener.FamWorkoutEntityListener;

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
@EntityListeners({FamWorkoutEntityListener.class})
@Table(name = FamMatch.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamMatch.findAll", query = "SELECT f FROM FamMatch f"),
    @NamedQuery(name = "FamMatch.findByIdMatch", query = "SELECT f FROM FamMatch f WHERE f.idMatch = :idMatch"),
    @NamedQuery(name = "FamMatch.findByCompetition", query = "SELECT f FROM FamMatch f WHERE f.famSeasonCompetition = :famSeasonCompetition"),
    @NamedQuery(name = "FamMatch.findByDtCreat", query = "SELECT f FROM FamMatch f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamMatch.findByDtModif", query = "SELECT f FROM FamMatch f WHERE f.dtModif = :dtModif")})
public class FamMatch extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_match";
    //
    /**
     * 
     */
    public static final String PROP_ID = "id_match";
    /**
     * 
     */
    public static final String COL_ID = "idMatch";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idMatch;
    
    @Override
    public Long getId(){
        return this.getIdMatch();
    }
    
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
    public static final String COL_ID_EVENT = "id_event";
    /**
     * 
     */
    public static final String PROP_EVENT = "famEvent";
    @JoinColumn(name = COL_ID_EVENT, referencedColumnName = FamEvent.COL_ID)
    @OneToOne(cascade = CascadeType.ALL)//(optional = false)
    @NotNull
    private FamEvent famEvent;
    //
    @OneToMany(mappedBy = FamMatchTeam.PROP_MATCH, cascade = CascadeType.ALL)
    private List<FamMatchTeam> famMatchTeamList;
    //
    /**
     *
     */
    public static final String COL_ID_FIXTURE = "id_fixture";
    /**
     *
     */
    public static final String PROP_FIXTURE = "famFixture";
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COL_ID_FIXTURE, referencedColumnName = FamFixture.COL_ID)
    private FamFixture famFixture;

    // TODO : ajouter : arbirage, pelouse, météo, spectateurs, ...

    public FamMatchTeam getHomeTeam(){
        if (famMatchTeamList != null){
            for (FamMatchTeam mt : famMatchTeamList){
                if (mt.getHome()){
                    return mt;
                }
            }
        }
        return null;
    }

    public FamMatchTeam getAwayTeam(){
        if (famMatchTeamList != null){
            for (FamMatchTeam mt : famMatchTeamList){
                if (!mt.getHome()){
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

    /**
     *
     * @param idMatch
     */
    public FamMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    /**
     *
     * @return
     */
    public Long getIdMatch() {
        return idMatch;
    }

    /**
     *
     * @param idMatch
     */
    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    /**
     *
     * @return
     */
    public FamEvent getFamEvent() {
        return famEvent;
    }

    /**
     *
     * @param famEvent
     */
    public void setFamEvent(FamEvent famEvent) {
        this.famEvent = famEvent;
    }

    /**
     *
     * @return
     */
    public FamSeasonCompetition getFamSeasonCompetition() {
        return famSeasonCompetition;
    }

    /**
     *
     * @param famSeasonCompetition
     */
    public void setFamSeasonCompetition(FamSeasonCompetition famSeasonCompetition) {
        if (famSeasonCompetition != null) {
            this.famSeasonCompetition = famSeasonCompetition;
        }
    }

    /**
     *
     * @return
     */
    public List<FamMatchTeam> getFamMatchTeamList() {
        return famMatchTeamList;
    }

    /**
     *
     * @param famMatchTeamList
     */
    public void setFamMatchTeamList(List<FamMatchTeam> famMatchTeamList) {
        this.famMatchTeamList = famMatchTeamList;
    }

    /**
     * 
     * @return
     */
    public FamFixture getFamFixture() {
        return famFixture;
    }

    /**
     * 
     * @param famFixture
     */
    public void setFamFixture(FamFixture famFixture) {
        this.famFixture = famFixture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idMatch != null ? idMatch.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamMatch)) {
            return false;
        }
        FamMatch other = (FamMatch) object;
        if (( this.idMatch == null && other.idMatch != null ) || ( this.idMatch != null && !this.idMatch.equals(other.idMatch) )) {
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
