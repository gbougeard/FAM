/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.constant.FamConstantes;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamTypEvent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.Query;
import java.util.List;

/**
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTypEventFacade extends AbstractFacade<FamTypEvent> {

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
    public FamTypEventFacade() {
        super(FamTypEvent.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
        for (int i = 0;
             i < 10;
             i++) {
            FamTypEvent item = new FamTypEvent();
            item.setLibTypEvent("TypEvent_" + i);
            this.create(item);
        }
    }

    /**
     *
     */
    public void initData() {
        this.truncate();

        FamTypEvent item = new FamTypEvent();
        item.setLibTypEvent(FamConstantes.EVENT_MATCH_LIB);
        item.setCodTypEvent(FamConstantes.EVENT_MATCH_COD);
        this.create(item);

        item = new FamTypEvent();
        item.setLibTypEvent(FamConstantes.EVENT_PRACTICE_LIB);
        item.setCodTypEvent(FamConstantes.EVENT_PRACTICE_COD);
        this.create(item);
    }

    /**
     * @param cod
     * @return
     */
    public FamTypEvent findByCod(String cod) {
        Query query = getEntityManager().createNamedQuery("FamTypEvent.findByCodTypEvent");
        query.setParameter(FamTypEvent.PROP_COD, cod);

        List<FamTypEvent> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * @return
     */
    public FamTypEvent getMatch() {
        return findByCod(FamConstantes.EVENT_MATCH_COD);
    }

    /**
     * @return
     */
    public FamTypEvent getPractice() {
        return findByCod(FamConstantes.EVENT_PRACTICE_COD);
    }
}
