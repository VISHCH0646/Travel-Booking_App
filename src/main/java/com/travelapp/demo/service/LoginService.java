package com.travelapp.demo.service;

import com.travelapp.demo.dto.LoginModel;

public interface LoginService {
	
	public String adminLogin(LoginModel adminLoginModel);
	
	public String adminLogout(int id);
	
	public String serviceProviderLogin(LoginModel spoginModel);
	
	public String serviceProviderLogout(int id);

	public String customerLogin(LoginModel credentials);

	public String customerLogout(int id);
	
	
}
