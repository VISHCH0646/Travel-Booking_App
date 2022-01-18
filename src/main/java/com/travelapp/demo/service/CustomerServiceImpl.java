package com.travelapp.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.Customer;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		customerRepository=theCustomerRepository;
	}

	@Override
	public Customer register(Customer theCustomer) {
		Customer c=null;
		try {
			c=customerRepository.save(theCustomer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomerNotFoundException("Customer Registered with Contact Number: "+theCustomer.getContact());
		}
		return c;
	}

	@Override
	public List<Booking> getBookings(int id) {
		
		if(!customerRepository.getById(id).isAuthenticated())
			throw new CustomerNotFoundException("Customer not Authenticated id: "+id);
		return customerRepository.getById(id).getBookings();
	}

	
	
}
