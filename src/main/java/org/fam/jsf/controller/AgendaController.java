/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamEvent;
import org.fam.ejb.session.FamEventFacade;
import org.joda.time.DateTime;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@ManagedBean(name = "agendaController")
@ViewScoped
public class AgendaController implements Serializable {

    @EJB
    private FamEventFacade ejb;
    private FamEvent event1;
    private FamEvent selectedEvent;
    private List<FamEvent> eventList;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private String theme;

    /**
     * Creates a new instance of TestController
     */
    public AgendaController() {
        eventModel = new DefaultScheduleModel();
//        DateTime dtStart = new DateTime();
//        DateTime dtEnd = dtStart.plusHours(2);
//        
//        eventModel.addEvent(new DefaultScheduleEvent("Test", dtStart.toDate(), dtEnd.toDate(), false));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//
//        lazyEventModel = new LazyScheduleModel() {
//
//            @Override
//            public void fetchEvents(Date start, Date end) {
//                clear();
//
//                Date random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//
//                random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }
//        };
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log("event - postConstruct", Level.INFO, null);

    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log("event - preDestroy", Level.INFO, null);
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);

    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String findAll() {
        eventList = ejb.findAll();
        for (FamEvent evt : eventList) {
            String title = evt.getLibEvent();
            DateTime dtStart = new DateTime(evt.getDtEvent());
            DateTime dtEnd = dtStart.plusMinutes(evt.getDuration());
            eventModel.addEvent(new DefaultScheduleEvent(title, dtStart.toDate(), dtEnd.toDate(), false));
        }
        return "/users/agenda?faces-redirect=true";
    }

    public FamEvent getEvent1() {
        return event1;
    }

    public void setEvent1(FamEvent Event) {
        this.event1 = Event;
    }

    public List<FamEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<FamEvent> EventList) {
        this.eventList = EventList;
    }

    public FamEventFacade getEjb() {
        return ejb;
    }

    public void setEjb(FamEventFacade ejb) {
        this.ejb = ejb;
    }

    public FamEvent getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(FamEvent selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
