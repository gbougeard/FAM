/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.fam.common.constant.FamConstantes;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamScale;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamScaleFacade extends AbstractFacade<FamScale> {

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
    public FamScaleFacade() {

        super(FamScale.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 10;
             i++) {
            FamScale item = new FamScale();
            item.setLibScale("Scale_" + i);
            this.create(item);
        }
    }

    /**
     *
     */
    public void initData() {

        this.truncate();

        FamScale item = new FamScale();
        item.setLibScale(FamConstantes.SCALE_3PTS_LIB);
        item.setCodScale(FamConstantes.SCALE_3PTS_COD);
        item.setPtsVictory(FamConstantes.SCALE_3PTS_W);
        item.setPtsDraw(FamConstantes.SCALE_3PTS_D);
        item.setPtsDefeat(FamConstantes.SCALE_3PTS_L);
        this.create(item);

        item = new FamScale();
        item.setLibScale(FamConstantes.SCALE_4PTS_LIB);
        item.setCodScale(FamConstantes.SCALE_4PTS_COD);
        item.setPtsVictory(FamConstantes.SCALE_4PTS_W);
        item.setPtsDraw(FamConstantes.SCALE_4PTS_D);
        item.setPtsDefeat(FamConstantes.SCALE_4PTS_L);
        this.create(item);
    }

    /**
     * @param cod
     * @return
     */
    public FamScale findByCod(String cod) {

        Query query = getEntityManager().createNamedQuery("FamScale.findByCodScale");
        query.setParameter(FamScale.PROP_COD, cod);

        return (FamScale) query.getSingleResult();
    }

    /**
     * @return
     */
    public FamScale getMatch() {

        return findByCod(FamConstantes.EVENT_MATCH_COD);
    }

    /**
     * @return
     */
    public FamScale getPractice() {

        return findByCod(FamConstantes.EVENT_PRACTICE_COD);
    }
}
