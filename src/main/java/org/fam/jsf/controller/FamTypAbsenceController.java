package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTypAbsence;
import org.fam.ejb.session.FamTypAbsenceFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypAbsenceController")
@ViewScoped
public class FamTypAbsenceController extends AbstractController<FamTypAbsence> implements Serializable {

    @EJB
    private FamTypAbsenceFacade ejbFacade;

    public FamTypAbsenceController() {
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
    public FamTypAbsence getSelected() {
        if (current == null) {
            current = new FamTypAbsence();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypAbsenceFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypAbsence();
        return "pretty:editTypAbsence";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypAbsence();
        return "pretty:viewTypAbsence";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypAbsence";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypAbsence();
        selectedItemIndex = -1;
        return "pretty:createTypAbsence";
    }

//    @FacesConverter(forClass = FamTypAbsence.class)
//    public static class FamTypAbsenceControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamTypAbsenceController controller = (FamTypAbsenceController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famTypAbsenceController");
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
//            if (object instanceof FamTypAbsence) {
//                FamTypAbsence o = (FamTypAbsence) object;
//                return getStringKey(o.getIdTypAbsence());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypAbsenceController.class.getName());
//            }
//        }
//    }
}
