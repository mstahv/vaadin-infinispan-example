package org.vaadin.infinispan;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.infinispan.manager.CacheContainer;

@Stateless
public class Repository {

    @Resource(lookup = "java:jboss/infinispan/container/myCache")
    CacheContainer cc;
            
    Map<String, MyEntity> cache;
    
    @PostConstruct
    void init() {
        this.cache = cc.getCache();
    }
    
    public MyEntity[] getAll() {
        return cache.values().toArray(new MyEntity[0]);
    }
    
    public MyEntity getByName(String name) {
        return cache.get(name);
    }
    
    public void save(MyEntity data) {
        cache.put(data.getName(), data);
    }
    
    public int size() {
        return cache.size();
    }


}
