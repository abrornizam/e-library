package com.abror.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.abror.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
