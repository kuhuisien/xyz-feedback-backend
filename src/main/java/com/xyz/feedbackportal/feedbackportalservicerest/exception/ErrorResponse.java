package com.xyz.feedbackportal.feedbackportalservicerest.exception;

public class ErrorResponse {

	private String message;
	
	private String detail;
	
	private long timeStamp;

	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String message, String detail, long timeStamp) {
		this.message = message;
		this.detail = detail;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String description) {
		this.detail = description;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
