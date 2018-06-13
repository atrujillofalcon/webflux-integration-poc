package com.eci.sterling.api.webfluxintegration.poc.integration.cache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.store.AbstractKeyValueMessageStore;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author Arnaldo Trujillo
 */
@Component
public class HazelcastMessageStore extends AbstractKeyValueMessageStore implements BeanClassLoaderAware {

    @Autowired
    private HazelcastInstance hazelcastClient;

    @Value("hazelcast.store.map.name")
    private String storeMapName = "hazelcastMessageStore";

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        hazelcastClient.getConfig().setClassLoader(classLoader);
    }

    @Override
    protected Object doRetrieve(Object id) {
        Assert.notNull(id, "'id' must not be null");
        return getMessageStoreMap().get(id);
    }

    @Override
    protected void doStore(Object id, Object objectToStore) {
        Assert.notNull(id, "'id' must not be null");
        Assert.notNull(objectToStore, "'objectToStore' must not be null");
        getMessageStoreMap().put(id, objectToStore);
    }

    @Override
    protected void doStoreIfAbsent(Object id, Object objectToStore) {
        Assert.notNull(id, "'id' must not be null");
        Assert.notNull(objectToStore, "'objectToStore' must not be null");
        getMessageStoreMap().putIfAbsent(id, objectToStore);
    }

    @Override
    protected Object doRemove(Object id) {
        Assert.notNull(id, "'id' must not be null");

        Object removedObject = this.doRetrieve(id);
        if (removedObject != null)
            getMessageStoreMap().delete(removedObject);

        return removedObject;
    }

    @Override
    protected Collection<?> doListKeys(String keyPattern) {
        Assert.hasText(keyPattern, "'keyPattern' must not be empty");
        return getMessageStoreMap().keySet();
    }

    private IMap<Object, Object> getMessageStoreMap() {
        return hazelcastClient.getMap(storeMapName);

    }
}
