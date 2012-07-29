package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.model.FamCard;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamFixture;
import org.fam.ejb.model.FamGoal;
import org.fam.ejb.model.FamMatch;
import org.fam.ejb.model.FamMatchPlayer;
import org.fam.ejb.model.FamMatchTeam;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamSubstitution;
import org.fam.ejb.model.FamTeam;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamAnswerFacade;
import org.fam.ejb.session.FamEventFacade;
import org.fam.ejb.session.FamMatchFacade;
import org.fam.ejb.session.FamMatchPlayerFacade;
import org.fam.ejb.session.FamMatchTeamFacade;
import org.fam.ejb.session.FamTeamFacade;
import org.fam.jsf.bean.CanvasFormationItem;
import org.fam.jsf.bean.FamPlayerDataModel;
import org.fam.jsf.bean.TeamComposition;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean(name = "famMatchComposeWizardController")
@ViewScoped
@Loggable
@Getter
@Setter
public class FamMatchComposeWizardController extends AbstractController<FamMatch> {

    @Inject
    Logger LOGGER;
    @Inject
    private FamMatchFacade ejbFacade;
    @Inject
    private FamAnswerFacade ejbAnswer;
    @Inject
    private FamTeamFacade ejbTeam;
    @Inject
    private FamEventFacade ejbEvent;
    @Inject
    private FamMatchTeamFacade ejbMatchTeam;
    @Inject
    private FamMatchPlayerFacade ejbMatchPlayer;
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
    private List<TeamComposition> lstTeamComposition;

    @NotNull
    private TeamComposition teamComposition;
    private List<FamMatchPlayer> famMatchPlayerList = new ArrayList<FamMatchPlayer>();

    //    private List<FamAnswer> lstYes;
//    private List<FamAnswer> lstNo;
//    private List<FamAnswer> lstMaybe;
//    private List<FamPlayer> lstPlayer;
//    private FamAnswer[] selectedYes;
//    private List<FamAnswer> selectedNo;
//    private List<FamAnswer> selectedMaybe;
//    private FamPlayer[] selectedPlayers;
//    //
//    private FamPlayer selectedPlayer;
//    private List<FamPlayer> players;
//    private List<FamPlayer> preselectedLst;
    //
    private List<FamGoal> lstGoal;
    private FamGoal famGoal;
    private List<FamCard> lstCard;
    private FamCard famCard;
    private List<FamSubstitution> lstSubstitution;
    private FamSubstitution famSubstitution;
    //
    private FamMatchTeam famMatchTeam;
    //
//    private List<CanvasFormationItem> lstTarget = new ArrayList<CanvasFormationItem>();
//    private List<CanvasFormationItem> lstSubs = new ArrayList<CanvasFormationItem>();
    //    //
//    private DualListModel<FamPlayer> dlmPlayer;
//    //
    private int nbTit;
    private int nbSub;
    private int nbPlayers;

    private UIComponent userListComponent = null;
    private UIComponent selectedComponent = null;

    private boolean skip;

//    //
//    private FamPlayerDataModel playerDM;

    public FamMatchComposeWizardController() {
    }

    @PostConstruct
    private void postConstruct() {

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
            current.setFamEvent(famEvent);

            List<FamMatchTeam> mt = new ArrayList<FamMatchTeam>();
            mt.add(matchTeamHome);
            mt.add(matchTeamAway);
            current.setFamMatchTeamList(mt);

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamMatchCreated"));
            return prepareView();
        } catch (Exception e) {
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

//        if (current.getFamSeasonCompetition().getFamTypCompetition().getIsChampionship()) {
//            fixtureList = ejbFixture.findByCompetition(current.getFamSeasonCompetition());
//        }
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

    public String prepareCompose() {
        id = current.getIdMatch();
        //findTeam();
        return "pretty:composeMatch";
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

    public void loadForCompose() {

        super.loadAction();
        //findTeam();

//        lstTeamComposition = new ArrayList<TeamComposition>();
//
//        for (FamMatchTeam fmt : current.getFamMatchTeamList()) {
//            lstTeamComposition.add(loadTeamComposition(fmt.getFamTeam()));
//        }
//        nbTit = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbPlayer();
//        nbSub = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbSubstitute();
//        nbPlayers = nbTit + nbSub;
//        for (TeamComposition teamComposition : lstTeamComposition) {
//            teamComposition.setNbPlayers(nbPlayers);
//            teamComposition.setNbTit(nbTit);
//            teamComposition.setNbSub(nbSub);
//            teamComposition.genTarget();
//        }

//        for (int i = 1; i <= nbTit; i++) {
//            CanvasFormationItem cfi = new CanvasFormationItem();
//            cfi.setStrIdx(String.valueOf(i));
//            lstTarget.add(cfi);
//        }
//        for (int i = nbTit + 1; i <= nbPlayers; i++) {
//            CanvasFormationItem cfi = new CanvasFormationItem();
//            cfi.setStrIdx(String.valueOf(i));
//            lstSubs.add(cfi);
//        }

    }

    public void loadTeamComposition() {

        teamComposition = new TeamComposition();
        famTeam = famMatchTeam.getFamTeam();
        teamComposition.setFamTeam(famTeam);

        nbTit = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbPlayer();
        nbSub = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbSubstitute();
        nbPlayers = nbTit + nbSub;
        teamComposition.setNbPlayers(nbPlayers);
        teamComposition.setNbTit(nbTit);
        teamComposition.setNbSub(nbSub);


        for (FamMatchTeam matchTeam : current.getFamMatchTeamList()) {
            if (matchTeam.getFamTeam().equals(famTeam)) {
                teamComposition.setFamMatchTeam(matchTeam);
            }
        }

        teamComposition.getAnswerYes().setLstAnswer(ejbAnswer.findAnswerYesByEventAndTeam(current.getFamEvent(), famTeam));
        LOGGER.info("yes " + teamComposition.getAnswerYes().getLstAnswer().size());
        teamComposition.getAnswerNo().setLstAnswer(ejbAnswer.findAnswerNoByEventAndTeam(current.getFamEvent(), famTeam));
        LOGGER.info("no " + teamComposition.getAnswerNo().getLstAnswer().size());
        teamComposition.getAnswerMaybe().setLstAnswer(ejbAnswer.findAnswerMaybeByEventAndTeam(current.getFamEvent(), famTeam));
        LOGGER.info("maybe " + teamComposition.getAnswerMaybe().getLstAnswer().size());
        teamComposition.getAnswerUngiven().setLstAnswer(ejbAnswer.findByEventAndNoAnswerAndTeam(current.getFamEvent(), famTeam));
        LOGGER.info("nsp " + teamComposition.getAnswerUngiven().getLstAnswer().size());

        teamComposition.setPlayerDM(new FamPlayerDataModel((List<FamPlayer>) teamComposition.getAnswerUngiven().getLstAnswer()));

//        teamComposition.setDlmPlayer(new DualListModel<FamPlayer>(teamComposition.getPreselectedLst(), new ArrayList<FamPlayer>()));

        if (teamComposition.getFamMatchTeam().getFamMatchPlayerList() == null) {
            teamComposition.getFamMatchTeam().setFamMatchPlayerList(new ArrayList<FamMatchPlayer>());
        }
        if (teamComposition.getFamMatchTeam().getFamMatchPlayerList().isEmpty()) {

            for (int i = 1; i <= nbPlayers; i++) {
                FamMatchPlayer item = new FamMatchPlayer();
                item.setNum(i);
                item.setFamMatchTeam(teamComposition.getFamMatchTeam());
                teamComposition.getFamMatchTeam().getFamMatchPlayerList().add(item);
            }
        }

        if (famMatchTeam == null) {
            famMatchTeam = teamComposition.getFamMatchTeam();
        }
//        if (famMatchTeam.getFamMatchPlayerList().isEmpty()) {
//            for (int i = 1; i <= nbPlayers; i++) {
//                FamMatchPlayer fmp = new FamMatchPlayer();
//                fmp.setNum(i);
//                fmp.setFamMatchTeam(famMatchTeam);
//                famMatchTeam.getFamMatchPlayerList().add(fmp);
//            }
//        }
        famMatchPlayerList = famMatchTeam.getFamMatchPlayerList();

        teamComposition.genTarget();

//        return tc;

//        genTarget();
    }


    public void onSelectTeam(SelectEvent event) {
        loadTeamComposition();
//        int i = 1;
//        for (FamPlayer p : dlmPlayer.getTarget()) {
//            for (CanvasFormationItem cfi : lstTarget) {
//                if (cfi.getStrIdx().equals(String.format("%d", i))) {
//
//                    cfi.setFamPlayer(p);
//                    break;
//                }
//            }
//            i++;
//        }
    }

    public void onPreselectDrop(DragDropEvent ddEvent) {

//        FamPlayer player = ((FamPlayer) ddEvent.getData());
//
//        preselectedLst.add(player);
//        lstPlayer.remove(player);
    }

    public int coord(int coord) {
        return coord / 5;
    }

    public void onDrop(DragDropEvent ddEvent) {
        LOGGER.info("onDrop");
        FamPlayer player = ((FamPlayer) ddEvent.getData());
        LOGGER.info("player");
        Integer num = (Integer) ddEvent.getComponent().getAttributes().get("num");

        for (CanvasFormationItem cfi : teamComposition.getLstTarget()) {
            if (cfi.getFamFormationItem() != null
                 && cfi.getFamFormationItem().getNumItem().equals(num)) {

                cfi.setFamPlayer(player);
                break;
            }
        }
//
//        if (famMatchTeam == null) {
//            famMatchTeam = teamComposition.getFamMatchTeam();
//        }
//        if (famMatchTeam.getFamMatchPlayerList().isEmpty()) {
//            for (int i = 1; i <= nbPlayers; i++) {
//                FamMatchPlayer fmp = new FamMatchPlayer();
//                fmp.setNum(i);
//                fmp.setFamMatchTeam(famMatchTeam);
//                famMatchTeam.getFamMatchPlayerList().add(fmp);
//            }
//        }
//        famMatchPlayerList = famMatchTeam.getFamMatchPlayerList();

        for (FamMatchPlayer fmp : famMatchTeam.getFamMatchPlayerList()) {
            if (fmp.getNum().equals(num)) {
                if (fmp.getFamPlayer() == null) {
                    // Aucun joueur encore a ce poste
                    fmp.setFamPlayer(player);

                } else {
                    if (!fmp.getFamPlayer().equals(player)) {
                        // Un autre joueur est deja a ce poste
                        FamPlayer tmp = fmp.getFamPlayer();
                        fmp.setFamPlayer(player);
                    }
                }
                break;
            }
        }

        for (CanvasFormationItem cfi : teamComposition.getLstTarget()) {
            if (cfi.getStrIdx().equals(String.valueOf(num))) {
                cfi.setFamPlayer(player);
                break;
            }
        }
    }

    public void onSubDrop(DragDropEvent ddEvent) {
        LOGGER.info("onSubDrop");
        FamPlayer player = ((FamPlayer) ddEvent.getData());

        String strNum = (String) ddEvent.getComponent().getAttributes().get("num");
        if (strNum == null) {
            JsfUtil.addInfoMessage("Add " + player.getDisplayName() + " as Sub", "TODO!");
            return;
        }
        for (CanvasFormationItem cfi : teamComposition.getLstSubs()) {
            if (cfi.getStrIdx().equals(strNum)) {

                cfi.setFamPlayer(player);
                break;
            }
        }

        Integer num = Integer.parseInt(strNum);
        for (FamMatchPlayer fmp : famMatchTeam.getFamMatchPlayerList()) {
            if (fmp.getNum().equals(num)) {
                if (fmp.getFamPlayer() == null) {
                    // Aucun joueur encore a ce poste
                    fmp.setFamPlayer(player);

                } else {
                    if (!fmp.getFamPlayer().equals(player)) {
                        // Un autre joueur est deja a ce poste
                        FamPlayer tmp = fmp.getFamPlayer();
                        fmp.setFamPlayer(player);
                    }
                }
                break;
            }
        }

    }

    public void deselect(FamPlayer player) {


    }

    public void onPreselectYesDrop(DragDropEvent ddEvent) {

        FamAnswer answer = ((FamAnswer) ddEvent.getData());

//        preselectedLst.add(answer.getFamPlayer());
//        lstYes.remove(answer);
    }


    public void save(ActionEvent actionEvent) {
        //Persist user
        saveLineup();
//        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String saveLineup() {
        LOGGER.debug("saveLineup");
        try {
            for (FamMatchPlayer mp : famMatchTeam.getFamMatchPlayerList()) {
                if (mp.getFamPlayer() != null) {
                    ejbMatchPlayer.edit(mp);
                }
            }

            ejbMatchTeam.edit(famMatchTeam);
            JsfUtil.addSuccessMessage("Enregistré");
        } catch (Exception e) {
            LOGGER.error("saveLineup", e);
            JsfUtil.addErrorMessage(e.getMessage(), "saveLineup");
        }
        return "pretty:";
    }


}
