/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamFixture;
import org.fam.ejb.model.FamMatch;
import org.fam.ejb.model.FamSeasonCompetition;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamFixtureFacade extends AbstractFacade<FamFixture> {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    @EJB
    FamSeasonCompetitionFacade ejbSeasonCompetition;

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
    public FamFixtureFacade() {
        super(FamFixture.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
        for (int i = 0; i < 50; i++) {
            FamFixture item = new FamFixture();
            item.setLibFixture("Fixture_" + i);
            this.create(item);
        }
    }

    /**
     * 
     * @param competition
     */
    public void deleteByCompetition(FamSeasonCompetition competition) {
        List<FamFixture> list = findByCompetition(competition);

        for (FamFixture fixture : list) {
            remove(fixture);
        }
    }

    /**
     * 
     * @param competition
     * @return
     */
    public List<FamFixture> findByCompetition(FamSeasonCompetition competition) {
        Query query = em.createNamedQuery("FamFixture.findByCompetition");
        query.setParameter(FamFixture.PROP_SEASON_COMPETITION, competition);
        
        return query.getResultList();
    }

    /**
     * 
     * @param inFixture
     */
    public void propagateDate(FamFixture inFixture) {

        FamFixture fixture = find(inFixture.getIdFixture());
        if (fixture.getDtFixture() != null) {
            for (FamMatch match : fixture.getFamMatchList()) {
                // Pour tous les matches de la journée
                match.getFamEvent().setDtEvent(fixture.getDtFixture());
            }
        }

    }

    /**
     * 
     * @param idSeasonCompetition
     * @return
     */
    public List<FamFixture> findByCompetitionId(Long idSeasonCompetition) {
        FamSeasonCompetition famSeasonCompetition = ejbSeasonCompetition.find(idSeasonCompetition);
        return findByCompetition(famSeasonCompetition);
    }


}
