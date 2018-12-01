package com.library.client.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.library.client.model.Transaction;

public interface TransactionService {

	List<Transaction> findAll();
	
	Transaction saveTransaction(Transaction transaction);
	
	Transaction findByIdtransaction(String idtransaction);
	
	void acceptTransaction(String idtransaction);
	
	void fundTransaction(String idtransaction);
		
	void deleteTransaction(String idtransaction);
	
	boolean isAlreadyBorrow(int idmember);
	
	int durationTransaction(String idtransaction);
	
	int charge(String kdpeminjaman);
	
}
