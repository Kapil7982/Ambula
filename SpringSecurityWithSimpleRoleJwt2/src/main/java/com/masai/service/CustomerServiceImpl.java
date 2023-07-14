package com.masai.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

/**
 * Service implementation class for customer-related operations.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	
	private final CustomerRepository customerRepository;
	
	/**
     * Constructor to inject the CustomerRepository dependency.
     *
     * @param customerRepository The CustomerRepository instance.
     */
	@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	@Override
	public Customer createUser(Customer user) {
		return customerRepository.save(user);
	}

	@Override
	public Customer updateUser(Integer userId, Customer user) {
		Customer existingUser = customerRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        existingUser.setName(user.getName());
        existingUser.setLatitude(user.getLatitude());
        existingUser.setLongitude(user.getLongitude());
        
        return customerRepository.save(existingUser);
	}

	@Override
	public List<Customer> getNearestUsers(int count) {
		List<Customer> allUsers = customerRepository.findAll();
	    List<Customer> nearestUsers = new ArrayList<>();
	    
	    // Sort the users based on their distance from (0,0)
	    allUsers.sort(Comparator.comparingDouble(user -> calculateDistance(user.getLatitude(), user.getLongitude(), 0, 0)));
	    
	    // Add the nearest 'count' users to the result list
	    for (int i = 0; i < Math.min(count, allUsers.size()); i++) {
	        nearestUsers.add(allUsers.get(i));
	    }
	    
	    return nearestUsers;
	}
	
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
	    // Radius of the earth in kilometers
	    double earthRadius = 6371;

	    // Convert latitude and longitude to radians
	    double lat1Rad = Math.toRadians(lat1);
	    double lon1Rad = Math.toRadians(lon1);
	    double lat2Rad = Math.toRadians(lat2);
	    double lon2Rad = Math.toRadians(lon2);

	    // Haversine formula
	    double dLat = lat2Rad - lat1Rad;
	    double dLon = lon2Rad - lon1Rad;
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	            + Math.cos(lat1Rad) * Math.cos(lat2Rad)
	            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = earthRadius * c;

	    return distance;
	}

	@Override
    public void deleteUser(Integer userId) {
        customerRepository.deleteById(userId);
    }

	
}
