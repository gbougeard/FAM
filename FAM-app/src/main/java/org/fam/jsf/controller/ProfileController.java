/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamPlayerFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mask_hot
 */
@Named(value = "profileController")
@SessionScoped
public class ProfileController implements Serializable {

    private FamUser famUser;
    @EJB
    FamPlayerFacade ejbPlayer;
    FamPlayer famPlayer;
    List<FamPlayer> listPlayer = new ArrayList<FamPlayer>();


    /**
     * Creates a new instance of ProfileController
     */
    public ProfileController() {
    }

    public FamUser getFamUser() {
        return famUser;
    }

    public void setFamUser(FamUser famUser) {
        this.famUser = famUser;
    }

    public void afterSignup() {
        listPlayer = ejbPlayer.findPossiblePlayers(famUser);
    }

    public void afterSignin() {

    }

    public FamPlayer getFamPlayer() {
        return famPlayer;
    }

    public void setFamPlayer(FamPlayer famPlayer) {
        this.famPlayer = famPlayer;
    }

    public List<FamPlayer> getListPlayer() {
        return listPlayer;
    }

    public void setListPlayer(List<FamPlayer> listPlayer) {
        this.listPlayer = listPlayer;
    }


}
