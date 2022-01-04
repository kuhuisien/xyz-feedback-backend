package com.xyz.feedbackportal.feedbackportalservicerest.service;

import java.util.List;

import com.xyz.feedbackportal.feedbackportalservicerest.entity.Feedback;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.FeedbackProcessResult;

import reactor.core.publisher.Mono;

public interface FeedbackService {
	
	public void save(Feedback theFeedback);

	List<Feedback> findbyContactAndEmail(String contactNumber, String email);
	
	public Mono<FeedbackProcessResult> processFeedback(String feedback);

}
