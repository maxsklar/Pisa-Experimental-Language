package com.github.pisa.model.node;

public class PisaParameter implements PisaNode {

	final private String parameterName;
	
	public PisaParameter(String parameterName) {
		this.parameterName = parameterName;
	}
	
	@Override
	public int size() { return PisaNode.IS_PARAM_ERROR; }

	@Override
	public long get(int i) { return PisaNode.IS_PARAM_ERROR; }

	@Override
	public String getParameterName() { return parameterName; }

}
