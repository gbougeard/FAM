package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.ejb.common.LogUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamSubstitution.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamSubstitution.findAll", query = "SELECT f FROM FamSubstitution f"),
    @NamedQuery(name = "FamSubstitution.findByIdSubstitution", query = "SELECT f FROM FamSubstitution f WHERE f.idSubstitution = :idSubstitution"),
    @NamedQuery(name = "FamSubstitution.findBySubstitutionTime", query = "SELECT f FROM FamSubstitution f WHERE f.substitutionTime = :substitutionTime"),
    @NamedQuery(name = "FamSubstitution.findByMatchAndTeam", query = "SELECT f FROM FamSubstitution f WHERE f.famMatchPlayerIn.famMatchTeam.famMatch = :famMatch AND f.famMatchPlayerIn.famMatchTeam.famTeam = :famTeam"),
    @NamedQuery(name = "FamSubstitution.findByDtCreat", query = "SELECT f FROM FamSubstitution f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamSubstitution.findByDtModif", query = "SELECT f FROM FamSubstitution f WHERE f.dtModif = :dtModif")})
public class FamSubstitution extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String TABLE_NAME = "fam_substitution";
    //
    /**
     * 
     */
    public static final String PROP_ID = "idSubstitution";
    /**
     * 
     */
    public static final String COL_ID = "id_substitution";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idSubstitution;
    
    @Override
    public Long getId(){
        return this.getIdSubstitution();
    }
    
    /**
     * 
     */
    public static final String PROP_TIME = "substitutionTime";
    public static final String COL_TIME = "substitution_time";
    @Column(name = COL_TIME)
    private String substitutionTime;
    /**
     * 
     */
    public static final String COL_ID_MATCH = "id_match";
    /**
     * 
     */
    public static final String COL_ID_TEAM = "id_team";
    /**
     * 
     */
    public static final String COL_ID_PLAYER_IN = "id_player_in";
    /**
     * 
     */
    public static final String PROP_PLAYER_IN = "famMatchPlayerIn";
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatchPlayer.COL_ID_MATCH),
        @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamMatchPlayer.COL_ID_TEAM),
        @JoinColumn(name = COL_ID_PLAYER_IN, referencedColumnName = FamMatchPlayer.COL_ID_PLAYER)})
    private FamMatchPlayer famMatchPlayerIn;
    //
    /**
     *
     */
    public static final String COL_ID_PLAYER_OUT = "id_player_out";
    /**
     *
     */
    public static final String PROP_PLAYER_OUT = "famMatchPlayerOut";
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = COL_ID_MATCH, referencedColumnName = FamMatchPlayer.COL_ID_MATCH, insertable = false, updatable = false),
        @JoinColumn(name = COL_ID_TEAM, referencedColumnName = FamMatchPlayer.COL_ID_TEAM, insertable = false, updatable = false),
        @JoinColumn(name = COL_ID_PLAYER_OUT, referencedColumnName = FamMatchPlayer.COL_ID_PLAYER)})
    private FamMatchPlayer famMatchPlayerOut;

    /**
     *
     */
    public FamSubstitution() {
        this.setFamMatchPlayerIn(new FamMatchPlayer());
        this.setFamMatchPlayerOut(new FamMatchPlayer());
    }

    /**
     *
     * @param idSubstitution
     */
    public FamSubstitution(Long idSubstitution) {
        this.idSubstitution = idSubstitution;
    }

    /**
     *
     * @return
     */
    public Long getIdSubstitution() {
        return idSubstitution;
    }

    /**
     *
     * @param idSubstitution
     */
    public void setIdSubstitution(Long idSubstitution) {
        this.idSubstitution = idSubstitution;
    }

    /**
     *
     * @return
     */
    public String getSubstitutionTime() {
        return substitutionTime;
    }

    /**
     *
     * @param substitutionTime
     */
    public void setSubstitutionTime(String substitutionTime) {
        this.substitutionTime = substitutionTime;
    }

    public FamMatchPlayer getFamMatchPlayerOut() {
        return famMatchPlayerOut;
    }

    public void setFamMatchPlayerOut(FamMatchPlayer famMatchPlayerOut) {
        this.famMatchPlayerOut = famMatchPlayerOut;
    }

    public FamMatchPlayer getFamMatchPlayerIn() {
        return famMatchPlayerIn;
    }

    public void setFamMatchPlayerIn(FamMatchPlayer famMatchPlayerIn) {
        this.famMatchPlayerIn = famMatchPlayerIn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idSubstitution != null ? idSubstitution.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamSubstitution)) {
            return false;
        }
        FamSubstitution other = (FamSubstitution) object;
        if (( this.idSubstitution == null && other.idSubstitution != null ) || ( this.idSubstitution != null && !this.idSubstitution.equals(other.idSubstitution) )) {
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
