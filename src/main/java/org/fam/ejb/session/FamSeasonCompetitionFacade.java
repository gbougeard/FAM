/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author gbougear
 */
@Stateless
@LocalBean
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamSeasonCompetitionFacade extends AbstractFacade<FamSeasonCompetition> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamSeasonCompetitionFacade.class);

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    @EJB
    private FamFixtureFacade ejbFixture;
    @EJB
    private FamMatchFacade ejbMatch;
    @EJB
    private FamMatchTeamFacade ejbMatchTeam;
    @EJB
    private FamTypEventFacade ejbTypEvent;
    //    @EJB
//    FamEventStatusFacade ejbEventStatus;
    @EJB
    private FamEventFacade ejbEvent;

    /**
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public FamSeasonCompetitionFacade() {
        super(FamSeasonCompetition.class);
    }

    /**
     *
     */
    @Override
    public void genData() {
    }

    public FamFixture findByLibAndCompetition(String lib, FamSeasonCompetition competition) {
        Query query = getEntityManager().createNamedQuery("FamFixture.findByLibAndCompetition");
        query.setParameter(FamFixture.PROP_LIB, lib);
        query.setParameter(FamFixture.PROP_SEASON_COMPETITION, competition);

        List<FamFixture> result = new ArrayList<FamFixture>();
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
        if (result != null & !result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public void genChampionship(FamSeasonCompetition entity, Boolean bFixture) {

        LOGGER.debug("genChampionship " + entity.getDisplayName() + " " + bFixture);
        // On sauvegarde la conf de la competition
        this.edit(entity);

        if (!entity.getFamTypCompetition().getIsChampionship()) {
            // ERREUR! ce n'est pas un championat!
            throw new IllegalArgumentException();
        }

        if (bFixture) {
            LOGGER.debug("GENERATION AVEC JOURNEES");
            // On recharge les données
            FamSeasonCompetition famSeasonCompetition = this.find(entity.getIdSeasonCompetition());
            List<FamTeam> teams = famSeasonCompetition.getFamTeamList().isEmpty() ? entity.getFamTeamList() : famSeasonCompetition.getFamTeamList();

            LOGGER.debug("Suppression des journées existantes");
            // Suppression des journées existantes
            ejbFixture.deleteByCompetition(famSeasonCompetition);

            // If odd number of teams add a "ghost".
            boolean ghost = false;
            int nbTeams = teams.size();
            if (nbTeams % 2 == 1) {
                nbTeams++;
                ghost = true;
            }

            // Generate the fixtures using the cyclic algorithm.
            int totalRounds = nbTeams - 1;
            int matchesPerRound = nbTeams / 2;
            String[][] rounds = new String[totalRounds][matchesPerRound];

            for (int round = 0; round < totalRounds; round++) {

                FamFixture fixture = new FamFixture();
                fixture.setLibFixture("J" + String.format("%02d", round + 1));
                fixture.setFamSeasonCompetition(famSeasonCompetition);

                ejbFixture.create(fixture);

                fixture = new FamFixture();
                fixture.setLibFixture("J" + String.format("%02d", totalRounds + round + 1));
                fixture.setFamSeasonCompetition(famSeasonCompetition);

                ejbFixture.create(fixture);

                LOGGER.debug("Suppression des matches existants");
                // Suppression des matches existants
                ejbMatch.deleteByCompetition(famSeasonCompetition);

                LOGGER.debug("Génération des matchs");

                for (int match = 0; match < matchesPerRound; match++) {
                    int home = (round + match) % (nbTeams - 1);
                    int away = (nbTeams - 1 - match + round) % (nbTeams - 1);
                    // Last team stays in the same place while the others
                    // rotate around it.
                    if (match == 0) {
                        away = nbTeams - 1;
                    }
                    // Add one so teams are number 1 to teams not 0 to teams - 1
                    // upon display.
                    rounds[round][match] = (home + 1) + " v " + (away + 1);
                }
            }

            // Interleave so that home and away games are fairly evenly dispersed.
            String[][] interleaved = new String[totalRounds][matchesPerRound];

            int evn = 0;
            int odd = (nbTeams / 2);
            for (int i = 0; i < rounds.length; i++) {
                if (i % 2 == 0) {
                    interleaved[i] = rounds[evn++];
                } else {
                    interleaved[i] = rounds[odd++];
                }
            }

            rounds = interleaved;

            // Last team can't be away for every game so flip them
            // to home on odd rounds.
            for (int round = 0; round < rounds.length; round++) {
                if (round % 2 == 1) {
                    rounds[round][0] = flip(rounds[round][0]);
                }
            }

            // Display the fixtures
            for (int i = 0; i < rounds.length; i++) {
                LOGGER.debug("Round " + (i + 1));
                LOGGER.debug(Arrays.asList(rounds[i]).toString());

                // Get Fixture
                String libFixture = "J" + String.format("%02d", i + 1);
                FamFixture fixtureA = findByLibAndCompetition(libFixture, famSeasonCompetition);
                libFixture = "J" + String.format("%02d", (rounds.length * 2) - i);
                FamFixture fixtureR = findByLibAndCompetition(libFixture, famSeasonCompetition);

                for (int j = 0; j < (rounds[i]).length; j++) {
                    String[] components = rounds[i][j].split(" v ");
                    Integer idxHome = Integer.parseInt(components[0]);
                    Integer idxAway = Integer.parseInt(components[1]);
                    FamTeam home = teams.get(idxHome - 1);
                    FamTeam visitor = teams.get(idxAway - 1);

                    // match Aller
                    createMatchForFixture(fixtureA, home, visitor);
                    // match Retour
                    createMatchForFixture(fixtureR, visitor, home);

                }
            }


            if (ghost) {
                LOGGER.debug("Matches against team " + teams + " are byes.");
            }

            LOGGER.debug("Use mirror image of these rounds for return fixtures.");
//            for ( int journee = 1; journee <= teams.size()-1; ++journee )
//            {
//               LOGGER.debug("-----------------------");
//                for (int equipe = 0; equipe < teams.size(); ++equipe) {
//                    int idxHome = equipe;
//                    int idxVisitor = teams.size() - 1 - ( equipe + journee - 1 ) % teams.size() ;
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("J").append(journee).append( " : [")
//                            .append(teams.get(idxHome).getLibTeam()).append("] joue contre [")
//                            .append(teams.get(idxVisitor).getLibTeam()).append("]");
//                   LOGGER.debug(sb.toString());
//                }
//            }

        } else {
            genChampionship(entity);
        }
    }

    private void createMatchForFixture(FamFixture fixture, FamTeam home, FamTeam visitor) {
        FamTypEvent typEvent = ejbTypEvent.getMatch();
//        FamEventStatus status = ejbEventStatus.getScheduled();

        StringBuilder sb = new StringBuilder();
        sb.append(home.getLibTeam()).append(" - ").append(visitor.getLibTeam());
        String lib = sb.toString();
        LOGGER.debug(lib);

        LOGGER.debug("Event");
        // Event
        FamEvent eventMatch = ejbEvent.newEvent();
        eventMatch.setFamTypEvent(typEvent);

//        eventMatch.setFamEventStatus(status);

        eventMatch.setLibEvent(lib);

        eventMatch.setDtEvent(new Date());

        List<FamTeam> teamSet = new ArrayList<FamTeam>();
        teamSet.add(home);
        teamSet.add(visitor);
        eventMatch.setFamTeamList(teamSet);

        Integer duration = fixture.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getPeriodDuration() * 2;
        eventMatch.setDuration(duration);

        eventMatch.setFamSeason(fixture.getFamSeasonCompetition().getFamSeason());

        eventMatch.setAllDay(Boolean.FALSE);

        sb = new StringBuilder();
        sb.append(fixture.getFamSeasonCompetition().getDisplayName()).append(" - ").append(eventMatch.getLibEvent());
        eventMatch.setComments(sb.toString());

        if (home.getFamPlace() != null) {
            eventMatch.setFamPlace(home.getFamPlace());
        }

        LOGGER.debug("EventMatch " + eventMatch);
        ejbEvent.create(eventMatch);

        // Match
        FamMatch match = new FamMatch();
        match.setFamSeasonCompetition(fixture.getFamSeasonCompetition());
        match.setFamEvent(eventMatch);
        match.setFamFixture(fixture);
        ejbMatch.create(match);

        LOGGER.debug("MAJ MatchTeam");
        // MatchTeam
        match.setFamMatchTeamList(new ArrayList<FamMatchTeam>());

        FamMatchTeam matchTeamHome = new FamMatchTeam();
        matchTeamHome.setFamTeam(home);
        matchTeamHome.setHome(Boolean.TRUE);
        matchTeamHome.setFamMatch(match);
        match.getFamMatchTeamList().add(matchTeamHome);
//                    ejbMatchTeam.create(matchTeamHome);
        ejbMatch.edit(match);

//                   LOGGER.debug("match " + match.toString());
//                   LOGGER.debug("match from matchTeamHome" + matchTeamHome.getFamMatch().toString());

        FamMatchTeam matchTeamVisitor = new FamMatchTeam();
        matchTeamVisitor.setFamTeam(visitor);
        matchTeamVisitor.setHome(Boolean.FALSE);
        matchTeamVisitor.setFamMatch(match);
        match.getFamMatchTeamList().add(matchTeamVisitor);
//                    ejbMatchTeam.create(matchTeamVisitor);
        ejbMatch.edit(match);
    }

    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

    /**
     * @param entity
     */
    public void genChampionship(FamSeasonCompetition entity) throws IllegalArgumentException {

//#include <iostream>
//#include <string>
//using namespace std;
// 
//// Les équipes
//string Equipes[] = {
//    "Olympique Lyonnais",       "RC Lens",
//    "Olympique de Marseille",   "FC Sochaux",
//    "Lille OSC",                "Saint-Etienne",
//    "AS Nancy",                 "Girondins de Bordeaux",
//    "Toulouse FC",              "Le Mans",
//    "AJ Auxerre",               "Rennes",
//    "FC Lorient",               "Valenciennes",
//    "AS Monaco",                "FC Nantes",
//    "Paris Saint-Germain",      "ES Troyes AC",
//    "OGC Nice",                 "CS Sedan Ardennes"
//};
// 
//const int NombreEquipes = sizeof( Equipes ) /sizeof( Equipes[0] );
// 
//// -----------------------------------------------------------
//pair< int, int > MatchJournee( int numEquipe, int journee )
//{
//    return pair<int, int>( numEquipe, 
//                           NombreEquipes - 1 - ( numEquipe + journee - 1 ) % NombreEquipes );
//}
// 
//// -----------------------------------------------------------
//int main()
//{
//    for ( int journee = 1; journee <= NombreEquipes-1; ++journee )
//    {
//        cout << "----------------------------------\n";
//        for ( int equipe = 0; equipe < NombreEquipes; ++equipe )
//        {
//            pair< int, int > match = MatchJournee( equipe, journee );
//            cout << "Jour " << journee << " : ["
//                 << Equipes[ match.first ] << "] joue contre [" 
//                 << Equipes[ match.second ] << "]"
//                 << endl;
//        }
//    }
//}
        LOGGER.debug("GENERATION SANS JOURNEES");

        // On sauvegarde la conf de la competition
        this.edit(entity);

        if (!entity.getFamTypCompetition().getIsChampionship()) {
            LOGGER.error("Ce n'est pas un championat!");
            // ERREUR! ce n'est pas un championat!
            throw new IllegalArgumentException();
        }

        // On recharge les données
        FamSeasonCompetition famSeasonCompetition = this.find(entity.getIdSeasonCompetition());
        List<FamTeam> teams = famSeasonCompetition.getFamTeamList();

        LOGGER.debug("Equipes de la competition");
        for (FamTeam t : teams) {
            LOGGER.debug(t.getLibTeam());
        }

        LOGGER.debug("Suppression des journées existantes");
        // Suppression des journées existantes
        ejbFixture.deleteByCompetition(famSeasonCompetition);

        LOGGER.debug("Génération des journées");
        // Génération des journées
        for (int i = 0; i < (teams.size() - 1) * 2; i++) {
            // Journée
            FamFixture fixture = new FamFixture();
            fixture.setLibFixture("J" + String.format("%02d", i + 1));
            fixture.setFamSeasonCompetition(famSeasonCompetition);

            ejbFixture.create(fixture);

        }
        LOGGER.debug((teams.size() - 1) * 2 + " journées générées");

        FamTypEvent typEvent = ejbTypEvent.getMatch();
//        FamEventStatus status = ejbEventStatus.getScheduled();

        LOGGER.debug("Suppression des matches existants");
        // Suppression des matches existants
        ejbMatch.deleteByCompetition(famSeasonCompetition);

        LOGGER.debug("Génération des matchs");
        // Génération des matchs
        int i = 0;
        for (FamTeam home : teams) {
            for (FamTeam visitor : teams) {
                if (!home.equals(visitor)) {
                    i++;
                    LOGGER.debug("===============================");
                    LOGGER.debug("Match " + i);
                    StringBuilder sb = new StringBuilder();
                    sb.append(home.getLibTeam()).append(" - ").append(visitor.getLibTeam());
                    String lib = sb.toString();
                    LOGGER.debug(lib);

                    LOGGER.debug("Event");
                    // Event
                    FamEvent eventMatch = ejbEvent.newEvent();
                    eventMatch.setFamTypEvent(typEvent);

//                    eventMatch.setFamEventStatus(status);

                    eventMatch.setLibEvent(lib);

                    eventMatch.setDtEvent(new Date());

                    List<FamTeam> teamSet = new ArrayList<FamTeam>();
                    teamSet.add(home);
                    teamSet.add(visitor);
                    eventMatch.setFamTeamList(teamSet);

                    Integer duration = entity.getFamTypCompetition().getFamTypMatch().getPeriodDuration() * 2;
                    eventMatch.setDuration(duration);

                    eventMatch.setFamSeason(entity.getFamSeason());

                    eventMatch.setAllDay(Boolean.FALSE);

                    sb = new StringBuilder();
                    sb.append(famSeasonCompetition.getDisplayName()).append(" - ").append(eventMatch.getLibEvent());
                    eventMatch.setComments(sb.toString());

                    if (home.getFamPlace() != null) {
                        eventMatch.setFamPlace(home.getFamPlace());
                    }

                    LOGGER.debug("EventMatch " + eventMatch);
                    ejbEvent.create(eventMatch);

                    // Match
                    FamMatch match = new FamMatch();
                    match.setFamSeasonCompetition(famSeasonCompetition);
                    match.setFamEvent(eventMatch);

                    ejbMatch.create(match);

                    LOGGER.debug("MAJ MatchTeam");
                    // MatchTeam
                    match.setFamMatchTeamList(new ArrayList<FamMatchTeam>());

                    FamMatchTeam matchTeamHome = new FamMatchTeam();
                    matchTeamHome.setFamTeam(home);
                    matchTeamHome.setHome(Boolean.TRUE);
                    matchTeamHome.setFamMatch(match);
                    match.getFamMatchTeamList().add(matchTeamHome);
//                    ejbMatchTeam.create(matchTeamHome);
                    ejbMatch.edit(match);

//                   LOGGER.debug("match " + match.toString());
//                   LOGGER.debug("match from matchTeamHome" + matchTeamHome.getFamMatch().toString());

                    FamMatchTeam matchTeamVisitor = new FamMatchTeam();
                    matchTeamVisitor.setFamTeam(visitor);
                    matchTeamVisitor.setHome(Boolean.FALSE);
                    matchTeamVisitor.setFamMatch(match);
                    match.getFamMatchTeamList().add(matchTeamVisitor);
//                    ejbMatchTeam.create(matchTeamVisitor);
                    ejbMatch.edit(match);

//                   LOGGER.debug("match " + match.toString());
//                   LOGGER.debug("match from matchTeamVisitor" + matchTeamVisitor.getFamMatch().toString());

                }
            }
        }
    }
}
