package com.xyz.feedbackportal.feedbackportalservicerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.xyz.feedbackportal.feedbackportalservicerest.dao.FeedbackDAO;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.Feedback;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.FeedbackProcessResult;

import reactor.core.publisher.Mono;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	private FeedbackDAO feedbackDAO;
	
	private final WebClient webClient;
	
	@Autowired
	public FeedbackServiceImpl(FeedbackDAO theFeedbackDAO) {
		feedbackDAO = theFeedbackDAO;
		this.webClient = WebClient.create();
	}

	@Override
	@Transactional
	public void save(Feedback theFeedback) {
		feedbackDAO.save(theFeedback);
	}

	@Override
	@Transactional
	public List<Feedback> findbyContactAndEmail(String contactNumber, String email) {
		return feedbackDAO.findbyContactAndEmail(contactNumber, email);
	}
	
	public Mono<FeedbackProcessResult> processFeedback(String feedback) {
		Mono<FeedbackProcessResult> feedbackProcessResult = this.webClient.get()
				.uri("http://processfeedback.atwebpages.com/submit.php?feedback={feedback}", feedback)
						.retrieve().bodyToMono(FeedbackProcessResult.class);
		
		return feedbackProcessResult;
	}

}
