/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.ejb.session.FamEventFacade;
import org.fam.jsf.cache.CacheBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 * @author gbougear
 */
@Model
@Loggable
@Getter
@Setter
public class CompetitionSelectController {

    @Inject
    private Logger LOGGER;
    @Inject
    private CacheBean cacheBean;
    @Inject
    private FamEventFacade ejb;


    private TreeNode root;
    private TreeNode selectedNode;

    /**
     * Creates a new instance of TestController
     */
    public CompetitionSelectController() {

    }

    @PostConstruct
    private void postConstruct() {
        root = new DefaultTreeNode("Root", null);
        for (FamSeasonCompetition famSeasonCompetition : cacheBean.getListSeasonCompetition()) {
            TreeNode nodeSeason = new DefaultTreeNode(famSeasonCompetition.getFamSeason().getLibSeason(), root);
            TreeNode nodeCompetition = new DefaultTreeNode(famSeasonCompetition, nodeSeason);
        }
    }

    @PreDestroy
    private void preDestroy() {
    }

    public void displaySelectedSingle(ActionEvent event) {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
