/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import org.fam.ejb.model.FamEntity;
import org.fam.ejb.session.AbstractFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.common.AbstractBackingBean;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
public abstract class AbstractController<T> extends AbstractBackingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    public final static String PRETTY_PREFIX = "pretty:";
    protected T current;
    protected List<T> items = new ArrayList<T>();
    //    private Boolean select;
    protected int selectedItemIndex;
    //
    private LazyDataModel<T> lazyModel;
    private List<T> lazyItems = new ArrayList<T>();
    //
//    @URLQueryParameter("id")
    Long id;

    public AbstractController() {
//        init();
    }

    public String getPrettyId(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(PRETTY_PREFIX).append(id);
        return sb.toString();
    }

    public void initLazyModel() {
        LOGGER.debug("initLazyModel");

        lazyModel = new LazyDataModel<T>() {

            @Override
            public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                LOGGER.debug("Loading the lazy data between " + first + " " + pageSize);

                try {
                    lazyItems = getFacade().findAllLazy(first, pageSize,
                            sortField, sortOrder.equals(SortOrder.ASCENDING), filters);

                    /**
                     * In a real application, this number should be resolved by a projection query
                     */
                    int count = getFacade().countLazy(filters);

                    LOGGER.debug("nb items " + lazyItems.size() + " count " + count);

                    lazyModel.setRowCount(count);

                } catch (Exception e) {
                    LOGGER.debug("LazyLoad", Level.SEVERE, e);
                    JsfUtil.addErrorMessage("LazyLoad", e.getMessage());
                }

                return lazyItems;
            }

            @Override
            public Object getRowKey(T t) {
                return ((FamEntity) t).getId();
            }

            @Override
            public T getRowData(String rowKey) {
                return rowKey.equals("null") ? null : (T) getFacade().find(Long.parseLong(rowKey));
            }
        };
    }

    public abstract AbstractFacade getFacade();

    public T getSelected() {
        return current;
    }

    public void setSelected(T current) {
        LOGGER.debug(this.getClass() + " - Abstract - setSelected");
        this.current = current;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    //    public Boolean getSelect() {
//        return select;
//    }
//
//    public void setSelect(Boolean select) {
//        this.select = select;
//    }
    public List<T> getLazyItems() {
        return lazyItems;
    }

    public void setLazyItems(List<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public LazyDataModel<T> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<T> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public void onRowSelect(SelectEvent event) {
        LOGGER.debug("onRowSelect");
//        select = Boolean.TRUE;

        T o = (T) event.getObject();
        current = o;
    }

    public void onRowUnselect(UnselectEvent event) {
        LOGGER.debug("onRowUnselect");
//        select = Boolean.FALSE;
        current = null;
    }

    public String prepareEdit() {
        return "Edit?faces-redirect=true";
    }

    public String prepareView() {
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        return "Create?faces-redirect=true";
    }

    public String prepareList() {
        LOGGER.debug("prepareList");
        initLazyModel();
//        setSelect(Boolean.FALSE);
        return "List?faces-redirect=true";
    }

    protected void findAll() {
        try {
            items = getFacade().findAll();
        } catch (Exception e) {
            LOGGER.debug("FindAll", Level.SEVERE, e);
            JsfUtil.addErrorMessage("FindAll", e.getMessage());
        }
    }

    public String destroy() {
        try {
            getFacade().remove(current);
            return prepareList();
        } catch (Exception e) {
            LOGGER.debug("Destroy", Level.SEVERE, e);
            JsfUtil.addErrorMessage("Destroy", e.getMessage());
            return null;
        }

    }

    public String create() {
        LOGGER.debug(this.getClass() + ":create " + current.getClass());
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamTeamCreated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        LOGGER.debug(this.getClass() + ":update " + current);
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Update Done");//ResourceBundle.getBundle("/Bundle").getString("FamTeamUpdated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Update Failed");//ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return prepareView();
    }

    public String updateAndNoRedirect() {
        LOGGER.debug(this.getClass() + ":update " + current);
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Update Done");//ResourceBundle.getBundle("/Bundle").getString("FamTeamUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Update Failed");//ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return null;
    }

    //    @URLAction(phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public String loadAction() {
        LOGGER.debug(this.getClass() + " - loadAction " + id);
        if (id != null) {

            try {
                current = (T) getFacade().find(id);
                return null;

            } catch (Exception e) {
                LOGGER.debug("Load Action", Level.SEVERE, e);
                JsfUtil.addErrorMessage(e, "Load Action id=" + id);
                return "pretty:";
            }

        } else {
            JsfUtil.addErrorMessage("Load Action id NULL");
            return "pretty:";
        }
    }

    public String destroyAndView() {
        performDestroy();
//        recreateModel();
//        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return prepareView();
        } else {
            // all items were removed - go back to list
//            recreateModel();
            return prepareList();
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamTeamDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void testAction(ActionEvent actionEvent) {
        JsfUtil.addSuccessMessage("test Action", actionEvent.getComponent().getClientId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected void infoDestroy() {
        JsfUtil.addInfoMessage("Votre session a expiré. Veuillez vous re-logguer.", "Attention!");
    }
}
