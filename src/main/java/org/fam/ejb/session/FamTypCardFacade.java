/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.FamConstantes;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamTypCard;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamTypCardFacade extends AbstractFacade<FamTypCard> {

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
    public FamTypCardFacade() {
        super(FamTypCard.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 10; i++) {
            FamTypCard item = new FamTypCard();
            item.setLibTypCard("TypCard_" + i);
            this.create(item);
        }
    }

    /**
     * 
     */
    public void initData() {
        this.truncate();
        
        FamTypCard item = new FamTypCard();
        item.setLibTypCard(FamConstantes.CARD_YELLOW_LIB);
        item.setCodTypCard(FamConstantes.CARD_YELLOW_COD);
        this.create(item);

        item = new FamTypCard();
        item.setLibTypCard(FamConstantes.CARD_RED_LIB);
        item.setCodTypCard(FamConstantes.CARD_RED_COD);
        this.create(item);
        
        item = new FamTypCard();
        item.setLibTypCard(FamConstantes.CARD_WHITE_LIB);
        item.setCodTypCard(FamConstantes.CARD_WHITE_COD);
        this.create(item);
    }

    /**
     * 
     * @param cod
     * @return
     */
    public FamTypCard findByCod(String cod) {
        Query query = em.createNamedQuery("FamTypCard.findByCodTypCard");
        query.setParameter(FamTypCard.PROP_COD, cod);

        return (FamTypCard) query.getSingleResult();
    }

//    public FamTypCard getMatch() {
//        return findByCod(FamConstantes.EVENT_MATCH_COD);
//    }
//
//    public FamTypCard getPractice() {
//        return findByCod(FamConstantes.EVENT_PRACTICE_COD);
//    }
}
