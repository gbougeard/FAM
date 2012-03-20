/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.jsf.bean;

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
import org.slf4j.LoggerFactory;

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
public class AppointmentBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentBean.class);

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
        LOGGER.info(this.getClass() + " - postConstruct");
    }

    @PreDestroy
    private void preDestroy() {
        LOGGER.info(this.getClass() + " - preDestroy");
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

    /* GETTER / SETTER */
    public FamUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FamUser currentUser) {
        this.currentUser = currentUser;
    }

    public FamAnswer getCurrentUserAnswer() {
        return currentUserAnswer;
    }

    public void setCurrentUserAnswer(FamAnswer currentUserAnswer) {
        this.currentUserAnswer = currentUserAnswer;
    }

    public List<FamAnswer> getYesForEvent() {
        return lstYes;
    }

    public Integer getNbYesForEvent() {
        return lstYes.size();
    }

    public List<FamAnswer> getNoForEvent() {
        return lstNo;
    }

    public Integer getNbNoForEvent() {
        return lstNo.size();
    }

    public List<FamAnswer> getMaybeForEvent() {
        return lstMaybe;
    }

    public Integer getNbMaybeForEvent() {
        return lstMaybe.size();
    }

    public List<FamPlayer> getNoAnswerForEvent() {
        return lstNsp;
    }

    public Integer getNbNoAnswerForEvent() {
        return lstNsp.size();
    }

    public FamEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(FamEvent currentEvent) {
        this.currentEvent = currentEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
