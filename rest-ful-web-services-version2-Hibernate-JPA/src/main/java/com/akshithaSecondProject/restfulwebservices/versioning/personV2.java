package com.akshithaSecondProject.restfulwebservices.versioning;

public class personV2 {
  private Name name;

public personV2(Name name) {
	super();
	this.name = name;
}

public Name getName() {
	return name;
}

@Override
public String toString() {
	return "personV2 [name=" + name + "]";
}
  
}
