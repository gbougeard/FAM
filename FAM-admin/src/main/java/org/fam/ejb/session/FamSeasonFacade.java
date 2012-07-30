/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import javax.ejb.Stateless;
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

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamSeason;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({LoggingInterceptor.class, AuditInterceptor.class})
public class FamSeasonFacade extends AbstractFacade<FamSeason> {

//    @PersistenceContext//(unitName = "FAM-test-ejbPU")
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
    public FamSeasonFacade() {

        super(FamSeason.class);
    }

    /**
     * @return
     */
    public FamSeason getCurrentSeason() {

        Query query = getEntityManager().createNamedQuery("FamSeason.findCurrent");

        FamSeason result = null;
        try {
            result = (FamSeason) query.getSingleResult();
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
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 10;
             i++) {
            FamSeason item = new FamSeason();
            item.setLibSeason("Saison_" + i);
            this.create(item);
        }
    }

//    public void setEntityManager(EntityManager entityManager) {
//        em = entityManager;
//    }
}
