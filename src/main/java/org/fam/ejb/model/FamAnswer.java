package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Data
@Entity
@Table(name = FamAnswer.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "FamAnswer.findAll", query = "SELECT f FROM FamAnswer f"),
        @NamedQuery(name = "FamAnswer.findByIdAnswer", query = "SELECT f FROM FamAnswer f WHERE f.idAnswer = :idAnswer"),
        @NamedQuery(name = "FamAnswer.findByEvent", query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent"),
        @NamedQuery(name = "FamAnswer.findByEventAndTypAnswer", query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent AND f.famTypAnswer = :famTypAnswer"),
        @NamedQuery(name = "FamAnswer.findByEventAndInTypAnswer", query = "SELECT f FROM FamAnswer f JOIN f.famTypAnswer t WHERE f.famEvent = :famEvent AND t.grpTypAnswer = :grpTypAnswer "),
        @NamedQuery(name = "FamAnswer.findByEventAndPlayer", query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent AND f.famPlayer = :famPlayer"),
        @NamedQuery(name = "FamAnswer.findByDtCreat", query = "SELECT f FROM FamAnswer f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamAnswer.findByDtModif", query = "SELECT f FROM FamAnswer f WHERE f.dtModif = :dtModif")})
// @NamedQuery(name = "FamAnswer.findNotAnsweredByEventAndClub", 
//                query = "SELECT p FROM FamPlayer p WHERE p.famPlayer.famClub = :famClub "
//                        + "AND NOT EXISTS "
//                        + "(SELECT f FROM FamAnswer f.famEvent = :famEvent AND f.famPlayer.idPlayer = p.idPlayer )"),
public class FamAnswer extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_answer";
    //
    /**
     *
     */
    public static final String PROP_ID = "idAnswer";
    /**
     *
     */
    public static final String COL_ID = "id_answer";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idAnswer;

    @Override
    public Long getId() {
        return this.getIdAnswer();
    }

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
    public static final String COL_ID_TYP = "id_typ_answer";
    /**
     *
     */
    public static final String PROP_TYP = "famTypAnswer";
    @JoinColumn(name = COL_ID_TYP, referencedColumnName = FamTypAnswer.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamTypAnswer famTypAnswer;
    //
    /**
     *
     */
    public static final String COL_ID_EVENT = "id_event";
    /**
     *
     */
    public static final String PROP_EVENT = "famEvent";
    @JoinColumn(name = COL_ID_EVENT, referencedColumnName = FamEvent.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamEvent famEvent;
    //
    /**
     *
     */
    public static final String COL_ID_PLAYER = "id_player";
    /**
     *
     */
    public static final String PROP_PLAYER = "famPlayer";
    @JoinColumn(name = COL_ID_PLAYER, referencedColumnName = FamPlayer.COL_ID)
    @ManyToOne(optional = false)
    @NotNull
    private FamPlayer famPlayer;

    /**
     *
     */
    public FamAnswer() {
    }

}
