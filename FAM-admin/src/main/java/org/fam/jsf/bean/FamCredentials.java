/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */

@Model
@Getter
@Setter
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


}
