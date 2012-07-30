/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaQuery;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamSeason;
import org.fam.ejb.model.FamWorkout;
import org.slf4j.Logger;

/**
 * @author gbougear
 */
@Named
@Stateless
@Loggable
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
@Getter
@Setter
public class FamWorkoutFacade extends AbstractFacade<FamWorkout> {

    @Inject
    private Logger LOGGER;
    //
    @Inject
    private FamTypEventFacade ejbTypEvent;
    @Inject
    private FamEventFacade ejbEvent;
    //

    /**
     *
     */
    public FamWorkoutFacade() {

        super(FamWorkout.class);
    }

    public FamWorkout newWorkout() {

        FamWorkout res = new FamWorkout();
        FamEvent evt = ejbEvent.newEvent();

        res.setFamEvent(evt);

        return res;
    }

    /**
     *
     */
    @Override
//    @GET // HTTP's GET verb/operation
//    @Path("") // specializes the path with a parameter
    public void genData() {

    }

    @Override
    public void create(FamWorkout entity) {
        // Force TypEvent to Practice
        entity.getFamEvent().setFamTypEvent(ejbTypEvent.getPractice());
        super.create(entity);
    }

    @Override
    public void edit(FamWorkout entity) {
        // Force TypEvent to Match
        entity.getFamEvent().setFamTypEvent(ejbTypEvent.getPractice());
        super.edit(entity);
    }

    public List<FamWorkout> findBySeason(FamSeason season) {

        Query query = getEntityManager().createNamedQuery(FamWorkout.FIND_BY_SEASON);
        query.setParameter(FamEvent.PROP_SEASON, season);

        List<FamWorkout> result = new ArrayList<FamWorkout>();
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

    @Override
    public void remove(FamWorkout entity) {

        super.remove(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public FamWorkout find(Object id) {

        return super.find(id);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamWorkout> findAll() {

        return super.findAll();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamWorkout> findByAttributes(Map<String, Object> attributes) {

        return super.findByAttributes(attributes);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamWorkout> findByCriteria(CriteriaQuery cq) {

        return super.findByCriteria(cq);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamWorkout> findAllLazy(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {

        return super.findAllLazy(first, pageSize, sortField, sortOrder, filters);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int countLazy(Map<String, String> filters) {

        return super.countLazy(filters);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int count() {

        return super.count();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamWorkout> findRange(int[] range) {

        return super.findRange(range);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
