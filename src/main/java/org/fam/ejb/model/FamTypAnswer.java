package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
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
    public Long getId(){
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
    @OneToMany(mappedBy = FamAnswer.PROP_TYP)
    private List<FamAnswer> famAnswerList;

    /**
     * 
     */
    public FamTypAnswer() {
    }

    /**
     * 
     * @param idTypAnswer
     */
    public FamTypAnswer(Long idTypAnswer) {
        this.idTypAnswer = idTypAnswer;
    }

    /**
     * 
     * @return
     */
    public Long getIdTypAnswer() {
        return idTypAnswer;
    }

    /**
     * 
     * @param idTypAnswer
     */
    public void setIdTypAnswer(Long idTypAnswer) {
        this.idTypAnswer = idTypAnswer;
    }

    /**
     * 
     * @return
     */
    public String getLibTypAnswer() {
        return libTypAnswer;
    }

    /**
     * 
     * @param libTypAnswer
     */
    public void setLibTypAnswer(String libTypAnswer) {
        this.libTypAnswer = libTypAnswer;
    }

    /**
     * 
     * @return
     */
    public String getGrpTypAnswer() {
        return grpTypAnswer;
    }

    /**
     * 
     * @param grpTypAnswer
     */
    public void setGrpTypAnswer(String grpTypAnswer) {
        this.grpTypAnswer = grpTypAnswer;
    }

    /**
     * 
     * @return
     */
    public List<FamAnswer> getFamAnswerList() {
        return famAnswerList;
    }

    /**
     * 
     * @param famAnswerList
     */
    public void setFamAnswerList(List<FamAnswer> famAnswerList) {
        this.famAnswerList = famAnswerList;
    }

    /**
     * 
     * @return
     */
    public String getCodTypAnswer() {
        return codTypAnswer;
    }

    /**
     * 
     * @param codTypAnswer
     */
    public void setCodTypAnswer(String codTypAnswer) {
        this.codTypAnswer = codTypAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTypAnswer != null ? idTypAnswer.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamTypAnswer)) {
            return false;
        }
        FamTypAnswer other = (FamTypAnswer) object;
        if (( this.idTypAnswer == null && other.idTypAnswer != null ) || ( this.idTypAnswer != null && !this.idTypAnswer.equals(other.idTypAnswer) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Class cls = this.getClass();
        int ii = 0;
        builder.append(this.getClass()).append(" [");
        for (Field f : cls.getDeclaredFields()) {
            try {
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
