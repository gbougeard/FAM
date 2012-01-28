package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
@Entity
@Table(name = FamPlace.TABLE_NAME)
@NamedQueries({
    @NamedQuery(name = "FamPlace.findAll", query = "SELECT f FROM FamPlace f"),
    @NamedQuery(name = "FamPlace.findByIdPlace", query = "SELECT f FROM FamPlace f WHERE f.idPlace = :idPlace"),
    @NamedQuery(name = "FamPlace.findByLibPlace", query = "SELECT f FROM FamPlace f WHERE f.libPlace = :libPlace"),
    @NamedQuery(name = "FamPlace.findByDtCreat", query = "SELECT f FROM FamPlace f WHERE f.dtCreat = :dtCreat"),
    @NamedQuery(name = "FamPlace.findByDtModif", query = "SELECT f FROM FamPlace f WHERE f.dtModif = :dtModif")
})
public class FamPlace extends FamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_place";
    //
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
    public Long getId(){
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

    /**
     *
     * @param idPlace
     */
    public FamPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    /**
     *
     * @return
     */
    public Long getIdPlace() {
        return idPlace;
    }

    /**
     *
     * @param idPlace
     */
    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    /**
     *
     * @return
     */
    public String getLibPlace() {
        return libPlace;
    }

    /**
     *
     * @param libPlace
     */
    public void setLibPlace(String libPlace) {
        this.libPlace = libPlace;
    }

    /**
     *
     * @return
     */
    public List<FamTeam> getFamTeamList() {
        return famTeamList;
    }

    /**
     *
     * @param famTeamList
     */
    public void setFamTeamList(List<FamTeam> famTeamList) {
        this.famTeamList = famTeamList;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     *
     * @param fullAddress
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     *
     * @return
     */
    public FamTypPlace getFamTypPlace() {
        return famTypPlace;
    }

    /**
     * 
     * @param famTypPlace
     */
    public void setFamTypPlace(FamTypPlace famTypPlace) {
        this.famTypPlace = famTypPlace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idPlace != null ? idPlace.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamPlace)) {
            return false;
        }
        FamPlace other = (FamPlace) object;
        if (( this.idPlace == null && other.idPlace != null ) || ( this.idPlace != null && !this.idPlace.equals(other.idPlace) )) {
            return false;
        }
        return true;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    void fillTransients() {
        StringBuilder builder = new StringBuilder();
        if (( address != null ) && !address.isEmpty()) {
            builder.append(address);
        }
        if (( zipcode != null ) && !zipcode.isEmpty()) {
            builder.append(" ").append(zipcode);
        }
        if (( city != null ) && !city.isEmpty()) {
            builder.append(" ").append(city);
        }
        fullAddress = builder.toString();

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Class cls = this.getClass();
        int ii = 0;
        builder.append(this.getClass()).append(" [");
        for (Field f : cls.getDeclaredFields()) {
            if (f.getName().equals(PROP_TEAMS) == false) {
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
        }
        builder.append("\n]");
        return builder.toString();
    }
}
