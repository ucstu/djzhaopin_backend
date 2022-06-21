package com.ucstu.guangbt.djzhaopin.utils;

import org.springframework.context.ConfigurableApplicationContext;

// 获取 Spring bean 容器
public class ApplicationContextUtil {
    private static ConfigurableApplicationContext applicationContext;

    private ApplicationContextUtil() {
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz, String name) {
        return applicationContext.getBean(name, clazz);
    }
}
