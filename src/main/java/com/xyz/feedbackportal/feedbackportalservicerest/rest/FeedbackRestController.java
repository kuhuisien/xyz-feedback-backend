package com.xyz.feedbackportal.feedbackportalservicerest.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.Feedback;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.FeedbackProcessResult;
import com.xyz.feedbackportal.feedbackportalservicerest.entity.FeedbackProcesssingResult;
import com.xyz.feedbackportal.feedbackportalservicerest.service.FeedbackService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FeedbackRestController {

	private FeedbackService feedbackService;
	
	@Autowired
	public FeedbackRestController(FeedbackService theFeedbackService) {
		feedbackService = theFeedbackService;
	}
	
	@GetMapping("/feedbacks")
	public List<Feedback> findByContactAndEmail(@RequestParam Map<String, String> customQuery) {
		
		String contactNumber = customQuery.getOrDefault("contactNumber", null);

		String email = customQuery.getOrDefault("email", null);
		
		return feedbackService.findbyContactAndEmail(contactNumber, email);
	}
	
	@PostMapping("/feedbacks")
	public Feedback addFeedback(@Valid @RequestBody Feedback theFeedback) {
		
		// ensure new feedback is created by setting id=0
		theFeedback.setId(0);
		
		// save new feedback
		feedbackService.save(theFeedback);
		
		
		// apply filter so that not all fields of 'feedback' class is returned
		/*
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "email", "contactNumber", 
						"agencyName", "feedback", "createdAt");
		FilterProvider filters = new SimpleFilterProvider().addFilter("feedbackPostFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(theFeedback);
		mapping.setFilters(filters); */
		
		Mono<FeedbackProcessResult> feedbackProcessResult = 
				feedbackService.processFeedback(theFeedback.getFeedback());
	
		
		feedbackProcessResult
	      .doOnSuccess(s -> {
	    	  System.out.println(s.getSentiment());
	    	  String sentiment = s.getSentiment();
	    	  if (sentiment.equals("negative")) {
	    		  theFeedback.setProcessingResult(FeedbackProcesssingResult.Rejected);
	    	  } else {
	    		  theFeedback.setProcessingResult(FeedbackProcesssingResult.Accepted);
	    	  }
	    	  feedbackService.save(theFeedback);
	    	  })
	      .doOnError(e -> System.out.println(e.getMessage()))
	      .subscribe(i -> System.out.println("Result: " + i)); 
		
		return theFeedback;
	} 
}
