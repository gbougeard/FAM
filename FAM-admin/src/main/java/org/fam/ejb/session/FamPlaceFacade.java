/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamPlace;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * @author gbougear
 */
@Stateless
@LocalBean
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
