package org.cap.demo.service;

import java.util.ArrayList;
import java.util.Optional;


import org.cap.demo.dao.ICustomerRepository;
import org.cap.demo.json.CustomerRequest;
import org.cap.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService{

	@Autowired
	private ICustomerRepository repository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<CustomerRequest> customer=repository.findByEmailId(emailId);
		return new User(customer.get().getEmailId() , customer.get().getPassword(), new ArrayList<>());
	
	}
	

}
