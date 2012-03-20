package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author gbougear
 */
@Getter
@Setter
@Entity
@Table(name = FamEventStatus.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {FamEventStatus.COL_COD})
        })
@NamedQueries({
        @NamedQuery(name = FamEventStatus.FIND_ALL,
                query = "SELECT f FROM FamEventStatus f"),
        @NamedQuery(name = FamEventStatus.FIND_BY_ID_EVENT_STATUS,
                query = "SELECT f FROM FamEventStatus f WHERE f.idEventStatus = :idEventStatus"),
        @NamedQuery(name = FamEventStatus.FIND_BY_LIB_EVENT_STATUS,
                query = "SELECT f FROM FamEventStatus f WHERE f.libEventStatus = :libEventStatus"),
        @NamedQuery(name = FamEventStatus.FIND_BY_COD_EVENT_STATUS,
                query = "SELECT f FROM FamEventStatus f WHERE f.codEventStatus = :codEventStatus"),
        @NamedQuery(name = FamEventStatus.FIND_BY_DT_CREAT,
                query = "SELECT f FROM FamEventStatus f WHERE f.dtCreat = :dtCreat"),
        @NamedQuery(name = FamEventStatus.FIND_BY_DT_MODIF,
                query = "SELECT f FROM FamEventStatus f WHERE f.dtModif = :dtModif")
})
@XmlRootElement
public class FamEventStatus extends FamEntity implements Serializable {

    private static final long serialVersionUID = -4885063208236440270L;
    /**
     *
     */
    public static final String TABLE_NAME = "fam_event_status";
    //
    public static final String FIND_ALL = "FamEventStatus.findAll";
    public static final String FIND_BY_ID_EVENT_STATUS = "FamEventStatus.findByIdEventStatus";
    public static final String FIND_BY_LIB_EVENT_STATUS = "FamEventStatus.findByLibEventStatus";
    public static final String FIND_BY_COD_EVENT_STATUS = "FamEventStatus.findByCodEventStatus";
    public static final String FIND_BY_DT_CREAT = "FamEventStatus.findByDtCreat";
    public static final String FIND_BY_DT_MODIF = "FamEventStatus.findByDtModif";
    /**
     *
     */
    public static final String PROP_ID = "idEventStatus";
    /**
     *
     */
    public static final String COL_ID = "id_event_status";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long idEventStatus;

    @Override
    public Long getId() {
        return this.getIdEventStatus();
    }

    /**
     *
     */
    public static final String PROP_LIB = "libEventStatus";
    public static final String COL_LIB = "lib_event_status";
    @Basic(optional = false)
    @NotEmpty(message = "Libellé obligatoire")
    @Column(name = COL_LIB)
    private String libEventStatus;
    //
    /**
     *
     */
    public static final String PROP_COD = "codEventStatus";
    public static final String COL_COD = "cod_event_status";
    @Basic(optional = false)
    @NotEmpty(message = "Code obligatoire")
    @Column(name = COL_COD)
    private String codEventStatus;
    //
//    public static final String PROP_EVENTS = "famEventList";
//    @OneToMany(mappedBy = FamEvent.PROP_STATUS)
//    private List<FamEvent> famEventList;

    /**
     *
     */
    public FamEventStatus() {
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

        FamEventStatus that = (FamEventStatus) o;

        return idEventStatus.equals(that.idEventStatus);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idEventStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamEventStatus");
        sb.append("{idEventStatus=").append(idEventStatus);
        sb.append(", libEventStatus='").append(libEventStatus).append('\'');
        sb.append(", codEventStatus='").append(codEventStatus).append('\'');
//        sb.append(", famEventList=").append(famEventList);
        sb.append('}');
        return sb.toString();
    }
}
