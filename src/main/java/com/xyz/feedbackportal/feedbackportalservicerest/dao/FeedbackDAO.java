package com.xyz.feedbackportal.feedbackportalservicerest.dao;

import java.util.List;

import com.xyz.feedbackportal.feedbackportalservicerest.entity.Feedback;

public interface FeedbackDAO {
	
	public void save(Feedback theFeedback);

	List<Feedback> findbyContactAndEmail(String contactNumber, String email);
}
