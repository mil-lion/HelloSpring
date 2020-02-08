/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_2;

import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import ru.lionsoft.hello.spring.context.entity.MusicItem;

// TODO - Make sure JavaTunesCatalog implements Catalog
public class JavaTunesCatalog 
        implements Catalog, // business interface 
            BeanNameAware, // for get bean name
            BeanFactoryAware, // for get ref on BeanFactory
            ApplicationContextAware, // for get ref on ApplicationContext
            InitializingBean, // for method afterPropertiesSet()
            DisposableBean, // for bean destruction - method destroy()
            ApplicationListener<ApplicationEvent> // for events
{
    // ************** Bean Properties ******************
    
    private ItemDAO dao;

    public void setDao(ItemDAO dao) {
        this.dao = dao;
    }
    
    private int maxSearchResults = 0;

    public void setMaxSearchResults(int maxIn) {
        System.out.println("\n@@@@ JavaTunesCatalog.setMaxSearchResults() called");
        maxSearchResults = maxIn;
    }

    // ************** Business Methods ***********************

    @Override
    public MusicItem findById(Long id) {
        System.out.println("\n@@@@ JavaTunesCatalog.findById() - " + id);

        // delegate to the search bean
        return dao.get(id);
    }

    @Override
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("\n@@@@ JavaTunesCatalog.findByKeyword() - " + keyword);
        System.out.println("- maxSearchResults = " + maxSearchResults);

        // delegate to the search Bean, then trim the results
        return trim(dao.searchByArtistTitle(keyword));
    }

    // Simple method to trim the results collection down to a size of maxSearchResults
    // We only bother to do it for Lists because their is an easy method to do so, and that's adequate to test the lab
    private Collection<MusicItem> trim(Collection<MusicItem> results) {
        Collection<MusicItem> ret = results;
        if ((maxSearchResults > 0)
                && (results.size() > maxSearchResults)
                && (results instanceof List)) {
            ret = ((List<MusicItem>) results).subList(0, maxSearchResults);
        }
        return ret;
    }

    // ************** Bean Lyfecycle Callback ****************
    
    // Constructor
    public JavaTunesCatalog() {
        System.out.println("\n@@@@ Constructor JavaTunesCatalog()");
        System.out.println("dao = " + dao);
        System.out.println("maxSearchResults = " + maxSearchResults);
        System.out.println("beanName = " + beanName);
        System.out.println("beanFactory = " + beanFactory);
        System.out.println("ctx = " + ctx);
    }
    
    // Constructor after injection
    public void init() {
        System.out.println("\n@@@@ JavaTunesCatalog.init()");
        System.out.println("dao = " + dao);
        System.out.println("maxSearchResults = " + maxSearchResults);
        System.out.println("beanName = " + beanName);
        System.out.println("beanFactory = " + beanFactory);
        System.out.println("ctx = " + ctx);
    }
    
    public void cleanup() {
        System.out.println("\n@@@@ JavaTunesCatalog.cleanup()");
    }
    
    @PostConstruct
    public void alterCreate() {
        System.out.println("\n@@@@ JavaTunesCatalog.afterCreate()");
    } 
    
    @PreDestroy
    public void beforeDelete() {
        System.out.println("\n@@@@ JavaTunesCatalog.beforeDelete()");
    }

    // Implements interface BeanNameAware
    
    private String beanName;
    
    @Override
    public void setBeanName(String name) {
        System.out.println("\n@@@@ JavaTunesCatalog.setBeanName('" + name + "')");
        beanName = name;
    }

    // Implements interface BeanFactoryAware
    
    private BeanFactory beanFactory;
    
    @Override
    public void setBeanFactory(BeanFactory bf) throws BeansException {
        System.out.println("\n@@@@ JavaTunesCatalog.setBeanFactory()");
        beanFactory = bf;
    }

    // Implements interface ApplicationContextAware
    
    private ApplicationContext ctx;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("\n@@@@ JavaTunesCatalog.setApplicationContext()");
        ctx = applicationContext;
    }
    
    // Implements interface InitializingBean
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("\n@@@@ JavaTunesCatalog.afterPropertiesSet()");
    }    

    // Implements interface DesposableBean
    
    @Override
    public void destroy() throws Exception {
        System.out.println("\n@@@@ JavaTunesCatalog.destroy()");
    }

    // ************** Events ********************
    
    // Implements interface ApplicationListener
    
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("\n@@@@ JavaTunesCatalog.onApplicationEvent()");
        System.out.println("Event received - " + event.getClass().getName());
    }
  
}
