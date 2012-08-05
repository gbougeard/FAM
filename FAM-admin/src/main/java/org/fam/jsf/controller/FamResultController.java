/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamMatch;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.session.FamMatchFacade;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.List;

/**
 * @author gbougear
 */
@Model
@Loggable
@Getter
@Setter
public class FamResultController {

    @Inject
    private Logger LOGGER;
    //    @Inject
//    private CacheBean cacheBean;
    @Inject
    private FamMatchFacade ejb;

    private TreeNode selectedNode;
    private List<FamMatch> lstMatch;

    private FamSeasonCompetition selectedCompetition;

    /**
     * Creates a new instance of TestController
     */
    public FamResultController() {

    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {
    }

    public void onSelect() {
        lstMatch = ejb.findByCompetition(selectedCompetition);
    }

    public void onNodeSelect(NodeSelectEvent event) {
        lstMatch = ejb.findByCompetition((FamSeasonCompetition) selectedNode.getData());

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void displaySelectedSingle(ActionEvent event) {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
