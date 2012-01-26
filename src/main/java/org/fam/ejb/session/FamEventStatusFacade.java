/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.FamConstantes;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamEventStatus;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamEventStatusFacade extends AbstractFacade<FamEventStatus> {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;

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
    public FamEventStatusFacade() {
        super(FamEventStatus.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 5; i++) {
            FamEventStatus item = new FamEventStatus();
            item.setLibEventStatus("EventStatus_" + i);
            this.create(item);
        }
    }

    /**
     * 
     */
    public void initData() {
        this.truncate();

        FamEventStatus item = new FamEventStatus();
        item.setLibEventStatus(FamConstantes.EVENT_CANCELED_LIB);
        item.setCodEventStatus(FamConstantes.EVENT_CANCELED_COD);
        this.create(item);

        item = new FamEventStatus();
        item.setLibEventStatus(FamConstantes.EVENT_DELAYED_LIB);
        item.setCodEventStatus(FamConstantes.EVENT_DELAYED_COD);
        this.create(item);

        item = new FamEventStatus();
        item.setLibEventStatus(FamConstantes.EVENT_FINISHED_LIB);
        item.setCodEventStatus(FamConstantes.EVENT_FINISHED_COD);
        this.create(item);

        item = new FamEventStatus();
        item.setLibEventStatus(FamConstantes.EVENT_SCHEDULED_LIB);
        item.setCodEventStatus(FamConstantes.EVENT_SCHEDULED_COD);
        this.create(item);
    }

    /**
     * 
     * @param cod
     * @return
     */
    public FamEventStatus findByCod(String cod) {
        Query query = em.createNamedQuery("FamEventStatus.findByCodEventStatus");
        query.setParameter(FamEventStatus.PROP_COD, cod);
        List<FamEventStatus> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * 
     * @return
     */
    public FamEventStatus getScheduled() {
        return findByCod(FamConstantes.EVENT_SCHEDULED_COD);
    }

    /**
     * 
     * @return
     */
    public FamEventStatus getDelayed() {
        return findByCod(FamConstantes.EVENT_DELAYED_COD);
    }

    /**
     * 
     * @return
     */
    public FamEventStatus getFinished() {
        return findByCod(FamConstantes.EVENT_FINISHED_COD);
    }

    /**
     * 
     * @return
     */
    public FamEventStatus getCanceled() {
        return findByCod(FamConstantes.EVENT_CANCELED_COD);
    }
}
