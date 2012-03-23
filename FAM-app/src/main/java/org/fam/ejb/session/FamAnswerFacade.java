/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.session;

import org.fam.common.constant.FamConstantes;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.*;
import org.fam.jsf.cache.CachePlayer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamAnswerFacade extends AbstractFacade<FamAnswer> {

    //    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    @EJB
    FamTypAnswerFacade ejbTypAnswer;
    @EJB
    FamPlayerSeasonFacade ejbPlayerSeason;
    //
    @Inject
    private CachePlayer cachePlayer;

    /**
     *
     */
    public FamAnswerFacade() {
        super(FamAnswer.class);
    }

    /**
     *
     */
    @Override
//    @GET // HTTP's GET verb/operation
//    @Path("") // specializes the path with a parameter
    public void genData() {
    }

    /**
     * @param event
     * @return
     */
    public List<FamAnswer> findAnswerByEvent(FamEvent event) {
        Query query = getEntityManager().createNamedQuery("FamAnswer.findByEvent");
        query.setParameter(FamAnswer.PROP_EVENT, event);

        List<FamAnswer> result = new ArrayList<FamAnswer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * @param event
     * @param typAnswer
     * @return
     */
    public List<FamAnswer> findAnswerByEventAndTypAnswer(FamEvent event, FamTypAnswer typAnswer) {
        Query query = getEntityManager().createNamedQuery("FamAnswer.findByEventAndTypAnswer");
        query.setParameter(FamAnswer.PROP_EVENT, event);
        query.setParameter(FamAnswer.PROP_TYP, typAnswer);

        List<FamAnswer> result = new ArrayList<FamAnswer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * @param event
     * @param grpAnswer
     * @return
     */
    public List<FamAnswer> findAnswerByEventAndInTypAnswer(FamEvent event, String grpAnswer) {
        Query query = getEntityManager().createNamedQuery("FamAnswer.findByEventAndInTypAnswer");
        query.setParameter(FamAnswer.PROP_EVENT, event);
        query.setParameter(FamTypAnswer.PROP_GRP, grpAnswer);

        List<FamAnswer> result = new ArrayList<FamAnswer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * @param event
     * @return
     */
    public List<FamPlayer> findByEventAndNoAnswer(FamEvent event) {


        List<FamAnswer> answerList = findAnswerByEvent(event);


        List<FamPlayer> players = new ArrayList<FamPlayer>();
        for (FamTeam team : event.getFamTeamList()) {
            List<FamPlayerSeason> playerSeasonList = ejbPlayerSeason.findByTeamAndSeason(team, event.getFamSeason());


            for (FamPlayerSeason ps : playerSeasonList) {
                players.add(ps.getFamPlayer());
            }
        }

        for (FamAnswer a : answerList) {
            players.remove(a.getFamPlayer());
        }


        return players;
    }

    public List<FamPlayer> findByEventAndNoAnswerAndTeam(FamEvent event, FamTeam team) {

        List<FamAnswer> answerList = findAnswerByEvent(event);


        List<FamPlayer> players = new ArrayList<FamPlayer>();

        if (event.getFamTeamList().contains(team) == false) {
            throw new IllegalArgumentException("The team is not concerned by this event");
        }

        List<FamPlayerSeason> playerSeasonList = ejbPlayerSeason.findByTeamAndSeason(team, event.getFamSeason());


        for (FamPlayerSeason ps : playerSeasonList) {
            players.add(ps.getFamPlayer());
        }

        for (FamAnswer a : answerList) {
            players.remove(a.getFamPlayer());
        }

        return players;
    }

    /**
     * @param event
     * @return
     */
    public List<FamAnswer> findAnswerYesByEvent(FamEvent event) {
        return findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_YES);
    }

    private List<FamAnswer> getAnswerForTeam(List<FamAnswer> lstAnswer, FamTeam team) {
        List<FamAnswer> list = new ArrayList<FamAnswer>();

        if (lstAnswer == null || lstAnswer.isEmpty()) {
            return list;
        } else {
            FamEvent evt = lstAnswer.get(0).getFamEvent();
            FamSeason season = evt.getFamSeason();
            // On parcourt les reponses
            for (FamAnswer a : lstAnswer) {
                // il faut que pour la meme saison que l'event, l'équipe corresponde
                // a celle recherchee
                for (FamPlayerSeason ps : a.getFamPlayer().getFamPlayerSeasons()) {
                    if (ps.getFamSeason().equals(season) && ps.getFamTeam().equals(team)) {
                        list.add(a);
                    }
                }
            }
        }
        return list;
    }

    public List<FamAnswer> findAnswerYesByEventAndTeam(FamEvent event, FamTeam team) {
        return getAnswerForTeam(findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_YES), team);
    }

    public List<FamAnswer> findAnswerNoByEventAndTeam(FamEvent event, FamTeam team) {
        return getAnswerForTeam(findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_NO), team);
    }

    public List<FamAnswer> findAnswerMaybeByEventAndTeam(FamEvent event, FamTeam team) {
        return getAnswerForTeam(findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_MAYBE), team);
    }

    /**
     * @param event
     * @return
     */
    public List<FamAnswer> findAnswerNoByEvent(FamEvent event) {
        return findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_NO);
    }

    /**
     * @param event
     * @return
     */
    public List<FamAnswer> findAnswerMaybeByEvent(FamEvent event) {
        return findAnswerByEventAndInTypAnswer(event, FamConstantes.GRP_ANSWER_MAYBE);
    }

    /**
     * @param event
     * @param player
     * @return
     */
    public FamAnswer findAnswerByEventAndPlayer(FamEvent event, FamPlayer player) {
        Query query = getEntityManager().createNamedQuery("FamAnswer.findByEventAndPlayer");
        query.setParameter(FamAnswer.PROP_EVENT, event);
        query.setParameter(FamAnswer.PROP_PLAYER, player);

        List<FamAnswer> result = new ArrayList<FamAnswer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        if (result.isEmpty()) {
            FamAnswer answer = new FamAnswer();
            answer.setFamEvent(event);
            answer.setFamPlayer(player);
            return answer;
        } else {
            if (result.size() > 1) {
                // ERREUR
                throw new NonUniqueResultException();
            } else {
                return result.get(0);
            }
        }

    }

    public void setCachePlayer(CachePlayer cachePlayer) {
        this.cachePlayer = cachePlayer;
    }
}
