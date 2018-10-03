package com.abror.demo.service;

/**
 * @author ANIZAM
 *
 */

public interface SecurityService {

	String findLoggedInUsername();
    
	void autologin(String username, String password);
	
}
