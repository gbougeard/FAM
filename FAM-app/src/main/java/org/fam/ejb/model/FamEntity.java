package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Data;
import org.fam.ejb.listener.AbstractEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author gbougear
 */
@MappedSuperclass
@EntityListeners({
        AbstractEntityListener.class
})
@Data
public class FamEntity implements Serializable {

    @Version
    private long version;
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

    public Long getId() {
        return null;
    }
}