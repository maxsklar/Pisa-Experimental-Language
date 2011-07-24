package com.github.pisa.model.node;

public interface PisaNode {
	int OUT_OF_BOUNDS_ERR = -1;
	int IS_PARAM_ERROR = -2;
	int PARAM_DEF_CYCLE_ERROR = -3;
	
	int size();
	long get(int i);
	String getParameterName(); //Returns null if not a parameter
}
