/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamMatch;
import org.fam.ejb.model.FamMatchTeam;
import org.fam.ejb.model.FamSubstitution;
import org.fam.ejb.model.FamTeam;
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
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamSubstitutionFacade extends AbstractFacade<FamSubstitution> {

    @Inject
    private Logger LOGGER;

//    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
//    private EntityManager em;

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
    public FamSubstitutionFacade() {

        super(FamSubstitution.class);
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
     * @param match
     * @return
     */
    public List<FamSubstitution> findByMatchAndTeam(FamMatch match, FamTeam team) {

        Query query = getEntityManager().createNamedQuery("FamSubstitution.findByMatchAndTeam");
        query.setParameter(FamMatchTeam.PROP_MATCH, match);
        query.setParameter(FamMatchTeam.PROP_TEAM, team);

        List<FamSubstitution> result = new ArrayList<FamSubstitution>();
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
