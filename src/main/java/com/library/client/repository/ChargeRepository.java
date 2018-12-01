/**
 * 
 */
package com.library.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.client.model.Charge;

/**
 * @author ANIZAM
 *
 */
public interface ChargeRepository extends JpaRepository<Charge, Integer> {

	@Query(value = "select * from charge where status=true", nativeQuery = true)
	List<Charge> findAll();
	
	Charge findByIdcharge(String idcharge);

	@Query(value = "select count(id) from charge", nativeQuery = true)
	int getTotalData();
	
	@Query(value = "select max(id) from charge", nativeQuery = true)
	int getSequenceId();
	
}
