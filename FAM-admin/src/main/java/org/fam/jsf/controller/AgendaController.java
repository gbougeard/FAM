/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
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
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.List;

/**
 * @author gbougear
 */
@ManagedBean(name = "agendaController")
@ViewScoped
@Loggable
@Getter
@Setter
public class AgendaController {

    @Inject
    private Logger LOGGER;
    @Inject
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

    }

    @PreDestroy
    private void preDestroy() {
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

}
