package org.fam.jsf.bean;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamStaffFunction;
import org.fam.ejb.session.FamStaffFunctionFacade;
import org.fam.jsf.bean.util.JsfUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

@Named
@SessionScoped
public class FamStaffFunctionController implements Serializable {

    private FamStaffFunction current = new FamStaffFunction();
    private List<FamStaffFunction> items = new ArrayList<FamStaffFunction>();
    @EJB
    private FamStaffFunctionFacade ejbFacade;
    private int selectedItemIndex;

    public FamStaffFunctionController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    public FamStaffFunction getSelected() {
        if (current == null) {
            current = new FamStaffFunction();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(FamStaffFunction s) {
        this.current = s;
    }

    private FamStaffFunctionFacade getFacade() {
        return ejbFacade;
    }


    public String prepareList() {
        //recreateModel();
        findAll();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        //current = (FamStaffFunction)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new FamStaffFunction();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamStaffFunctionCreated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        //current = (FamStaffFunction)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            /*if (current.getIdStaffFunction() == null) {
            getFacade().create(current);
            } else {
            getFacade().edit(current);
            }
            findAll();
            
            current = new FamStaffFunction();  //reset form  
             */
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamStaffFunctionUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamStaffFunctionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public List<FamStaffFunction> getItems() {
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

    @FacesConverter(forClass = FamStaffFunction.class)
    public static class FamStaffFunctionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamStaffFunctionController controller = (FamStaffFunctionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famStaffFunctionController");
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
            if (object instanceof FamStaffFunction) {
                FamStaffFunction o = (FamStaffFunction) object;
                return getStringKey(o.getIdStaffFunction());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamStaffFunctionController.class.getName());
            }
        }
    }
}
