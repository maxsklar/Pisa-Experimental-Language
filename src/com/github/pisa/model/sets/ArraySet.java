package com.github.pisa.model.sets;

import com.github.pisa.model.PisaNamespace;
import com.github.pisa.model.node.PisaNode;

public class ArraySet implements PisaSet {
	private final PisaSet set;
	
	public ArraySet(PisaSet set) {
		this.set = set;
	}
	
	@Override
	public boolean contains(PisaNamespace ns, long ref) {
		PisaNode node = ns.getPisaObject(ref);
		for(int i = 0; i < node.size(); i++) {
			if (!set.contains(ns, node.get(i))) return false;
		}
		return true;
	}

}
