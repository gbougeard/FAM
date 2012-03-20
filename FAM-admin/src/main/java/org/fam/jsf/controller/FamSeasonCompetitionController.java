package org.fam.jsf.controller;

import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.model.FamTeam;
import org.fam.ejb.session.FamFixtureFacade;
import org.fam.ejb.session.FamSeasonCompetitionFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "famSeasonCompetitionController")
@ViewScoped
public class FamSeasonCompetitionController extends AbstractController<FamSeasonCompetition> implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamSeasonCompetitionController.class);

    @EJB
    private FamSeasonCompetitionFacade ejbFacade;
    //
    private DualListModel<FamTeam> teams;
    @Inject
    private CacheBean cacheBean;
    @Inject
    private CachePlayer cachePlayer;
    @EJB
    private FamFixtureFacade ejbFixture;
    private Boolean warningExisting;
    private Boolean genFixture = Boolean.FALSE;


    public FamSeasonCompetitionController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOGGER.debug(this.getClass() + " - postConstruct");
    }

    @PreDestroy
    private void preDestroy() {
        LOGGER.debug(this.getClass() + " - preDestroy");
    }

    @Override
    public FamSeasonCompetition getSelected() {
        if (current == null) {
            current = new FamSeasonCompetition();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamSeasonCompetitionFacade getFacade() {
        return ejbFacade;
    }

    public DualListModel<FamTeam> getTeams() {
        return teams;
    }

    public Boolean getGenFixture() {
        return genFixture;
    }

    public void setGenFixture(Boolean genFixture) {
        this.genFixture = genFixture;
    }

    public CacheBean getCacheBean() {
        return cacheBean;
    }

    public void setCacheBean(CacheBean cacheBean) {
        this.cacheBean = cacheBean;
    }

    public void setTeams(DualListModel<FamTeam> teams) {
        LOGGER.debug("setTeams src" + teams.getSource().size());
        LOGGER.debug("setTeams trg" + teams.getTarget().size());
        if (teams != null) {
            this.teams = teams;
        }
    }

    public Boolean getWarningExisting() {
        return warningExisting;
    }

    public String generateChampionship() {
        List<FamTeam> set = new ArrayList<FamTeam>(teams.getTarget());
        current.setFamTeamList(set);
        ejbFacade.genChampionship(current, Boolean.TRUE);

        JsfUtil.addSuccessMessage("Génération terminée");

        return super.prepareList();
    }

    public Boolean getIsChampionship() {
        Boolean bRes = Boolean.FALSE;
        if (current != null) {
            if (current.getFamTypCompetition() != null) {
                bRes = current.getFamTypCompetition().getIsChampionship();
            }
        }

        return bRes;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdSeasonCompetition();
        return "pretty:editSeasonCompetition";
    }

    @Override
    public String prepareView() {
        id = current.getIdSeasonCompetition();
        return "pretty:viewSeasonCompetition";
    }

    @Override
    public String prepareList() {
        return "pretty:listSeasonCompetition";
    }

    @Override
    public String prepareCreate() {
        current = new FamSeasonCompetition();
        selectedItemIndex = -1;
        return "pretty:createSeasonCompetition";
    }

    public void initForWizard() {
        super.loadAction();
        if (cacheBean == null) {
            LOGGER.warn("cacheBean NULL");
        }
        List<FamTeam> source = new ArrayList<FamTeam>();
        source.addAll(cachePlayer.getListTeam());
        if (current.getFamTeamList() != null) {
            source.removeAll(current.getFamTeamList());
        }

        List<FamTeam> target = new ArrayList<FamTeam>();
        if (current.getFamTeamList() != null) {
            target.addAll(current.getFamTeamList());
        }

        teams = new DualListModel<FamTeam>(source, target);
        warningExisting = (ejbFixture.findByCompetition(current).size() > 0);
    }

    public String prepareWizard() {
        id = current.getIdSeasonCompetition();
        initForWizard();
        return "pretty:wizardSeasonCompetition";
    }

    public String onFlowProcess(FlowEvent event) {
//        logger.info("Current wizard step:" + event.getOldStep());  
//        logger.info("Next step:" + event.getNewStep());  

//        if(skip) {  
//            skip = false;   //reset in case user goes back  
//            return "confirm";  
//        }  
//        else {  
//            return event.getNewStep();  
//        }  
        return event.getNewStep();
    }
//    @Override
//    public void onRowSelect(SelectEvent event) {
//        super.onRowSelect(event);
//        FamSeasonCompetition o = (FamSeasonCompetition) event.getObject();
//        StringBuilder sb = new StringBuilder();
//        sb.append("RowSelect ").append(o.getDisplayName());
//        JsfUtil.addSuccessMessage(sb.toString());
//    }
//
//    @Override
//    public void onRowUnselect(UnselectEvent event) {
//        super.onRowUnselect(event);
//        FamSeasonCompetition o = (FamSeasonCompetition) event.getObject();
//        StringBuilder sb = new StringBuilder();
//        sb.append("RowUnselect ").append(o.getDisplayName());
//        JsfUtil.addSuccessMessage(sb.toString());
//    }

    public void setCachePlayer(CachePlayer cachePlayer) {
        this.cachePlayer = cachePlayer;
    }
}
