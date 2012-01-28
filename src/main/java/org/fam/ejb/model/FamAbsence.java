package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mask_hot
 */
@Data
@Entity
@Table(name = FamAbsence.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamAbsence.findAll", query = "SELECT f FROM FamAbsence f"),
        @NamedQuery(name = "FamAbsence.findByIdAbsence", query = "SELECT f FROM FamAbsence f WHERE f.idAbsence = :idAbsence"),
        @NamedQuery(name = "FamAbsence.findByDtBegAbsence", query = "SELECT f FROM FamAbsence f WHERE f.dtBegAbsence = :dtBegAbsence"),
        @NamedQuery(name = "FamAbsence.findByDtEndAbsence", query = "SELECT f FROM FamAbsence f WHERE f.dtEndAbsence = :dtEndAbsence"),
        @NamedQuery(name = "FamAbsence.findByDtCreat", query = "SELECT f FROM FamAbsence f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamAbsence.findByDtModif", query = "SELECT f FROM FamAbsence f WHERE f.dtModif = :dtModif")})
public class FamAbsence extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_absence";
    //
    /**
     *
     */
    public static final String PROP_ID = "idAbsence";
    /**
     *
     */
    public static final String COL_ID = "id_absence";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idAbsence;

    @Override
    public Long getId() {
        return this.getIdAbsence();
    }
    //
    /**
     *
     */
    public static final String PROP_COMMENTS = "comments";
    @Lob
    @Column(name = PROP_COMMENTS)
    private String comments;
    //
    /**
     *
     */
    public static final String PROP_DT_BEG = "dtBegAbsence";
    public static final String COL_DT_BEG = "dt_beg_absence";
    @Basic(optional = false)
    @Column(name = COL_DT_BEG)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtBegAbsence;
    //
    /**
     *
     */
    public static final String PROP_DT_END = "dtEndAbsence";
    public static final String COL_DT_END = "dt_end_absence";
    @Basic(optional = false)
    @Column(name = COL_DT_END)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtEndAbsence;
    //
    /**
     *
     */
    public static final String COL_ID_TYP_ABSENCE = "id_typ_absence";
    /**
     *
     */
    public static final String PROP_TYP_ABSENCE = "famTypAbsence";
    @JoinColumn(name = COL_ID_TYP_ABSENCE, referencedColumnName = FamTypAbsence.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamTypAbsence famTypAbsence;

}
