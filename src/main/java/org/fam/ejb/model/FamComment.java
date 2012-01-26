package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamComment.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamComment.findAll", query = "SELECT f FROM FamComment f"),
    @NamedQuery(name = "FamComment.findByIdComment", query = "SELECT f FROM FamComment f WHERE f.idComment = :idComment"),
    @NamedQuery(name = "FamComment.findByDtCreat", query = "SELECT f FROM FamComment f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamComment.findByDtModif", query = "SELECT f FROM FamComment f WHERE f.dtModif = :dtModif")})
public class FamComment extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_comment";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idComment";
    /**
     * 
     */
    public static final String COL_ID = "id_comment";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idComment;

    
    @Override
    public Long getId(){
        return this.getIdComment();
    }
    /**
     * 
     */
    public static final String PROP_COMMENTS = "comments";
    @Basic(optional = false)
    @Lob
    @Column(name = PROP_COMMENTS)
    @NotEmpty
    private String comments;
//    @JoinColumn(name = "id_match", referencedColumnName = "id_match")
//    @ManyToOne
//    private FamMatch famMatch;
//    @JoinColumn(name = "id_season_event", referencedColumnName = "id_season_event")
//    @ManyToOne
//    private FamSeasonEvent famSeasonEvent;
    //
    /**
     * 
     */
    public static final String COL_ID_USER = "id_user";
    /**
     * 
     */
    public static final String PROP_USER = "famUser";
    @JoinColumn(name = COL_ID_USER, referencedColumnName = FamUser.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamUser famUser;

    /**
     * 
     */
    public FamComment() {
    }

    /**
     * 
     * @param idComment
     */
    public FamComment(Long idComment) {
        this.idComment = idComment;
    }

    /**
     * 
     * @return
     */
    public Long getIdComment() {
        return idComment;
    }

    /**
     * 
     * @param idComment
     */
    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    /**
     * 
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     */
    public FamUser getFamUser() {
        return famUser;
    }

    /**
     * 
     * @param famUser
     */
    public void setFamUser(FamUser famUser) {
        this.famUser = famUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idComment != null ? idComment.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamComment)) {
            return false;
        }
        FamComment other = (FamComment) object;
        if (( this.idComment == null && other.idComment != null ) || ( this.idComment != null && !this.idComment.equals(other.idComment) )) {
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
