package org.fam.jsf.controller;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTeam;
import org.fam.ejb.session.FamTeamFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTeamController")
@ViewScoped
//@URLMappings(mappings = {
//        @URLMapping(id = FamTeamController.PRETTY_ID_LIST,
//                pattern = FamTeamController.PATTERN,
//                viewId = "/ok/famTeam/List.xhtml"),
//        @URLMapping(id = FamTeamController.PRETTY_ID_CREATE,
//                parentId = FamTeamController.PRETTY_ID_LIST,
//                pattern = "c",
//                viewId = "/ok/famTeam/Create.xhtml"),
//        @URLMapping(id = FamTeamController.PRETTY_ID_VIEW,
//                parentId = FamTeamController.PRETTY_ID_LIST,
//                pattern = "v/#{ FamTeamController.id }",
//                viewId = "/ok/famTeam/View.xhtml"),
//        @URLMapping(id = FamTeamController.PRETTY_ID_EDIT,
//                parentId = FamTeamController.PRETTY_ID_LIST,
//                pattern = "e/#{ FamTeamController.id }",
//                viewId = "/ok/famTeam/Edit.xhtml")
//})
public class FamTeamController extends AbstractController<FamTeam> implements Serializable {

    public static final String PATTERN = "/team/";
    public static final String PRETTY_ID_LIST = "listTeam";
    public static final String PRETTY_ID_CREATE = "createTeam";
    public static final String PRETTY_ID_EDIT = "editTeam";
    public static final String PRETTY_ID_VIEW = "viewTeam";
    //
    @EJB
    private FamTeamFacade ejbFacade;

    public FamTeamController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamTeam getSelected() {
        if (current == null) {
            current = new FamTeam();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTeamFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        LogUtil.log(this.getClass() + "::prepareEdit " + id, Level.OFF, null);
        id = current.getIdTeam();
        LogUtil.log(this.getClass() + "::prepareEdit " + id, Level.OFF, null);
        return getPrettyId(PRETTY_ID_EDIT);
    }

    @Override
    public String prepareView() {
        id = current.getIdTeam();
        return getPrettyId(PRETTY_ID_VIEW);
    }

    @Override
    public String prepareList() {
        return getPrettyId(PRETTY_ID_LIST);
    }

    @Override
    public String prepareCreate() {
        current = new FamTeam();
        id = null;
        selectedItemIndex = -1;
        return getPrettyId(PRETTY_ID_CREATE);
    }

    @Override
    @URLActions(actions = {
            @URLAction(mappingId = PRETTY_ID_LIST, onPostback = false)
    })
    public void initLazyModel() {
        super.initLazyModel();
    }

    @Override
    @URLActions(actions = {
            @URLAction(mappingId = PRETTY_ID_EDIT, onPostback = false),
            @URLAction(mappingId = PRETTY_ID_VIEW, onPostback = false)
    })
    public String loadAction() {
        LogUtil.log(this.getClass() + "::loadAction " + id, Level.OFF, null);
        return super.loadAction();
    }

    @FacesConverter(forClass = FamTeam.class)
    public static class FamTeamControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamTeamController controller = (FamTeamController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famTeamController");
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
            if (object instanceof FamTeam) {
                FamTeam o = (FamTeam) object;
                return getStringKey(o.getIdTeam());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTeamController.class.getName());
            }
        }
    }
}
