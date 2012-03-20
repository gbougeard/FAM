package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTypCard;
import org.fam.ejb.session.FamTypCardFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypCardController")
@ViewScoped
public class FamTypCardController extends AbstractController<FamTypCard> implements Serializable {

    @EJB
    private FamTypCardFacade ejbFacade;

    public FamTypCardController() {
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
    public FamTypCard getSelected() {
        if (current == null) {
            current = new FamTypCard();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypCardFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypCard();
        return "pretty:editTypCard";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypCard();
        return "pretty:viewTypCard";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypCard";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypCard();
        selectedItemIndex = -1;
        return "pretty:createTypCard";
    }

//    @FacesConverter(forClass = FamTypCard.class)
//    public static class FamTypCardControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamTypCardController controller = (FamTypCardController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famTypCardController");
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
//            if (object instanceof FamTypCard) {
//                FamTypCard o = (FamTypCard) object;
//                return getStringKey(o.getIdTypCard());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypCardController.class.getName());
//            }
//        }
//    }
}
