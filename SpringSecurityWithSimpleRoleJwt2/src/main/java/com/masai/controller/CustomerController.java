package com.masai.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masai.model.Customer;
import com.masai.service.CustomerService;

/**
 * Controller class for handling customer-related operations.
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	
	private final CustomerService customerService;
	
	/**
     * Constructor to inject the CustomerService dependency.
     *
     * @param userService The CustomerService instance.
     */
	@Autowired
    public CustomerController(CustomerService userService) {
        this.customerService = userService;
    }
    
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	/**
     * Endpoint to create a new customer.
     *
     * @param customer The Customer object to create.
     * @return The ResponseEntity containing the created customer and HTTP status.
     */
	@PostMapping("/create_data")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){

		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer registeredCustomer= customerService.createUser(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	/**
     * Endpoint to update customer data.
     *
     * @param userId The ID of the customer to update.
     * @param user   The updated Customer object.
     * @return The ResponseEntity containing the updated customer and HTTP status.
     */
	@PatchMapping("/update_data/{userId}")
    public ResponseEntity<Customer> updateData(@PathVariable Integer userId, @RequestBody Customer user) {
		Customer updatedUser = customerService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
	
	/**
     * Endpoint to get a list of nearest users.
     *
     * @param count The number of nearest users to retrieve.
     * @return The ResponseEntity containing the list of nearest users and HTTP status.
     */
	@GetMapping("/get_users/{N}")
    public ResponseEntity<List<Customer>> getNearestUsers(@PathVariable int N) {
        List<Customer> nearestUsers = customerService.getNearestUsers(N);
        
        if (nearestUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(nearestUsers);
        }
    }
	
	/**
     * Endpoint to delete a customer.
     *
     * @param userId The ID of the customer to delete.
     * @return The ResponseEntity with a success message and HTTP status.
     */
	@DeleteMapping("/delete_user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        customerService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
	
	
	
}
