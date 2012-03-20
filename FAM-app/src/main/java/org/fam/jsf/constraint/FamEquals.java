/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author mask_hot
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FamEquals {

    String message() default "{org.fam.jsf.constraint.FamEquals}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
