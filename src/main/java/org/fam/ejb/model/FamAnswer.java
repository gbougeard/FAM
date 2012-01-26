package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import org.fam.ejb.common.LogUtil;

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
    public Long getId(){
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

    /**
     *
     * @param idAnswer
     */
    public FamAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }

    /**
     *
     * @return
     */
    public Long getIdAnswer() {
        return idAnswer;
    }

    /**
     *
     * @param idAnswer
     */
    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
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
    public FamEvent getFamEvent() {
        return famEvent;
    }

    /**
     *
     * @param famEvent
     */
    public void setFamEvent(FamEvent famEvent) {
        this.famEvent = famEvent;
    }

    /**
     *
     * @return
     */
    public FamTypAnswer getFamTypAnswer() {
        return famTypAnswer;
    }

    /**
     *
     * @param famTypAnswer
     */
    public void setFamTypAnswer(FamTypAnswer famTypAnswer) {
        this.famTypAnswer = famTypAnswer;
    }

    /**
     *
     * @return
     */
    public FamPlayer getFamPlayer() {
        return famPlayer;
    }

    /**
     * 
     * @param famPlayer
     */
    public void setFamPlayer(FamPlayer famPlayer) {
        this.famPlayer = famPlayer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idAnswer != null ? idAnswer.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamAnswer)) {
            return false;
        }
        FamAnswer other = (FamAnswer) object;
        if (( this.idAnswer == null && other.idAnswer != null ) || ( this.idAnswer != null && !this.idAnswer.equals(other.idAnswer) )) {
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
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
            catch (IllegalAccessException e) {
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
