package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamAbsence;
import org.fam.ejb.session.AbstractFacade;
import org.fam.ejb.session.FamAbsenceFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famAbsenceController")
@ViewScoped
public class FamAbsenceController extends AbstractController<FamAbsence> implements Serializable {

    @EJB
    private FamAbsenceFacade ejbFacade;

    public FamAbsenceController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamAbsence getSelected() {
        if (current == null) {
            current = new FamAbsence();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdAbsence();
        return "pretty:editAbsence";
    }

    @Override
    public String prepareView() {
        id = current.getIdAbsence();
        return "pretty:viewAbsence";
    }

    @Override
    public String prepareList() {
        return "pretty:listAbsence";
    }

    @Override
    public String prepareCreate() {
        current = new FamAbsence();
        selectedItemIndex = -1;
        return "pretty:createAbsence";
    }

    @Override
    public AbstractFacade getFacade() {
        return ejbFacade;
    }


//    @FacesConverter(forClass = FamAbsence.class)
//    public static class FamAbsenceControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamAbsenceController controller = (FamAbsenceController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famAbsenceController");
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
//            if (object instanceof FamAbsence) {
//                FamAbsence o = (FamAbsence) object;
//                return getStringKey(o.getIdAbsence());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamAbsenceController.class.getName());
//            }
//        }
//    }
}
