/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LogUtil;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({LoggingInterceptor.class, AuditInterceptor.class})
public class FamUserFacade extends AbstractFacade<FamUser> implements Serializable {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    private FamUser newUser = new FamUser();

    /**
     * 
     * @return
     */
    @Produces
    @RequestScoped
    @Named
    public FamUser getNewUser() {
        return newUser;
    }

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
    public FamUserFacade() {
        super(FamUser.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 50; i++) {
            FamUser item = new FamUser();
            item.setPassword("user_" + i);
            item.setFirstName("prenom_" + i);
            item.setLastName("nom_" + i);
            this.create(item);
        }
    }

    /**
     * 
     * @param email
     * @return
     */
    public List<FamUser> findByEmailAndOpenid(String email) {
        Query query = getEntityManager().createNamedQuery("FamUser.findByEmailAndOpenid");
        query.setParameter(FamUser.PROP_EMAIL, email);
        query.setParameter(FamUser.PROP_OPENID, Boolean.TRUE);

        List<FamUser> result = new ArrayList<FamUser>();
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
     * 
     * @param username
     * @param password
     * @return
     */
    public FamUser login(String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("login ").append(username).append(" / ").append(password);
        LogUtil.log(sb.toString(), Level.INFO, null);

        Query query = getEntityManager().createNamedQuery("FamUser.login");
        query.setParameter(FamUser.PROP_EMAIL, username);
        query.setParameter(FamUser.PROP_PWD, password);

        List<FamUser> results = new ArrayList<FamUser>();
        try {
            results = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
             LogUtil.log("NoResultException", Level.SEVERE, e);
             return null;
        } catch (NonUniqueResultException e) {
            //- if more than one result
             LogUtil.log("NonUniqueResultException", Level.SEVERE, e);
             return null;
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
             LogUtil.log("IllegalStateException", Level.SEVERE, e);
             return null;
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
             LogUtil.log("QueryTimeoutException", Level.SEVERE, e);
             return null;
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
             LogUtil.log("TransactionRequiredException", Level.SEVERE, e);
             return null;
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
             LogUtil.log("PessimisticLockException", Level.SEVERE, e);
             return null;
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
             LogUtil.log("LockTimeoutException", Level.SEVERE, e);
             return null;
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
             LogUtil.log("PersistenceException", Level.SEVERE, e);
             return null;
        }

        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
             LogUtil.log("Cannot have more than one user with the same username", Level.SEVERE, null);
            throw new IllegalStateException("Cannot have more than one user with the same username!");
        } else {
            LogUtil.log("LOGIN SUCCESS!", Level.INFO, null);
            return results.get(0);
        }
    }
}
