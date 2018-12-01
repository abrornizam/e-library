package com.library.client.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.client.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
