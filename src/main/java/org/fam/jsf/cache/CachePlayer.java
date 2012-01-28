/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.cache;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamCity;
import org.fam.ejb.model.FamCountry;
import org.fam.ejb.model.FamProvince;
import org.fam.ejb.model.FamState;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@SessionScoped
@Named
public class CachePlayer implements Serializable {

    @Inject
    CacheBean cacheBean;
    private List<FamState> lstState = new ArrayList<FamState>();
    private List<FamProvince> lstProvince = new ArrayList<FamProvince>();
    private List<FamCity> lstCity = new ArrayList<FamCity>();

    public CachePlayer() {
    }

    @PostConstruct
    void postConstruct() {
        LogUtil.log("CachePlayer:PostConstruct", Level.INFO, null);
    }

    public void filterState(FamCountry country) {
        LogUtil.log("CachePlayer:filterState", Level.INFO, null);
        lstState.clear();
        lstProvince.clear();
        lstCity.clear();
        LogUtil.log("CachePlayer:filterState search " + country.getLibCountry(), Level.INFO, null);
        for (FamState item : cacheBean.getListState()) {
            LogUtil.log("item " + item.getFamCountry().getLibCountry(), Level.INFO, null);
            if (item.getFamCountry().equals(country)) {
                lstState.add(item);
//                LogUtil.log("added", Level.INFO, null);
            }
        }
    }

    public void filterProvince(FamState state) {
        LogUtil.log("CachePlayer:filterProvince", Level.INFO, null);
        lstProvince.clear();
        lstCity.clear();
        LogUtil.log("CachePlayer:filterProvince search " + state.getLibState(), Level.INFO, null);
        for (FamProvince item : cacheBean.getListProvince()) {
//            LogUtil.log("item " + item.getFamState().getLibState(), Level.INFO, null);
            if (item.getFamState().equals(state)) {
                lstProvince.add(item);
//                LogUtil.log("added", Level.INFO, null);
            }
        }
    }

    public void filterCity(FamProvince province) {
        LogUtil.log("CachePlayer:filterCity", Level.INFO, null);
        lstCity.clear();
        LogUtil.log("CachePlayer:filterCity search " + province.getLibProvince(), Level.INFO, null);

        for (FamCity item : cacheBean.getListCity()) {
            if (item.getFamProvince().equals(province)) {
                lstCity.add(item);
//                LogUtil.log("added", Level.INFO, null);
            }
        }
    }

    public List<FamCountry> completeCountry(String query) {
        List<FamCountry> suggestions = new ArrayList<FamCountry>();

        for (FamCountry p : cacheBean.getListCountry()) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamState> completeState(String query) {
        List<FamState> suggestions = new ArrayList<FamState>();

        for (FamState p : lstState) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamProvince> completeProvince(String query) {
        List<FamProvince> suggestions = new ArrayList<FamProvince>();

        for (FamProvince p : lstProvince) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamCity> completeCity(String query) {
        List<FamCity> suggestions = new ArrayList<FamCity>();

        for (FamCity p : lstCity) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public void onChangeValue(SelectEvent event) {
        LogUtil.log("CachePlayer:onChangeValue", Level.INFO, null);

        if (event.getObject().getClass().equals(FamCountry.class)) {
            filterState((FamCountry) event.getObject());
        } else if (event.getObject().getClass().equals(FamState.class)) {
            filterProvince((FamState) event.getObject());
        } else if (event.getObject().getClass().equals(FamProvince.class)) {
            filterCity((FamProvince) event.getObject());
        }

    }

    public List<FamCity> getLstCity() {
        return lstCity;
    }

    public void setLstCity(List<FamCity> lstCity) {
        this.lstCity = lstCity;
    }

    public List<FamProvince> getLstProvince() {
        return lstProvince;
    }

    public void setLstProvince(List<FamProvince> lstProvince) {
        this.lstProvince = lstProvince;
    }

    public List<FamState> getLstState() {
        return lstState;
    }

    public void setLstState(List<FamState> lstState) {
        this.lstState = lstState;
    }
}
