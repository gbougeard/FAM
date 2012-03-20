/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.model.FamPlayer;

import javax.enterprise.inject.Model;

/**
 * @author mask_hot
 */
@Model
@Getter
@Setter
public class WorkoutDebriefBean {
    private FamAnswer famAnswer;
    private FamPlayer famPlayer;
}
