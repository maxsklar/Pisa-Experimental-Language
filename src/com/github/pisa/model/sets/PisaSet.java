package com.github.pisa.model.sets;

import com.github.pisa.model.PisaNamespace;

public interface PisaSet {
	boolean contains(PisaNamespace ns, long ref);
}
