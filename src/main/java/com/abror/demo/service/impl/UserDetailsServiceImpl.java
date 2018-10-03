package com.abror.demo.service.impl;

/**
 * @author ANIZAM
 *
 */

import java.util.HashSet;
import java.util.Set;

//import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abror.demo.model.Role;
import com.abror.demo.model.User;
import com.abror.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username);
        
    	if(user == null) {
        	System.out.println("User not found '" + user + "' in database");
        	throw new UsernameNotFoundException("User " + user + " not found in database");
        }        
        System.out.println("Found user : " + user);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));            
        }
        
//        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        
//        return userDetails;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles());
    }
}
