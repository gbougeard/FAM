/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamFixture;
import org.fam.ejb.model.FamMatchTeam;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.model.VRankings;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
public class FamMatchTeamFacade extends AbstractFacade<FamMatchTeam> {

    @Inject
    private Logger LOGGER;

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
    public FamMatchTeamFacade() {

        super(FamMatchTeam.class);
    }

    public List<VRankings> getRanking(FamSeasonCompetition competition) {

        Query query = getEntityManager().createNamedQuery(VRankings.FIND);
        query.setParameter(FamFixture.PROP_SEASON_COMPETITION, competition);

        List<VRankings> result = new ArrayList<VRankings>();
        try {
            result = query.getResultList();
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
        } catch (NoResultException
                  | IllegalStateException
                  | QueryTimeoutException
                  | TransactionRequiredException
                  | PessimisticLockException
                  | LockTimeoutException
                  | NonUniqueResultException e) {
            //- if there is no result}
            LOGGER.error("Persistence Error", e);
        }

        return result;

    }

    /**
     *
     */
    @Override
    public void genData() {

    }

}
