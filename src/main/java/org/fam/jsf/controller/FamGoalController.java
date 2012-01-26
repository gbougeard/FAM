package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamGoal;
import org.fam.ejb.session.FamGoalFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famGoalController")
@ViewScoped
public class FamGoalController extends AbstractController<FamGoal> implements Serializable {

    @EJB
    private FamGoalFacade ejbFacade;

    public FamGoalController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamGoal getSelected() {
        if (current == null) {
            current = new FamGoal();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamGoalFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdGoal();
        return "pretty:editGoal";
    }

    @Override
    public String prepareView() {
        id = current.getIdGoal();
        return "pretty:viewGoal";
    }

    @Override
    public String prepareList() {
        return "pretty:listGoal";
    }

    @Override
    public String prepareCreate() {
        current = new FamGoal();
        selectedItemIndex = -1;
        return "pretty:createGoal";
    }


//    @FacesConverter(forClass = FamGoal.class)
//    public static class FamGoalControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamGoalController controller = (FamGoalController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famGoalController");
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
//            if (object instanceof FamGoal) {
//                FamGoal o = (FamGoal) object;
//                return getStringKey(o.getIdGoal());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamGoalController.class.getName());
//            }
//        }
//    }
}
