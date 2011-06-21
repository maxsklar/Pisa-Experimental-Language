package com.github.pisa.model;

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

    public long addPisaObject(PisaObject object) {
        return pisaDb.addPisaObject(object);
    }

    public long addPisaObject(long... values) {
        return pisaDb.addPisaObject(new PisaObject(values));
    }

    private long resolveReference(long ref) {
        return standIns.containsKey(ref)? standIns.get(ref): ref;
    }

    public PisaObject getPisaObject(long reference) {
        return pisaDb.getPisaObject(resolveReference(reference));
    }

    public boolean checkEqual(long ref1, long ref2) {
        boolean equal = checkEqual(ref1, ref2, new UnionFind<Long>());
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

    private boolean checkEqual(Long ref1, Long ref2, UnionFind<Long> uf) {
        ref1 = resolveReference(ref1);
        ref2 = resolveReference(ref2);

        if (ref1.longValue() == ref2.longValue()) return true;

        if (uf.isEqual(ref1, ref2)) return true;

        PisaObject pobj1 = getPisaObject(ref1);
        PisaObject pobj2 = getPisaObject(ref2);

        if (pobj1.size() != pobj2.size()) return false;

        uf.union(ref1, ref2);

        for(int i = 0; i < pobj1.size(); i++) {
             boolean equal = checkEqual(ref1, ref2, uf);
             if (!equal) return false;
        }

        return true;
    }
}
