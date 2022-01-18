package com.travelapp.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.dto.LoginModel;
import com.travelapp.demo.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private LoginService loginService;
	
	
	@Autowired
	public LoginController(LoginService theLoginService) {
		loginService=theLoginService;
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}
	
	@PostMapping("/admin")
	public String adminLogin(@RequestBody LoginModel credentials) {
		
		return loginService.adminLogin(credentials);
	}
	
	@GetMapping("{id}/adminlogout")
	public String adminLogout(@PathVariable int id) {
		return loginService.adminLogout(id);	
	}
	
	@PostMapping("/serviceProvider")
	public String serviceProviderLogin(@RequestBody LoginModel credentials) {
		
		return loginService.serviceProviderLogin(credentials);
	}
	
	@GetMapping("{id}/serviceProviderlogout")
	public String serviceProviderLogout(@PathVariable int id) {
		return loginService.serviceProviderLogout(id);	
	}
	
	@PostMapping("/customer")
	public String customerLogin(@RequestBody LoginModel credentials) {
		
		return loginService.customerLogin(credentials);
	}
	
	@GetMapping("{id}/customerlogout")
	public String customerLogout(@PathVariable int id) {
		return loginService.customerLogout(id);	
	}
}
