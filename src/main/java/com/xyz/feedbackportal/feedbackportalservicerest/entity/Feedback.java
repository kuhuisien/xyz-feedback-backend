package com.xyz.feedbackportal.feedbackportalservicerest.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;


//@JsonFilter("feedbackPostFilter")
@Entity
@Table(name="feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	@Size(min=2, message="Name should be at least 2 characters")
	private String name;
	
	@Column(name="email")
	@Email(message="Email should be a well-formed email address")
	private String email;
	
	@Column(name="contact_number")
	@Pattern(regexp="(6|8|9)\\d{7}", message="Contact number should be Singapore phone number")
	private String contactNumber;
	
	@Column(name="agency_name")
	@Size(min=2, message="Agency name should be at least 2 characters")
	private String agencyName;
	
	@Column(name="feedback")
	@Size(min=2, message="Feedback should be at least 2 characters")
	private String feedback;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "processing_result")
	private FeedbackProcesssingResult processingResult;

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		updatedAt = LocalDateTime.now();
	    if (createdAt==null) {
	    	createdAt = updatedAt;
	    }
	}

	public Feedback() {
		
	}

	public Feedback(String name, String email, String contactNumber, String agencyName, String feedback) {
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.agencyName = agencyName;
		this.feedback = feedback;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	} 
	
	public FeedbackProcesssingResult getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(FeedbackProcesssingResult processingResult) {
		this.processingResult = processingResult;
	}
}
