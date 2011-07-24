package com.github.pisa.model;

import java.util.HashSet;
import java.util.Set;

import com.github.pisa.model.node.PisaNode;
import com.github.pisa.model.node.PisaObject;
import com.github.pisa.tools.TwoWayMap;
import com.github.pisa.tools.UnionFind;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 6/20/11
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class PisaDbStandinLayer {
    private PisaDb pisaDb = new PisaDb();

    final private TwoWayMap<Long, Long> standIns = new TwoWayMap<Long, Long>();

    public long addPisaObject(PisaNode object) {
        return pisaDb.addPisaObject(object);
    }

    public long addPisaObject(long... values) {
        return pisaDb.addPisaObject(new PisaObject(values));
    }

    private long resolveReference(long ref) {
        return standIns.containsKey(ref)? standIns.get(ref): ref;
    }

    public PisaNode getPisaObject(PisaNamespace ns, long reference) {
    	reference = resolveReference(reference);
        PisaNode node = pisaDb.getPisaObject(reference);
        
        Set<String> seenParams = new HashSet<String>();
        while(node.getParameterName() != null) {
        	String paramName = node.getParameterName();
        	if (seenParams.contains(paramName)) return null; //Param definition cycle
        	seenParams.add(paramName);
        	node = ns.getPisaObject(paramName);
        }
        
        return node;
    }

    public boolean checkEqual(long ref1, long ref2, PisaNamespace ns) {
        boolean equal = checkEqual(ref1, ref2, new UnionFind<Long>(), ns);
        if (equal) {
            long resRef1 = resolveReference(ref1);
            long resRef2 = resolveReference(ref2);

            if (resRef1 != resRef2) {

                long rememberedRef = Math.min(resRef1, resRef2);
                long forgottenRef = Math.max(resRef1, resRef2);

                standIns.redirect(forgottenRef, rememberedRef);
                standIns.put(forgottenRef, rememberedRef);

                pisaDb.forget(forgottenRef);
            }
        }
        return equal;
    }

    private boolean checkEqual(Long ref1, Long ref2, UnionFind<Long> uf, PisaNamespace ns) {
        ref1 = resolveReference(ref1);
        ref2 = resolveReference(ref2);

        if (ref1.longValue() == ref2.longValue()) return true;

        if (uf.isEqual(ref1, ref2)) return true;

        PisaNode pobj1 = getPisaObject(ns, ref1);
        PisaNode pobj2 = getPisaObject(ns, ref2); 

        if (pobj1.size() != pobj2.size()) return false;

        uf.union(ref1, ref2);

        for(int i = 0; i < pobj1.size(); i++) {
             boolean equal = checkEqual(ref1, ref2, uf, ns);
             if (!equal) return false;
        }

        return true;
    }
}
