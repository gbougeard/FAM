/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamTeam;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;


/**
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTeamFacade extends AbstractFacade<FamTeam> {

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
        for (int i = 0;
             i < 50;
             i++) {
            FamTeam item = new FamTeam();
            item.setLibTeam("Team_" + i);
            item.setCodTeam("T_" + i);
            this.create(item);
        }
    }

}
