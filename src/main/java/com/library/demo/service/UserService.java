package com.library.demo.service;

import com.library.demo.model.User;

public interface UserService {

	void save(User user);
    
	User findByUsername(String username);
	
}
