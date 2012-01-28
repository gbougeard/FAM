package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    public Long getId() {
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

}
