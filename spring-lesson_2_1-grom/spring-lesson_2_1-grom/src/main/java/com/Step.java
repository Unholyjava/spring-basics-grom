package com;

import java.util.Map;

public class Step {
	private long id;
	private Service serviceFrom;
	private Service serviceTo;
	private Map paramsServiceFrom;
	private Map paramsServiceTo;
	
	
	public long getId() {
		return id;
	}
	
	public Service getServiceFrom() {
		return serviceFrom;
	}
	
	public Service getServiceTo() {
		return serviceTo;
	}
	
	public Map getParamsServiceFrom() {
		return paramsServiceFrom;
	}
	
	public Map getParamsServiceTo() {
		return paramsServiceTo;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setServiceFrom(Service serviceFrom) {
		this.serviceFrom = serviceFrom;
	}
	
	public void setServiceTo(Service serviceTo) {
		this.serviceTo = serviceTo;
	}
	
	public void setParamsServiceFrom(Map paramsServiceFrom) {
		this.paramsServiceFrom = paramsServiceFrom;
	}
	
	public void setParamsServiceTo(Map paramsServiceTo) {
		this.paramsServiceTo = paramsServiceTo;
	}

	@Override
	public String toString() {
		return "Step [id=" + id + ", serviceFrom=" + serviceFrom + ", serviceTo=" + serviceTo + ", paramsServiceFrom="
				+ paramsServiceFrom + ", paramsServiceTo=" + paramsServiceTo + "]";
	}
	
}
