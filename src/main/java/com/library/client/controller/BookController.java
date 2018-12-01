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
import org.springframework.web.bind.annotation.RequestParam;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.service.AuthorService;
import com.library.client.service.BookService;

@Controller
@RequestMapping("/library/book")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/listBook", method = RequestMethod.GET)
	public String listBook(ModelMap model) {
		List<Book> book = bookService.findAll();
		model.addAttribute("book", book);
		return "library/book/listBook";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String addBook(ModelMap model) {
		List<Author> author = authorService.findAll();
		model.addAttribute("author", author);
		return "library/book/addBook";
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult result,  ModelMap model) throws Exception {
		bookService.saveBook(book);
		return "redirect:/library/book/listBook";
	}

	// Testing AJAX

	@RequestMapping(value = "/year", method = RequestMethod.GET)
	public String yearBook(@RequestParam(value = "year", required = false, defaultValue = "2018") String year, ModelMap model) {
		Book book = bookService.findByYear(year);
		model.addAttribute("book", book);
		return "/library/book/year";
	}

	// END OF AJAX

	@RequestMapping(value = "/detail/{idbook}", method = RequestMethod.GET)
	public String detailBook(@PathVariable String idbook, ModelMap model) {
		Book book = bookService.findByIdbook(idbook);
		model.addAttribute("book", book);
		return "/library/book/detailBook";
	}

	@RequestMapping(value = "/edit/{idbook}", method = RequestMethod.GET)
	public String editBook(@PathVariable String idbook, ModelMap model) {
		Book book = bookService.findByIdbook(idbook);
		List<Author> author = authorService.findAll();
		model.addAttribute("book", book);
		model.addAttribute("author", author);		
		return "/library/book/editBook";
	}

	@RequestMapping(value = "/saveEditBook", method = RequestMethod.POST)
	public String saveEditBook(@Valid Book book, ModelMap model) {
		bookService.updateBook(book);
		return "redirect:/library/book/listBook";
	}

	@RequestMapping(value = "/delete/{idbook}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable String idbook, ModelMap model) {
		bookService.deleteBook(idbook);
		return "redirect:/library/book/listBook";
	}
	
}
