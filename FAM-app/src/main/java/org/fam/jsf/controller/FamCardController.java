package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamCard;
import org.fam.ejb.session.FamCardFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famCardController")
@ViewScoped
public class FamCardController extends AbstractController<FamCard> implements Serializable {

    @EJB
    private FamCardFacade ejbFacade;

    public FamCardController() {
    }

    @PostConstruct
    private void postConstruct() {

        findAll();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamCard getSelected() {
        if (current == null) {
            current = new FamCard();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCardFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdCard();
        return "pretty:editCard";
    }

    @Override
    public String prepareView() {
        id = current.getIdCard();
        return "pretty:viewCard";
    }

    @Override
    public String prepareList() {
        return "pretty:listCard";
    }

    @Override
    public String prepareCreate() {
        current = new FamCard();
        selectedItemIndex = -1;
        return "pretty:createCard";
    }

//    @FacesConverter(forClass = FamCard.class)
//    public static class FamCardControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamCardController controller = (FamCardController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famCardController");
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
//            if (object instanceof FamCard) {
//                FamCard o = (FamCard) object;
//                return getStringKey(o.getIdCard());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamCardController.class.getName());
//            }
//        }
//    }
}
