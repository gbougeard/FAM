package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamCompetitionTeam;
import org.fam.ejb.session.FamCompetitionTeamFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;

@Named
@SessionScoped
public class FamCompetitionTeamController extends AbstractController<FamCompetitionTeam> implements Serializable {

    @EJB
    private FamCompetitionTeamFacade ejbFacade;

    public FamCompetitionTeamController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamCompetitionTeam getSelected() {
        if (current == null) {
            current = new FamCompetitionTeam();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCompetitionTeamFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareCreate() {
        current = new FamCompetitionTeam();
        selectedItemIndex = -1;
        return super.prepareCreate();
    }


    @FacesConverter(forClass = FamCompetitionTeam.class)
    public static class FamCompetitionTeamControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamCompetitionTeamController controller = (FamCompetitionTeamController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famCompetitionTeamController");
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
            if (object instanceof FamCompetitionTeam) {
                FamCompetitionTeam o = (FamCompetitionTeam) object;
                return getStringKey(o.getIdCompetitionTeam());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamCompetitionTeamController.class.getName());
            }
        }
    }
}
