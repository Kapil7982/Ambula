package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

/**
 * Controller class for handling login-related operations.
 */
@RestController
public class LoginController {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
     * Endpoint to get the details of the currently logged-in customer.
     *
     * @param auth The Authentication object representing the logged-in user.
     * @return The ResponseEntity containing the customer details and HTTP status.
     */
	@GetMapping("/signIn")
	public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		
		 Customer customer= customerRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		
		 
		 return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		
		
	}
	
}
