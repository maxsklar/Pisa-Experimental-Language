package com.github.pisa.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class PisaDb {
    final private Map<Long, PisaObject> pisaObjects = new HashMap<Long, PisaObject>();

    private long identifier = 0;

    public long addPisaObject(PisaObject object) {
        pisaObjects.put(identifier, object);
        return identifier++;
    }

    public long addPisaObject(long... values) {
        return addPisaObject(new PisaObject(values));
    }
}
