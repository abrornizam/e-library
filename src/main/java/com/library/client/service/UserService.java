package com.library.client.service;

import com.library.client.model.User;

public interface UserService {

	void save(User user);
    
	User findByUsername(String username);
	
}
