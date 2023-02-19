package org.cap.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.cap.demo.model.AuthRequest;
import org.cap.demo.model.ChangePassword;
import org.cap.demo.model.Customer;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForgotController {

    @Autowired
    public ICustomerService userService;
    
    @Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ICustomerService customerDbService;
    
    @PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("invalid username/password");
		}
		Map<String,Object> res = new HashMap<>();
		res.put("token", jwtUtil.generateToken(authRequest.getEmailId()));
		Customer cust = userService.findByEmailId(authRequest.getEmailId());
		res.put("user", cust);
		return ResponseEntity.ok(res);
	}
    @SecurityRequirement(name = "policy")
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getById(@PathVariable long id) {
		Customer customer = customerDbService.getById(id);
		return ResponseEntity.ok(customer);
	}

    @PostMapping("/forgot_password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String,String> forgotPayload){
        try {
            return userService.forgotPassword(forgotPayload);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");

    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(@RequestParam String token,@RequestBody Map<String,String> resetPayload){

        try {
            return userService.resetPassword(token,resetPayload);
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");

    }
    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changepld){

        return userService.changePassword(changepld);

    }

}
