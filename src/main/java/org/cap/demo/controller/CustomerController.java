package org.cap.demo.controller;

import java.util.List;
import java.util.Optional;

import org.cap.demo.exception.ResourceNotFoundException;
import org.cap.demo.model.AuthRequest;
import org.cap.demo.model.Customer;
import org.cap.demo.model.JwtResponse;
import org.cap.demo.service.ICustomerService;
import org.cap.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ICustomerService customerDbService;


	@SecurityRequirement(name = "policy")
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		List<Customer> customer = customerDbService.getAllCustomers();
		return customer;
	}

	@PostMapping("/customer")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity saveCustomer(@RequestBody Customer customer) {
		ResponseEntity customer2 = customerDbService.saveOrUpdate(customer);
		if (customer2 == null)
			return new ResponseEntity("Customer Details INsertion Error!", HttpStatus.BAD_REQUEST);
		return new ResponseEntity("Customer Details added successfully!", HttpStatus.OK);
	}
	@SecurityRequirement(name = "policy")
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getById(@PathVariable long id) {
		Customer customer = customerDbService.getById(id);

		return ResponseEntity.ok(customer);
	}
	@SecurityRequirement(name = "policy")
	@PutMapping("/customer")

	public ResponseEntity updateEmployee(@RequestBody Customer customerDetails) {
       return customerDbService.saveOrUpdate(customerDetails); 
	}

//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
//		} catch (Exception e) {
//			throw new Exception("invalid username/password");
//		}
//		return (jwtUtil.generateToken(authRequest.getEmailId()));
//	}

}
