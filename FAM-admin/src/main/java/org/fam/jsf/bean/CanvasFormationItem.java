/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.model.FamFormationItem;
import org.fam.ejb.model.FamPlayer;

import javax.enterprise.inject.Model;

/**
 * @author mask_hot
 */
@Model
@Getter
@Setter
public class CanvasFormationItem {
    private String strIdx;
    private FamFormationItem famFormationItem;
    private FamPlayer famPlayer;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CanvasFormationItem that = (CanvasFormationItem) o;

        if (famFormationItem != null ? !famFormationItem.equals(that.famFormationItem) : that.famFormationItem != null) {
            return false;
        }
        if (famPlayer != null ? !famPlayer.equals(that.famPlayer) : that.famPlayer != null) {
            return false;
        }
        if (strIdx != null ? !strIdx.equals(that.strIdx) : that.strIdx != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = strIdx != null ? strIdx.hashCode() : 0;
        result = 31 * result + (famFormationItem != null ? famFormationItem.hashCode() : 0);
        result = 31 * result + (famPlayer != null ? famPlayer.hashCode() : 0);
        return result;
    }
}
