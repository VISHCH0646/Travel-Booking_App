package com.travelapp.demo.service;

import java.util.List;

import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.Customer;

public interface CustomerService {
	
	public Customer register(Customer theCustomer);

	public List<Booking> getBookings(int id);

}
