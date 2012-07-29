package org.fam.jsf.controller;

import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.model.FamTeam;
import org.fam.ejb.session.FamFixtureFacade;
import org.fam.ejb.session.FamSeasonCompetitionFacade;
import org.fam.ejb.session.FamTeamFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

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

    @Inject
    private Logger LOGGER;

    @EJB
    private FamSeasonCompetitionFacade ejbFacade;
    @EJB
    private FamTeamFacade ejbTeam;
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
    public String loadAction() {
        String str = super.loadAction();
        loadTeams();
        return str;
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
        if (teams != null) {
            this.teams = teams;
        }
    }

    @Override
    public String update() {
        getTargetTeams();
        return super.update();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String create() {
        getTargetTeams();
        return super.create();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void getTargetTeams() {
        List<FamTeam> set = new ArrayList<FamTeam>(teams.getTarget());

//        List<FamPlayerPosition> list = new ArrayList<FamPlayerPosition>();
        Integer i = 1;
        if (current.getFamTeamList() == null) {
            current.setFamTeamList(new ArrayList<FamTeam>());
        }
        current.getFamTeamList().clear();
        current.getFamTeamList().addAll(set);
//        return list;
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
        loadTeams();
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

    public void loadTeams() {
        LOGGER.debug("loadTeams");
        List<FamTeam> source = new ArrayList<FamTeam>();
        List<FamTeam> target = new ArrayList<FamTeam>();
        if (current != null) {
            source.addAll(ejbTeam.findByCategory(current.getFamCategory()));

            if (current.getFamTeamList() != null) {
                source.removeAll(current.getFamTeamList());
            }

            if (current.getFamTeamList() != null) {
                target.addAll(current.getFamTeamList());
            }
            LOGGER.debug("teams source : {}", source.size());
            LOGGER.debug("teams target : {}", target.size());
        }
        teams = new DualListModel<FamTeam>(source, target);
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
