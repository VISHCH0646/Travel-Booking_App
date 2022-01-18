package com.travelapp.demo.service;

import java.util.List;
import java.util.Optional;

import com.travelapp.demo.entity.ServiceProvider;

public interface AdminService {

	public String registerServiceProvider(ServiceProvider theServiceProvider);
	public List<ServiceProvider> getServiceProviders();
	
	public Optional<ServiceProvider> getServiceProvider(int id);
	
	
}
