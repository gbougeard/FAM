/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamPlayerProfile;
import org.fam.ejb.model.FamPlayerSeason;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.util.ChartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author gbougear
 */
//@DataSourceDefinition(name = "java:global/jdbc/sampleDS",
//        className = "org.apache.derby.jdbc.EmbeddedDriver",
//        url = "jdbc:derby:memory:sampleDB;create=true;user=app;password=app"
//)
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPlayerFacade extends AbstractFacade<FamPlayer> {

    //    @PersistenceContext//(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    //
    @Inject
    private FamSeasonFacade ejbSeason;
    //
//    @Inject
//    @LoggedIn
//    private FamUser currentUser;


    private static final Logger LOGGER = LoggerFactory.getLogger(FamPlayerFacade.class);

    /**
     *
     */
    public FamPlayerFacade() {
        super(FamPlayer.class);
    }

    @PostConstruct
    void postConstruct() {
        LOGGER.info("PostConstruct");
    }

    @PreDestroy
    void preDestroy() {
        LOGGER.info("PreDestroy");
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
     * @param user
     * @return
     */
    public List<FamPlayer> findPossiblePlayers(FamUser user) {
        Query query = getEntityManager().createNamedQuery(FamPlayer.FIND_POSSIBLE_PLAYERS);
        query.setParameter(FamPlayer.PROP_EMAIL, user.getEmail());
        query.setParameter(FamPlayer.PROP_FIRST_NAME, user.getFirstName());
        query.setParameter(FamPlayer.PROP_LAST_NAME, user.getLastName());

        List<FamPlayer> result = new ArrayList<FamPlayer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

    /**
     * @param user
     * @return
     */
    public List<FamPlayer> findByFamUser(FamUser user) {
        Query query = getEntityManager().createNamedQuery(FamPlayer.FIND_BY_FAM_USER);
        query.setParameter(FamPlayer.PROP_USER, user);

        List<FamPlayer> result = new ArrayList<FamPlayer>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
        } catch (NonUniqueResultException e) {
            //- if more than one result
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
        }

        return result;
    }

//    public void setLogger(Logger LOGGER) {
//        this.LOGGER = LOGGER;
//    }

//    public void setEntityManager(EntityManager entityManager) {
//        em = entityManager;
//    }

    public void setEjbSeason(FamSeasonFacade ejbSeason) {
        this.ejbSeason = ejbSeason;
    }

    public void setCurrentUser(FamUser currentUser) {
        this.currentUser = currentUser;
    }
}
