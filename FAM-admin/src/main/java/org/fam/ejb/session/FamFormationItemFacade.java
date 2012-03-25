/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.cdi.Loggable;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamFormation;
import org.fam.ejb.model.FamFormationItem;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @author gbougear
 */
@Stateless
@Loggable
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamFormationItemFacade extends AbstractFacade<FamFormationItem> {
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
    public FamFormationItemFacade() {
        super(FamFormationItem.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
    }

    /**
     * @param formation
     * @return
     */
    public List<FamFormationItem> findByFormation(FamFormation formation) {
        Query query = getEntityManager().createNamedQuery("FamFormationItem.findByFormation");
        query.setParameter(FamFormationItem.PROP_FORMATION, formation);

        List<FamFormationItem> result = null;
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
    public void create(FamFormationItem entity) {
        super.create(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void edit(FamFormationItem entity) {
        super.edit(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void remove(FamFormationItem entity) {
        super.remove(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public FamFormationItem find(Object id) {
        return super.find(id);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamFormationItem> findAllLazy(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {
        return super.findAllLazy(first, pageSize, sortField, sortOrder, filters);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
