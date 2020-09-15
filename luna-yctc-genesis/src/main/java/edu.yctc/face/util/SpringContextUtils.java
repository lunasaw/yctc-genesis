package edu.yctc.face.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 手动注入工具类
 * 
 * @author xiaotao
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public class SpringContextUtils implements ApplicationContextAware {

    /** 实用的Bean工厂 */
    private static ApplicationContext applicationContext;

    /**
     * 设置applicationContext对象
     * 
     * @param arg0 applicationContext对象
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     * 
     * @return applicationContext对象
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     * 
     * @param id bean的id
     * @return bean对象
     */
    public static Object getBeanById(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * 
     * @param c bean的class
     * @return bean对象
     */
    public static Object getBeanByClass(Class c) {
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * 
     * @param c bean的class
     * @return 返回与给定对象类型(包括子类)匹配的bean实例。
     */
    public static Map getBeansByClass(Class c) {
        return applicationContext.getBeansOfType(c);
    }
}
