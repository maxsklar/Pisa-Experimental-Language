package com.github.pisa.model;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:01 AM
 *
 * The "long" value for each pisa object is a pointer to another object.
 */
public class PisaObject {
    final private long[] data;

    public PisaObject(long... data) {
        this.data = data.clone();
    }

    public int size() { return data.length; }

    public long get(int i) { return data[i % size()]; }
}
