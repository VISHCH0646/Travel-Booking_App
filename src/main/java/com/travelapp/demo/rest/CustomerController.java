package com.travelapp.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.Customer;
import com.travelapp.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(CustomerService theCustomerService) {
		customerService=theCustomerService;
	}

	
	@PostMapping("/register")
	public Customer customerRegistration(@RequestBody Customer theCustomer) {
		
		return customerService.register(theCustomer);
	}
	
	@GetMapping("{id}/bookings")
	public List<Booking> getBookings(@PathVariable int id){
		return customerService.getBookings(id);
	}
	
	
}
