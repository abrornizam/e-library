/**
 * 
 */
package com.library.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.demo.model.Denda;

/**
 * @author ANIZAM
 *
 */
public interface DendaRepository extends JpaRepository<Denda, Integer> {

	@Query(value = "select * from denda where status=true", nativeQuery = true)
	List<Denda> findAll();
	
	Denda findByKddenda(String kddenda);

	@Query(value = "select count(id) from denda", nativeQuery = true)
	int getTotalData();
	
	@Query(value = "select max(id) from denda", nativeQuery = true)
	int getSequenceId();
	
}
