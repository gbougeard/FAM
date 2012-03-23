/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Data;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Model
@Data
public class LoginBean implements Serializable {

    @NotNull
    private String email;
    @NotNull
    private String password;


}
