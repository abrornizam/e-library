package com.library.client.repository;

import java.util.List;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.client.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	List<Author> findAll();
	
}
