/**
 * 
 */
package com.library.client.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.client.model.Book;
import com.library.client.model.Charge;
import com.library.client.model.Member;
import com.library.client.model.Transaction;
import com.library.client.service.BookService;
import com.library.client.service.ChargeService;
import com.library.client.service.MemberService;
import com.library.client.service.TransactionService;

/**
 * @author ANIZAM
 *
 */

@RestController
@RequestMapping("/rest/library/transaction")
public class TransactionRestController {

	@Autowired
	BookService bookService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ChargeService chargeService;
	
	@GetMapping(value = "/listTransaction")
	public @ResponseBody List<Transaction> listTransaction(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return transactionService.findAll();
	}
	
	@PostMapping(value = "/isAlreadyBorrow")
	public @ResponseBody Boolean isAlreadyBorrow(@RequestParam(value = "idmember") String idmember, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		Member member = memberService.findByIdmember(idmember);
		Boolean isBorrow = transactionService.isAlreadyBorrow(member.getId());
		if(isBorrow) {
			return true;
		}else {
			return false;
		}		
	}
	
	@PostMapping(value = "/save", headers="Accept=application/json")
	public @ResponseBody Transaction saveBook(@RequestBody Transaction transaction, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		Member member = memberService.findByIdmember(transaction.getMember().getIdmember());
    	Book book = bookService.findByIdbook(transaction.getBook().getIdbook());
    	transaction.setMember(member);
    	transaction.setBook(book);
		int totalDipinjam = transaction.getTotal();
    	int totalBuku = book.getAvailability();
    	totalBuku = totalBuku - totalDipinjam;
    	bookService.updateBookLeft(book.getIdbook(), totalBuku);
    	return transactionService.saveTransaction(transaction);
	}
	
	@GetMapping(value = "/detail/{idtransaction}")
	public @ResponseBody Transaction detail(@PathVariable String idtransaction, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return transactionService.findByIdtransaction(idtransaction);
	}	
	
	@GetMapping(value = "/approve/{idtransaction}")
	public @ResponseBody void approve(@PathVariable String idtransaction, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		transactionService.acceptTransaction(idtransaction);
		response.sendRedirect("/rest/library/transaction/detail/"+idtransaction);
	}	
	
	@GetMapping(value = "/charge/{idtransaction}")
	public @ResponseBody void denda(@PathVariable String idtransaction, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		Transaction trx = transactionService.findByIdtransaction(idtransaction);
		int chargeNomine = transactionService.charge(idtransaction);
		Charge charge = new Charge();
		charge.setTransaction(trx);
		charge.setNomine(chargeNomine);
		chargeService.saveCharge(charge);
    	response.sendRedirect("/rest/library/transaction/detail/"+idtransaction);
	}
	
	@GetMapping(value = "/fund/{idtransaction}")
	public @ResponseBody void fund(@PathVariable String idtransaction, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		transactionService.fundTransaction(idtransaction);
		int duration = transactionService.durationTransaction(idtransaction);
		if(duration > 5) {
			response.sendRedirect("/rest/library/transaction/charge/"+idtransaction);
		}else {
			response.sendRedirect("/rest/library/transaction/detail/"+idtransaction);
		}
	}
	
	@DeleteMapping(value = "/delete/{idtransaction}")
	public @ResponseBody void delete(@PathVariable String idtransaction, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		transactionService.deleteTransaction(idtransaction);
	}
	
}
