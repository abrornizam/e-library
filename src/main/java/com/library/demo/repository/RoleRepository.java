package com.library.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
