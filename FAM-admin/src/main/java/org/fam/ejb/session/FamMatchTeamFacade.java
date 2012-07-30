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
import org.fam.ejb.model.FamMatchTeam;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamMatchTeamFacade extends AbstractFacade<FamMatchTeam> {

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
    public FamMatchTeamFacade() {

        super(FamMatchTeam.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

    }

}
