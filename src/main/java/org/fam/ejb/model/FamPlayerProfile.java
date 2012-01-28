package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.fam.common.log.LogUtil;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Embeddable
@Access(AccessType.FIELD)
public class FamPlayerProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    //
    /**
     * 
     */
    public static final String PROP_WEIGHT = "weight";
    @Basic(optional = false)
    @Column(name = PROP_WEIGHT)
    @Max(value = 150)
    @Min(value = 20)
    private Integer weight;
    //
    /**
     * 
     */
    public static final String PROP_HEIGHT = "height";
    @Basic(optional = false)
    @Column(name = PROP_HEIGHT)
    @Max(value = 220)
    @Min(value = 100)
    private Integer height;
    //
    /**
     * 
     */
    public static final String PROP_ATT = "att";
    @Basic(optional = false)
    @Column(name = PROP_ATT)
    @Max(value = 100)
    @Min(value = 0)
    private Integer att;
    //
    /**
     * 
     */
    public static final String PROP_DEF = "def";
    @Basic(optional = false)
    @Column(name = PROP_DEF)
    @Max(value = 100)
    @Min(value = 0)
    private Integer def;
    //
    /**
     * 
     */
    public static final String PROP_VIT = "vit";
    @Basic(optional = false)
    @Column(name = PROP_VIT)
    @Max(value = 100)
    @Min(value = 0)
    private Integer vit;
    //
    /**
     * 
     */
    public static final String PROP_TEC = "tec";
    @Basic(optional = false)
    @Column(name = PROP_TEC)
    @Max(value = 100)
    @Min(value = 0)
    private Integer tec;
    //
    /**
     * 
     */
    public static final String PROP_PUI = "pui";
    @Basic(optional = false)
    @Column(name = PROP_PUI)
    @Max(value = 100)
    @Min(value = 0)
    private Integer pui;
    //
    /**
     * 
     */
    public static final String PROP_PHY = "phy";
    @Basic(optional = false)
    @Column(name = PROP_PHY)
    @Max(value = 100)
    @Min(value = 0)
    private Integer phy;
    /**
     *
     */
    public static final String PROP_PROFILE_CHART_URL = "profileChartUrl";
    public static final String COL_PROFILE_CHART_URL = "profile_chart_url";
    @Basic(optional = false)
    @Column(name = COL_PROFILE_CHART_URL,length=1048)
    private String profileChartUrl;

    /**
     * 
     */
    public FamPlayerProfile() {
        weight = 0;
        height = 0;
        att = 0;
        def = 0;
        vit = 0;
        tec = 0;
        pui = 0;
        phy = 0;
    }

    /**
     * 
     * @return
     */
    public Integer getAtt() {
        return att;
    }

    /**
     * 
     * @param att
     */
    public void setAtt(Integer att) {
        this.att = att;
    }

    /**
     * 
     * @return
     */
    public Integer getDef() {
        return def;
    }

    /**
     * 
     * @param def
     */
    public void setDef(Integer def) {
        this.def = def;
    }

    /**
     * 
     * @return
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     */
    public Integer getPhy() {
        return phy;
    }

    /**
     * 
     * @param phy
     */
    public void setPhy(Integer phy) {
        this.phy = phy;
    }

    /**
     * 
     * @return
     */
    public Integer getPui() {
        return pui;
    }

    /**
     * 
     * @param pui
     */
    public void setPui(Integer pui) {
        this.pui = pui;
    }

    /**
     * 
     * @return
     */
    public Integer getTec() {
        return tec;
    }

    /**
     * 
     * @param tec
     */
    public void setTec(Integer tec) {
        this.tec = tec;
    }

    /**
     * 
     * @return
     */
    public Integer getVit() {
        return vit;
    }

    /**
     * 
     * @param vit
     */
    public void setVit(Integer vit) {
        this.vit = vit;
    }

    /**
     * 
     * @return
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getProfileChartUrl() {
        return profileChartUrl;
    }

    public void setProfileChartUrl(String profileChartUrl) {
        this.profileChartUrl = profileChartUrl;
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
