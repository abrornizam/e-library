package com.library.client.service;

/**
 * @author ANIZAM
 *
 */

public interface SecurityService {

	String findLoggedInUsername();
    
	void autologin(String username, String password);
	
}
