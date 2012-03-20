/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamFormation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamFormationFacade extends AbstractFacade<FamFormation> {
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
    public FamFormationFacade() {
        super(FamFormation.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
        for (int i = 0;
             i < 10;
             i++) {
            FamFormation item = new FamFormation();
            item.setLibFormation("Formation_" + i);
            item.setCodFormation("F" + i);
            this.create(item);
        }
    }

}
