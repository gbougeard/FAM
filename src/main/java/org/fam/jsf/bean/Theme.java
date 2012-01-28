package org.fam.jsf.bean;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/01/12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class Theme implements Serializable {

    private String name;

    private String image;

    public Theme() {}

    public Theme(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
