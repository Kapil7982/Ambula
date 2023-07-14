package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.masai.model.Customer;

/**
 * Repository interface for managing customer data.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	/**
     * Finds a customer by email.
     *
     * @param email The email of the customer to find.
     * @return The optional Customer object.
     */
	public Optional<Customer> findByEmail(String email);
}
