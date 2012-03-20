package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamWorkout;
import org.fam.ejb.session.FamAnswerFacade;
import org.fam.ejb.session.FamWorkoutFacade;
import org.fam.jsf.bean.WorkoutDebriefBean;
import org.fam.jsf.bean.util.JsfUtil;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@ManagedBean(name = "famWorkoutController")
@ViewScoped
@Getter
@Setter
public class FamWorkoutController extends AbstractController<FamWorkout>
        implements Serializable {

    @EJB
    private FamWorkoutFacade ejbFacade;
    @EJB
    private FamAnswerFacade ejbAnswer;
    //
    private List<FamAnswer> answerYesList;
    private List<FamAnswer> answerMaybeList;
    private List<FamAnswer> answerNoList;
    private List<FamPlayer> noAnswerList;
    private List<FamAnswer> answerList;
    //
    private DualListModel<FamPlayer> playerPickList;
    private List<FamPlayer> answerPlayerList;
    private List<FamPlayer> playerList;
    //
    private DualListModel<WorkoutDebriefBean> pickList;

    public FamWorkoutController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
//        super.infoDestroy();
    }

    @Override
    public FamWorkout getSelected() {
        if (current == null) {
            current = ejbFacade.newWorkout();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamWorkoutFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdWorkout();
        return "pretty:editWorkout";
    }

    @Override
    public String prepareView() {
        id = current.getIdWorkout();
        return "pretty:viewWorkout";
    }

    public String prepareDebrief() {
        id = current.getIdWorkout();
        loadForDebrief();
        return "pretty:debriefWorkout";
    }

    public void loadForDebrief() {
        super.loadAction();
        answerYesList = ejbAnswer.findAnswerYesByEvent(current.getFamEvent());
        answerNoList = ejbAnswer.findAnswerNoByEvent(current.getFamEvent());
        answerMaybeList = ejbAnswer.findAnswerMaybeByEvent(current.getFamEvent());
        noAnswerList = ejbAnswer.findByEventAndNoAnswer(current.getFamEvent());

        List<FamAnswer> tmpList = new ArrayList<FamAnswer>();
        answerList = new ArrayList<FamAnswer>();
        for (FamAnswer answer : answerYesList) {
            if (current.getFamPlayerList().contains(answer.getFamPlayer())) {
                tmpList.add(answer);
            } else {
                answerList.add(answer);
            }
        }
        for (FamAnswer answer : answerNoList) {
            if (current.getFamPlayerList().contains(answer.getFamPlayer())) {
                tmpList.add(answer);
            } else {
                answerList.add(answer);
            }
        }
        for (FamAnswer answer : answerMaybeList) {
            if (current.getFamPlayerList().contains(answer.getFamPlayer())) {
                tmpList.add(answer);
            } else {
                answerList.add(answer);
            }
        }
//        tmpList.addAll(answerYesList);
//        tmpList.addAll(answerNoList);
//        tmpList.addAll(answerMaybeList);
        for (FamPlayer player : noAnswerList) {
            FamAnswer answer = new FamAnswer();
            answer.setFamPlayer(player);
            if (current.getFamPlayerList().contains(answer.getFamPlayer())) {
                tmpList.add(answer);
            } else {
                answerList.add(answer);
            }
//            tmpList.add(answer);
        }
        answerPlayerList = new ArrayList<FamPlayer>();
        for (FamAnswer a : answerList) {
            answerPlayerList.add(a.getFamPlayer());
        }
        // On enleve ceux qui sont deja enregistrés comme présents
//        for (FamAnswer answer : tmpList) {
//            if (current.getFamPlayerList().contains(answer.getFamPlayer()) == false) {
//                answerList.add(answer);
//            }
//        }

        //TODO Passer à une picklist
        playerPickList = new DualListModel<FamPlayer>();
        playerPickList.setSource(answerPlayerList);
//        tmpList = new ArrayList<FamAnswer>();
//        for (FamPlayer player : current.getFamPlayerList()) {
//            FamAnswer answer = new FamAnswer();
//            answer.setFamPlayer(player);
//            tmpList.add(answer);
//        }
//        playerPickList.setTarget(tmpList);
        playerPickList.setTarget(current.getFamPlayerList());

        List<WorkoutDebriefBean> src = new ArrayList<WorkoutDebriefBean>();
        List<WorkoutDebriefBean> target = new ArrayList<WorkoutDebriefBean>();

        for (FamAnswer a : answerList) {
            WorkoutDebriefBean item = new WorkoutDebriefBean();
            item.setFamAnswer(a);
            item.setFamPlayer(a.getFamPlayer());
            src.add(item);
        }

        for (FamPlayer p : current.getFamPlayerList()) {
            WorkoutDebriefBean item = new WorkoutDebriefBean();
            item.setFamPlayer(p);
            src.add(item);
        }
        pickList = new DualListModel<WorkoutDebriefBean>();
        pickList.setSource(src);
        pickList.setTarget(target);

    }

    @Override
    public String prepareList() {
        return "pretty:listWorkout";
    }

    @Override
    public String prepareCreate() {
        current = ejbFacade.newWorkout();
        id = null;
        selectedItemIndex = -1;
        return "pretty:createWorkout";
    }

    @Override
    public String update() {
        // On rajoute les présents
//        List<FamAnswer> list = new ArrayList<FamAnswer>(playerPickList.getTarget());
//        for (FamAnswer answer : list) {
//            if (current.getFamPlayerList().contains(answer.getFamPlayer()) == false) {
//                current.getFamPlayerList().add(answer.getFamPlayer());
//            }
//        }


        // On supprimer les absents
//        list = new ArrayList<FamAnswer>(playerPickList.getSource());
//        for (FamAnswer answer : list) {
//            if (current.getFamPlayerList().contains(answer.getFamPlayer())) {
//                current.getFamPlayerList().remove(answer.getFamPlayer());
//            }
//        }
        if (playerPickList != null) {
            // on est en débrief
            List<FamPlayer> list = new ArrayList<FamPlayer>(playerPickList.getTarget());
            current.getFamPlayerList().clear();
            current.getFamPlayerList().addAll(list);
        }

        return super.update();
    }


    public void onAnswerDrop(DragDropEvent ddEvent) {
        FamAnswer answer = ((FamAnswer) ddEvent.getData());

        current.getFamPlayerList().add(answer.getFamPlayer());
        answerList.remove(answer);
    }

    public void onAnswerYesDrop(DragDropEvent ddEvent) {
        FamAnswer answer = ((FamAnswer) ddEvent.getData());

        current.getFamPlayerList().add(answer.getFamPlayer());
        answerYesList.remove(answer);
    }

    public void onAnswerNoDrop(DragDropEvent ddEvent) {
        FamAnswer answer = ((FamAnswer) ddEvent.getData());

        current.getFamPlayerList().add(answer.getFamPlayer());
        answerNoList.remove(answer);
    }

    public void onAnswerMaybeDrop(DragDropEvent ddEvent) {
        FamAnswer answer = ((FamAnswer) ddEvent.getData());
        JsfUtil.addInfoMessage(answer.getFamPlayer().getDisplayName(), "onAnswerDrop");
        current.getFamPlayerList().add(answer.getFamPlayer());
        answerMaybeList.remove(answer);
    }

    public void onPlayerDrop(DragDropEvent ddEvent) {
        FamPlayer player = ((FamPlayer) ddEvent.getData());
        JsfUtil.addInfoMessage(player.getDisplayName(), "onPlayerDrop");
        current.getFamPlayerList().add(player);
        noAnswerList.remove(player);
    }


}
