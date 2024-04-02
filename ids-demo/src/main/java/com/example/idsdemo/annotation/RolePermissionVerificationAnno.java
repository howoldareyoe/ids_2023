package com.example.idsdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
// 运行时可见
@Retention(RetentionPolicy.RUNTIME)
public @interface RolePermissionVerificationAnno {

    /**
     * 有权限访问的角色id 配置了之后只有当前指定的角色id才可以访问该接口
     */
    long[] roleIdList() default {};


}
