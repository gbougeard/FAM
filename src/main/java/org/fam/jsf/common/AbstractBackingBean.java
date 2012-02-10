/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.VariableResolver;
import javax.faces.event.PhaseId;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Interceptors({BasePhaseListener.class})
public class AbstractBackingBean implements Serializable {

    private PhaseBean phaseBean;

    public PhaseBean getPhaseBean() {
        if (phaseBean == null) {
            phaseBean = (PhaseBean) getVariable("phaseBean");
        }
        return phaseBean;
    }

    public PhaseId getPhaseId() {
        return getPhaseBean().getCurrentPhaseId();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Application getApplication() {
        return getFacesContext().getApplication();
    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    protected VariableResolver getVariableResolver() {
        return getApplication().getVariableResolver();
    }

    protected Object getVariable(String name) {
        return getVariableResolver().resolveVariable(getFacesContext(), name);
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected String getParameter(String name) {
        HttpServletRequest request = getRequest();
        return request.getParameter(name);
    }

    protected void addSessionAttribute(String name, Object o) {
        FacesContext facesContext = getFacesContext();
        StringBuilder sb = new StringBuilder();
        sb.append("#{sessionScope.").append(name).append("}");
        getApplication().createValueBinding(sb.toString()).setValue(facesContext, o);
    }

    protected void addMessage(Severity severity, String message) {
        getFacesContext().addMessage(null, new FacesMessage(severity, message, message));
    }

    protected void addMessage(Severity severity, String summary, String detail) {
        getFacesContext().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    protected void addMessage(String clientid, Severity severity, String summary, String detail) {
        getFacesContext().addMessage(clientid, new FacesMessage(severity, summary, detail));
    }

    protected void addMessage(UIComponent component, Severity severity, String summary, String detail) {
        String clientid = component.getClientId(getFacesContext());
        getFacesContext().addMessage(clientid, new FacesMessage(severity, summary, detail));
    }
}
