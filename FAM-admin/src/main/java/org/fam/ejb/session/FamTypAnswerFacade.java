/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import java.util.ArrayList;
import java.util.List;
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

import org.fam.common.constant.FamConstantes;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamTypAnswer;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTypAnswerFacade extends AbstractFacade<FamTypAnswer> {

//    @PersistenceContext //(unitName = "FAM-test-ejbPU")
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
    public FamTypAnswerFacade() {

        super(FamTypAnswer.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 10;
             i++) {
            FamTypAnswer item = new FamTypAnswer();
            item.setLibTypAnswer("TypAnswer_" + i);
            this.create(item);
        }
    }

    /**
     * @param grp
     * @return
     */
    public List<FamTypAnswer> findTypAnswerByGrp(String grp) {

        Query query = getEntityManager().createNamedQuery("FamTypAnswer.findByGrpTypAnswer");
        query.setParameter(FamTypAnswer.PROP_GRP, grp);

        List<FamTypAnswer> result = new ArrayList<FamTypAnswer>();
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
    public List<FamTypAnswer> findTypAnswerByGrpYes() {

        return findTypAnswerByGrp(FamConstantes.GRP_ANSWER_YES);
    }

    /**
     * @return
     */
    public List<FamTypAnswer> findTypAnswerByGrpNo() {

        return findTypAnswerByGrp(FamConstantes.GRP_ANSWER_NO);
    }

    /**
     * @return
     */
    public List<FamTypAnswer> findTypAnswerByGrpMaybe() {

        return findTypAnswerByGrp(FamConstantes.GRP_ANSWER_MAYBE);
    }
}
