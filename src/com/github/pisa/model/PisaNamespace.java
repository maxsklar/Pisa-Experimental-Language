package com.github.pisa.model;

import com.github.pisa.tools.TwoWayMap;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PisaNamespace {
    final private PisaDbStandinLayer db = new PisaDbStandinLayer();
    final private TwoWayMap<String, Long> nameMap = new TwoWayMap<String, Long>();

    public void addObject(String name, PisaObject object) {
        long id = db.addPisaObject(object);
        nameMap.put(name, id);
    }

    public void addObject(String name, String... refs) {
        long[] data = new long[refs.length];
        for(int i = 0; i < refs.length; i++) data[i] = nameMap.get(refs[i]);
        addObject(name, new PisaObject(data));
    }
    
    public PisaObject getPisaObject(String name) {
    	if (!nameMap.containsKey(name)) return null;
    	return db.getPisaObject(nameMap.get(name));
    }

    public boolean checkEqual(String name1, String name2) {
       long ref1 = nameMap.get(name1);
       long ref2 = nameMap.get(name2);
       boolean equal = db.checkEqual(ref1, ref2);
       if (equal && (ref1 != ref2)) {
           long redundantRef = Math.max(ref1, ref2);
           long originalRef = Math.min(ref1, ref2);
           nameMap.redirect(redundantRef, originalRef);
       }
       return equal;
    }

}
