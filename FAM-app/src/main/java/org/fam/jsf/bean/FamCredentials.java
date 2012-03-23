/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import org.fam.common.log.LogUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.logging.Level;

/**
 * @author mask_hot
 */

@Model
public class FamCredentials implements Serializable {


    @NotNull
//    @NotEmpty
    private String username;
    @NotNull
//    @NotEmpty
    private String password;
//    @NotNull
//    @NotEmpty
//    private String passwordConfirmed;

//    public void validPassword(FacesContext context, UIComponent component, Object value) {
//
//        if (this.password != null && !this.password.isEmpty()) {
//            if (password.equals((String) value) == false) {
//                // password mismatch
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password mismatch", null);
//                throw new ValidatorException(message);
//            }
//        }
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //
//    public String getPasswordConfirmed() {
//        return passwordConfirmed;
//    }
//
//    public void setPasswordConfirmed(String passwordConfirmed) {
//
//        this.passwordConfirmed = passwordConfirmed;
//    }
    @PostConstruct
    void init() {

    }
}
