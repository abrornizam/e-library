package com.library.client.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.client.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
