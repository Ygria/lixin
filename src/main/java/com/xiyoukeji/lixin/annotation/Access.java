package com.xiyoukeji.lixin.annotation;

import com.xiyoukeji.lixin.type.AdminRoleEnum;

import java.lang.annotation.*;

/**
 * Created by ygria on 2018/2/7.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {

    AdminRoleEnum value();

}
