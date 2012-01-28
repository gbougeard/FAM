package org.fam.jsf.bean;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPlayerSeason;
import org.fam.ejb.session.FamPlayerSeasonFacade;
import org.fam.jsf.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

@ManagedBean(name = "famPlayerSeasonController")
@SessionScoped
public class FamPlayerSeasonController implements Serializable {

    private FamPlayerSeason current = new FamPlayerSeason();
    private List<FamPlayerSeason> items = new ArrayList<FamPlayerSeason>();
    @EJB
    private FamPlayerSeasonFacade ejbFacade;
    private int selectedItemIndex;

    public FamPlayerSeasonController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    public FamPlayerSeason getSelected() {
        if (current == null) {
            current = new FamPlayerSeason();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(FamPlayerSeason s) {
        this.current = s;
    }

    private FamPlayerSeasonFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        //recreateModel();
        findAll();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        //current = (FamPlayerSeason)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new FamPlayerSeason();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamPlayerSeasonCreated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        //current = (FamPlayerSeason)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            /*if (current.getFamPlayer().getIdPlayer() == null) {
            getFacade().create(current);
            } else {
            getFacade().edit(current);
            }
            findAll();
            
            current = new FamPlayerSeason();  //reset form  
             */
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamPlayerSeasonUpdated"));
            return "View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        getFacade().remove(current);
        return "List?faces-redirect=true";
    }

    private void findAll() {
        items = ejbFacade.findAll();
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        if (selectedItemIndex >= 0) {
            return "View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamPlayerSeasonDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }


    public List<FamPlayerSeason> getItems() {
        /*if (items == null) {
        items = getPagination().createPageDataModel();
        }*/
        return items;
    }

    private void recreateModel() {
        items = null;
    }


    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = FamPlayerSeason.class)
    public static class FamPlayerSeasonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamPlayerSeasonController controller = (FamPlayerSeasonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famPlayerSeasonController");
            return controller.ejbFacade.find(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FamPlayerSeason) {
                FamPlayerSeason o = (FamPlayerSeason) object;
                return getStringKey(o.getFamPlayer().getIdPlayer());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamPlayerSeasonController.class.getName());
            }
        }
    }
}
