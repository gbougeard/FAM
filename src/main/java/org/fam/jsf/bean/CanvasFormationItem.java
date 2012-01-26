/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Data;
import org.fam.ejb.model.FamFormationItem;
import org.fam.ejb.model.FamPlayer;

import javax.enterprise.inject.Model;

/**
 * @author mask_hot
 */
@Model
@Data
public class CanvasFormationItem {
    private String strIdx;
    private FamFormationItem famFormationItem;
    private FamPlayer famPlayer;
}
