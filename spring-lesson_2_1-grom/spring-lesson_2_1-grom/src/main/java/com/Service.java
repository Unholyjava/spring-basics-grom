package com;

import java.util.List;

public class Service {
	private long id;
	private String name;
	private List paramsToCall;
	
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List getParamsToCall() {
		return paramsToCall;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParamsToCall(List paramsToCall) {
		this.paramsToCall = paramsToCall;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", paramsToCall=" + paramsToCall + "]";
	}
		
}
