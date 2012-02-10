/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.common;

import org.fam.common.log.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
public class BasePhaseListener implements PhaseListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePhaseListener.class);

    @Override
    public void beforePhase(PhaseEvent pe) {
//        FacesContext ctx = event.getFacesContext();
//        VariableResolver vr = ctx.getApplication().getVariableResolver();
//        PhaseBean phaseBean = (PhaseBeanvr.resolveVariable(ctx, "phaseBean");
//        phaseBean.setCurrentPhaseId(event.getPhaseId());
        LOGGER.trace("BEFORE PHASE " + pe.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
        LOGGER.trace("AFTER PHASE " + pe.getPhaseId());
    }
}
