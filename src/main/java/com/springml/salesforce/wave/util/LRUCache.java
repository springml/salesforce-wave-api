package com.springml.salesforce.wave.util;

import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 7579846591074774824L;
    private int cacheSize;

    public LRUCache(int size) {
        super(size, 0.75f, true);
        this.cacheSize = size;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}