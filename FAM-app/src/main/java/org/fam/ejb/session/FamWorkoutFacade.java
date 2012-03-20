/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamSeason;
import org.fam.ejb.model.FamWorkout;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamWorkoutFacade extends AbstractFacade<FamWorkout> {

    //
    @EJB
    private FamTypEventFacade ejbTypEvent;
    @EJB
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
        Query query = getEntityManager().createNamedQuery("FamWorkout.findBySeason");
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
}
