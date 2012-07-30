/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamTypPlace;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTypPlaceFacade extends AbstractFacade<FamTypPlace> {

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
    public FamTypPlaceFacade() {

        super(FamTypPlace.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 5;
             i++) {
            FamTypPlace item = new FamTypPlace();
            item.setLibTypPlace("TypPlace_" + i);
            this.create(item);
        }
    }

}
