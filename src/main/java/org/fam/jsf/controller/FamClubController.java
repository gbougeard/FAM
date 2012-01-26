package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamClub;
import org.fam.ejb.session.FamClubFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famClubController")
@ViewScoped
//@URLMappings(mappings = {
//    @URLMapping(id = "clubList", pattern = "/club/", viewId = "/ok/famClub/List.xhtml"),
//    @URLMapping(id = "clubCreate", pattern = "/club/c/", viewId = "/ok/famClub/Create.xhtml"),
//    @URLMapping(id = "clubView", pattern = "/club/#{ FamClubController.id }/", viewId = "/ok/famClub/View.jsf"),
//    @URLMapping(id = "clubEdit", pattern = "/club/e/#{ FamClubController.id }/", viewId = "/ok/famClub/Edit.jsf" )
//})
public class FamClubController extends AbstractController<FamClub>
        implements Serializable {

    @EJB
    private FamClubFacade ejbFacade;

    public FamClubController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
//        super.infoDestroy();
    }

    @Override
    public FamClub getSelected() {
        if (current == null) {
            current = new FamClub();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamClubFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdClub();
        return "pretty:editClub";
    }

    @Override
    public String prepareView() {
        id = current.getIdClub();
        return "pretty:viewClub";
    }

    @Override
    public String prepareList() {
        return "pretty:listClub";
    }

    @Override
    public String prepareCreate() {
        current = new FamClub();
        id = null;
        selectedItemIndex = -1;
        return "pretty:createClub";
    }

//    @Override
//    public void onRowSelect(SelectEvent event) {
//        super.onRowSelect(event);
//        FamClub o = (FamClub) event.getObject();
//        JsfUtil.addInfoMessage(o.getLibClub(), "Selected");
//    }
//    @Override
//    public void onRowUnselect(UnselectEvent event) {
//        super.onRowUnselect(event);
//        FamClub o = (FamClub) event.getObject();
//        JsfUtil.addInfoMessage(o.getLibClub(), "Unselected");
//    }
//    @FacesConverter(forClass = FamClub.class)
//    public static class FamClubControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            LogUtil.log(this.getClass() + "::getAsObject " + value, Level.OFF, null);
//            FamClubController controller = (FamClubController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famClubController");
//            return controller.getFacade().find(getKey(value));
//        }
//
//        java.lang.Long getKey(String value) {
//            java.lang.Long key;
//            key = Long.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(java.lang.Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null || object.equals("")) {
//                return null;
//            }
//            if (object instanceof FamClub) {
//                FamClub o = (FamClub) object;
//                return getStringKey(o.getIdClub());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamClubController.class.getName());
//            }
//        }
//    }
}
