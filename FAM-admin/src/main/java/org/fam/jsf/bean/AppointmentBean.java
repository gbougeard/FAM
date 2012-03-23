/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamAnswerFacade;
import org.fam.ejb.session.FamEventFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbougeard
 */
@ManagedBean
@ViewScoped
@Loggable
@Getter
@Setter
public class AppointmentBean {

    @Inject
    private Logger LOGGER;

    @EJB
    private FamEventFacade ejbEvent;
    @EJB
    private FamAnswerFacade ejbAnswer;
    //
    private List<FamAnswer> lstYes = new ArrayList<FamAnswer>();
    private List<FamAnswer> lstNo = new ArrayList<FamAnswer>();
    private List<FamAnswer> lstMaybe = new ArrayList<FamAnswer>();
    private List<FamPlayer> lstNsp = new ArrayList<FamPlayer>();
    //
    @Inject
    @LoggedIn
    private FamUser currentUser;
    @Inject
    @Player
    private FamPlayer currentPlayer;
    //
    private FamAnswer currentUserAnswer;
    private FamEvent currentEvent;
    private Long id;

    public AppointmentBean() {
    }

    @PostConstruct
    private void postConstruct() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("postConstruct");
        }
    }

    @PreDestroy
    private void preDestroy() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("preDestroy");
        }
    }

    public void loadAction() {
        if (id != null) {

            try {
                currentEvent = ejbEvent.find(id);
                lstYes = ejbAnswer.findAnswerYesByEvent(currentEvent);
                lstNo = ejbAnswer.findAnswerNoByEvent(currentEvent);
                lstMaybe = ejbAnswer.findAnswerMaybeByEvent(currentEvent);
                lstNsp = ejbAnswer.findByEventAndNoAnswer(currentEvent);
                currentUserAnswer = ejbAnswer.findAnswerByEventAndPlayer(currentEvent, currentPlayer);
            } catch (Exception e) {
                LOGGER.error("loadAction " + id, e);
                JsfUtil.addErrorMessage(e, "loadAction failed");
            }
        } else {
            JsfUtil.addErrorMessage("loadAction failed : id NULL");
        }
    }

    public String saveAnswer() {
        try {
            if (currentUserAnswer.getIdAnswer() == null) {
                ejbAnswer.create(currentUserAnswer);
            } else {
                ejbAnswer.edit(currentUserAnswer);
            }
            JsfUtil.addSuccessMessage("Bien enregistrée", "Réponse");
        } catch (Exception e) {
            LOGGER.error("saveAnswer", e);
            JsfUtil.addErrorMessage(e, "Answer failed");
        }
        return null;
    }

    public String cancelAnswer() {
        try {
            if (currentUserAnswer.getIdAnswer() == null) {
                currentUserAnswer.setFamTypAnswer(null);
                currentUserAnswer.setComments(null);
            } else {
                ejbAnswer.remove(currentUserAnswer);
                currentUserAnswer.setFamEvent(currentEvent);
                currentUserAnswer.setFamPlayer(currentPlayer);
            }
            JsfUtil.addSuccessMessage("Annulée", "Réponse");
        } catch (Exception e) {
            LOGGER.error("cancelAnswer", e);
            JsfUtil.addErrorMessage(e, "Cancel Answer failed");
        }
        return null;
    }

}
