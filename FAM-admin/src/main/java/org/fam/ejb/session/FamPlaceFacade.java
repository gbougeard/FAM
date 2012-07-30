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
import org.fam.ejb.model.FamPlace;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPlaceFacade extends AbstractFacade<FamPlace> {

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
    public FamPlaceFacade() {

        super(FamPlace.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 50;
             i++) {
            FamPlace item = new FamPlace();
            item.setLibPlace("Place_" + i);
            this.create(item);
        }
    }

}
