package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.session.FamAnswerFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famAnswerController")
@ViewScoped
public class FamAnswerController extends AbstractController<FamAnswer> implements Serializable {

    @EJB
    private FamAnswerFacade ejbFacade;

    public FamAnswerController() {
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
    public FamAnswer getSelected() {
        if (current == null) {
            current = new FamAnswer();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamAnswerFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdAnswer();
        return "pretty:editAnswer";
    }

    @Override
    public String prepareView() {
        id = current.getIdAnswer();
        return "pretty:viewAnswer";
    }

    @Override
    public String prepareList() {
        return "pretty:listAnswer";
    }

    @Override
    public String prepareCreate() {
        current = new FamAnswer();
        selectedItemIndex = -1;
        return "pretty:createAnswer";
    }


//    @FacesConverter(forClass = FamAnswer.class)
//    public static class FamAnswerControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamAnswerController controller = (FamAnswerController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famAnswerController");
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
//            if (object instanceof FamAnswer) {
//                FamAnswer o = (FamAnswer) object;
//                return getStringKey(o.getIdAnswer());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamAnswerController.class.getName());
//            }
//        }
//    }
}
