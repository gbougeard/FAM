package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
@Entity
@Table(name = FamPlace.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = FamPlace.FIND_ALL,
                query = "SELECT f FROM FamPlace f"),
        @NamedQuery(name = FamPlace.FIND_BY_ID_PLACE,
                query = "SELECT f FROM FamPlace f WHERE f.idPlace = :idPlace"),
        @NamedQuery(name = FamPlace.FIND_BY_LIB_PLACE,
                query = "SELECT f FROM FamPlace f WHERE f.libPlace = :libPlace"),
        @NamedQuery(name = FamPlace.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamPlace f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamPlace.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamPlace f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamPlace extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1833411171271063405L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_place";
    //
    public static final String FIND_ALL = "FamPlace.findAll";
    public static final String FIND_BY_ID_PLACE = "FamPlace.findByIdPlace";
    public static final String FIND_BY_LIB_PLACE = "FamPlace.findByLibPlace";
    public static final String FIND_BY_DT_CREAT = "FamPlace.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamPlace.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idPlace";
    /**
     *
     */
    public static final String COL_ID = "id_place";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idPlace;


    @Override
    public Long getId() {
        return this.getIdPlace();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libPlace";
    public static final String COL_LIB = "lib_place";
    @Basic(optional = false)
    @NotEmpty
    @Column(name = COL_LIB)
    private String libPlace;
    //
    /**
     *
     */
    public static final String PROP_ADDRESS = "address";
    @Basic(optional = false)
    @Column(name = PROP_ADDRESS)
    private String address;
    //
    /**
     *
     */
    public static final String PROP_ZIPCODE = "zipcode";
    @Basic(optional = false)
    @Column(name = PROP_ZIPCODE)
    private String zipcode;
    //
    /**
     *
     */
    public static final String PROP_CITY = "city";
    @Basic(optional = false)
    @Column(name = PROP_CITY)
    private String city;
    //
    /**
     *
     */
    public static final String PROP_LONGITUDE = "longitude";
    @Basic(optional = false)
    @Column(name = PROP_LONGITUDE, precision = 10, scale = 8)
    private BigDecimal longitude;
    //
    /**
     *
     */
    public static final String PROP_LATITUDE = "latitude";
    @Basic(optional = false)
    @Column(name = PROP_LATITUDE, precision = 10, scale = 8)
    private BigDecimal latitude;
    //
    /**
     *
     */
    public static final String PROP_TEAMS = "famTeamList";
    @OneToMany(mappedBy = FamTeam.PROP_PLACE)
    private List<FamTeam> famTeamList;
    //
    /**
     *
     */
    public static final String COL_ID_TYP = "id_typ_place";
    /**
     *
     */
    public static final String PROP_TYP = "famTypPlace";
    @ManyToOne//(optional = false)
    @JoinColumn(name = COL_ID_TYP, referencedColumnName = FamTypPlace.COL_ID)
    @NotNull
    private FamTypPlace famTypPlace;
    //
    @Transient
    private String fullAddress;

    /**
     *
     */
    public FamPlace() {
    }


    @PostLoad
    @PostPersist
    @PostUpdate
    void fillTransients() {
        StringBuilder builder = new StringBuilder();
        if ((address != null) && !address.isEmpty()) {
            builder.append(address);
        }
        if ((zipcode != null) && !zipcode.isEmpty()) {
            builder.append(" ").append(zipcode);
        }
        if ((city != null) && !city.isEmpty()) {
            builder.append(" ").append(city);
        }
        fullAddress = builder.toString();

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

        FamPlace famPlace = (FamPlace) o;

        if (idPlace != null ? !idPlace.equals(famPlace.idPlace) : famPlace.idPlace != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idPlace != null ? idPlace.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamPlace");
        sb.append("{idPlace=").append(idPlace);
        sb.append(", libPlace='").append(libPlace).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
//        sb.append(", famTeamList=").append(famTeamList);
        sb.append(", famTypPlace=").append(famTypPlace.getLibTypPlace());
        sb.append(", fullAddress='").append(fullAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
