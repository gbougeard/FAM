/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPositionFacade extends AbstractFacade<FamPosition> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamPositionFacade.class);

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
    public FamPositionFacade() {
        super(FamPosition.class);
    }

    @PostConstruct
    private void init() {
        LOGGER.debug("Init");
    }

    /**
     *
     */
    @Override
    public void genData() {
        for (int i = 0;
             i < 10;
             i++) {
            FamPosition item = new FamPosition();
            item.setLibPosition("Position_" + i);
            item.setCodPosition("P_" + i);
            this.create(item);
        }
    }

}
