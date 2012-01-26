/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamTeam;

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
public class FamTeamFacade extends AbstractFacade<FamTeam> {
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
    public FamTeamFacade() {
        super(FamTeam.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 50; i++) {
           FamTeam item = new FamTeam();
           item.setLibTeam("Team_"+i);
           item.setCodTeam("T_"+i);
           this.create(item);
        }
    }

}
