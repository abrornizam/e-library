package com.library.client.repository;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.client.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query(value = "select * from book where status=true", nativeQuery = true)
	List<Book> findAll();

	Book findById(int id);
	
	Book findByIdbook(String bookid);

	Book findByYear(String year);
	
	@Query(value = "select count(id) from book", nativeQuery = true)
	int getTotalData();
	
	@Query(value = "select max(id) from book", nativeQuery = true)
	int getSequenceId();
}
