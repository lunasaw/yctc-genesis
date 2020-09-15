package edu.yctc.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

import edu.yctc.framework.aspectj.lang.enums.DataSourceType;

/**
 * 自定义多数据源切换注解
 * 
 * @author luna
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
