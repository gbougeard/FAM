package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.*;
import org.fam.ejb.session.*;
import org.fam.jsf.bean.CanvasFormationItem;
import org.fam.jsf.bean.FamPlayerDataModel;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean(name = "famMatchController")
@ViewScoped
@Loggable
@Getter
@Setter
public class FamMatchController extends AbstractController<FamMatch> {

    @Inject
    Logger LOGGER;
    @Inject
    private FamMatchFacade ejbFacade;
    @Inject
    private FamAnswerFacade ejbAnswer;
    @Inject
    private FamTeamFacade ejbTeam;
    @Inject
    private FamGoalFacade ejbGoal;
    @Inject
    private FamCardFacade ejbCard;
    @Inject
    private FamSubstitutionFacade ejbSub;
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
    private List<FamAnswer> lstYes;
    private List<FamAnswer> lstNo;
    private List<FamAnswer> lstMaybe;
    private List<FamPlayer> lstPlayer;
    private FamAnswer[] selectedYes;
    private List<FamAnswer> selectedNo;
    private List<FamAnswer> selectedMaybe;
    private FamPlayer[] selectedPlayers;
    //
    private FamPlayer selectedPlayer;
    private List<FamPlayer> players;
    private List<FamPlayer> preselectedLst;
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
    private List<CanvasFormationItem> lstTarget = new ArrayList<CanvasFormationItem>();
    private List<CanvasFormationItem> lstSubs = new ArrayList<CanvasFormationItem>();
    //
    private DualListModel<FamPlayer> dlmPlayer;
    //
    private int nbTit;
    private int nbSub;
    private int nbPlayers;
    //
    private FamPlayerDataModel playerDM;

    public FamMatchController() {
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

    public String prepareCompose() {
        id = current.getIdMatch();
        findTeam();
        return "pretty:composeMatch";
    }

    public String prepareDebrief() {
        id = current.getIdMatch();
        findTeam();
        return "pretty:debriefMatch";
    }

    public void loadForCompose() {

        super.loadAction();
        findTeam();

        lstYes = ejbAnswer.findAnswerYesByEventAndTeam(current.getFamEvent(), famTeam);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("yes " + lstYes.size());
        }
        lstNo = ejbAnswer.findAnswerNoByEventAndTeam(current.getFamEvent(), famTeam);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("no " + lstNo.size());
        }
        lstMaybe = ejbAnswer.findAnswerMaybeByEventAndTeam(current.getFamEvent(), famTeam);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("maybe " + lstMaybe.size());
        }
        lstPlayer = ejbAnswer.findByEventAndNoAnswerAndTeam(current.getFamEvent(), famTeam);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("nsp " + lstPlayer.size());
        }

        playerDM = new FamPlayerDataModel(lstPlayer);

        preselectedLst = new ArrayList<FamPlayer>();

        dlmPlayer = new DualListModel<FamPlayer>(preselectedLst, new ArrayList<FamPlayer>());


        if (famMatchTeam.getFamMatchPlayerList() == null) {
            famMatchTeam.setFamMatchPlayerList(new ArrayList<FamMatchPlayer>());
        }
        if (famMatchTeam.getFamMatchPlayerList().isEmpty()) {

            for (int i = 1;
                 i <= nbPlayers;
                 i++) {
                FamMatchPlayer item = new FamMatchPlayer();
                item.setNum(i);
                item.setFamMatchTeam(famMatchTeam);
                famMatchTeam.getFamMatchPlayerList().add(item);
            }
        }

        genTarget();

    }

    public void loadForDebrief() {

        super.loadAction();
        findTeam();

        famGoal = new FamGoal();
        famCard = new FamCard();
        famSubstitution = new FamSubstitution();

        lstGoal = ejbGoal.findByMatchAndTeam(current, famTeam);
        lstCard = ejbCard.findByMatchAndTeam(current, famTeam);
        lstSubstitution = ejbSub.findByMatchAndTeam(current, famTeam);

    }

    private void findTeam() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("findTeam " + idTeam);
        }
        if (idTeam == null) {
            FamClub club = currentPlayer.getClubForSeason(current.getFamSeasonCompetition().getFamSeason());
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("club " + club);
            }
            for (FamMatchTeam matchTeam : current.getFamMatchTeamList()) {
                // On se positionne sur l'equipe de notre club
                //TODO gerer un match entre 2 equipes de notre club

                if (cachePlayer.getListTeam().contains(matchTeam.getFamTeam())) {
                    famTeam = matchTeam.getFamTeam();
                }

            }


            idTeam = famTeam.getIdTeam();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("team trouvee " + idTeam);
            }
        } else {
            famTeam = ejbTeam.find(idTeam);
        }

        if (current.getFamMatchTeamList() == null) {
            JsfUtil.addErrorMessage("Le match n'a pas d'équipes!");
            famMatchTeam = null;
        }

        for (FamMatchTeam mt : current.getFamMatchTeamList()) {
            if (mt.getFamTeam().equals(famTeam)) {
                famMatchTeam = mt;
            }
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("matchTeam trouvee " + famMatchTeam);
        }

        nbTit = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbPlayer();
        nbSub = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbSubstitute();
        nbPlayers = nbTit + nbSub;
    }

    public void genTarget() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("genTarget");
        }
        lstTarget.clear();

        for (int i = 1;
             i <= 30;
             i++) {
            CanvasFormationItem item = new CanvasFormationItem();
            item.setStrIdx(String.format("%d", i));

            if (famMatchTeam != null && famMatchTeam.getFamFormation() != null) {
                for (FamFormationItem fi : famMatchTeam.getFamFormation().getFamFormationItemList()) {
                    if (fi.getCoord().equals(Integer.valueOf(i))) {
                        item.setFamFormationItem(fi);
                        break;
                    }
                }
            }
            lstTarget.add(item);
        }
        // Remplaçants
        for (int i = 1;
             i <= nbSub;
             i++) {
            CanvasFormationItem item = new CanvasFormationItem();
            item.setStrIdx(String.format("%d", i + nbTit));

            if (famMatchTeam != null && famMatchTeam.getFamFormation() != null) {
                for (FamFormationItem fi : famMatchTeam.getFamFormation().getFamFormationItemList()) {
                    if (fi.getCoord().equals(Integer.valueOf(i + nbTit))) {
                        item.setFamFormationItem(fi);
                        break;
                    }
                }
            }
            lstSubs.add(item);
        }
        // Add Players from LineUp
        for (FamMatchPlayer fmp : famMatchTeam.getFamMatchPlayerList()) {
            if (fmp.getFamPlayer() != null) {
                if (fmp.getNum() <= nbTit) {
                    // Ajoute aux titulaires
                    for (CanvasFormationItem cfi : lstTarget) {
                        if (cfi.getFamFormationItem() != null
                                && cfi.getFamFormationItem().getNumItem().equals(fmp.getNum())) {

                            cfi.setFamPlayer(fmp.getFamPlayer());
                            break;
                        }
                    }
                } else {
                    // Ajoute aux remplacants
                    for (CanvasFormationItem cfi : lstSubs) {
                        if (cfi.getStrIdx().equals(String.format("%d", fmp.getNum()))) {
                            cfi.setFamPlayer(fmp.getFamPlayer());
                            break;
                        }
                    }
                }


            }
        }
    }

    public void onValueChange(ValueChangeEvent event) {

        int i = 1;
        for (FamPlayer p : dlmPlayer.getTarget()) {
            for (CanvasFormationItem cfi : lstTarget) {
                if (cfi.getStrIdx().equals(String.format("%d", i))) {

                    cfi.setFamPlayer(p);
                    break;
                }
            }
            i++;
        }
    }

    public void onPreselectDrop(DragDropEvent ddEvent) {

        FamPlayer player = ((FamPlayer) ddEvent.getData());

        preselectedLst.add(player);
        lstPlayer.remove(player);
    }

    public int coord(int coord) {
        return coord / 5;
    }

    public void onDrop(DragDropEvent ddEvent) {

        FamPlayer player = ((FamPlayer) ddEvent.getData());

        Integer num = (Integer) ddEvent.getComponent().getAttributes().get("num");

        for (CanvasFormationItem cfi : lstTarget) {
            if (cfi.getFamFormationItem() != null
                    && cfi.getFamFormationItem().getNumItem().equals(num)) {

                cfi.setFamPlayer(player);
                break;
            }
        }

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

    public void onSubDrop(DragDropEvent ddEvent) {

        FamPlayer player = ((FamPlayer) ddEvent.getData());

        String strNum = (String) ddEvent.getComponent().getAttributes().get("num");
        for (CanvasFormationItem cfi : lstSubs) {
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

        preselectedLst.add(answer.getFamPlayer());
        lstYes.remove(answer);
    }

    public String addSelectedYes() {
        LOGGER.info("addSelectedYes");
        for (FamAnswer answer : selectedYes) {
            preselectedLst.add(answer.getFamPlayer());
            lstYes.remove(answer);
        }

        return null;
    }

    public String addSelectedNo() {
        LOGGER.info("addSelectedNo");
        for (FamAnswer answer : selectedNo) {
            preselectedLst.add(answer.getFamPlayer());
            lstNo.remove(answer);
        }

        return null;
    }

    public String addSelectedMaybe() {
        LOGGER.info("addSelectedMaybe");
        for (FamAnswer answer : selectedMaybe) {
            preselectedLst.add(answer.getFamPlayer());
            lstMaybe.remove(answer);
        }

        return null;
    }

    public String addSelectedPlayers() {
        LOGGER.info("addSelectedPlayers");
        for (FamPlayer player : selectedPlayers) {
            preselectedLst.add(player);
            lstPlayer.remove(player);
        }
        // on raz la selection
        selectedPlayers = null;
        return null;
    }

    public String addGoal() {
        lstGoal.add(famGoal);
        famGoal = new FamGoal();
        return null;
    }

    public String addCard() {
        lstCard.add(famCard);
        famCard = new FamCard();
        return null;
    }

    public String addSubstitution() {
        lstSubstitution.add(famSubstitution);
        famSubstitution = new FamSubstitution();
        return null;
    }

    public String saveLineup() {
        try {
            for (FamMatchPlayer mp : famMatchTeam.getFamMatchPlayerList()) {
                if (mp.getFamPlayer() != null) {
                    ejbMatchPlayer.edit(mp);
                }
            }

//            ejbMatchTeam.edit(famMatchTeam);
            JsfUtil.addSuccessMessage("Enregistré");
        } catch (Exception e) {
            LOGGER.error("saveLineup", e);
            JsfUtil.addErrorMessage(e.getMessage(), "saveLineup");
        }
        return "pretty:";
    }
//    @Override
//    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Selected", ((FamPlayer) event.getObject()).getDisplayName());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        JsfUtil.addSuccessMessage((selectedPlayers == null ? "0" : selectedPlayers.length) + " selected");
//    }
//
//    @Override
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Unselected", ((FamPlayer) event.getObject()).getDisplayName());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        JsfUtil.addSuccessMessage((selectedPlayers == null ? "0" : selectedPlayers.length) + " selected");
//    }
}
