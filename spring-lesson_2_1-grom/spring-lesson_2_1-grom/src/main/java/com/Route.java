package com;

import java.util.List;

public class Route {
	private String id;
	private List steps;
	
	public String getId() {
		return id;
	}
	
	public List getSteps() {
		return steps;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setSteps(List steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", steps=" + steps + "]";
	}
		
}
