/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import org.fam.ejb.model.FamClub;
import org.hibernate.validator.constraints.Email;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Model
public class SignupBean implements Serializable {

    @NotNull(message = "saisissez un mot de passe")
    private String password;
    @NotNull(message = "confirmez votre mot de passe")
    private String password2;
    //    @AssertTrue(message="les mots de passe sont différents")
//    private Boolean bPasswordOk;
    @Email(message = "entrez un email valide")
    private String email;
    //    @NotNull(message="prénom obligatoire")
//    private String firstName;
//    @NotNull(message="nom obligatoire")
//    private String lastName;
    private FamClub club;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public FamClub getClub() {
        return club;
    }

    public void setClub(FamClub club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "SignupBean{" + "password=" + password + ", password2=" + password2 + ", email=" + email + ", club=" + club + '}';
    }


}
