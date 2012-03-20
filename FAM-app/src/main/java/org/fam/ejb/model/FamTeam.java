package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.fam.common.constant.FamConstantes;
import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Entity
@Table(name = FamTeam.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamTeam.COL_COD})
        })
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = FamClub.COL_ID, contextProperty = FamConstantes.PROP_CLUB_TENANT)
@NamedQueries({
        @NamedQuery(name = FamTeam.FIND_ALL,
                query = "SELECT f FROM FamTeam f"),
        @NamedQuery(name = FamTeam.FIND_BY_ID_TEAM,
                query = "SELECT f FROM FamTeam f WHERE f.idTeam = :idTeam"),
        @NamedQuery(name = FamTeam.FIND_BY_LIB_TEAM,
                query = "SELECT f FROM FamTeam f WHERE f.libTeam = :libTeam"),
        @NamedQuery(name = FamTeam.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamTeam f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamTeam.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamTeam f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamTeam extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_team";
    //
    public static final String FIND_ALL = "FamTeam.findAll";
    public static final String FIND_BY_ID_TEAM = "FamTeam.findByIdTeam";
    public static final String FIND_BY_LIB_TEAM = "FamTeam.findByLibTeam";
    public static final String FIND_BY_DT_CREAT = "FamTeam.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamTeam.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idTeam";
    /**
     *
     */
    public static final String COL_ID = "id_team";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idTeam;

    @Override
    public Long getId() {
        return this.getIdTeam();
    }

    /**
     *
     */
    public static final String COL_LIB = "lib_team";
    /**
     *
     */
    public static final String PROP_LIB = "libTeam";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libTeam;
    //
    /**
     *
     */
    public static final String COL_COD = "cod_team";
    /**
     *
     */
    public static final String PROP_COD = "codTeam";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codTeam;
    //
    /**
     *
     */
    public static final String COL_ID_PLACE = "id_place";
    /**
     *
     */
    public static final String PROP_PLACE = "famPlace";
    @ManyToOne
    @JoinColumn(name = COL_ID_PLACE, referencedColumnName = FamPlace.COL_ID)
    private FamPlace famPlace;
    //
    /**
     *
     */
//    public static final String COL_ID_CLUB = "id_club";
//    /**
//     *
//     */
//    public static final String PROP_CLUB = "famClub";
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID, insertable=false, updatable=false)
//    private FamClub famClub;
    //
    @ManyToMany
    @JoinTable(name = FamCompetitionTeam.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamSeasonCompetition.COL_ID)})
    private List<FamSeasonCompetition> famCompetitionList;
    //
    @ManyToMany
    @JoinTable(name = "fam_event_team",
            joinColumns = {
                    @JoinColumn(name = COL_ID)},
            inverseJoinColumns = {
                    @JoinColumn(name = FamEvent.COL_ID)})
    private List<FamEvent> famEventList;
    //
    @OneToMany(mappedBy = FamMatchTeam.PROP_TEAM)
    private List<FamMatchTeam> famMatchTeamList;
    //
    @OneToMany(mappedBy = FamPlayerSeason.PROP_TEAM)
    private List<FamPlayerSeason> famPlayerSeasonList;

    /**
     *
     */
    public FamTeam() {
    }

    /**
     * @param idTeam
     */
    public FamTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    /**
     * @return
     */
    public Long getIdTeam() {
        return idTeam;
    }

    /**
     * @param idTeam
     */
    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    /**
     * @return
     */
    public String getLibTeam() {
        return libTeam;
    }

    /**
     * @param libTeam
     */
    public void setLibTeam(String libTeam) {
        this.libTeam = libTeam;
    }

    /**
     * @return
     */
    public FamPlace getFamPlace() {
        return famPlace;
    }

    /**
     * @param famPlace
     */
    public void setFamPlace(FamPlace famPlace) {
        this.famPlace = famPlace;
    }

    /**
     * @return
     */
//    public FamClub getFamClub() {
//        return famClub;
//    }
//
//    /**
//     * @param famClub
//     */
//    public void setFamClub(FamClub famClub) {
//        this.famClub = famClub;
//    }

    /**
     * @return
     */
    public List<FamMatchTeam> getFamMatchTeamList() {
        return famMatchTeamList;
    }

    /**
     * @param famMatchTeamList
     */
    public void setFamMatchTeamList(List<FamMatchTeam> famMatchTeamList) {
        this.famMatchTeamList = famMatchTeamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeam != null ? idTeam.hashCode() : 0);
        return hash;
    }

    /**
     * @return
     */
    public String getCodTeam() {
        return codTeam;
    }

    /**
     * @param codTeam
     */
    public void setCodTeam(String codTeam) {
        this.codTeam = codTeam;
    }

    /**
     * @return
     */
    public List<FamSeasonCompetition> getFamCompetitionList() {
        return famCompetitionList;
    }

    /**
     * @param famCompetitionList
     */
    public void setFamCompetitionList(List<FamSeasonCompetition> famCompetitionList) {
        this.famCompetitionList = famCompetitionList;
    }

    /**
     * @return
     */
    public List<FamEvent> getFamEventList() {
        return famEventList;
    }

    /**
     * @param famEventList
     */
    public void setFamEventList(List<FamEvent> famEventList) {
        this.famEventList = famEventList;
    }

    public List<FamPlayerSeason> getFamPlayerSeasonList() {
        return famPlayerSeasonList;
    }

    public void setFamPlayerSeasonList(List<FamPlayerSeason> famPlayerSeasonList) {
        this.famPlayerSeasonList = famPlayerSeasonList;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamTeam)) {
            return false;
        }
        FamTeam other = (FamTeam) object;
        if ((this.idTeam == null && other.idTeam != null) || (this.idTeam != null && !this.idTeam.equals(other.idTeam))) {
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
//            if (f.getName().equals(PROP_CLUB) == true) {
//                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(this.getFamClub().getLibClub());
//            } else {

            try {
                builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                LogUtil.log("Erreur!", Level.SEVERE, e);
            }
//            }
        }
        builder.append("\n]");
        return builder.toString();
    }
}
