package org.cap.demo.util;

import java.util.List;
import java.util.stream.Collectors;

//import org.cap.demo.json.AddressResquest;
import org.cap.demo.json.CustomerRequest;

import org.cap.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
     public Customer entityToDto(CustomerRequest cr) {
    	 Customer c=new Customer();
    	 c.setId(cr.getId());
    	 c.setFirstName(cr.getFirstName());
    	 c.setLastName(cr.getLastName());
 		c.setMarital(cr.getMarital());
 		c.setEmailId(cr.getEmailId());
 		c.setMobNo(cr.getMobNo());
 		c.setPassword(cr.getPassword());
 		c.setConfirmPassword(cr.getConfirmPassword());
 		c.setGender(cr.getGender());
 	    
 	    c.setAddressLine1(cr.getAddressLine1());
 	    c.setCity(cr.getCity());
 	    c.setState(cr.getState());
 	    //c.setAddress(a);
 	    c.setPincode(cr.getPincode());
 	    return c;
     }
     public List<Customer> entityToDto(List<CustomerRequest> customer){
    	 return	customer.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
     }
     
     
     public CustomerRequest dtoToEntity(Customer customer) {
    	 CustomerRequest cr = new CustomerRequest();
    	 cr.setId(customer.getId());
 		cr.setFirstName(customer.getFirstName());
 		cr.setLastName(customer.getLastName());
 		cr.setMarital(customer.getMarital());
 		cr.setEmailId(customer.getEmailId());
 		cr.setMobNo(customer.getMobNo());
 		cr.setPassword(passwordEncoder.encode(customer.getPassword()));
        cr.setConfirmPassword(passwordEncoder.encode(customer.getConfirmPassword()));
 		cr.setGender(customer.getGender());
 		
 		cr.setAddressLine1(customer.getAddressLine1());
 		cr.setCity(customer.getCity());
 		cr.setState(customer.getState());
 		//cr.setAddressResquest(ar);
 		cr.setPincode(customer.getPincode());
 		return cr;
    	 
     }
     
     public List<CustomerRequest> dtoToEntity(List<Customer> dto)
 	{

 		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
 	}
}
