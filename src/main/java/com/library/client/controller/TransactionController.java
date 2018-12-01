package com.library.client.controller;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.client.model.Book;
import com.library.client.model.Charge;
import com.library.client.model.Member;
import com.library.client.model.Transaction;
import com.library.client.service.BookService;
import com.library.client.service.ChargeService;
import com.library.client.service.MemberService;
import com.library.client.service.TransactionService;

@Controller
@RequestMapping("/library/transaction")
public class TransactionController {

	@Autowired
	BookService bookService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ChargeService chargeService;
	
	@RequestMapping(value = "/listTransaction", method = RequestMethod.GET)
    public String listTransaction(ModelMap model) {
    	List<Transaction> transaction = transactionService.findAll();
    	model.addAttribute("transaction", transaction);
    	return "/library/transaction/listTransaction";
    }
    
    @RequestMapping(value = "/borrow/{idbook}", method = RequestMethod.GET)
    public String borrowBook(@PathVariable String idbook, ModelMap model) {
    	Book book = bookService.findByIdbook(idbook);
    	List<Member> member = memberService.findAll();
    	model.addAttribute("book", book);
    	model.addAttribute("member", member);
    	return "/library/book/borrowBook";
    }
    
    @RequestMapping(value = "/saveBorrow", method = RequestMethod.POST)
    public String saveBorrow(@Valid Transaction transaction, BindingResult result, RedirectAttributes red, ModelMap model) {
    	Member member = memberService.findByIdmember(transaction.getMember().getIdmember());
    	Book book = bookService.findByIdbook(transaction.getBook().getIdbook());
    	transaction.setMember(member);
    	transaction.setBook(book);
    	Boolean isBorrow = transactionService.isAlreadyBorrow(transaction.getMember().getId());
		if(result.getErrorCount() > 0 && isBorrow == true) {
	    	red.addFlashAttribute("msg", isBorrow);
	    	model.addAttribute("msg", isBorrow);
			return "redirect:/library/transaction/borrow/"+book.getIdbook();
		}else {
			transactionService.saveTransaction(transaction);
			int totalDipinjam = transaction.getTotal();
	    	int totalBuku = book.getAvailability();
	    	totalBuku = totalBuku - totalDipinjam;
	    	bookService.updateBookLeft(book.getIdbook(), totalBuku);	
		}
		return "redirect:/library/book/listBook";		
    }
    
    @RequestMapping(value = "/detail/{idtransaction}", method = RequestMethod.GET)
    public String detail(@PathVariable String idtransaction, ModelMap model) {
    	Transaction trx = transactionService.findByIdtransaction(idtransaction);
    	model.addAttribute("transaction", trx);
    	return "/library/transaction/detailTransaction";
    }
	
    @RequestMapping(value = "/delete/{idtransaction}", method = RequestMethod.GET)
    public String delete(@PathVariable String idtransaction) {
    	transactionService.deleteTransaction(idtransaction);
    	return "redirect:/library/transaction/listTransaction";
    }
    
    @RequestMapping(value = "/approve/{idtransaction}", method = RequestMethod.GET)
    public String approve(@PathVariable String idtransaction) {
    	transactionService.acceptTransaction(idtransaction);
    	return "redirect:/library/transaction/listTransaction";
    }
    
    @RequestMapping(value = "/fund/{idtransaction}", method = RequestMethod.GET)
    public String fund(@PathVariable String idtransaction) {
    	transactionService.fundTransaction(idtransaction);
    	int duration = transactionService.durationTransaction(idtransaction);
    	if(duration > 5) {
    		return "redirect:/library/transaction/charge/"+idtransaction;
    	}
    	return "redirect:/library/transaction/listTransaction";
    }
    
    @RequestMapping(value = "/charge/{idtransaction}", method = RequestMethod.GET)
    public String charge(@Valid Charge charge, @PathVariable String idtransaction) {
    	Transaction trx = transactionService.findByIdtransaction(idtransaction);
    	int chargeNomine = transactionService.charge(idtransaction);
    	charge.setTransaction(trx);
    	charge.setNomine(chargeNomine);
    	chargeService.saveCharge(charge);
    	return "redirect:/library/transaction/listTransaction";
    }
    
}
