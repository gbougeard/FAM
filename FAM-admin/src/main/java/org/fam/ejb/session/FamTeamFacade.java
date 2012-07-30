/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamCategory;
import org.fam.ejb.model.FamTeam;


/**
 * @author gbougear
 */
@Named
@Stateless
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

        for (int i = 0; i < 50; i++) {
            FamTeam item = new FamTeam();
            item.setLibTeam("Team_" + i);
            item.setCodTeam("T_" + i);
            this.create(item);
        }
    }

    public List<FamTeam> findByCategory(FamCategory famCategory) {

        Query query = getEntityManager().createNamedQuery(FamTeam.FIND_BY_CATEGORY);
        query.setParameter(FamTeam.PROP_CATEGORY, famCategory);

        return query.getResultList();
    }
}
