/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.Player;
import org.fam.common.constant.FamConstantes;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamEventStatus;
import org.fam.ejb.model.FamPlayer;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gbougear
 */
@Named
@Loggable
@Stateless
@Getter
@Setter
public class FamEventFacade extends AbstractFacade<FamEvent> {

    //    @PersistenceContext//(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    @Inject
    private Logger LOGGER;
    //
    @Inject
    private FamEventStatusFacade ejbEventStatus;
    @Inject
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

        for (int i = 0; i < 50; i++) {
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
//        Query query = getEntityManager().createNamedQuery(FamEvent.FIND_BETWEEN_BY_TEAM);
        Query query = getEntityManager().createNamedQuery(FamEvent.FIND_BETWEEN);
        query.setParameter("dtStart", startDate);
        query.setParameter("dtEnd", endDate);
        //query.setParameter("team", currentPlayer.getCurrentTeam());

        List<FamEvent> result = new ArrayList<FamEvent>();
        try {
            result = query.getResultList();
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
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
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
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


    @Override
    public FamEvent find(Object id) {

        return super.find(id);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamEvent> findAll() {

        return super.findAll();
    }

    @Override
    public void remove(FamEvent entity) {

        super.remove(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void edit(FamEvent entity) {

        super.edit(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void create(FamEvent entity) {

        super.create(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamEvent> findByAttributes(Map<String, Object> attributes) {

        return super.findByAttributes(attributes);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamEvent> findAllLazy(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {

        return super.findAllLazy(first, pageSize, sortField, sortOrder, filters);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int countLazy(Map<String, String> filters) {

        return super.countLazy(filters);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamEvent> findByCriteria(CriteriaQuery cq) {

        return super.findByCriteria(cq);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
