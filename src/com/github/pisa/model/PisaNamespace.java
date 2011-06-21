package com.github.pisa.model;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PisaNamespace {
    final private PisaDb db;
    final private Map<String, Long> nameMap = new HashMap<String, Long>();
    final private Map<Long, Set<String>> reverseNameMap = new HashMap<Long, Set<String>>();

    public PisaNamespace(PisaDb db) {
        this.db = db;
    }

    public void addObject(String name, PisaObject object) {
        long id = db.addPisaObject(object);
        nameMap.put(name, id);
        if (!reverseNameMap.containsKey(id)) reverseNameMap.put(id, new HashSet<String>());
        reverseNameMap.get(id).add(name);
    }

    public void addObject(String name, long... data) {
        addObject(name, new PisaObject(data));
    }

    public boolean checkEqual(String obj1, String obj2) {
        checkEqual(obj1, obj2, )
    }

}
