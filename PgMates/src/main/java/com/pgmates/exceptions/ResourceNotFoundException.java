package com.pgmates.exceptions;

public class ResourceNotFoundException {
	String resourceName;
	String fieldName;
	long fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
