package com.akshithaSecondProject.restfulwebservices.versioning;

public class personV1 {
	private String name;

	public personV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "personV1 [name=" + name + "]";
	}
	
	
}
