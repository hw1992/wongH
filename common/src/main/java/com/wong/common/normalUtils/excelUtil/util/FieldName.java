package com.wong.common.normalUtils.excelUtil.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义字段名
 * @author wangH
 * @since 2017/12/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface FieldName {
    /**
     * 字段名
     */
    String value() default "";
    /**
     * 是否忽略
     */
    boolean Ignore() default false;
}
