package com.library.client.repository;

/**
 * @author ANIZAM
 *
 */
import java.util.List;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.client.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query(value = "select * from transaction where status=true", nativeQuery=true)
	List<Transaction> findAll();
	
	Transaction findById(int id);
	
	Transaction findByIdtransaction(String idtransaction);
	
	@Query(value = "select count(id) from transaction", nativeQuery = true)
	int getTotalData();
	
	@Query(value = "select max(id) from transaction", nativeQuery=true)
	int getSequenceId();

}
