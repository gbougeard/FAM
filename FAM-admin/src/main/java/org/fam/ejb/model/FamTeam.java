package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamTeam.TABLE_NAME,
       uniqueConstraints = {
                            @UniqueConstraint(columnNames = {FamTeam.COL_COD})
       })
@NamedQueries({
               @NamedQuery(name = FamTeam.FIND_ALL,
                           query = "SELECT f FROM FamTeam f"),
               @NamedQuery(name = FamTeam.FIND_BY_ID_TEAM,
                           query = "SELECT f FROM FamTeam f WHERE f.idTeam = :idTeam"),
               @NamedQuery(name = FamTeam.FIND_BY_LIB_TEAM,
                           query = "SELECT f FROM FamTeam f WHERE f.libTeam = :libTeam"),
               @NamedQuery(name = FamTeam.FIND_BY_DT_CREAT,
                           query = "SELECT f FROM FamTeam f WHERE f.dtCreat = :dtCreat"),
               @NamedQuery(name = FamTeam.FIND_BY_CATEGORY,
                           query = "SELECT f FROM FamTeam f WHERE f.famCategory = :famCategory"),
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
    public static final String FIND_BY_CATEGORY = "FamTeam.findByFamCategory";
    public static final String FIND_BY_DT_CREAT = "FamTeam.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamTeam.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idTeam";
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
    public static final String PROP_PLACE = "famPlace";
    @ManyToOne
    @JoinColumn(name = COL_ID_PLACE, referencedColumnName = FamPlace.COL_ID)
    private FamPlace famPlace;
    //
    /**
     *
     */
    public static final String COL_ID_CLUB = "id_club";
    public static final String PROP_CLUB = "famClub";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_CLUB, referencedColumnName = FamClub.COL_ID)
    private FamClub famClub;

    public static final String COL_ID_CATEGORY = "id_category";
    public static final String PROP_CATEGORY = "famCategory";
    @NotNull
    @ManyToOne
    @JoinColumn(name = COL_ID_CATEGORY, referencedColumnName = FamCategory.COL_ID)
    private FamCategory famCategory;
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

        FamTeam famTeam = (FamTeam) o;

        if (!codTeam.equals(famTeam.codTeam)) {
            return false;
        }
        if (idTeam != null ? !idTeam.equals(famTeam.idTeam) : famTeam.idTeam != null) {
            return false;
        }
        if (!libTeam.equals(famTeam.libTeam)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idTeam != null ? idTeam.hashCode() : 0);
        result = 31 * result + libTeam.hashCode();
        result = 31 * result + codTeam.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamTeam");
        sb.append("{idTeam=").append(idTeam);
        sb.append(", libTeam='").append(libTeam).append('\'');
        sb.append(", codTeam='").append(codTeam).append('\'');
        sb.append(", famPlace=").append(famPlace);
        sb.append(", famClub=").append(famClub);
        sb.append(", famCategory=").append(famCategory);
//        sb.append(", famCompetitionList=").append(famCompetitionList);
//        sb.append(", famEventList=").append(famEventList);
//        sb.append(", famMatchTeamList=").append(famMatchTeamList);
//        sb.append(", famPlayerSeasonList=").append(famPlayerSeasonList);
        sb.append('}');
        return sb.toString();
    }
}
