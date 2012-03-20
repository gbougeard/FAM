package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Data
@Entity
@Table(name = FamTypAbsence.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTypAbsence.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamTypAbsence.findAll", query = "SELECT f FROM FamTypAbsence f"),
        @NamedQuery(name = "FamTypAbsence.findByIdTypAbsence", query = "SELECT f FROM FamTypAbsence f WHERE f.idTypAbsence = :idTypAbsence"),
        @NamedQuery(name = "FamTypAbsence.findByLibTypAbsence", query = "SELECT f FROM FamTypAbsence f WHERE f.libTypAbsence = :libTypAbsence"),
        @NamedQuery(name = "FamTypAbsence.findByDtCreat", query = "SELECT f FROM FamTypAbsence f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypAbsence.findByDtModif", query = "SELECT f FROM FamTypAbsence f WHERE f.dtModif = :dtModif")})
public class FamTypAbsence extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_absence";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypAbsence";
    /**
     *
     */
    public static final String COL_ID = "id_typ_absence";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypAbsence;

    @Override
    public Long getId() {
        return this.getIdTypAbsence();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypAbsence";
    public static final String COL_LIB = "lib_typ_absence";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libTypAbsence;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypAbsence";
    public static final String COL_COD = "cod_typ_absence";
    @Basic(optional = false)
    @Column(name = COL_COD)
    @NotEmpty
    private String codTypAbsence;
    //
//    public static final String PROP_ABSENCES = "famAbsenceList";
//    @OneToMany(mappedBy = FamAbsence.PROP_TYP_ABSENCE)
//    private List<FamAbsence> famAbsenceList;

    /**
     *
     */
    public FamTypAbsence() {
    }


}
