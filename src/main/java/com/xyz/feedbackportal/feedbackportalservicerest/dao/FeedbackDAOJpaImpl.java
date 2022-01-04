package com.xyz.feedbackportal.feedbackportalservicerest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xyz.feedbackportal.feedbackportalservicerest.entity.Feedback;
import com.xyz.feedbackportal.feedbackportalservicerest.util.ListUtil;

@Repository
public class FeedbackDAOJpaImpl implements FeedbackDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public FeedbackDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Feedback> findbyContactAndEmail(String contactNumber, String email) {
		
		Query query = entityManager.createQuery(
				"from Feedback where contactNumber=:contactNumber and email=:email order by createdAt desc");
		
		query.setParameter("contactNumber", contactNumber);
		query.setParameter("email", email);
		
		List<Feedback> feedbacks = ListUtil.castList(Feedback.class, query.getResultList());
		
		return feedbacks;
	}

	@Override
	public void save(Feedback theFeedback) {
		Feedback dbFeedback = entityManager.merge(theFeedback);
		
		theFeedback.setId(dbFeedback.getId());
		theFeedback.setCreatedAt(dbFeedback.getCreatedAt());
		theFeedback.setUpdatedAt(dbFeedback.getUpdatedAt());
	}
}
