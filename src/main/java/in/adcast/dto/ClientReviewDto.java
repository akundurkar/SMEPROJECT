package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientReviewDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
    
	private Integer id;
	
	private String userId;
	private String name;
	private String phone;
	private String email;
	private String comment;
	
	private BigDecimal rating;
	
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone.trim();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment.trim();
	}
	
	
	
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	
	@Override
	public String toString() {
		return "ClientReviewDto [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", comment="
				+ comment + ", userId=" + userId + ", rating=" + rating + "]";
	}
	
	

}
