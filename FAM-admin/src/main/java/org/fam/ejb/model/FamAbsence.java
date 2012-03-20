package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mask_hot
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = FamAbsence.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamAbsence.FIND_ALL,
                query = "SELECT f FROM FamAbsence f"),
        @NamedQuery(name = FamAbsence.FIND_BY_ID_ABSENCE,
                query = "SELECT f FROM FamAbsence f WHERE f.idAbsence = :idAbsence"),
        @NamedQuery(name = FamAbsence.FIND_BY_DT_BEG_ABSENCE,
                query = "SELECT f FROM FamAbsence f WHERE f.dtBegAbsence = :dtBegAbsence"),
        @NamedQuery(name = FamAbsence.FIND_BY_DT_END_ABSENCE,
                query = "SELECT f FROM FamAbsence f WHERE f.dtEndAbsence = :dtEndAbsence"),
        @NamedQuery(name = FamAbsence.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamAbsence f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamAbsence.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamAbsence f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamAbsence extends FamEntity implements Serializable {

    private static final long serialVersionUID = -7958086676251312322L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_absence";
    //
    public static final String FIND_ALL = "FamAbsence.findAll";
    public static final String FIND_BY_ID_ABSENCE = "FamAbsence.findByIdAbsence";
    public static final String FIND_BY_DT_BEG_ABSENCE = "FamAbsence.findByDtBegAbsence";
    public static final String FIND_BY_DT_END_ABSENCE = "FamAbsence.findByDtEndAbsence";
    public static final String FIND_BY_DT_CREAT = "FamAbsence.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamAbsence.findByDtModif";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FamAbsence that = (FamAbsence) o;

        return idAbsence.equals(that.idAbsence);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idAbsence.hashCode();
        return result;
    }
}
