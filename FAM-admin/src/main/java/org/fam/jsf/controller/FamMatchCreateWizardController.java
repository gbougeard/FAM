package org.fam.jsf.controller;

import lombok.Getter;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamFixture;
import org.fam.ejb.model.FamMatch;
import org.fam.ejb.model.FamMatchPlayer;
import org.fam.ejb.model.FamMatchTeam;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.model.FamTeam;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamEventFacade;
import org.fam.ejb.session.FamFixtureFacade;
import org.fam.ejb.session.FamMatchFacade;
import org.fam.ejb.session.FamMatchPlayerFacade;
import org.fam.ejb.session.FamMatchTeamFacade;
import org.fam.ejb.session.FamTeamFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean(name = "famMatchCreateWizardController")
@ViewScoped
@Getter
public class FamMatchCreateWizardController extends AbstractController<FamMatch> {

    @Inject
    Logger LOGGER;
    @Inject
    private FamMatchFacade ejbFacade;
    @Inject
    private FamTeamFacade ejbTeam;
    @Inject
    private FamEventFacade ejbEvent;
    @Inject
    private FamMatchTeamFacade ejbMatchTeam;
    @Inject
    private FamMatchPlayerFacade ejbMatchPlayer;
    @Inject
    private FamFixtureFacade ejbFixture;
    //
    @Inject
    private CacheBean cacheBean;
    @Inject
    private CachePlayer cachePlayer;
    //
    @Inject
    @LoggedIn
    private FamUser currentUser;
    @Inject
    @Player
    private FamPlayer currentPlayer;
    //
    private FamMatchTeam matchTeamHome = new FamMatchTeam();
    private FamMatchTeam matchTeamAway = new FamMatchTeam();
    private FamEvent famEvent = new FamEvent();

    private List<FamFixture> fixtureList = new ArrayList<FamFixture>();
    //
    private Long idTeam;
    private FamTeam famTeam;
    //

    private List<FamMatchPlayer> famMatchPlayerList = new ArrayList<FamMatchPlayer>();

    //
    //
    private FamMatchTeam famMatchTeam;
    private FamSeasonCompetition famSeasonCompetition;
    private List<FamTeam> famTeamList;
    //
    private int nbTit;
    private int nbSub;
    private int nbPlayers;


    private boolean skip;


    public FamMatchCreateWizardController() {
    }

    @PostConstruct
    private void postConstruct() {
        prepareCreate();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamMatch getSelected() {
        if (current == null) {
            current = new FamMatch();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamMatchFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareCreate() {
        current = new FamMatch();
        matchTeamHome = new FamMatchTeam();
        matchTeamAway = new FamMatchTeam();
        matchTeamHome.setHome(Boolean.TRUE);
        matchTeamAway.setHome(Boolean.FALSE);

        famEvent = ejbEvent.newEvent();
        famEvent.setFamTypEvent(cacheBean.getTypEventMatch());

        selectedItemIndex = -1;
        return "pretty:createMatch";
    }

    @Override
    public String create() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(matchTeamHome.getFamTeam().getLibTeam()).append(" - ").append(matchTeamAway.getFamTeam().getLibTeam());
            famEvent.setLibEvent(sb.toString());
            List<FamTeam> teams = new ArrayList<FamTeam>();
            teams.add(matchTeamHome.getFamTeam());
            teams.add(matchTeamAway.getFamTeam());
            famEvent.setFamTeamList(teams);
            famEvent.setDuration(current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getPeriodDuration() * 2);
            current.setFamEvent(famEvent);

            List<FamMatchTeam> mt = new ArrayList<FamMatchTeam>();
            mt.add(matchTeamHome);
            mt.add(matchTeamAway);
            current.setFamMatchTeamList(mt);

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamMatchCreated"));
            return prepareView();
        } catch (Exception e) {
            LOGGER.error("Error while creating match", e);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    public String prepareEdit() {
        loadForEdit();
        id = current.getIdMatch();
        return "pretty:editMatch";
    }

    public void loadForEdit() {
        super.loadAction();

        if (current.getFamSeasonCompetition().getFamTypCompetition().getIsChampionship()) {
            fixtureList = ejbFixture.findByCompetition(current.getFamSeasonCompetition());
        }
    }

    @Override
    public String prepareView() {
        id = current.getIdMatch();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("matchId " + id);
        }
        return "pretty:viewMatch";
    }

    @Override
    public String prepareList() {
        return "pretty:listMatch";
    }

    public String onFlowProcess(FlowEvent event) {
        LOGGER.info("Current wizard step:" + event.getOldStep());
        LOGGER.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }


    public void test() {
        String msg = "OUU";
        LOGGER.info("HELL YEAH!");

        JsfUtil.addInfoMessage("test", msg);
    }

    public void setEjbFacade(FamMatchFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public void setEjbTeam(FamTeamFacade ejbTeam) {
        this.ejbTeam = ejbTeam;
    }

    public void setEjbEvent(FamEventFacade ejbEvent) {
        this.ejbEvent = ejbEvent;
    }

    public void setEjbMatchTeam(FamMatchTeamFacade ejbMatchTeam) {
        this.ejbMatchTeam = ejbMatchTeam;
    }

    public void setEjbMatchPlayer(FamMatchPlayerFacade ejbMatchPlayer) {
        this.ejbMatchPlayer = ejbMatchPlayer;
    }

    public void setEjbFixture(FamFixtureFacade ejbFixture) {
        this.ejbFixture = ejbFixture;
    }

    public void setCacheBean(CacheBean cacheBean) {
        this.cacheBean = cacheBean;
    }

    public void setCachePlayer(CachePlayer cachePlayer) {
        this.cachePlayer = cachePlayer;
    }

    public void setCurrentUser(FamUser currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentPlayer(FamPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setMatchTeamHome(FamMatchTeam matchTeamHome) {
        this.matchTeamHome = matchTeamHome;
    }

    public void setMatchTeamAway(FamMatchTeam matchTeamAway) {
        this.matchTeamAway = matchTeamAway;
    }

    public void setFamEvent(FamEvent famEvent) {
        this.famEvent = famEvent;
    }

    public void setFixtureList(List<FamFixture> fixtureList) {
        this.fixtureList = fixtureList;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public void setFamTeam(FamTeam famTeam) {
        this.famTeam = famTeam;
    }

    public void setFamMatchPlayerList(List<FamMatchPlayer> famMatchPlayerList) {
        this.famMatchPlayerList = famMatchPlayerList;
    }

    public void setFamMatchTeam(FamMatchTeam famMatchTeam) {
        this.famMatchTeam = famMatchTeam;
    }

    public void setFamSeasonCompetition(FamSeasonCompetition famSeasonCompetition) {
        this.famSeasonCompetition = famSeasonCompetition;
        current.setFamSeasonCompetition(famSeasonCompetition);
        famTeamList = famSeasonCompetition.getFamTeamList();
    }

    public void setNbTit(int nbTit) {
        this.nbTit = nbTit;
    }

    public void setNbSub(int nbSub) {
        this.nbSub = nbSub;
    }

    public void setNbPlayers(int nbPlayers) {
        this.nbPlayers = nbPlayers;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
