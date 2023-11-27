package com.fintech.fintech.data.cache;

import com.fintech.fintech.config.property.CacheProperty;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WeatherCache<K, V> extends LinkedHashMap<K, V> {

  private final CacheProperty cacheProperty;

  private WeatherCache(CacheProperty cacheProperty) {
    this.cacheProperty = cacheProperty;
  }

  @Override
  public V get(Object key) {
    V value = super.get(key);
    super.remove(key);
    return super.put((K) key, value);
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return size() >= cacheProperty.getSize();
  }

  public static <K, V> Map<K, V> getInstance(CacheProperty property) {
    return Collections.synchronizedMap(new WeatherCache<>(property));
  }
}
