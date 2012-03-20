/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import lombok.Data;
import org.fam.common.cdi.ClubFilter;
import org.fam.ejb.model.FamClub;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Named
@Data
public class SessionFilters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Produces
    @ClubFilter
    private FamClub clubFilter;
}
