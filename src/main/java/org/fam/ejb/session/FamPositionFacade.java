/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamPosition;

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
public class FamPositionFacade extends AbstractFacade<FamPosition> {
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
    public FamPositionFacade() {
        super(FamPosition.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 10; i++) {
           FamPosition item = new FamPosition();
           item.setLibPosition("Position_"+i);
           item.setCodPosition("P_"+i);
           this.create(item);
        }
    }

}
