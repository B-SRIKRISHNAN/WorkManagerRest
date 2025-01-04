package org.work.core.models;

import java.util.List;

import org.springframework.http.HttpStatus;

public class WorkError {

	private int statusCode;
	private String message;
	private List<String> errors;
	

	public WorkError() {
	}
	public WorkError(int statusCode, String message, List<String> errors) {
		this.statusCode = statusCode;
		this.message = message;
		this.errors = errors;
	}
	
	// getters and setters

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "WorkError [statusCode=" + statusCode + ", message=" + message + ", errors=" + errors + "]";
	}
	
}
