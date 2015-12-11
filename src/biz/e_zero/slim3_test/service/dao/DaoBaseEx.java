package biz.e_zero.slim3_test.service.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import biz.e_zero.slim3_test.model.Slim3Model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.Expiration;

public abstract class DaoBaseEx<T extends Slim3Model> extends DaoBase<T>  {
    private MemcacheEx memcacheEx = new MemcacheEx();
    private static final int EXPIR_SECONDS = 60 * 60 * 24;
    
    // Memcache経由でDatastoreへ<T>オブジェクトを格納するメソッド(P5)
    @Override
    public Key put(T model) {
        Key key = super.put(model);
        Memcache.put(key, model, Expiration.byDeltaSeconds(EXPIR_SECONDS));
        return key;
    }
    
    // Memcache経由でDatastoreへ<T>オブジェクトを格納するメソッド(P5)
    @Override
    public List<Key> put(List<T> models) {
        Map<Key, T> map = new HashMap<Key, T>();
        for (T model : models) {
            map.put(model.getKey(), model);
        }

        memcacheEx.putAll(map);
        return Datastore.put(models);
    }

    // Memcache経由でDatastoreから<T>オブジェクトを取得するメソッド(P5)
    @Override
    public T get(Key key) {
        T model = null;
        if (Memcache.contains(key)) {
            model = Memcache.get(key);
        } else {
            model = super.get(key);
            Memcache.put(model.getKey(), model, Expiration.byDeltaSeconds(EXPIR_SECONDS));
        }
        return model;
    }

    // Memcache経由でDatastoreから<T>オブジェクトを取得するメソッド(P5)
    @Override
    public T getOrNull(Key key) {
        T model = null;
        if (Memcache.contains(key)) {
            model = Memcache.get(key);
        } else {
            model = super.getOrNull(key);
            if (model != null) {
                Memcache.put(model.getKey(), model, Expiration.byDeltaSeconds(EXPIR_SECONDS));
            }
        }
        return model;
    }
    
    // Memcache経由でDatastoreから<T>オブジェクトを取得するメソッド（Bulk get）(P5)
    @Override
    public List<T> get(List<Key> keys) {
        Map<Key, T> memModelMap = memcacheEx.getAll(keys);
        Map<Key, T> dsModelMap = getDSModelMap(keys, memModelMap);
        memcacheEx.putAll(dsModelMap);
        
        List<T> modelList = new ArrayList<T>();
        for (Key key : keys) {
            if (memModelMap.containsKey(key)) {
                modelList.add(memModelMap.get(key));
            } else if (dsModelMap.containsKey(key)) {
                modelList.add(dsModelMap.get(key));
            }
        }
        return modelList;        
    }

    // Memcacheを経由でDatastoreから<T>オブジェクトを削除するメソッド(P5)
    @Override
    public void delete(Key key) {
        Memcache.delete(key);
        super.delete(key);
    }

    // Memcacheを経由でDatastoreから<T>オブジェクトを削除するメソッド(P5)
    private Map<Key, T> getDSModelMap(List<Key> keys, Map<Key, T> memModelMap) {
        Collection<Key> subCollection =
            CollectionUtils.subtract(keys, memModelMap.keySet());
        List<Key> subList = new ArrayList<Key>(subCollection);
        return super.getAsMap(subList);
    }
    
    private class MemcacheEx {
        public void putAll(Map<Key, T> values) {
            Map<Object, Object> objectMap = new HashMap<Object, Object>();
            for (Entry<Key, T> entry : values.entrySet()) {
                Object key = entry.getKey();
                Object model = entry.getValue();
                objectMap.put(key, model);
            }
            Memcache.putAll(objectMap, Expiration.byDeltaSeconds(EXPIR_SECONDS));
        }
        public Map<Key, T> getAll(List<Key> keyList) {
            Map<Key, T> map = new HashMap<Key, T>();
            Map<Object, Object> memcachedMap = Memcache.getAll(keyList);
            for (Entry<Object, Object> entry : memcachedMap.entrySet()) {
                Key key = (Key) entry.getKey();
                @SuppressWarnings("unchecked")
                T model = (T) entry.getValue();
                map.put(key, model);
            }
            return map;
        }
    }
}
