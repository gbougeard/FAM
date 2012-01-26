/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamPlayerProfile;
import org.fam.ejb.model.FamPlayerSeason;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.util.ChartUtil;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPlayerFacade extends AbstractFacade<FamPlayer> {

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    //
    @EJB
    FamSeasonFacade ejbSeason;

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
    public FamPlayerFacade() {
        super(FamPlayer.class);
    }

    /**
     * 
     */
    @Override
    public void genData() {
    }

    @Override
    public void create(FamPlayer entity) {
        if (entity.getFamPlayerSeasons() == null) {
            List<FamPlayerSeason> list = new ArrayList<FamPlayerSeason>();
            entity.setFamPlayerSeasons(list);

            if (entity.getFamPlayerSeasons().isEmpty()) {
                // Pas de saison encore associée, on rajoute la courante
                FamPlayerSeason current = new FamPlayerSeason();
                current.setFamPlayer(entity);
                current.setFamSeason(ejbSeason.getCurrentSeason());
                current.setFamPlayerProfile(new FamPlayerProfile());

                entity.getFamPlayerSeasons().add(current);
            }
        }
        entity.getCurrentProfile().setProfileChartUrl(ChartUtil.radarProfile(entity.getCurrentProfile()));
        super.create(entity);
    }

    @Override
    public void edit(FamPlayer entity) {
        entity.getCurrentProfile().setProfileChartUrl(ChartUtil.radarProfile(entity.getCurrentProfile()));
        super.edit(entity);
    }

    /**
     * 
     * @param user
     * @return
     */
    public List<FamPlayer> findPossiblePlayers(FamUser user) {
        Query query = getEntityManager().createNamedQuery("FamPlayer.findPossiblePlayers");
        query.setParameter(FamPlayer.PROP_EMAIL, user.getEmail());
        query.setParameter(FamPlayer.PROP_FIRST_NAME, user.getFirstName());
        query.setParameter(FamPlayer.PROP_LAST_NAME, user.getLastName());

        List<FamPlayer> result = new ArrayList<FamPlayer>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }
}
