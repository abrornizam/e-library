package com.abror.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.abror.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
