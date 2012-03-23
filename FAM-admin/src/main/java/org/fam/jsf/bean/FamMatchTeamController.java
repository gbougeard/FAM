package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamMatchTeam;
import org.fam.ejb.session.FamMatchTeamFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Named
@SessionScoped
@Loggable
@Getter
@Setter
public class FamMatchTeamController implements Serializable {

    private static final long serialVersionUID = 2955219154097614663L;
    @Inject
    private Logger LOGGER;
    @Inject
    private FamMatchTeamFacade ejbFacade;

    private FamMatchTeam current = new FamMatchTeam();
    private List<FamMatchTeam> items = new ArrayList<FamMatchTeam>();

    private int selectedItemIndex;

    public FamMatchTeamController() {
    }

    @PostConstruct
    private void postConstruct() {
    }

    @PreDestroy
    private void preDestroy() {
    }

    public FamMatchTeam getSelected() {
        if (current == null) {
            current = new FamMatchTeam();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(FamMatchTeam s) {
        this.current = s;
    }

    private FamMatchTeamFacade getFacade() {
        return ejbFacade;
    }


    public String prepareList() {
        //recreateModel();
        findAll();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        //current = (FamMatchTeam)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new FamMatchTeam();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamMatchTeamCreated"));
            return prepareView();
        } catch (Exception e) {
            LOGGER.error("create", e);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        //current = (FamMatchTeam)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            /*if (current.getFamMatch().getIdMatch() == null) {
            getFacade().create(current);
            } else {
            getFacade().edit(current);
            }
            findAll();
            
            current = new FamMatchTeam();  //reset form  
             */
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamMatchTeamUpdated"));
            return "View?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("update", e);
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamMatchTeamDeleted"));
        } catch (Exception e) {
            LOGGER.error("delete", e);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }


    private void recreateModel() {
        items = null;
    }


    @FacesConverter(forClass = FamMatchTeam.class)
    public static class FamMatchTeamControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamMatchTeamController controller = (FamMatchTeamController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famMatchTeamController");
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
            if (object instanceof FamMatchTeam) {
                FamMatchTeam o = (FamMatchTeam) object;
                return getStringKey(o.getFamMatch().getIdMatch());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamMatchTeamController.class.getName());
            }
        }
    }
}
