package com.library.client.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.client.model.Transaction;
import com.library.client.repository.BookRepository;
import com.library.client.repository.MemberRepository;
import com.library.client.repository.TransactionRepository;
import com.library.client.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	MemberRepository memberRepository;

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		int totalData = transactionRepository.getTotalData();
		int seqId = 0;	
		if(totalData == 0) {
			seqId = 1;
		}else {
			seqId = transactionRepository.getSequenceId()+1;
		}
		transaction.setId(seqId);
    	String idtransaction = Integer.valueOf(seqId).toString();
    	transaction.setIdtransaction("TRX".concat(idtransaction));
    	Date tgl_pinjam = new Date();
		transaction.setBorrow_date(tgl_pinjam);
		transaction.setTrx_status("IN PROGRESS");
		transaction.setStatus(true);
		return transactionRepository.save(transaction);
	}	

	@Override
	public Transaction findByIdtransaction(String idtransaction) {
		// TODO Auto-generated method stub
		return transactionRepository.findByIdtransaction(idtransaction);
	}

	@Override
	public void deleteTransaction(String idtransaction) {
		// TODO Auto-generated method stub
		Transaction entity = transactionRepository.findByIdtransaction(idtransaction);
		if(entity != null) {
			Date return_date = new Date();
			entity.setStatus(false);
			entity.setReturn_date(return_date);			
		}
	}

	@Override
	public void acceptTransaction(String idtransaction) {
		// TODO Auto-generated method stub
		Transaction entity = transactionRepository.findByIdtransaction(idtransaction);
		if(entity.getTrx_status().equals("IN PROGRESS")) {
			entity.setTrx_status("APPROVED");
		}
	}

	@Override
	public void fundTransaction(String idtransaction) {
		// TODO Auto-generated method stub
		Transaction entity = transactionRepository.findByIdtransaction(idtransaction);
		if(entity.getTrx_status().equals("APPROVED")) {
			entity.setTrx_status("FINISH");
			Date return_date = new Date();
			entity.setReturn_date(return_date);
		}
	}

	@Override
	public boolean isAlreadyBorrow(int idmember) {
		// TODO Auto-generated method stub
		int idfound = 0;
		String status = "";
		List<Transaction> listTransaction = transactionRepository.findAll();
		for(Transaction transaction : listTransaction) {
			idfound = transaction.getMember().getId();
			status = transaction.getTrx_status();
			if(idmember == idfound && !status.equalsIgnoreCase("FINISH")) {
				break;
			}
		}
		
		if(idmember == idfound && !status.equalsIgnoreCase("FINISH")) {
			return true;
		}else {
			return false;
		}
	}	
	
	@Override
	public int durationTransaction(String idtransaction) {
		// TODO Auto-generated method stub
		Transaction p = transactionRepository.findByIdtransaction(idtransaction);
		Date borrowDate = p.getBorrow_date();
		Date returnDate = p.getReturn_date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String trxDate = dateFormat.format(borrowDate);
		String returnTrxDate = dateFormat.format(returnDate);
		int tglP = Integer.parseInt(trxDate.substring(8, 10));
		int bulanP = Integer.parseInt(trxDate.substring(5, 7));
		int tahunP = Integer.parseInt(trxDate.substring(0, 4));
		int tglK = Integer.parseInt(returnTrxDate.substring(8, 10));
		int bulanK = Integer.parseInt(returnTrxDate.substring(5, 7));
		int tahunK = Integer.parseInt(returnTrxDate.substring(0, 4));
		int hari = tglK - tglP;
		int bulan = (bulanK - bulanP) * 30;
		int tahun = (tahunK - tahunP) * 365;
		int durationTransaction = hari + bulan + tahun;
		return durationTransaction;
	}
	
	@Override
	public int charge(String idtransaction) {
		// TODO Auto-generated method stub
		int chargeNomine = 0;
		int duration = durationTransaction(idtransaction);
		if(duration > 5) {			
			chargeNomine = 5000;
		}else if(duration > 10) {
			chargeNomine = 10000;
		}else {
			chargeNomine = 25000;
		}
		return chargeNomine;
	}

}
