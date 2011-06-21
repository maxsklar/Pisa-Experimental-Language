package com.github.pisa.tools;

import sun.security.krb5.internal.KdcErrException;
import sun.tools.tree.NewArrayExpression;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TwoWayMap<K, V> implements Map<K, V> {
    private HashMap<K, V> map = new HashMap<K, V>();
    private HashMap<V, Set<K>> revMap = new HashMap<V, Set<K>>();

    public int size() { return map.size(); }
    public boolean isEmpty() { return map.isEmpty(); }
    public boolean containsKey(Object key) { return map.containsKey(key); }

    public boolean containsValue(Object value) {
        return revMap.containsKey(value) && !revMap.get(value).isEmpty();
    }

    public V get(Object key) {
        return map.get(key);
    }

    public V put(K key, V value) {
        remove(key);
        map.put(key, value);
        if (!revMap.containsKey(value)) revMap.put(value, new HashSet<K>());
        revMap.get(value).add(key);
        return value;
    }

    public V remove(Object key) {

        if (!map.containsKey(key)) return null;

        V value = map.get(key);
        map.remove(key);
        revMap.get(value).remove(key);
        if (revMap.get(value).isEmpty()) revMap.remove(value);
        return value;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for(K key: map.keySet()) put(key, m.get(key));
    }

    public void clear() {
        map.clear();
        revMap.clear();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Collection<V> values() {
        return map.values();
    }

    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public Set<K> getKeys(V value) {
        return revMap.containsKey(value)? revMap.get(value): new HashSet<K>();
    }

    /**
     * @param oldValue
     * @param newValue
     * Everything that used to map to the old value is now going to map to the new value.
     */
    public void redirect(V oldValue, V newValue) {
        Set<K> keys = getKeys(oldValue);
        HashSet<K> clonedKeys = new HashSet<K>();
        clonedKeys.addAll(keys);
        for(K key: clonedKeys) put(key, newValue);
    }
}
