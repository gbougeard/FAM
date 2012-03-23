package org.fam.jsf.bean;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamFormationItem;
import org.fam.ejb.session.FamFormationItemFacade;
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
public class FamFormationItemController implements Serializable {

    private FamFormationItem current = new FamFormationItem();
    private List<FamFormationItem> items = new ArrayList<FamFormationItem>();
    @EJB
    private FamFormationItemFacade ejbFacade;
    private int selectedItemIndex;

    public FamFormationItemController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    public FamFormationItem getSelected() {
        if (current == null) {
            current = new FamFormationItem();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(FamFormationItem s) {
        this.current = s;
    }

    private FamFormationItemFacade getFacade() {
        return ejbFacade;
    }


    public String prepareList() {
        //recreateModel();
        findAll();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        //current = (FamFormationItem)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new FamFormationItem();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamFormationItemCreated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        //current = (FamFormationItem)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            /*if (current.getIdFormationItem() == null) {
            getFacade().create(current);
            } else {
            getFacade().edit(current);
            }
            findAll();
            
            current = new FamFormationItem();  //reset form  
             */
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamFormationItemUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamFormationItemDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }


    public List<FamFormationItem> getItems() {
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

    @FacesConverter(forClass = FamFormationItem.class)
    public static class FamFormationItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamFormationItemController controller = (FamFormationItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famFormationItemController");
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
            if (object instanceof FamFormationItem) {
                FamFormationItem o = (FamFormationItem) object;
                return getStringKey(o.getIdFormationItem());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamFormationItemController.class.getName());
            }
        }
    }
}
