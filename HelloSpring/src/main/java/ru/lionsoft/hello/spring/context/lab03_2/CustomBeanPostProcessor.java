/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean Post Processor (Interceptor)
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
public class CustomBeanPostProcessor 
        implements BeanPostProcessor // for intercept before/after invoke method init() for beans
{
    
    // ************** Implements interface BeanPostProcessors *****************

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("\n@@@@ CustomBeanPostProcessor.postProcessBeforeInitialization() for : " + beanName);
        return bean;
//        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("\n@@@@ CustomBeanPostProcessor.postProcessAfterInitialization() for : " + beanName);
        return bean;
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
