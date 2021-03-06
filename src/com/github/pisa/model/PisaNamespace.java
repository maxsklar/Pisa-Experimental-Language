package com.github.pisa.model;

import com.github.pisa.model.node.PisaNode;
import com.github.pisa.model.node.PisaObject;
import com.github.pisa.model.node.PisaObjectPrimitive;
import com.github.pisa.tools.TwoWayMap;

/**
 * User: max
 * Date: 6/20/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PisaNamespace {
	final private PisaNamespace parent;
	
    final private PisaDbStandinLayer db = new PisaDbStandinLayer();
    final private TwoWayMap<String, Long> nameMap = new TwoWayMap<String, Long>();
    
    public PisaNamespace() { this(null); }
    
    public PisaNamespace(PisaNamespace parent) {
    	this.parent = parent;
    }

    public void addObject(String name, PisaNode object) {
        long id = db.addPisaObject(object);
        nameMap.put(name, id);
    }

    public void addObject(String name, String... refs) {
        long[] data = new long[refs.length];
        for(int i = 0; i < refs.length; i++) data[i] = nameMap.get(refs[i]);
        addObject(name, new PisaObject(data));
    }
    
    public void addStringObject(String name, String object) {
    	long[] chars = new long[name.length()];
    	for(int i = 0; i < name.length(); i++) {
    		char c = name.charAt(i);
    		long ref = db.addPisaObject(new PisaObjectPrimitive.Char(c));
    		chars[i] = ref;
    	}
    	addObject(name, new PisaObject(chars));
    }
    
    public PisaNode getPisaObject(String name) {
    	if (!nameMap.containsKey(name)) {
    		return (parent == null)? null: parent.getPisaObject(name);
    	}
    	return db.getPisaObject(this, nameMap.get(name));
    }
    
    public PisaNode getPisaObject(long ref) {
    	return db.getPisaObject(this, ref);
    }

    public boolean checkEqual(String name1, String name2) {
       long ref1 = nameMap.get(name1);
       long ref2 = nameMap.get(name2);
       boolean equal = db.checkEqual(ref1, ref2, this);
       if (equal && (ref1 != ref2)) {
           long redundantRef = Math.max(ref1, ref2);
           long originalRef = Math.min(ref1, ref2);
           nameMap.redirect(redundantRef, originalRef);
       }
       return equal;
    }

}
