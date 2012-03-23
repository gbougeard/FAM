package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.*;
import org.fam.ejb.session.FamAnswerFacade;
import org.fam.ejb.session.FamEventFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.fam.jsf.cache.CachePlayer;
import org.joda.time.DateTime;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.*;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "famEventController")
@ViewScoped
@Getter
@Setter
@Loggable
public class FamEventController extends AbstractController<FamEvent> implements Serializable {

    private static final long serialVersionUID = -6296773571175172927L;
    @Inject
    private Logger LOGGER;

    @EJB
    private FamEventFacade ejbFacade;
    @EJB
    private FamAnswerFacade ejbAnswer;
    //
    private DualListModel<FamTeam> teams;
    @Inject
    private CacheBean cacheBean;
    @Inject
    private CachePlayer cachePlayer;
    //
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    //
    private List<FamAnswer> lstYes;
    private List<FamAnswer> lstNo;
    private List<FamAnswer> lstMaybe;
    private List<FamAnswer> lstNsp;
    //
    @Inject
    @LoggedIn
    private FamUser currentUser;
    @Inject
    @Player
    private FamPlayer currentPlayer;
    private FamAnswer currentUserAnswer;
    //
    private Date now;

    public FamEventController() {
    }

    @PostConstruct
    private void postConstruct() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(" - postConstruct");
        }
        now = new Date();
    }

    @PreDestroy
    private void preDestroy() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(" - preDestroy");
        }
    }

    @Override
    public FamEvent getSelected() {
        if (current == null) {
            current = ejbFacade.newEvent();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamEventFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdEvent();
        return "pretty:editEvent";
    }

    @Override
    public String prepareView() {
        id = current.getIdEvent();
        return "pretty:viewEvent";
    }

    @Override
    public String prepareList() {
        return "pretty:listEvent";
    }

    @Override
    public String prepareCreate() {
        current = ejbFacade.newEvent();
//        current.setFamSeason(cacheBean.getCurrentSeason());
//        current.setFamEventStatus(cacheBean.getEventStatusScheduled());

        selectedItemIndex = -1;
        return "pretty:createEvent";
    }

    public String showAppointment() {
        id = current.getIdEvent();
        return "pretty:appointment";
    }

    // SCHEDULE
    public String prepareAgenda() {
        return "pretty:agenda";
    }

    public void initLazyEventModel() {
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date dtStart, Date dtEnd) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("loadEvent " + dtStart + " " + dtEnd);
                }

                items = ejbFacade.find(dtStart, dtEnd);
                for (Object o : items) {
                    FamEvent evt = (FamEvent) o;
                    String title = evt.getLibEvent();
                    DateTime dtS = new DateTime(evt.getDtEvent());
                    DateTime dtE = dtS.plusMinutes(evt.getDuration());
                    DefaultScheduleEvent e = new DefaultScheduleEvent(title, dtS.toDate(), dtE.toDate());
                    e.setAllDay(evt.getAllDay());
                    e.setData(evt);
                    if (evt.getFamTypEvent().getCodTypEvent().equals("M")) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("MatchEvent");
                        }
                        e.setStyleClass("redEvent");
                    } else {
                        e.setStyleClass("greenEvent");
                    }
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("dayofWeek " + dtS.getDayOfWeek());
                    }

                    addEvent(e);
                }
            }
        };
    }

    @Override
    public void initLazyModel() {
        super.initLazyModel();
//        initLazyEventModel();
    }

    public DualListModel<FamTeam> getTeams() {
        if (cacheBean == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("cacheBean NULL");
            }
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
        return teams;
    }


    public void addEvent(ActionEvent actionEvent) {

//        if (event.getId() == null) {
//            eventModel.addEvent(event);
//        } else {
//            eventModel.updateEvent(event);
//        }

        event = new DefaultScheduleEvent();
    }

    public Long getEventId() {
        return ((FamEvent) event.getData()).getIdEvent();
    }

    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
        current = (FamEvent) event.getData();
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());
    }

    public void onEventMove(ScheduleEntryMoveEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
        int dayDelta = selectEvent.getDayDelta();
        int minuteDelta = selectEvent.getMinuteDelta();

        JsfUtil.addInfoMessage("from " + event.getStartDate(), "EventMove");
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + dayDelta + ", Minute delta:" + minuteDelta);
//        addMessage(message);

        current = (FamEvent) event.getData();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("move " + event);
            LOGGER.debug("dayDelta " + dayDelta);
            LOGGER.debug("minuteDelta " + minuteDelta);
        }

        DateTime dt = new DateTime(current.getDtEvent());
        dt = dt.plusDays(dayDelta);
        dt = dt.plusMinutes(minuteDelta);
        current.setDtEvent(dt.toDate());
        current.setDuration(current.getDuration() + minuteDelta);
        JsfUtil.addInfoMessage("to " + current.getDtEvent(), "EventMove");
        update();
    }

    public void onEventResize(ScheduleEntryResizeEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
        int dayDelta = selectEvent.getDayDelta();
        int minuteDelta = selectEvent.getMinuteDelta();

        JsfUtil.addInfoMessage("from " + event.getEndDate(), "EventResize");
        JsfUtil.addInfoMessage("added mn " + minuteDelta, "EventResize");
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + dayDelta + ", Minute delta:" + minuteDelta);
//        addMessage(message);

        current = (FamEvent) event.getData();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("resize " + event);
            LOGGER.debug("dayDelta " + dayDelta);
            LOGGER.debug("minuteDelta " + minuteDelta);
        }
        current.setDuration(current.getDuration() + minuteDelta);
        update();
    }


    @Override
    public String loadAction() {
        super.loadAction();
        try {
            lstYes = ejbAnswer.findAnswerYesByEvent(current);
            lstNo = ejbAnswer.findAnswerNoByEvent(current);
            lstMaybe = ejbAnswer.findAnswerMaybeByEvent(current);
            currentUserAnswer = ejbAnswer.findAnswerByEventAndPlayer(current, currentPlayer);

            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "loadAction failed");
            LOGGER.error("loadAction failed", e);
            return "pretty:";
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
            JsfUtil.addErrorMessage(e, "Answer failed");
            LOGGER.error("saveAnswer failed", e);
        }
        return null;
    }

}
