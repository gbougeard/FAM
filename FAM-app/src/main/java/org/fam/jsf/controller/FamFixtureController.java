package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamFixture;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.session.FamFixtureFacade;
import org.fam.jsf.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famFixtureController")
@ViewScoped
public class FamFixtureController extends AbstractController<FamFixture> implements Serializable {

    @EJB
    private FamFixtureFacade ejbFacade;
    private Long idSeasonCompetition;
    //
//    private List<Timeline> lstTimeLine;

    /**
     * In a real application, this number should be resolved by a projection query
     */
    public FamFixtureController() {
    }

    @PostConstruct
    private void postConstruct() {

//        findAll();
//        lstTimeLine = new ArrayList<Timeline>();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamFixture getSelected() {
        if (current == null) {
            current = new FamFixture();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamFixtureFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdFixture();
        return "pretty:editFixture";
    }

    @Override
    public String prepareView() {
        id = current.getIdFixture();
        return "pretty:viewFixture";
    }

    @Override
    public String prepareList() {
        return "pretty:listFixture";
    }

    @Override
    public String prepareCreate() {
        current = new FamFixture();
        selectedItemIndex = -1;
        return "pretty:createFixture";
    }

    public void loadBySeasonCompetition() {

        if (idSeasonCompetition != null) {

            try {
                items = getFacade().findByCompetitionId(idSeasonCompetition);

//                lstTimeLine = new ArrayList<Timeline>();
//                DefaultTimeLine timeLine = new DefaultTimeLine("fixture", "Calendrier des journées");
//                timeLine.setFocusDate(new Date());
//                timeLine.setInitialZoom(20);
//                for (FamFixture item : items) {
//                    timeLine.addEvent(new DefaultTimelineEvent(item.getLibFixture(), item.getLibFixture(), item.getDtFixture(), null,
//                            "/images/silk/sport_soccer.png"));
//                }
//
//                lstTimeLine.add(timeLine);

            } catch (Exception e) {

                JsfUtil.addErrorMessage(e, "loadBySeasonCompetition");
            }
        }
    }

    public String loadBySeasonCompetition(FamSeasonCompetition famSeasonCompetition) {

        try {
            items = getFacade().findByCompetition(famSeasonCompetition);

//            lstTimeLine = new ArrayList<Timeline>();
//            DefaultTimeLine timeLine = new DefaultTimeLine("fixture", famSeasonCompetition.getDisplayName());
//            timeLine.setFocusDate(new Date());
//            timeLine.setInitialZoom(20);
//            for (FamFixture item : items) {
//                timeLine.addEvent(new DefaultTimelineEvent("Project start", item.getLibFixture(), item.getDtFixture(), null,
//                        "/images/silk/sport_soccer.png"));
//            }
//            lstTimeLine.add(timeLine);
        } catch (Exception e) {

            JsfUtil.addErrorMessage(e, "loadBySeasonCompetition Failed");
        }
        String url = "/ok/famFixture/Grid?faces-redirect=true&id=" + famSeasonCompetition.getIdSeasonCompetition();
        return url;
    }

    public String propagateDate() {
        try {
            getFacade().propagateDate(current);
        } catch (Exception e) {

            JsfUtil.addErrorMessage(e, "propagateDate");
        }
        return super.prepareView();
    }

    public Long getIdSeasonCompetition() {
        return idSeasonCompetition;
    }

    public void setIdSeasonCompetition(Long idSeasonCompetition) {
        this.idSeasonCompetition = idSeasonCompetition;
    }

//    public List<Timeline> getModel() {
//        return lstTimeLine;
//    }
//
//    public void setLstTimeLine(List<Timeline> lstTimeLine) {
//        this.lstTimeLine = lstTimeLine;
//    }
}
