package org.cap.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.cap.demo.dao.ICustomerRepository;
//import org.cap.demo.dao.IAddressRepository;
import org.cap.demo.exception.ResourceNotFoundException;
//import org.cap.demo.json.AddressResquest;
import org.cap.demo.json.CustomerRequest;
import org.cap.demo.model.ChangePassword;
//import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.util.EmailService;
import org.cap.demo.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements ICustomerService  {

	@Autowired
	private ICustomerRepository customerDbDao;
	
	@Autowired
	private EntityConverter entityConverter;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Customer> getAllCustomers() {
		List<CustomerRequest> customer=customerDbDao.findAll();
		
		return entityConverter.entityToDto(customer);
	}

	@Override
	public Customer getById(long id) {
		CustomerRequest c1=customerDbDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not Exist with that id "+id));
	    return entityConverter.entityToDto(c1);
	    
			}

	@Override
	public Customer findByEmailId(String emailId) {
		
				Optional<CustomerRequest> c1 =customerDbDao.findByEmailId(emailId);
				 return entityConverter.entityToDto(c1.get());
				
				 
	}
	@Override
	public Customer findByEmailIdAndPassword(String emailId, String password) {
				Customer c1 =customerDbDao.findByEmailIdAndPassword(emailId, password);
				 return (c1);
				
				 
	}

	@Override
	public ResponseEntity saveOrUpdate(Customer customer) {
		Optional<CustomerRequest> c= customerDbDao.findByEmailId(customer.getEmailId());
		
		if(c.isPresent()) {
//			CustomerRequest c1=entityConverter.dtoToEntity(customer);
//			c1.setId(c.get().getId());
//			System.out.println("Customer Id" + c1.getId());
//			
//			System.out.println("Customer Email" + c1.getEmailId());
//			CustomerRequest updateCustomer= customerDbDao.save(c1);
//			Customer c2=entityConverter.entityToDto(updateCustomer);
			CustomerRequest c1 = c.get();
            c1.setEmailId(customer.getEmailId());
            c1.setMobNo(customer.getMobNo());
            c1.setAddressLine1(customer.getAddressLine1());
            c1.setCity(customer.getCity());
            c1.setState(customer.getState());
            c1.setPincode(customer.getPincode());

            customerDbDao.save(c1);
            Map<String,Object> res = new HashMap<>();
            res.put("updatedProfile",c1);
            System.out.println(res);
            return ResponseEntity.ok(res);
		}
		else {
			 CustomerRequest c1=entityConverter.dtoToEntity(customer);
				//AddressResquest address= addressDbDao.save(c1.getAddressResquest());
				
				//c1.setAddressResquest(address);
				CustomerRequest customer2= customerDbDao.save(c1);
				 Customer customer1=entityConverter.entityToDto(c1);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	

 public ResponseEntity<?> forgotPassword(Map<String, String> forgotPayload) {

        try {
            Optional<CustomerRequest> userOP = customerDbDao.findByEmailId(forgotPayload.get("emailId"));

            if(userOP.isPresent()) {

                CustomerRequest user = userOP.get();

                String token = UUID.randomUUID().toString();

                user.setResetToken(token);

                customerDbDao.save(user);
                System.out.println(user.getEmailId());

                emailService.sendEmail(user.getEmailId(), token);
                System.out.println("Data is there");
            }else {
            	 return ResponseEntity.ok("this email is not registered");
            }
            return ResponseEntity.ok("We have sent the reset link to the provided mail.");
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");

    }

 

 

    public ResponseEntity<?> resetPassword(String token,Map<String, String> resetPayload) {

        try {

            Optional<CustomerRequest> userOP = customerDbDao.findByResetToken(token);

            if(userOP.isPresent()) {

                CustomerRequest user = userOP.get();
                user.setPassword(passwordEncoder.encode(resetPayload.get("password")));
                user.setResetToken(null);
                customerDbDao.save(user);
                return ResponseEntity.ok("You successfully resetted your password. You can login now ");
            }

            return ResponseEntity.badRequest().body("Invalid reset link");

        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
    public ResponseEntity<?> changePassword(ChangePassword changepld) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<CustomerRequest> userRecord = customerDbDao.findByEmailId(SecurityContextHolder.getContext().getAuthentication().getName());
      
        if(userRecord.isPresent()) {
            CustomerRequest user=userRecord.get();
            if(passwordEncoder.matches(changepld.getOldPassword(), user.getPassword())) {
                //user.getPassword().equals(changepld.getOldPassword())


                if(changepld.getOldPassword().equals(changepld.getNewPassword())) {
                    return ResponseEntity.ok("Old password and New password should not be same");
                }
                user.setPassword(passwordEncoder.encode(changepld.getNewPassword()));
                customerDbDao.save(user);
                return ResponseEntity.ok("Password Successfully Updated");
            }
            return ResponseEntity.ok("Incorrect Old Passsword");
        }
        return ResponseEntity.internalServerError().body("Cannot Update Password");
    }      

}
