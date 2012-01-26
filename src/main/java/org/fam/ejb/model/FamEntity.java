package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.ejb.listener.AbstractEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gbougear
 */
@MappedSuperclass
@EntityListeners({
    AbstractEntityListener.class
})
public class FamEntity implements Serializable {

    /**
     * 
     */
    public static final String PROP_DT_CREAT = "dt_creat";
    @Basic(optional = false)
    @Column(name = PROP_DT_CREAT)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreat;
    /**
     * 
     */
    public static final String PROP_DT_MODIF = "dt_modif";
    @Column(name = PROP_DT_MODIF)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtModif;

    /**
     * 
     * @return
     */
    public Date getDtCreat() {
        return dtCreat;
    }

    /**
     * 
     * @param dt
     */
    public void setDtCreat(Date dt) {
        this.dtCreat = dt;
    }

    /**
     * 
     * @return
     */
    public Date getDtModif() {
        return dtModif;
    }

    /**
     * 
     * @param dt
     */
    public void setDtModif(Date dt) {
        this.dtModif = dt;
    }
    
    public Long getId(){
        return null;
    }
}