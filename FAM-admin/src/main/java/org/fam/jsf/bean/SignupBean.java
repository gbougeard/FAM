/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Data;
import org.fam.ejb.model.FamClub;
import org.hibernate.validator.constraints.Email;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Model
@Data
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


}
