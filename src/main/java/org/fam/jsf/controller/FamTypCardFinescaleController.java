package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTypCardFinescale;
import org.fam.ejb.session.FamTypCardFinescaleFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypCardFinescaleController")
@ViewScoped
public class FamTypCardFinescaleController extends AbstractController<FamTypCardFinescale> implements Serializable {

    @EJB
    private FamTypCardFinescaleFacade ejbFacade;

    public FamTypCardFinescaleController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamTypCardFinescale getSelected() {
        if (current == null) {
            current = new FamTypCardFinescale();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypCardFinescaleFacade getFacade() {
        return ejbFacade;
    }


    @Override
    public String prepareEdit() {
        id = current.getIdTypCardFinescale();
        return "pretty:editTypCardFinescale";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypCardFinescale();
        return "pretty:viewTypCardFinescale";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypCardFinescale";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypCardFinescale();
        selectedItemIndex = -1;
        return "pretty:createTypCardFinescale";
    }


//    @FacesConverter(forClass = FamTypCardFinescale.class)
//    public static class FamTypCardFinescaleControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamTypCardFinescaleController controller = (FamTypCardFinescaleController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famTypCardFinescaleController");
//            return controller.ejbFacade.find(getKey(value));
//        }
//
//        Long getKey(String value) {
//            Long key;
//            key = Long.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof FamTypCardFinescale) {
//                FamTypCardFinescale o = (FamTypCardFinescale) object;
//                return getStringKey(o.getIdTypCardFinescale());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypCardFinescaleController.class.getName());
//            }
//        }
//    }
}
