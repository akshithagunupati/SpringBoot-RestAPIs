package com.akshithaSecondProject.restfulwebservices.versioning;

public class Name {
	private String fName;
	private String lName;
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public Name(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	@Override
	public String toString() {
		return "Name [fName=" + fName + ", lName=" + lName + "]";
	}
	
}
