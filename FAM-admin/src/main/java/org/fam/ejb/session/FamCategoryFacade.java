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
import org.fam.ejb.model.FamCategory;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamCategoryFacade extends AbstractFacade<FamCategory> {

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
    public FamCategoryFacade() {

        super(FamCategory.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        FamCategory item = new FamCategory();
        item.setLibCategory("SENIOR");
        item.setCodCategory("SEN");
        this.create(item);

        item = new FamCategory();
        item.setLibCategory("VETERAN");
        item.setCodCategory("VET");
        this.create(item);

        for (int i = 6; i <= 21; i++) {
            item = new FamCategory();
            item.setLibCategory("U" + i);
            item.setCodCategory("U" + i);
            this.create(item);
        }
    }

}
