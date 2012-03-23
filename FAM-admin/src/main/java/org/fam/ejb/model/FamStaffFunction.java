package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamStaffFunction.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamStaffFunction.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = "FamStaffFunction.findAll", query = "SELECT f FROM FamStaffFunction f"),
        @NamedQuery(name = "FamStaffFunction.findByIdStaffFunction", query = "SELECT f FROM FamStaffFunction f WHERE f.idStaffFunction = :idStaffFunction"),
        @NamedQuery(name = "FamStaffFunction.findByLibStaffFunction", query = "SELECT f FROM FamStaffFunction f WHERE f.libStaffFunction = :libStaffFunction"),
        @NamedQuery(name = "FamStaffFunction.findByDtCreat", query = "SELECT f FROM FamStaffFunction f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = "FamStaffFunction.findByDtModif", query = "SELECT f FROM FamStaffFunction f WHERE f.dtModif = :dtModif")})
public class FamStaffFunction extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_staff_function";
    //
    /**
     *
     */
    public static final String PROP_ID = "idStaffFunction";
    /**
     *
     */
    public static final String COL_ID = "id_staff_function";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idStaffFunction;


    @Override
    public Long getId() {
        return this.getIdStaffFunction();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libStaffFunction";
    public static final String COL_LIB = "lib_staff_function";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libStaffFunction;
    //
    /**
     *
     */
    public static final String PROP_COD = "codStaffFunction";
    public static final String COL_COD = "cod_staff_function";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codStaffFunction;
    //
    /**
     *
     */
    public static final String COL_ID_CLUB = "id_club";
    /**
     *
     */
    public static final String PROP_CLUB = "famClub";
    @ManyToOne
    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID)
    private FamClub famClub;
    //
    @ManyToMany
    @JoinTable(name = "fam_staff",
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamUserSeason.COL_ID_USER, referencedColumnName = FamUserSeason.COL_ID_USER)
                    , @JoinColumn(name = FamUserSeason.COL_ID_SEASON, referencedColumnName = FamUserSeason.COL_ID_SEASON)
//        ,@JoinColumn(name = FamUserSeason.COL_ID_CLUB, referencedColumnName = FamUserSeason.COL_ID_CLUB)
            })
    private List<FamUserSeason> famUserSeasonList;

    /**
     *
     */
    public FamStaffFunction() {
    }

    /**
     * @param idStaffFunction
     */
    public FamStaffFunction(Long idStaffFunction) {
        this.idStaffFunction = idStaffFunction;
    }

    /**
     * @return
     */
    public Long getIdStaffFunction() {
        return idStaffFunction;
    }

    /**
     * @param idStaffFunction
     */
    public void setIdStaffFunction(Long idStaffFunction) {
        this.idStaffFunction = idStaffFunction;
    }

    /**
     * @return
     */
    public String getLibStaffFunction() {
        return libStaffFunction;
    }

    /**
     * @param libStaffFunction
     */
    public void setLibStaffFunction(String libStaffFunction) {
        this.libStaffFunction = libStaffFunction;
    }

    /**
     * @return
     */
    public String getCodStaffFunction() {
        return codStaffFunction;
    }

    /**
     * @param codStaffFunction
     */
    public void setCodStaffFunction(String codStaffFunction) {
        this.codStaffFunction = codStaffFunction;
    }

    /**
     * @return
     */
    public List<FamUserSeason> getFamUserSeasonList() {
        return famUserSeasonList;
    }

    /**
     * @param famUserSeasonList
     */
    public void setFamUserSeasonList(List<FamUserSeason> famUserSeasonList) {
        this.famUserSeasonList = famUserSeasonList;
    }

    /**
     * @return
     */
    public FamClub getFamClub() {
        return famClub;
    }

    /**
     * @param famClub
     */
    public void setFamClub(FamClub famClub) {
        this.famClub = famClub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStaffFunction != null ? idStaffFunction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamStaffFunction)) {
            return false;
        }
        FamStaffFunction other = (FamStaffFunction) object;
        if ((this.idStaffFunction == null && other.idStaffFunction != null) || (this.idStaffFunction != null && !this.idStaffFunction.equals(other.idStaffFunction))) {
            return false;
        }
        return true;
    }

}
