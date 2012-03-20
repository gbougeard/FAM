package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamAnswer.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamAnswer.FIND_ALL,
                query = "SELECT f FROM FamAnswer f"),
        @NamedQuery(name = FamAnswer.FIND_BY_ID_ANSWER,
                query = "SELECT f FROM FamAnswer f WHERE f.idAnswer = :idAnswer"),
        @NamedQuery(name = FamAnswer.FIND_BY_EVENT,
                query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent"),
        @NamedQuery(name = FamAnswer.FIND_BY_EVENT_AND_TYP_ANSWER,
                query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent AND f.famTypAnswer = :famTypAnswer"),
        @NamedQuery(name = FamAnswer.FIND_BY_EVENT_AND_IN_TYP_ANSWER,
                query = "SELECT f FROM FamAnswer f JOIN f.famTypAnswer t WHERE f.famEvent = :famEvent AND t.grpTypAnswer = :grpTypAnswer "),
        @NamedQuery(name = FamAnswer.FIND_BY_EVENT_AND_PLAYER,
                query = "SELECT f FROM FamAnswer f WHERE f.famEvent = :famEvent AND f.famPlayer = :famPlayer"),
        @NamedQuery(name = FamAnswer.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamAnswer f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamAnswer.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamAnswer f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
// @NamedQuery(name = "FamAnswer.findNotAnsweredByEventAndClub", 
//                query = "SELECT p FROM FamPlayer p WHERE p.famPlayer.famClub = :famClub "
//                        + "AND NOT EXISTS "
//                        + "(SELECT f FROM FamAnswer f.famEvent = :famEvent AND f.famPlayer.idPlayer = p.idPlayer )"),
public class FamAnswer extends FamEntity implements Serializable {

    private static final long serialVersionUID = 4632870447735084266L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_answer";
    //
    public static final String FIND_ALL = "FamAnswer.findAll";
    public static final String FIND_BY_ID_ANSWER = "FamAnswer.findByIdAnswer";
    public static final String FIND_BY_EVENT = "FamAnswer.findByEvent";
    public static final String FIND_BY_EVENT_AND_TYP_ANSWER = "FamAnswer.findByEventAndTypAnswer";
    public static final String FIND_BY_EVENT_AND_IN_TYP_ANSWER = "FamAnswer.findByEventAndInTypAnswer";
    public static final String FIND_BY_EVENT_AND_PLAYER = "FamAnswer.findByEventAndPlayer";
    public static final String FIND_BY_DT_CREAT = "FamAnswer.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamAnswer.findByDtModif";
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

        FamAnswer famAnswer = (FamAnswer) o;

        return idAnswer.equals(famAnswer.idAnswer);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idAnswer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamAnswer");
        sb.append("{idAnswer=").append(idAnswer);
        sb.append(", comments='").append(comments).append('\'');
        sb.append(", famTypAnswer=").append(famTypAnswer);
        sb.append(", famEvent=").append(famEvent.getLibEvent());
        sb.append(", famPlayer=").append(famPlayer.getDisplayName());
        sb.append('}');
        return sb.toString();
    }
}
