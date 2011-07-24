package com.github.pisa.model.node;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:01 AM
 *
 * The "long" value for each pisa object is a pointer to another object.
 */
public class PisaObject implements PisaNode {
    final private long[] data;

    public PisaObject(long... data) {
        this.data = data.clone();
    }

    public int size() { return data.length; }

    public long get(int i) { 
    	return (i >= 0 && i < data.length)? data[i]: PisaNode.OUT_OF_BOUNDS_ERR; 
    }

	@Override
	public String getParameterName() { return null; }
}
