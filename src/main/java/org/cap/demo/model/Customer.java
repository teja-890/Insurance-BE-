package org.cap.demo.model;


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

import org.hibernate.validator.constraints.Length;



public class Customer {
	
	
	private long id;
	
	private String firstName;
	private String lastName;
	
	private String marital;
	
	private String emailId;
	
	private String mobNo;
	
	private String password;
	
	private String confirmPassword;
	
	private String gender;

	private  String addressLine1;
	
	private String city;
	private String state;
	private String pincode;
	
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

	

	public Customer(long id, String firstName, String lastName, String surName, String emailId, String mobNo,
			String password, String confirmPassword, String gender, String addressLine1, String city, String state,
			String pincode, String resetToken) {
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

	

	public Customer(String firstName, String lastName, String surName, String emailId, String mobNo, String password,
			String confirmPassword, String gender, String addressLine1, String city, String state, String pincode,
			String resetToken) {
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

	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", surName=" + marital
				+ ", emailId=" + emailId + ", mobNo=" + mobNo + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", gender=" + gender + ", addressLine1=" + addressLine1 + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", resetToken=" + resetToken + "]";
	}
	

	
}
