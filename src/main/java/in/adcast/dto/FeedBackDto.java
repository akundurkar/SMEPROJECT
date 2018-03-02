package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FeedBackDto implements Serializable {

	/**
	 * 
	 */
	private int feedbackId;
	private int clientId;
	private int branchId;
	private int orderId;
	private int serviceExperienceQue;
	private int offerExperienceQue;
	private int commentsQue;
	private String serviceExperience;
	private String offerExperience;
	private String comments;
	private String firstName;
	private String lastName;
	
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getServiceExperienceQue() {
		return serviceExperienceQue;
	}
	public void setServiceExperienceQue(int serviceExperienceQue) {
		this.serviceExperienceQue = serviceExperienceQue;
	}
	public int getOfferExperienceQue() {
		return offerExperienceQue;
	}
	public void setOfferExperienceQue(int offerExperienceQue) {
		this.offerExperienceQue = offerExperienceQue;
	}
	public int getCommentsQue() {
		return commentsQue;
	}
	public void setCommentsQue(int commentsQue) {
		this.commentsQue = commentsQue;
	}

	
	public String getServiceExperience() {
		return serviceExperience;
	}
	public void setServiceExperience(String serviceExperience) {
		this.serviceExperience = serviceExperience.trim();
	}
	public String getOfferExperience() {
		return offerExperience;
	}
	public void setOfferExperience(String offerExperience) {
		this.offerExperience = offerExperience.trim();
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments.trim();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}
	
	@Override
	public String toString() {
		return "FeedBackDto [feedbackId=" + feedbackId + ", clientId=" + clientId + ", branchId=" + branchId
				+ ", orderId=" + orderId + ", serviceExperienceQue=" + serviceExperienceQue + ", offerExperienceQue="
				+ offerExperienceQue + ", commentsQue=" + commentsQue + ", serviceExperience=" + serviceExperience
				+ ", offerExperience=" + offerExperience + ", comments=" + comments + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
}
