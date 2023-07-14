package com.masai.service;
import java.util.List;

import com.masai.model.Customer;

/**
 * Service interface for customer-related operations.
 */
public interface CustomerService {
	
	/**
     * Creates a new customer.
     *
     * @param customer The Customer object to create.
     * @return The created customer.
     */
	Customer createUser(Customer user);
    
	/**
     * Updates customer data.
     *
     * @param userId The ID of the customer to update.
     * @param user   The updated Customer object.
     * @return The updated customer.
     */
	Customer updateUser(Integer userId, Customer user);
    
	/**
     * Retrieves a list of nearest users.
     *
     * @param count The number of nearest users to retrieve.
     * @return The list of nearest users.
     */
    List<Customer> getNearestUsers(int count);
    
    /**
     * Deletes a customer by ID.
     *
     * @param userId The ID of the customer to delete.
     */
    void deleteUser(Integer userId);

}
