package org.cap.demo.service;

import java.util.List;
import java.util.Map;

import org.cap.demo.json.CustomerRequest;
import org.cap.demo.model.ChangePassword;
import org.cap.demo.model.Customer;
import org.springframework.http.ResponseEntity;

public interface ICustomerService  {
	//public Customer saveCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getById(long id);

	public Customer findByEmailIdAndPassword(String emailId, String password);

	//public Customer update(Customer customer);

	Customer findByEmailId(String emailId);

	public ResponseEntity saveOrUpdate(Customer customer);
	public ResponseEntity<?> forgotPassword(Map<String, String> forgotPayload);
	
	 public ResponseEntity<?> resetPassword(String token,Map<String, String> resetPayload);
	 public ResponseEntity<?> changePassword(ChangePassword changepld);
	

	
}
