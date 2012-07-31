/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamSeason;
import org.fam.ejb.model.FamWorkout;
import org.slf4j.Logger;

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
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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

}
