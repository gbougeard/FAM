/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamSeason;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.model.FamUserSeason;
import org.slf4j.Logger;

import javax.ejb.EJB;
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
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamUserSeasonFacade extends AbstractFacade<FamUserSeason> {

    @Inject
    private Logger LOGGER;

    //    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    @EJB
    FamSeasonFacade ejbSeason;

    /**
     * @return
     */
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }

    /**
     *
     */
    public FamUserSeasonFacade() {

        super(FamUserSeason.class);
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
     * @param user
     * @return
     */
    public List<FamUserSeason> findByUser(FamUser user) {

        Query query = getEntityManager().createNamedQuery("FamUserSeason.findByUser");
        query.setParameter(FamUserSeason.PROP_USER, user);

        List<FamUserSeason> result = new ArrayList<FamUserSeason>();
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

    /**
     * @param season
     * @return
     */
    public List<FamUserSeason> findBySeason(FamSeason season) {

        Query query = getEntityManager().createNamedQuery("FamUserSeason.findBySeason");
        query.setParameter(FamUserSeason.PROP_SEASON, season);

        List<FamUserSeason> result = new ArrayList<FamUserSeason>();
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
     * @return
     */
    public List<FamUserSeason> findByCurrentSeason() {

        Boolean bRes = Boolean.FALSE;
        FamSeason season = ejbSeason.getCurrentSeason();

        return findBySeason(season);
    }
}
