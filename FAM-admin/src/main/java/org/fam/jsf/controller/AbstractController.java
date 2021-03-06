/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.model.FamEntity;
import org.fam.ejb.session.AbstractFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.common.AbstractBackingBean;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author mask_hot
 */
@Getter
@Setter
public abstract class AbstractController<T> extends AbstractBackingBean {

    @Inject
    private Logger LOGGER;

    public final static String PRETTY_PREFIX = "pretty:";
    protected T current;
    protected List items = new ArrayList<T>();
    //    private Boolean select;
    protected int selectedItemIndex;
    //
    private LazyDataModel<T> lazyModel;
    private List<T> lazyItems = new ArrayList<T>();
    //
//    @URLQueryParameter("id")
    protected Long id;
    //    @URLQueryParameter("page")
    protected Integer page;

    public AbstractController() {
//        init();
    }

    protected String getPrettyId(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(PRETTY_PREFIX).append(id);
        return sb.toString();
    }

    public void initLazyModel() {
        LOGGER.debug("initLazyModel");

        lazyModel = new LazyDataModel<T>() {

            @Override
            public List<T> load(int first,
                                int pageSize,
                                String sortField,
                                SortOrder sortOrder,
                                Map<String, String> filters) {
                if (first != 0) {
                    page = (first / pageSize) + 1;
                }
                LOGGER.debug("Loading the lazy data between " + first + " " + pageSize);

                try {
                    lazyItems = getFacade().findAllLazy(first,
                                                        pageSize,
                                                        sortField,
                                                        sortOrder.equals(SortOrder.ASCENDING),
                                                        filters);

                    /**
                     * In a real application, this number should be resolved by a projection query
                     */
                    int count = getFacade().countLazy(filters);

                    LOGGER.debug("nb items " + lazyItems.size() + " count " + count);

                    lazyModel.setRowCount(count);

                } catch (Exception e) {
                    LOGGER.error("LazyLoad", e);
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

    public void setSelected(T t) {
        current = t;
    }

    public void onRowSelect(SelectEvent event) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("onRowSelect");
        }
//        select = Boolean.TRUE;

        T o = (T) event.getObject();
        current = o;
    }

    public void onRowUnselect(UnselectEvent event) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("onRowUnselect");
        }
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("prepareList");
        }
        initLazyModel();
//        setSelect(Boolean.FALSE);
        return "List?faces-redirect=true";
    }

    protected void findAll() {
        try {
            items = getFacade().findAll();
        } catch (Exception e) {
            LOGGER.error("FindAll", e);
            JsfUtil.addErrorMessage("FindAll", e.getMessage());
        }
    }

    public String destroy() {
        try {
            getFacade().remove(current);
            return prepareList();
        } catch (Exception e) {
            LOGGER.error("Destroy", e);
            JsfUtil.addErrorMessage("Destroy", e.getMessage());
            return null;
        }

    }

    public String create() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.getClass() + ":create " + current.getClass());
        }
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.getClass() + ":update " + current);
        }
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
        if (page == null) {
            page = 1;
        }
        if (id != null) {

            try {
                current = (T) getFacade().find(id);
                return null;

            } catch (Exception e) {
                LOGGER.error("Load Action", e);
                JsfUtil.addErrorMessage(e, "Load Action id=" + id);
                return PRETTY_PREFIX;
            }

        } else {
            JsfUtil.addErrorMessage("Load Action id NULL");
            return PRETTY_PREFIX;
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


    protected void infoDestroy() {
        JsfUtil.addInfoMessage("Votre session a expiré. Veuillez vous re-logguer.", "Attention!");
    }


}
