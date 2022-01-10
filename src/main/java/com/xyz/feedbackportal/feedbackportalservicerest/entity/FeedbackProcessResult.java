package com.xyz.feedbackportal.feedbackportalservicerest.entity;

// Response entity structure returned by external handling API
public class FeedbackProcessResult {
	
	private String sentiment;
	
	private long time;
	
	private String feedback;
	
	public FeedbackProcessResult() {
	}

	public FeedbackProcessResult(String sentiment, long time, String feedback) {
		this.sentiment = sentiment;
		this.time = time;
		this.feedback = feedback;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
