package org.fam.jsf.bean;

import lombok.Getter;

import javax.enterprise.inject.Model;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 25/03/12
 * Time: 01:25
 * To change this template use File | Settings | File Templates.
 */
@Model
@Getter
public class Common {
    Date now = new Date();
}
