package org.cap.demo.json;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

@Entity

public class CustomerRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message="Please Enter first name")
	private String firstName;
	//@NotEmpty(message="Please Enter Last name")
	private String lastName;
	@NotEmpty(message ="*Please enter ur surname.")
	private String marital;
	@Email(message="*Enter Valid Email ID") 
	private String emailId;
	@Length(message="*Mobile No must be 10 digits.", min=10,max=15)
	private String mobNo;
	private String password;
	@Transient
	private String confirmPassword;
	
	private String gender;

     private  String addressLine1;
	
	private String city;
	
	private String state;
	@Length(message="* Pincode mustbbe 6 digits",min=6,max=6)
	private String pincode;
	@Column(name="resetToken", nullable=true)
    private String resetToken;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMarital() {
		return marital;
	}
	public void setMarital(String surName) {
		this.marital = surName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public CustomerRequest(long id, @NotEmpty(message = "Please Enter first name") String firstName,
			@NotEmpty(message = "Please Enter Last name") String lastName,
			@NotEmpty(message = "*Please enter ur surname.") String surName,
			@Email(message = "*Enter Valid Email ID") String emailId,
			@Length(message = "*Mobile No must be 10 digits.", min = 10, max = 15) String mobNo,
			@Length(message = "*Password must be between 8-20 characters.", min = 2, max = 20) String password,
			String confirmPassword, String gender, String addressLine1, String city, String state,
			@Length(message = "* Pincode mustbbe 6 digits", min = 6, max = 6) String pincode,
			@NotEmpty String resetToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marital = surName;
		this.emailId = emailId;
		this.mobNo = mobNo;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.resetToken = resetToken;
	}
	public CustomerRequest(@NotEmpty(message = "Please Enter first name") String firstName,
			@NotEmpty(message = "Please Enter Last name") String lastName,
			@NotEmpty(message = "*Please enter ur surname.") String surName,
			@Email(message = "*Enter Valid Email ID") String emailId,
			@Length(message = "*Mobile No must be 10 digits.", min = 10, max = 15) String mobNo,
			@Length(message = "*Password must be between 8-20 characters.", min = 2, max = 20) String password,
			String confirmPassword, String gender, String addressLine1, String city, String state,
			@Length(message = "* Pincode mustbbe 6 digits", min = 6, max = 6) String pincode,
			@NotEmpty String resetToken) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.marital = surName;
		this.emailId = emailId;
		this.mobNo = mobNo;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.resetToken = resetToken;
	}
	public CustomerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
