package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author gbougear
 */
@Entity
@Table(name = FamClub.TABLE_NAME,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {FamClub.COL_COD})
})
@NamedQueries({
    @NamedQuery(name = "FamClub.findAll", query = "SELECT f FROM FamClub f"),
    @NamedQuery(name = "FamClub.findByIdClub", query = "SELECT f FROM FamClub f WHERE f.idClub = :idClub"),
    @NamedQuery(name = "FamClub.findByLibClub", query = "SELECT f FROM FamClub f WHERE f.libClub = :libClub"),
    @NamedQuery(name = "FamClub.findByCodeFff", query = "SELECT f FROM FamClub f WHERE f.codeFff = :codeFff"),
    @NamedQuery(name = "FamClub.findByDtCreat", query = "SELECT f FROM FamClub f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamClub.findByDtModif", query = "SELECT f FROM FamClub f WHERE f.dtModif = :dtModif")})
@XmlRootElement
@Data
@EqualsAndHashCode(callSuper=false)
public class FamClub extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "fam_club";
    //
    public static final String PROP_ID = "idClub";
    public static final String COL_ID = "id_club";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idClub;
    
    @Override
    public Long getId(){
        return this.getIdClub();
    }
    //
    public static final String PROP_LIB = "libClub";
    public static final String COL_LIB = "lib_club";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    //@Size(max = 30, message = "Taille maxi : {max} caractères.")
    private String libClub;
    //
    public static final String PROP_COD = "codeFff";
    public static final String COL_COD = "code_fff";
    @Basic(optional = false)
    @NotNull(message = "Code FFF obligatoire")
    @Column(name = COL_COD)
    @Max(value = 200, message = "#{bundle.testBV_JPA}")//"Valeur maxi {value}.")
    private Integer codeFff;
    //
    public static final String PROP_COUNTRY = "famCountry";
    public static final String COL_ID_COUNTRY = "id_country";
    @ManyToOne
    @JoinColumn(name = FamCountry.COL_ID)
    private FamCountry famCountry;
    //
    public static final String PROP_STATE = "famState";
    public static final String COL_ID_STATE = "id_state";
    @ManyToOne
    @JoinColumn(name = FamState.COL_ID)
    private FamState famState;
    //
    public static final String PROP_PROVINCE = "famProvince";
    public static final String COL_ID_PROVINCE = "id_province";
    @ManyToOne
    @JoinColumn(name = FamProvince.COL_ID)
    private FamProvince famProvince;
    //
    public static final String PROP_CITY = "famCity";
    public static final String COL_ID_CITY = "id_city";
    @ManyToOne
    @JoinColumn(name = FamCity.COL_ID)
    private FamCity famCity;
    //
    public static final String PROP_COMMENTS = "comments";
    public static final String COL_COMMENTS = "comments";
    @Lob
    @Column(name = COL_COMMENTS)
    private String comments;
    //
    public static final String PROP_TEAMS = "famTeamList";
    @OneToMany(mappedBy = FamTeam.PROP_CLUB, fetch = FetchType.EAGER)
    private List<FamTeam> famTeamList;

    public FamClub() {
    }

//    public FamClub(Long idClub) {
//        this.idClub = idClub;
//    }
//
//    public Long getIdClub() {
//        return idClub;
//    }
//
//    public void setIdClub(Long idClub) {
//        this.idClub = idClub;
//    }
//
//    public String getLibClub() {
//        return libClub;
//    }
//
//    public void setLibClub(String libClub) {
//        this.libClub = libClub;
//    }
//
//    public Integer getCodeFff() {
//        return codeFff;
//    }
//
//    public void setCodeFff(Integer codeFff) {
//        this.codeFff = codeFff;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public FamCity getFamCity() {
//        return famCity;
//    }
//
//    public void setFamCity(FamCity famCity) {
//        this.famCity = famCity;
//    }
//
//    public FamCountry getFamCountry() {
//        return famCountry;
//    }
//
//    public void setFamCountry(FamCountry famCountry) {
//        this.famCountry = famCountry;
//    }
//
//    public FamProvince getFamProvince() {
//        return famProvince;
//    }
//
//    public void setFamProvince(FamProvince famProvince) {
//        this.famProvince = famProvince;
//    }
//
//    public FamState getFamState() {
//        return famState;
//    }
//
//    public void setFamState(FamState famState) {
//        this.famState = famState;
//    }
//
//    public List<FamTeam> getFamTeamList() {
//        return famTeamList;
//    }
//
//    public void setFamTeamList(List<FamTeam> famTeamList) {
//        this.famTeamList = famTeamList;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += ( idClub != null ? idClub.hashCode() : 0 );
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!( object instanceof FamClub )) {
//            return false;
//        }
//        FamClub other = (FamClub) object;
//        if (( this.idClub == null && other.idClub != null ) || ( this.idClub != null && !this.idClub.equals(other.idClub) )) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//
//        Class cls = this.getClass();
//        int ii = 0;
//        builder.append(this.getClass()).append(" [");
//        for (Field f : cls.getDeclaredFields()) {
//            if (f.getName().equals(PROP_TEAMS) == false) {
//                try {
//                    builder.append(ii++ == 0 ? "\n" : "\n,").append(f.getName()).append(" : ").append(f.get(this));
//                }
//                catch (IllegalArgumentException e) {
//                    LogUtil.log("Erreur!", Level.SEVERE, e);
//                }
//                catch (IllegalAccessException e) {
//                    LogUtil.log("Erreur!", Level.SEVERE, e);
//                }
//            }
//        }
//        builder.append("\n]");
//        return builder.toString();
//    }
}
