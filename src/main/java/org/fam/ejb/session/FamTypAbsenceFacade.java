/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamTypAbsence;

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
public class FamTypAbsenceFacade extends AbstractFacade<FamTypAbsence> {
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
    public FamTypAbsenceFacade() {
        super(FamTypAbsence.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 10; i++) {
           FamTypAbsence item = new FamTypAbsence();
           item.setLibTypAbsence("TypAbsence_"+i);
           this.create(item);
        }
    }

}