/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.jsf.cache;

import org.fam.common.log.LogUtil;
import org.fam.ejb.event.DBUpdated;
import org.fam.ejb.model.*;
import org.fam.ejb.session.*;
import org.fam.jsf.event.FamPlaceUpdated;
import org.fam.jsf.event.InitDataDone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.*;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */

/**
 * @author arungupta
 */
@Singleton
@Named
@ApplicationScoped
@Startup
public class CacheBean {

    private FamSeason currentSeason;
    private FamTypEvent typEventMatch;
    //
    private Locale locale = Locale.FRANCE;
    private TimeZone timeZone = TimeZone.getDefault();
    //
    Boolean debug = Boolean.TRUE;
    // CLUBS
    @EJB
    private FamClubFacade ejbClub;
    private final List<FamClub> listClub = new ArrayList<FamClub>();
    // TEAMS
    @EJB
    private FamTeamFacade ejbTeam;
    private final List<FamTeam> listTeam = new ArrayList<FamTeam>();
    // ORGANIZATIONS
    @EJB
    private FamOrganizationFacade ejbOrganization;
    private final List<FamOrganization> listOrganization = new ArrayList<FamOrganization>();
    // TEAMS
    @EJB
    private FamSeasonFacade ejbSeason;
    private final List<FamSeason> listSeason = new ArrayList<FamSeason>();
    // PLACES
    @EJB
    private FamPlaceFacade ejbPlace;
    private final List<FamPlace> listPlace = new ArrayList<FamPlace>();
    // TYPE PLACES
    @EJB
    private FamTypPlaceFacade ejbTypPlace;
    private final List<FamTypPlace> listTypPlace = new ArrayList<FamTypPlace>();
    // TYPE EVENT
    @EJB
    private FamTypEventFacade ejbTypEvent;
    private final List<FamTypEvent> listTypEvent = new ArrayList<FamTypEvent>();
    // EVENT STATUS
    @EJB
    private FamEventStatusFacade ejbEventStatus;
    private final List<FamEventStatus> listEventStatus = new ArrayList<FamEventStatus>();
    FamEventStatus eventStatusScheduled;
    // TYP COMPETITION
    @EJB
    private FamTypCompetitionFacade ejbTypCompetition;
    private final List<FamTypCompetition> listTypCompetition = new ArrayList<FamTypCompetition>();
    // SCALE
    @EJB
    private FamScaleFacade ejbScale;
    private final List<FamScale> listScale = new ArrayList<FamScale>();
    // COMPETITION
    @EJB
    private FamSeasonCompetitionFacade ejbSeasonCompetition;
    private final List<FamSeasonCompetition> listSeasonCompetition = new ArrayList<FamSeasonCompetition>();
    // PLAYER
    @EJB
    private FamPlayerFacade ejbPlayer;
    private final List<FamPlayer> listPlayer = new ArrayList<FamPlayer>();
    // COMPETITION
    @EJB
    private FamUserFacade ejbUser;
    private final List<FamUser> listUser = new ArrayList<FamUser>();
    // POSITION
    @EJB
    private FamPositionFacade ejbPosition;
    private final List<FamPosition> listPosition = new ArrayList<FamPosition>();
    // TYP MATCH
    @EJB
    private FamTypMatchFacade ejbTypMatch;
    private final List<FamTypMatch> listTypMatch = new ArrayList<FamTypMatch>();
    // TYP ANSWER
    @EJB
    private FamTypAnswerFacade ejbTypAnswer;
    private final List<FamTypAnswer> listTypAnswer = new ArrayList<FamTypAnswer>();
    // COUNTRY
    @EJB
    private FamCountryFacade ejbCountry;
    private final List<FamCountry> listCountry = new ArrayList<FamCountry>();
    // COUNTRY
    @EJB
    private FamStateFacade ejbState;
    private final List<FamState> listState = new ArrayList<FamState>();
    // COUNTRY
    @EJB
    private FamProvinceFacade ejbProvince;
    private final List<FamProvince> listProvince = new ArrayList<FamProvince>();
    // COUNTRY
    @EJB
    private FamCityFacade ejbCity;
    private final List<FamCity> listCity = new ArrayList<FamCity>();
    // TYPE EVENT
    @EJB
    private FamTypCardFacade ejbTypCard;
    private final List<FamTypCard> listTypCard = new ArrayList<FamTypCard>();
    // TYPE EVENT
    @EJB
    private FamFormationFacade ejbFormation;
    private final List<FamFormation> listFormation = new ArrayList<FamFormation>();

    public CacheBean() {
    }

    @PostConstruct
    void postConstruct() {
        LogUtil.log("IN: CacheBean:PostConstruct", Level.INFO, null);
        //Initialisation du cache
        initCache();
    }

    // CDI event to update the cache
    public void onFamPlaceUpdated(@Observes FamPlaceUpdated updated) {
        // TODO: Get the state from "updated" to update the cache
        LogUtil.log("cacheBean::onFamPlaceUpdated ", Level.INFO, null);
        loadAllPlaces();
    }

    // CDI event to update the cache
    public void onDBUpdated(@Observes(during = TransactionPhase.AFTER_COMPLETION) DBUpdated updated) {
        // TODO: Get the state from "updated" to update the cache
        LogUtil.log("cacheBean::onDBUpdated ", Level.INFO, null);
    }

    public void onUpdatedClub(@Observes(during = TransactionPhase.AFTER_SUCCESS,
            notifyObserver = Reception.IF_EXISTS) final FamClub famEntity) {
        LogUtil.log("cacheBean::onUpdatedClub during AFTER SUCCESS", Level.INFO, null);
        loadAllClubs();
    }

    public void onUpdatedTeam(@Observes(notifyObserver = Reception.IF_EXISTS) final FamTeam famEntity) {
        LogUtil.log("cacheBean::onUpdatedTeam ", Level.INFO, null);
        loadAllTeams();
    }

    public void onUpdatedOrganization(@Observes(notifyObserver = Reception.IF_EXISTS) final FamOrganization famEntity) {
        LogUtil.log("cacheBean::onUpdatedOrganization ", Level.INFO, null);
        loadAllOrganizations();
    }

    public void onUpdatedSeason(@Observes(notifyObserver = Reception.IF_EXISTS) final FamSeason famEntity) {
        LogUtil.log("cacheBean::onUpdatedSeason ", Level.INFO, null);
        loadAllSeasons();
    }

    public void onUpdatedFormation(@Observes(notifyObserver = Reception.IF_EXISTS) final FamFormation famEntity) {
        LogUtil.log("cacheBean::onUpdatedFormation ", Level.INFO, null);
        loadAllFormations();
    }

    public void onInitDataDone(@Observes InitDataDone event) {
        // TODO: Get the state from "updated" to update the cache
        LogUtil.log("cacheBean::onInitDataDone ", Level.INFO, null);
        initCache();
    }
    // Update the cache every 30 seconds
//    @Schedule(hour = "*", minute = "*", second = "*/30")
//    @LoggingInterceptor

    public void initCache() {
        LogUtil.log("IN: updateCache", Level.INFO, null);
        // CLUBS
        loadAllClubs();
        // TEAMS
        loadAllTeams();
        // ORGANIZATIONS
        loadAllOrganizations();
        // SEASONS
        loadAllSeasons();
        // PLACES
        loadAllPlaces();
        // TYP PLACES
        loadAllTypPlaces();
        //TYPCARD
        loadAllTypCards();
        // TYP EVENT
        loadAllTypEvents();
        //EVENT STATUS
        loadAllEventStatuses();
        //TYP COMPETITIONS
        loadAllTypCompetitions();
        //SCALES
        loadAllScales();
        //COMPETITIONS
        loadAllCompetitions();
        //PLAYER
        loadAllPlayers();
        //USER
        loadAllUsers();
        //POSITION
        loadAllPositions();
        //TYP MATCH
        loadAllTypMatchs();
        //TYP ANSWER
        loadAllTypAnswers();
        //COUNTRY
        loadAllCountries();
        //STATES
        loadAllStates();
        //PROVINCE
        loadAllProvinces();
        //CITY
        loadAllCities();
        //FORMATION
        loadAllFormations();

    }

    private void loadAllClubs() {
        listClub.clear();

        for (FamClub o : ejbClub.findAll()) {
            listClub.add(o);
        }
    }

    private void loadAllTeams() {
        listTeam.clear();

        for (FamTeam o : ejbTeam.findAll()) {
            listTeam.add(o);
        }
    }

    private void loadAllOrganizations() {
        listOrganization.clear();

        for (FamOrganization o : ejbOrganization.findAll()) {
            listOrganization.add(o);
        }
    }

    private void loadAllSeasons() {
        listSeason.clear();

        for (FamSeason o : ejbSeason.findAll()) {
            listSeason.add(o);
        }
        currentSeason = ejbSeason.getCurrentSeason();
    }

    private void loadAllPlaces() {
        listPlace.clear();

        for (FamPlace o : ejbPlace.findAll()) {
            listPlace.add(o);
        }
    }

    private void loadAllTypPlaces() {
        listTypPlace.clear();

        for (FamTypPlace o : ejbTypPlace.findAll()) {
            listTypPlace.add(o);
        }
    }

    private void loadAllTypEvents() {
        listTypEvent.clear();

        for (FamTypEvent o : ejbTypEvent.findAll()) {
            listTypEvent.add(o);
        }
        typEventMatch = ejbTypEvent.getMatch();
    }

    private void loadAllEventStatuses() {
        listEventStatus.clear();

        for (FamEventStatus o : ejbEventStatus.findAll()) {
            listEventStatus.add(o);
        }
        eventStatusScheduled = ejbEventStatus.getScheduled();
    }

    private void loadAllTypCompetitions() {
        listTypCompetition.clear();

        for (FamTypCompetition o : ejbTypCompetition.findAll()) {
            listTypCompetition.add(o);
        }
    }

    private void loadAllScales() {
        listScale.clear();

        for (FamScale o : ejbScale.findAll()) {
            listScale.add(o);
        }
    }

    private void loadAllCompetitions() {
        listSeasonCompetition.clear();

        for (FamSeasonCompetition o : ejbSeasonCompetition.findAll()) {
            listSeasonCompetition.add(o);
        }
    }

    private void loadAllPlayers() {
        listPlayer.clear();

        for (FamPlayer o : ejbPlayer.findAll()) {
            listPlayer.add(o);
        }
    }

    private void loadAllUsers() {
        listUser.clear();

        for (FamUser o : ejbUser.findAll()) {
            listUser.add(o);
        }
    }

    private void loadAllPositions() {
        listPosition.clear();

        for (FamPosition o : ejbPosition.findAll()) {
            listPosition.add(o);
        }
    }

    private void loadAllTypMatchs() {
        listTypMatch.clear();

        for (FamTypMatch o : ejbTypMatch.findAll()) {
            listTypMatch.add(o);
        }
    }

    private void loadAllTypAnswers() {
        listTypAnswer.clear();

        for (FamTypAnswer o : ejbTypAnswer.findAll()) {
            listTypAnswer.add(o);
        }
    }

    private void loadAllCountries() {
        listCountry.clear();

        for (FamCountry o : ejbCountry.findAll()) {
            listCountry.add(o);
        }
    }

    private void loadAllProvinces() {
        listProvince.clear();

        for (FamProvince o : ejbProvince.findAll()) {
            listProvince.add(o);
        }
    }

    private void loadAllStates() {
        listState.clear();

        for (FamState o : ejbState.findAll()) {
            listState.add(o);
        }
    }

    private void loadAllCities() {
        listCity.clear();

        for (FamCity o : ejbCity.findAll()) {
            listCity.add(o);
        }
    }

    private void loadAllTypCards() {
        listTypCard.clear();

        for (FamTypCard o : ejbTypCard.findAll()) {
            listTypCard.add(o);
        }
    }

    private void loadAllFormations() {
        listFormation.clear();

        for (FamFormation o : ejbFormation.findAll()) {
            listFormation.add(o);
        }
    }

    public List<FamOrganization> getListOrganization() {
        return listOrganization;
    }

    public List<FamSeason> getListSeason() {
        return listSeason;
    }

    public List<FamTeam> getListTeam() {
        return listTeam;
    }

    public List<FamClub> getListClub() {
        return listClub;
    }

    public List<FamPlace> getListPlace() {
        return listPlace;
    }

    public List<FamTypPlace> getListTypPlace() {
        return listTypPlace;
    }

    public List<FamEventStatus> getListEventStatus() {
        return listEventStatus;
    }

    public List<FamTypEvent> getListTypEvent() {
        return listTypEvent;
    }

    public List<FamTypCompetition> getListTypCompetition() {
        return listTypCompetition;
    }

    public List<FamScale> getListScale() {
        return listScale;
    }

    public List<FamSeasonCompetition> getListSeasonCompetition() {
        return listSeasonCompetition;
    }

    @Produces
    @Named(value = "currentSeason")
    public FamSeason getCurrentSeason() {
        return currentSeason;
    }

    public FamEventStatus getEventStatusScheduled() {
        return eventStatusScheduled;
    }

    public FamTypEvent getTypEventMatch() {
        return typEventMatch;
    }

    public Date getNow() {
        return new Date();
    }

    public Locale getLocale() {
        return locale;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public List<FamPlayer> getListPlayer() {
        return listPlayer;
    }

    public List<FamUser> getListUser() {
        return listUser;
    }

    public List<FamPosition> getListPosition() {
        return listPosition;
    }

    public List<FamTypMatch> getListTypMatch() {
        return listTypMatch;
    }

    public List<FamTypAnswer> getListTypAnswer() {
        return listTypAnswer;
    }

    public List<FamCity> getListCity() {
        return listCity;
    }

    public List<FamCountry> getListCountry() {
        return listCountry;
    }

    public List<FamProvince> getListProvince() {
        return listProvince;
    }

    public List<FamState> getListState() {
        return listState;
    }

    public Boolean getDebug() {
        return debug;
    }

    public List<FamTypCard> getListTypCard() {
        return listTypCard;
    }

    public List<FamFormation> getListFormation() {
        return listFormation;
    }
}
