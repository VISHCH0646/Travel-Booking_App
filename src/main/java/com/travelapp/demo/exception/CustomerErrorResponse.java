package com.travelapp.demo.exception;

public class CustomerErrorResponse {
	
	private String message;
	private long timeStamp;
	
	public CustomerErrorResponse() {
		
	}

	public CustomerErrorResponse( String message, long timeStamp) {
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
