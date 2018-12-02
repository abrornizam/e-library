/**
 * 
 */
package com.library.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ANIZAM
 *
 */

@Controller
@RequestMapping("/library/guest")
public class GuestController {

	@GetMapping(value = {"", "/", "/home", "/dasboard"})
	public String listBuku() {
		return "library/guest/home";
	}
	
	@GetMapping(value = "/detail/book/{idbook}")
	public String detailBuku() {
		return "library/guest/bookDetail";
	}
	
	@GetMapping(value = "/borrow/{idbook}")
	public String transaction() {
		return "library/guest/bookBorrow";
	}
}
