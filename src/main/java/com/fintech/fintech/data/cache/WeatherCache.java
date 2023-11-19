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
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() >= cacheProperty.getSize();
    }
}
