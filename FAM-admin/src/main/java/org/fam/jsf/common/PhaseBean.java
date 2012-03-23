/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import lombok.Data;

import javax.faces.event.PhaseId;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Data
public class PhaseBean implements Serializable {

    private transient PhaseId currentPhaseId;

}
