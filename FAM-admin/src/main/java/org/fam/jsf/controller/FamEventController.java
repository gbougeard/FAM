package org.fam.jsf.controller;

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
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@ManagedBean(name = "famEventController")
@ViewScoped
public class FamEventController extends AbstractController<FamEvent> implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamEventController.class);

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

    public FamEventController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOGGER.info(this.getClass() + " - postConstruct");
    }

    @PreDestroy
    private void preDestroy() {
        LOGGER.info(this.getClass() + " - preDestroy");
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
                LOGGER.info("loadEvent " + dtStart + " " + dtEnd);

                items = ejbFacade.find(dtStart, dtEnd);
                for (FamEvent evt : items) {
                    String title = evt.getLibEvent();
                    DateTime dtS = new DateTime(evt.getDtEvent());
                    DateTime dtE = dtS.plusMinutes(evt.getDuration());
                    DefaultScheduleEvent e = new DefaultScheduleEvent(title, dtS.toDate(), dtE.toDate());
                    e.setAllDay(evt.getAllDay());
                    e.setData(evt);
                    if (evt.getFamTypEvent().getCodTypEvent().equals("M")) {
                        LOGGER.info("MatchEvent", Level.OFF, null);
                        e.setStyleClass("redEvent");
                    } else {
                        e.setStyleClass("greenEvent");
                    }
                    LOGGER.info("dayofWeek " + dtS.getDayOfWeek());

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
            LOGGER.info("cacheBean NULL", Level.WARNING, null);
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

    public void setTeams(DualListModel<FamTeam> teams) {
        this.teams = teams;
    }

    public CacheBean getCacheBean() {
        return cacheBean;
    }

    public void setCacheBean(CacheBean cacheBean) {
        this.cacheBean = cacheBean;
    }

    //    public void addEvent(ActionEvent actionEvent) {
//        if (event.getId() == null) {
//            eventModel.addEvent(event);
//        } else {
//            eventModel.updateEvent(event);
//        }
//
//        event = new DefaultScheduleEvent();
//    }
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
        LOGGER.info("move " + event, Level.OFF, null);
        LOGGER.info("dayDelta " + dayDelta, Level.OFF, null);
        LOGGER.info("minuteDelta " + minuteDelta, Level.OFF, null);

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
        LOGGER.info("resize " + event, Level.OFF, null);
        LOGGER.info("dayDelta " + dayDelta, Level.OFF, null);
        LOGGER.info("minuteDelta " + minuteDelta, Level.OFF, null);
        current.setDuration(current.getDuration() + minuteDelta);
        update();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
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
        }
        return null;
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
    // CONVERTER
//    @FacesConverter(forClass = FamEvent.class)
//    public static class FamEventControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamEventController controller = (FamEventController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famEventController");
//            return controller.ejbFacade.find(getKey(value));
//        }
//
//        Long getKey(String value) {
//            Long key;
//            key = Long.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof FamEvent) {
//                FamEvent o = (FamEvent) object;
//                return getStringKey(o.getIdEvent());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamEventController.class.getName());
//            }
//        }
//    }

    public void setCachePlayer(CachePlayer cachePlayer) {
        this.cachePlayer = cachePlayer;
    }
}
