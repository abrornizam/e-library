package com.library.client.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.library.client.model.Book;
import com.library.client.service.BookService;

/**
 * @author ANIZAM
 *
 */

@Controller
public class IndexController extends ResponseEntityExceptionHandler {

	@Autowired
	private BookService bookService;
	
	@GetMapping(value = {"/library", "/library/index"})
	public String indexApp(ModelMap model) {
		List<Book> listBook = bookService.findAll();
		model.addAttribute("book", listBook);
		return "/library/book/listBook";
	}
		
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request) throws Exception {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			int statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/error-404";
			}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error/error-500";
			}else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
				return "error/error-400";
			}
		}
		return "error";
	}
	
}
