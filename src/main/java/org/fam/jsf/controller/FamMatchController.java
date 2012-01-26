package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.*;
import org.fam.ejb.session.*;
import org.fam.jsf.bean.CanvasFormationItem;
import org.fam.jsf.bean.FamPlayerDataModel;
import org.fam.jsf.bean.Login;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

@ManagedBean(name = "famMatchController")
@ViewScoped
@Getter
@Setter
public class FamMatchController extends AbstractController<FamMatch> implements Serializable {

    @EJB
    private FamMatchFacade ejbFacade;
    @Inject
    private CacheBean cacheBean;
    private FamMatchTeam matchTeamHome = new FamMatchTeam();
    private FamMatchTeam matchTeamAway = new FamMatchTeam();
    private FamEvent famEvent = new FamEvent();
    @EJB
    private FamFixtureFacade ejbFixture;
    private List<FamFixture> fixtureList = new ArrayList<FamFixture>();
    //
    @EJB
    private FamAnswerFacade ejbAnswer;
    @EJB
    private FamTeamFacade ejbTeam;
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
    @Inject
    private Login login;
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
    @EJB
    private FamGoalFacade ejbGoal;
    @EJB
    private FamCardFacade ejbCard;
    @EJB
    private FamSubstitutionFacade ejbSub;
    @EJB
    private FamEventFacade ejbEvent;
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
    @EJB
    private FamMatchTeamFacade ejbMatchTeam;
    //
    private FamPlayerDataModel playerDM;
    //
    @EJB
    private FamMatchPlayerFacade ejbMatchPlayer;


    public FamMatchController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
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

    public List<FamFixture> getFixtureList() {
        return fixtureList;
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
        LogUtil.log(this.getClass() + " - loadForCompose - match " + id + " team " + idTeam, Level.INFO, null);
        super.loadAction();
        findTeam();

        lstYes = ejbAnswer.findAnswerYesByEventAndTeam(current.getFamEvent(), famTeam);
        lstNo = ejbAnswer.findAnswerNoByEventAndTeam(current.getFamEvent(), famTeam);
        lstMaybe = ejbAnswer.findAnswerMaybeByEventAndTeam(current.getFamEvent(), famTeam);
        lstPlayer = ejbAnswer.findByEventAndNoAnswerAndTeam(current.getFamEvent(), famTeam);

        playerDM = new FamPlayerDataModel(lstPlayer);

        preselectedLst = new ArrayList<FamPlayer>();

        dlmPlayer = new DualListModel<FamPlayer>(preselectedLst, new ArrayList<FamPlayer>());


        if (famMatchTeam.getFamMatchPlayerList() == null) {
            famMatchTeam.setFamMatchPlayerList(new ArrayList<FamMatchPlayer>());
        }
        if (famMatchTeam.getFamMatchPlayerList().isEmpty()) {

            for (int i = 1; i <= nbPlayers; i++) {
                FamMatchPlayer item = new FamMatchPlayer();
                item.setNum(i);
                item.setFamMatchTeam(famMatchTeam);
                famMatchTeam.getFamMatchPlayerList().add(item);
            }
        }

        genTarget();

    }

    public void loadForDebrief() {
        LogUtil.log(this.getClass() + " - loadForDebrief - match " + id + " team " + idTeam, Level.INFO, null);
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
        if (idTeam == null) {
            FamClub club = login.getCurrentUser().getFamPlayer().getClubForSeason(current.getFamSeasonCompetition().getFamSeason());
            LogUtil.log("Club de currentUser " + club.getLibClub(), Level.OFF, null);
            for (FamMatchTeam matchTeam : current.getFamMatchTeamList()) {
                // On se positionne sur l'equipe de notre club
                //TODO gerer un match entre 2 equipes de notre club

                if (club.getFamTeamList().contains(matchTeam.getFamTeam())) {
                    famTeam = matchTeam.getFamTeam();
                }

            }
            LogUtil.log("Match " + current.getFamEvent().getLibEvent(), Level.OFF, null);
            LogUtil.log("Team trouvee " + famTeam.getLibTeam(), Level.OFF, null);
            idTeam = famTeam.getIdTeam();
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


        nbTit = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbPlayer();
        nbSub = current.getFamSeasonCompetition().getFamTypCompetition().getFamTypMatch().getNbSubstitute();
        nbPlayers = nbTit + nbSub;
    }

    public void genTarget() {
        LogUtil.log(this.getClass() + " - genTarget", Level.INFO, null);
        lstTarget.clear();

        for (int i = 1; i <= 30; i++) {
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
        for (int i = 1; i <= nbSub; i++) {
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
        LogUtil.log("onValueChange", Level.INFO, null);
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
        LogUtil.log("onPreselectDrop", Level.INFO, null);
        FamPlayer player = ((FamPlayer) ddEvent.getData());

        preselectedLst.add(player);
        lstPlayer.remove(player);
    }

    public int coord(int coord) {
        return coord / 5;
    }

    public void onDrop(DragDropEvent ddEvent) {
        LogUtil.log("onDrop", Level.INFO, null);
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

                } else if (!fmp.getFamPlayer().equals(player)) {
                    // Un autre joueur est deja a ce poste
                    FamPlayer tmp = fmp.getFamPlayer();
                    fmp.setFamPlayer(player);
                }
                break;
            }
        }
    }

    public void onSubDrop(DragDropEvent ddEvent) {
        LogUtil.log("onSubDrop", Level.INFO, null);
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

                } else if (!fmp.getFamPlayer().equals(player)) {
                    // Un autre joueur est deja a ce poste
                    FamPlayer tmp = fmp.getFamPlayer();
                    fmp.setFamPlayer(player);
                }
                break;
            }
        }

    }

    public void deselect(FamPlayer player) {
        LogUtil.log("deselect " + player.getDisplayName(), Level.INFO, null);

    }

    public void onPreselectYesDrop(DragDropEvent ddEvent) {
        LogUtil.log("onPreselectYesDrop", Level.INFO, null);
        FamAnswer answer = ((FamAnswer) ddEvent.getData());

        preselectedLst.add(answer.getFamPlayer());
        lstYes.remove(answer);
    }

    public String addSelectedYes() {
        LogUtil.log("addSelectedYes", Level.INFO, null);

        for (FamAnswer answer : selectedYes) {
            preselectedLst.add(answer.getFamPlayer());
            lstYes.remove(answer);
        }

        return null;
    }

    public String addSelectedNo() {
        LogUtil.log("addSelectedNo", Level.INFO, null);

        for (FamAnswer answer : selectedNo) {
            preselectedLst.add(answer.getFamPlayer());
            lstNo.remove(answer);
        }

        return null;
    }

    public String addSelectedMaybe() {
        LogUtil.log("addSelectedMaybe", Level.INFO, null);

        for (FamAnswer answer : selectedMaybe) {
            preselectedLst.add(answer.getFamPlayer());
            lstMaybe.remove(answer);
        }

        return null;
    }

    public String addSelectedPlayers() {
        LogUtil.log("addSelectedPlayers", Level.INFO, null);

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
