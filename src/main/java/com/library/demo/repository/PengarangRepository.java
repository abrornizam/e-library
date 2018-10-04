package com.library.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.demo.model.Pengarang;

@Repository
public interface PengarangRepository extends JpaRepository<Pengarang, Integer>{
	
	
}
