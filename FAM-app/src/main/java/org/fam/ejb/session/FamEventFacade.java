/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.cdi.Player;
import org.fam.common.constant.FamConstantes;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamEventStatus;
import org.fam.ejb.model.FamPlayer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gbougear
 */
@Stateless
public class FamEventFacade extends AbstractFacade<FamEvent> {

//    @PersistenceContext//(unitName = "FAM-test-ejbPU")
//    private EntityManager em;

    @EJB
    private FamEventStatusFacade ejbEventStatus;
    @EJB
    private FamSeasonFacade ejbSeason;
    //
    @Inject
    @Player
    private FamPlayer currentPlayer;

    public FamEvent newEvent() {
        FamEvent evt = new FamEvent();
        evt.setFamSeason(ejbSeason.getCurrentSeason());
        evt.setFamEventStatus(ejbEventStatus.getScheduled());

        return evt;
    }

    /**
     *
     */
    public FamEventFacade() {
        super(FamEvent.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
        for (int i = 0;
             i < 50;
             i++) {
            FamEvent item = new FamEvent();
            item.setLibEvent("Event_" + i);
            item.setComments("<b>test</b>");
            this.create(item);
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public List<FamEvent> find(Date startDate, Date endDate) {
//        CriteriaBuilder cb = getCriteriaBuilder();
//        CriteriaQuery<FamEvent> cq = getCriteriaQuery();
//        Root<FamEvent> root = getRoot();
//
//        cq.select(root);
//        cq.where(cb.between(root.get(FamEvent_.dtEvent), startDate, endDate));
//
//        Query q = getEntityManager().createQuery(cq);
//        return q.getResultList();
        Query query = getEntityManager().createNamedQuery(FamEvent.FIND_BETWEEN_BY_TEAM);
        query.setParameter("dtStart", startDate);
        query.setParameter("dtEnd", endDate);
        query.setParameter("team", currentPlayer.getCurrentTeam());

        List<FamEvent> result = new ArrayList<FamEvent>();
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

//    @Schedule( hour = "0", minute = "15")

    /**
     *
     */
    public void markAsFinished() {

        FamEventStatus status = ejbEventStatus.findByCod(FamConstantes.EVENT_SCHEDULED_COD);

//        CriteriaBuilder cb = getCriteriaBuilder();
//        CriteriaQuery<FamEvent> cq = getCriteriaQuery();
//        Root<FamEvent> root = getRoot();
//
        Date dtNow = new Date();
//        cq.select(root);
//        cq.where(cb.lessThan(root.get(FamEvent_.dtEvent), dtNow),
//                 cb.notEqual(root.get(FamEvent_.famEventStatus), status));
//
//        Query q = getEntityManager().createQuery(cq);
//        List<FamEvent> results =  q.getResultList();

        Query query = getEntityManager().createNamedQuery(FamEvent.FIND_STATUS_BEFORE);
        query.setParameter(FamEvent.PROP_DT_EVENT, dtNow);
        query.setParameter(FamEvent.PROP_STATUS, status);

        List<FamEvent> results = new ArrayList<FamEvent>();
        try {
            results = query.getResultList();
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

        if (results.size() > 0) {
            status = ejbEventStatus.findByCod(FamConstantes.EVENT_FINISHED_COD);
        }
        for (FamEvent e : results) {
            e.setFamEventStatus(status);
            edit(e);
        }
    }

//    public void setCurrentPlayer(FamPlayer currentPlayer) {
//        this.currentPlayer = currentPlayer;
//    }
}
