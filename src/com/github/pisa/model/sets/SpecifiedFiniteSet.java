package com.github.pisa.model.sets;

import java.util.HashSet;
import java.util.Set;

import com.github.pisa.model.PisaNamespace;

public class SpecifiedFiniteSet implements PisaSet {
	private Set<Long> members = new HashSet<Long>();
	
	public SpecifiedFiniteSet(long... members) {
		for(long ref: members) {
			this.members.add(ref);
		}
	}

	@Override
	public boolean contains(PisaNamespace ns, long ref) {
		return members.contains(ref);
	}
}
