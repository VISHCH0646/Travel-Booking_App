package com.travelapp.demo.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelapp.demo.dto.LoginModel;
import com.travelapp.demo.entity.Admin;
import com.travelapp.demo.entity.Customer;
import com.travelapp.demo.entity.ServiceProvider;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.AdminRepository;
import com.travelapp.demo.repository.CustomerRepository;
import com.travelapp.demo.repository.ServiceProviderRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	private AdminRepository adminRepo;
	
	private ServiceProviderRepository spRepo;
	
	private CustomerRepository customerRepo;

	@Autowired
	public LoginServiceImpl(AdminRepository theAdminRepo,
			ServiceProviderRepository thespRepo,
			CustomerRepository theCustomerRepo
			) {
		adminRepo=theAdminRepo;
		spRepo=thespRepo;
		customerRepo=theCustomerRepo;
	}
	
	@Override
	public String adminLogin(LoginModel adminLoginModel) {	
		Optional<Admin> tempAdmin=adminRepo.findById(adminLoginModel.getId());
		if(!tempAdmin.isPresent())
		{
			throw new CustomerNotFoundException("Admin Not found with id: "+adminLoginModel.getId());
		}
		if(tempAdmin.get().isAuthenticated())
			return "Admin Already Authenticated...";
		
		
		else {
			if((adminLoginModel.getPassword()).equals(tempAdmin.get().getPassword())) {
				Admin theAdmin=tempAdmin.get();
				theAdmin.setAuthenticated(true);
				adminRepo.save(theAdmin);
				return "Admin Authenticated Successfully";
			}
			else {
				//throw exception
				return "Password Mismatch";
			}
		}
		
	}

	@Override
	public String adminLogout(int id) {
		
		Optional<Admin> theAdmin=adminRepo.findById(id);
		
		if(theAdmin.isEmpty()) {
			//throw exception
			throw new CustomerNotFoundException("Admin Not found with id: "+id);
		}
		else {
			Admin tempAdmin=theAdmin.get();
			tempAdmin.setAuthenticated(false);
			adminRepo.save(tempAdmin);
			return "Admin Logout successful with id: "+id;
		}
	}

	@Override
	public String serviceProviderLogin(LoginModel sploginModel) {
		Optional<ServiceProvider> tempServiceProvider=spRepo.findById(sploginModel.getId());
		if(!tempServiceProvider.isPresent())
		{
			throw new CustomerNotFoundException("Service Provider Not Found with id: "+sploginModel.getId());
		}
		else {
			System.out.println(tempServiceProvider.get().getPassword());
			if((sploginModel.getPassword()).equals(tempServiceProvider.get().getPassword())) {
				ServiceProvider theServiceProvider=tempServiceProvider.get();
				theServiceProvider.setAuthenticated(true);
				spRepo.save(theServiceProvider);
				return "Authentication Successfull";
			}
			else {
				//throw exception
				return "Password Mismatch";
			}
		}
	}

	@Override
	public String serviceProviderLogout(int id) {
		Optional<ServiceProvider> theServiceProvider=spRepo.findById(id);
		
		if(!theServiceProvider.isPresent()) {
			//throw exception
			return "Service Provider Not found with id: "+id;
		}
		else {
			ServiceProvider tempServiceProvider=theServiceProvider.get();
			tempServiceProvider.setAuthenticated(false);
			spRepo.save(tempServiceProvider);
			return "Service Provider Logout successful with id: "+id;
		}
	}

	@Override
	public String customerLogin(LoginModel credentials) {
		Optional<Customer> tempCustomer=customerRepo.findById(credentials.getId());
		if(!tempCustomer.isPresent())
		{
			throw new CustomerNotFoundException("Customer id not found: "+credentials.getId());
		}
		else {
			System.out.println(tempCustomer.get().getPassword());
			if((credentials.getPassword()).equals(tempCustomer.get().getPassword())) {
				Customer theCustomer=tempCustomer.get();
				theCustomer.setAuthenticated(true);
				customerRepo.save(theCustomer);
				return "Authentication Successful";
			}
			else {
				//throw exception
				return "Password Mismatch";
			}
		}
	}

	@Override
	public String customerLogout(int id) {
		Optional<Customer> theCustomer=customerRepo.findById(id);
		
		if(!theCustomer.isPresent()) {
			//throw exception
			throw new CustomerNotFoundException("Customer id not found: "+id);
		}
		else {
			Customer tempCustomer=theCustomer.get();
			tempCustomer.setAuthenticated(false);
			customerRepo.save(tempCustomer);
			return "Customer Logout successful with id: "+id;
		}
	}

}
