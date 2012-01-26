/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamClub;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamClubFacade extends AbstractFacade<FamClub> {

    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
    private EntityManager em;

    /**
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public FamClubFacade() {
        super(FamClub.class);
    }


    /**
     *
     */
    @Override
//    @GET // HTTP's GET verb/operation
//    @Path("") // specializes the path with a parameter
    public void genData() {
        for (int i = 0; i < 50; i++) {
            FamClub item = new FamClub();
            item.setLibClub("Club_" + i);
            item.setCodeFff(i);
            this.create(item);
        }
    }
}
