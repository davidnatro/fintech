package com.fintech.fintech.data.cache;

import com.fintech.fintech.config.property.CacheProperty;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class WeatherCache<K, V> extends LinkedHashMap<K, V> {

    private final CacheProperty cacheProperty;

    public WeatherCache(CacheProperty cacheProperty) {
        this.cacheProperty = cacheProperty;
    }

    @Override
    public V put(K key, V value) {
        super.remove(key);
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() >= cacheProperty.getSize();
    }
}
