package com.github.pisa.model;

import java.security.InvalidParameterException;
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
    //Actual objects
    final private Map<Long, PisaObject> pisaObjects = new HashMap<Long, PisaObject>();

    private long identifier = 0;

    public long addPisaObject(PisaObject object) {
        pisaObjects.put(identifier, object);
        return identifier++;
    }

    public long addPisaObject(long... values) {
        return addPisaObject(new PisaObject(values));
    }

    /**
     * @param reference
     * @return pisa object at the reference, null if the reference is forgotten
     */
    public PisaObject getPisaObject(long reference) {
        if (reference < 0 || reference >= identifier)
            throw new InvalidParameterException("ref " + reference + " out of range");

        if (!pisaObjects.containsKey(reference)) return null;
        return pisaObjects.get(reference);
    }

    public void forget(long reference) {
        pisaObjects.remove(reference);
    }
}
