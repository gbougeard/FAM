package org.fam.jsf.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/01/12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Theme implements Serializable {

    private String name;
    private String image;

    @Override
    public String toString() {
        return name;
    }
}
