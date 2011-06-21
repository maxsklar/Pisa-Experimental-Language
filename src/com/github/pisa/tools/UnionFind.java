package com.github.pisa.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:42 AM
 * todo: right now, this is an inefficient implementation.  Fix this.
 */
public class UnionFind<T> {
    final private Map<T, T> representative = new HashMap<T, T>();

    private T find(T obj) {
        if (!representative.containsKey(obj)) return obj;
        return find(representative.get(obj));
    }

    public void union(T obj1, T obj2) {
        T rep1 = find(obj1);
        T rep2 = find(obj2);
        representative.put(rep1, rep2);
    }

    public boolean isEqual(T obj1, T obj2) {
        return find(obj1) == find(obj2);
    }

}
