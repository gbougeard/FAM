package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Data
@Entity
@Table(name = FamTypAnswer.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTypAnswer.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamTypAnswer.findAll", query = "SELECT f FROM FamTypAnswer f"),
        @NamedQuery(name = "FamTypAnswer.findByIdTypAnswer", query = "SELECT f FROM FamTypAnswer f WHERE f.idTypAnswer = :idTypAnswer"),
        @NamedQuery(name = "FamTypAnswer.findByLibTypAnswer", query = "SELECT f FROM FamTypAnswer f WHERE f.libTypAnswer = :libTypAnswer"),
        @NamedQuery(name = "FamTypAnswer.findByGrpTypAnswer", query = "SELECT f FROM FamTypAnswer f WHERE f.grpTypAnswer = :grpTypAnswer"),
        @NamedQuery(name = "FamTypAnswer.findByDtCreat", query = "SELECT f FROM FamTypAnswer f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamTypAnswer.findByDtModif", query = "SELECT f FROM FamTypAnswer f WHERE f.dtModif = :dtModif")})
public class FamTypAnswer extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_typ_answer";
    //
    /**
     *
     */
    public static final String PROP_ID = "idTypAnswer";
    /**
     *
     */
    public static final String COL_ID = "id_typ_answer";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTypAnswer;

    @Override
    public Long getId() {
        return this.getIdTypAnswer();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libTypAnswer";
    /**
     *
     */
    public static final String COL_LIB = "lib_typ_answer";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libTypAnswer;
    //
    /**
     *
     */
    public static final String PROP_COD = "codTypAnswer";
    /**
     *
     */
    public static final String COL_COD = "cod_typ_answer";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_COD)
    private String codTypAnswer;
    //
    /**
     *
     */
    public static final String PROP_GRP = "grpTypAnswer";
    /**
     *
     */
    public static final String COL_GRP = "grp_typ_answer";
    @Basic(optional = false)
    @NotNull
    @Column(name = COL_GRP)
    private String grpTypAnswer;
    //
//    @OneToMany(mappedBy = FamAnswer.PROP_TYP)
//    private List<FamAnswer> famAnswerList;

    /**
     *
     */
    public FamTypAnswer() {
    }

}
