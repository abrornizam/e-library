package com.abror.demo.service;

/**
 * @author ANIZAM
 *
 */

import com.abror.demo.model.User;

public interface UserService {

	void save(User user);
    
	User findByUsername(String username);
	
}
