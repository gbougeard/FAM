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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamMatchFacade extends AbstractFacade<FamMatch> {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    @EJB
    private FamFixtureFacade ejbFixture;
    @EJB
    private FamTypEventFacade ejbTypEvent;

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
    public FamMatchFacade() {
        super(FamMatch.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
    }

    /**
     * 
     * @param competition
     */
    public void deleteByCompetition(FamSeasonCompetition competition) {
        List<FamMatch> list = findByCompetition(competition);

        for (FamMatch fixture : list) {
            remove(fixture);
        }
    }

    /**
     * 
     * @param competition
     * @return
     */
    public List<FamMatch> findByCompetition(FamSeasonCompetition competition) {
        Query query = em.createNamedQuery("FamMatch.findByCompetition");
        query.setParameter(FamMatch.PROP_SEASON_COMPETITION, competition);

        return query.getResultList();
    }

    /**
     * 
     * @param fixture
     * @param matchSet
     */
    public void linkMatches(FamFixture fixture, List<FamMatch> matchSet) {
        FamFixture fix = ejbFixture.find(fixture.getIdFixture());

        for (FamMatch item : matchSet) {
            FamMatch match = find(item.getIdMatch());
//            for (FamMatchTeam matchTeam : match.getFamMatchTeamSet()){
//                matchTeam.setFamFixture(fix);
//            }
            if (fix.getFamMatchList() == null) {
                fix.setFamMatchList(new ArrayList<FamMatch>());
            }
            fix.getFamMatchList().add(item);

            edit(match);
        }
    }

    /**
     * 
     * @param entity
     */
    @Override
    public void edit(FamMatch entity) {
        // Force TypEvent to Match
        entity.getFamEvent().setFamTypEvent(ejbTypEvent.getMatch());

        // Check si le match est de championnat
        if (entity.getFamSeasonCompetition().getFamTypCompetition().getIsChampionship()) {
            // Check si on est associé à une journée
            if (entity.getFamFixture() != null) {
                // Check si le match n'est pas déjà associé à une autre journée
            }
        }

        super.edit(entity);
    }

    @Override
    public void create(FamMatch entity) {
        // Force TypEvent to Match
        entity.getFamEvent().setFamTypEvent(ejbTypEvent.getMatch());
        super.create(entity);
    }
}
