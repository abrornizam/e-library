package com.abror.demo.service.impl;

/**
 * @author ANIZAM
 *
 */

import com.abror.demo.model.User;
import com.abror.demo.repository.RoleRepository;
import com.abror.demo.repository.UserRepository;
import com.abror.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
