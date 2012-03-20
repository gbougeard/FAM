/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamTypMatch;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTypMatchFacade extends AbstractFacade<FamTypMatch> {

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
    public FamTypMatchFacade() {
        super(FamTypMatch.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
        for (int i = 0;
             i < 50;
             i++) {
            FamTypMatch item = new FamTypMatch();
            item.setLibTypMatch("TypMatch_" + i);
            item.setCodTypMatch("TM_" + i);
            this.create(item);
        }
    }

}
