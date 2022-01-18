package com.travelapp.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.entity.ServiceProvider;
import com.travelapp.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService theAdminService) {
		adminService=theAdminService;
	}

	@PostMapping("/serviceproviders")
	public String registerServiceProvider(@RequestBody ServiceProvider theServiceProvider)
	{
		return adminService.registerServiceProvider(theServiceProvider);
	}
	
	@GetMapping("/serviceproviders")
	public List<ServiceProvider> getServiceProviders(){
		return adminService.getServiceProviders();
	}
	
	@GetMapping("/serviceproviders/{id}")
	public ServiceProvider getServiceProvider(@PathVariable int id){
		ServiceProvider sp=adminService.getServiceProvider(id).get();
		if(sp!=null)
		{
			return sp;
		}
		return null;
	}
	
	@PutMapping("/serviceproviders")
	public ServiceProvider updateServiceProvider(@RequestBody ServiceProvider theServiceProvider) {
		adminService.registerServiceProvider(theServiceProvider);
		return theServiceProvider;
	}
}
