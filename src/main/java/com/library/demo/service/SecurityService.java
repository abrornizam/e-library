package com.library.demo.service;

/**
 * @author ANIZAM
 *
 */

public interface SecurityService {

	String findLoggedInUsername();
    
	void autologin(String username, String password);
	
}
