package com.library.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
