package com.travelapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelapp.demo.entity.ServiceProvider;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.AdminRepository;
import com.travelapp.demo.repository.ServiceProviderRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;
	
	private ServiceProviderRepository serviceProviderRepo;
	
	@Autowired
	public AdminServiceImpl(ServiceProviderRepository theServiceProviderRepo,AdminRepository theAdminRepository) {
		serviceProviderRepo=theServiceProviderRepo;
		adminRepository=theAdminRepository;
	}

	@Override
	public String registerServiceProvider(ServiceProvider theServiceProvider) {
		if(adminRepository.getById(1).isAuthenticated()) {
			serviceProviderRepo.save(theServiceProvider);
			return "Service Provider Registered...\nName: "+theServiceProvider.getName()+"\nID: "+theServiceProvider.getId()+"\nPassword: "+theServiceProvider.getPassword();
		}
		else
		{
			//throw an exception
			throw new CustomerNotFoundException("Admin Not Authenticated");
		}
	}

	@Override
	public List<ServiceProvider> getServiceProviders() {
		if(adminRepository.getById(1).isAuthenticated()) {
			return serviceProviderRepo.findAll();
		}
		else
		{
			//throw an exception
			throw new CustomerNotFoundException("Admin Not Authenticated");
		}
	}

	@Override
	public Optional<ServiceProvider> getServiceProvider(int id) {
		if(adminRepository.getById(1).isAuthenticated()) {
			return serviceProviderRepo.findById(id);
		}
		else
		{
			throw new CustomerNotFoundException("Admin Not Authenticated with id: "+id);
		}
	}

}
