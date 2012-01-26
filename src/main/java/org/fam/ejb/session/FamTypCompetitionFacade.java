/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.model.FamTypCompetition;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gregory.bougeard
 */
@Stateless
public class FamTypCompetitionFacade extends AbstractFacade<FamTypCompetition> {
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
    public FamTypCompetitionFacade() {
        super(FamTypCompetition.class);
    }

    /**
     * 
     */
    @Override
    protected void genData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
