/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import org.fam.common.log.LogUtil;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
public class BasePhaseListener implements PhaseListener {

    @Override
    public void beforePhase(PhaseEvent pe) {
//        FacesContext ctx = event.getFacesContext();
//        VariableResolver vr = ctx.getApplication().getVariableResolver();
//        PhaseBean phaseBean = (PhaseBeanvr.resolveVariable(ctx, "phaseBean");
//        phaseBean.setCurrentPhaseId(event.getPhaseId());
        LogUtil.log("BEFORE PHASE " + pe.getPhaseId(), Level.FINEST, null);
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
        LogUtil.log("AFTER PHASE " + pe.getPhaseId(), Level.FINEST, null);
    }
}
