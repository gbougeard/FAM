/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamFormation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({  AuditInterceptor.class, LoggingInterceptor.class })
public class FamFormationFacade extends AbstractFacade<FamFormation> {
    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;

    /**
     * 
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

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
        for (int i = 0; i < 10; i++) {
           FamFormation item = new FamFormation();
           item.setLibFormation("Formation_"+i);
           item.setCodFormation("F"+i);
           this.create(item);
        }
    }

}
