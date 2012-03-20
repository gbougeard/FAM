/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import javax.faces.event.PhaseId;
import java.io.Serializable;

/**
 * @author mask_hot
 */
public class PhaseBean implements Serializable {

    private transient PhaseId currentPhaseId;

    public PhaseId getCurrentPhaseId() {
        return currentPhaseId;
    }

    public void setCurrentPhaseId(PhaseId currentPhaseId) {
        this.currentPhaseId = currentPhaseId;
    }
}
