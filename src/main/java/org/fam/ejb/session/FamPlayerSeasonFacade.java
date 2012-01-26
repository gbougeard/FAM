/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Named(value = "famPlayerSeasonFacade")
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPlayerSeasonFacade extends AbstractFacade<FamPlayerSeason> {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    @EJB
    FamSeasonFacade ejbSeason;
    @EJB
    FamWorkoutFacade ejbWorkout;
    @EJB
    FamMatchPlayerFacade ejbMatchPlayer;

    /**
     * 
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     */
    public FamPlayerSeasonFacade() {
        super(FamPlayerSeason.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
    }

    /**
     * 
     * @param player
     * @param season
     * @return
     */
    public FamPlayerSeason findByPlayerAndSeason(FamPlayer player, FamSeason season) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByPlayerAndSeason");
        query.setParameter(FamPlayerSeason.PROP_PLAYER, player);
        query.setParameter(FamPlayerSeason.PROP_SEASON, season);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        if (result == null) {
            return null;
        } else if (result.isEmpty()) {
            return null;
        } else if (result.size() > 1) {
            throw new NonUniqueResultException();
        }

        return result.get(0);
    }

    /**
     * 
     * @param player
     * @param season
     * @return
     */
    public List<FamPlayerSeason> findByTeamAndSeason(FamTeam team, FamSeason season) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByTeamAndSeason");
        query.setParameter(FamPlayerSeason.PROP_TEAM, team);
        query.setParameter(FamPlayerSeason.PROP_SEASON, season);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * 
     * @param player
     * @param season
     * @return
     */
    public List<FamPlayerSeason> findByClubAndSeason(FamClub club, FamSeason season) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByClubAndSeason");
        query.setParameter(FamPlayerSeason.PROP_CLUB, club);
        query.setParameter(FamPlayerSeason.PROP_SEASON, season);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }
    /**
     * 
     * @param player
     * @return
     */
    public FamPlayerSeason findByPlayerAndCurrentSeason(FamPlayer player) {
        FamSeason season = ejbSeason.getCurrentSeason();

        return findByPlayerAndSeason(player, season);
    }

    /**
     * 
     * @param player
     * @return
     */
    public List<FamPlayerSeason> findByTeamAndCurrentSeason(FamTeam team) {
        FamSeason season = ejbSeason.getCurrentSeason();

        return findByTeamAndSeason(team, season);
    }
    
    /**
     * 
     * @param player
     * @return
     */
    public List<FamPlayerSeason> findByClubAndCurrentSeason(FamClub club) {
        FamSeason season = ejbSeason.getCurrentSeason();

        return findByClubAndSeason(club, season);
    }

    /**
     * 
     * @param player
     * @return
     */
    public Boolean isEngagedForCurrentSeason(FamPlayer player) {
        Boolean bRes = Boolean.FALSE;
        bRes = ( findByPlayerAndCurrentSeason(player) != null );
        return bRes;
    }

    /**
     * 
     * @param player
     * @param season
     * @return
     */
    public Boolean isEngagedForSeason(FamPlayer player, FamSeason season) {
        Boolean bRes = Boolean.FALSE;
        bRes = ( findByPlayerAndSeason(player, season) != null );
        return bRes;
    }

    /**
     * 
     * @param club
     * @return
     */
    public List<FamPlayerSeason> findPlayersForClub(FamClub club) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByClub");
        query.setParameter(FamPlayerSeason.PROP_CLUB, club);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * 
     * @param season
     * @param club
     * @return
     */
    public List<FamPlayerSeason> findBySeasonAndClub(FamSeason season, FamClub club) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findBySeasonAndClub");
        query.setParameter(FamPlayerSeason.PROP_CLUB, club);
        query.setParameter(FamPlayerSeason.PROP_SEASON, season);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * 
     * @param player
     * @param club
     * @return
     */
    public List<FamPlayerSeason> findByPlayerAndClub(FamPlayer player, FamClub club) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByPlayerAndClub");
        query.setParameter(FamPlayerSeason.PROP_CLUB, club);
        query.setParameter(FamPlayerSeason.PROP_PLAYER, player);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * 
     * @param season
     * @return
     */
    public List<FamPlayerSeason> findByNotSeason(FamSeason season) {
        Query query = getEntityManager().createNamedQuery("FamPlayerSeason.findByNotSeason");
        query.setParameter(FamPlayerSeason.PROP_SEASON, season);

        List<FamPlayerSeason> result = new ArrayList<FamPlayerSeason>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * 
     * @return
     */
    public List<FamPlayerSeason> findByNotCurrentSeason() {
        FamSeason season = ejbSeason.getCurrentSeason();

        return findByNotSeason(season);
    }

    public void calcStatForPlayerSeason(FamPlayerSeason famPlayerSeason) {
        // Workouts
        List<FamWorkout> listWorkout = ejbWorkout.findBySeason(famPlayerSeason.getFamSeason());
        Integer nbWorkout = 0;
        for (FamWorkout w : listWorkout) {
            if (w.getFamPlayerList().contains(famPlayerSeason.getFamPlayer())) {
                nbWorkout++;
            }
        }
        famPlayerSeason.getFamPlayerStat().setNbWorkout(nbWorkout);

        // Matches
        List<FamMatchPlayer> listMatch = ejbMatchPlayer.findByPlayerAndSeason(famPlayerSeason.getFamPlayer(),
                famPlayerSeason.getFamSeason());
        famPlayerSeason.getFamPlayerStat().setNbMatch(listMatch.size());

        if (listMatch.isEmpty() == false) {
            Integer nbCaptain = 0;
            Integer nbNote = 0;
            BigDecimal sumNote = new BigDecimal(0);
            Integer time = 0;
            Integer nbGoal = 0;
            Integer nbAssist = 0;
            Integer nbCard = 0;

            for (FamMatchPlayer m : listMatch) {
                // Captain
                if (m.getCaptain()) {
                    nbCaptain++;
                }
                // Note
                if (m.getNote() != new BigDecimal(0)) {
                    nbNote++;
                    sumNote.add(m.getNote());
                }
                // Temps de jeu
                time += m.getTimePlayed();
                // Goals && Assist
                for (FamGoal g : m.getFamGoalList()) {
                    if (g.getFamMatchPlayerAssist().getFamPlayer().equals(famPlayerSeason.getFamPlayer())) {
                        nbAssist++;
                    }
                    if (g.getFamMatchPlayerStriker().getFamPlayer().equals(famPlayerSeason.getFamPlayer())) {
                        nbGoal++;
                    }
                }
                // Cards
                for (FamCard c : m.getFamCardList()) {
                    if (c.getFamMatchPlayer().getFamPlayer().equals(famPlayerSeason.getFamPlayer())) {
                        nbCard++;
                    }
                }
            }
        }
        famPlayerSeason.getFamPlayerStat().setDtModifStat(new Date());

        edit(famPlayerSeason);
    }

    public void calcStatForCurrentSeason() {
        FamSeason season = ejbSeason.getCurrentSeason();

        calcStatForSeason(season);
    }

    public void calcStatForSeason(FamSeason famSeason) {
    }

    public void calcStatForTeamAndSeason(FamTeam famTeam, FamSeason famSeason) {
        List<FamPlayerSeason> psList = findByTeamAndSeason(famTeam, famSeason);
        for (FamPlayerSeason ps : psList) {
            calcStatForPlayerSeason(ps);
        }
    }

    public void calcStatForTeamAndCurrentSeason(FamTeam famTeam) {
        FamSeason season = ejbSeason.getCurrentSeason();

        calcStatForTeamAndSeason(famTeam, season);
    }

    public void calcStatForPlayerAndSeason(FamPlayer famPlayer, FamSeason famSeason) {
        FamPlayerSeason ps = findByPlayerAndSeason(famPlayer, famSeason);
        calcStatForPlayerSeason(ps);
    }

    public void calcStatForPlayerAndCurrentSeason(FamPlayer famPlayer) {
        FamSeason season = ejbSeason.getCurrentSeason();

        calcStatForPlayerAndSeason(famPlayer, season);
    }

    public void calcStatForClubAndSeason(FamClub famClub, FamSeason famSeason) {
        List<FamPlayerSeason> psList = findByClubAndSeason(famClub, famSeason);
        for (FamPlayerSeason ps : psList) {
            calcStatForPlayerSeason(ps);
        }
    }

    public void calcStatForClubAndCurrentSeason(FamClub famClub) {
        FamSeason season = ejbSeason.getCurrentSeason();

        calcStatForClubAndSeason(famClub, season);
    }
}
