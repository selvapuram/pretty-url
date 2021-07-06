package com.stylight.prettyurl.library;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class <p>BiMap</p>
 * A bidirectional lookup map
 *
 * @param <K>  key of the map
 * @param <V>  value of the map
 */
public class BiMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = 1L;

    public Map<V, K> inversedMap = new ConcurrentHashMap<>();

    public K getKey(V value) {
        return inversedMap.get(value);
    }

    public K getKeyOrDefault(V value, K defaultValue) {
        return inversedMap.getOrDefault(value, defaultValue);
    }

    @Override
    public V remove(Object key) {
        V val = super.remove(key);
        inversedMap.remove(val);
        return val;
    }


    @Override
    public V put(K key, V value) {
        inversedMap.put(value, key);
        return super.put(key, value);
    }

}