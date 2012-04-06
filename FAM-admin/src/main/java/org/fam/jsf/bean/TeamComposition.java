package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.model.*;
import org.primefaces.model.DualListModel;

import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/03/12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
@Model
@Getter
@Setter
public class TeamComposition implements Serializable {

    private static final long serialVersionUID = 5265035756215442054L;

//    @Inject
//    private Logger LOGGER;

    private FamMatchTeam famMatchTeam;
    private FamTeam famTeam;

    private AnswerGiven answerYes = new AnswerGiven();
    private AnswerGiven answerNo = new AnswerGiven();
    private AnswerGiven answerMaybe = new AnswerGiven();
    private AnswerUngiven answerUngiven = new AnswerUngiven();

    //    private List<FamAnswer> lstYes;
//    private List<FamAnswer> lstNo;
//    private List<FamAnswer> lstMaybe;
//    private List<FamPlayer> lstPlayer;
//    private FamAnswer[] selectedYes;
//    private List<FamAnswer> selectedNo;
//    private List<FamAnswer> selectedMaybe;
//    private FamPlayer[] selectedPlayers;
    //
    private FamPlayer selectedPlayer;
    private List<FamPlayer> players = new ArrayList<FamPlayer>();
    private List<FamPlayer> preselectedLst = new ArrayList<FamPlayer>();

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

    public List<FamPlayer> getPreselectedLst() {
        preselectedLst.clear();
        for (FamAnswer answer : answerYes.getLstSelected()) {
            preselectedLst.add(answer.getFamPlayer());
        }
        for (FamAnswer answer : answerMaybe.getLstSelected()) {
            preselectedLst.add(answer.getFamPlayer());
        }
        for (FamAnswer answer : answerNo.getLstSelected()) {
            preselectedLst.add(answer.getFamPlayer());
        }
        preselectedLst.addAll(answerUngiven.getLstSelected());
//        JsfUtil.addInfoMessage("ungiven " + answerUngiven.getLstSelected().size(),"TC" );
//        LOGGER.info("presel "+preselectedLst.size());
        //JsfUtil.addInfoMessage("presel " + preselectedLst.size(),"TC" );
        return preselectedLst;
    }
    /*public String addSelectedYes() {
        for (FamAnswer answer : selectedYes) {
            preselectedLst.add(answer.getFamPlayer());
            lstYes.remove(answer);
        }

        return null;
    }

    public String addSelectedNo() {
        for (FamAnswer answer : selectedNo) {
            preselectedLst.add(answer.getFamPlayer());
            lstNo.remove(answer);
        }

        return null;
    }

    public String addSelectedMaybe() {
//        LOGGER.info("addSelectedMaybe");
        for (FamAnswer answer : selectedMaybe) {
            preselectedLst.add(answer.getFamPlayer());
            lstMaybe.remove(answer);
        }

        return null;
    }

    public String addSelectedPlayers() {
//        LOGGER.info("addSelectedPlayers");
        JsfUtil.addInfoMessage("addSelectedPlayers", selectedPlayers.length + " selected");
        for (FamPlayer player : selectedPlayers) {
            preselectedLst.add(player);
            lstPlayer.remove(player);
        }
        // on raz la selection
        selectedPlayers = null;
        return null;
    }*/

    /* public void addSelectedPlayers() {
        JsfUtil.addInfoMessage("addSelectedPlayers", selectedPlayers.length + " selected");
        for (FamPlayer player : selectedPlayers) {
            preselectedLst.add(player);
            lstPlayer.remove(player);
        }
        // on raz la selection
        selectedPlayers = null;
    }*/

    public void genTarget() {
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("genTarget");
//        }
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
        if (famMatchTeam != null) {
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

    }


}

