package com.github.pisa.model;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import com.github.pisa.model.node.PisaNode;
import com.github.pisa.model.node.PisaObject;

/**
 * User: max
 * Date: 6/20/11
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class PisaDb {
    final private Map<Long, PisaNode> pisaObjects = new HashMap<Long, PisaNode>();

    private long identifier = 0;

    public long addPisaObject(PisaNode object) {
        pisaObjects.put(identifier, object);
        return identifier++;
    }

    /**
     * @param reference
     * @return pisa object at the reference, null if the reference is forgotten
     */
    public PisaNode getPisaObject(long reference) {
        if (reference < 0 || reference >= identifier)
            throw new InvalidParameterException("ref " + reference + " out of range");

        if (!pisaObjects.containsKey(reference)) return null;
        return pisaObjects.get(reference);
    }

    public void forget(long reference) {
        pisaObjects.remove(reference);
    }
}
