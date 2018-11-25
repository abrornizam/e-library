/**
 * 
 */
package com.library.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ANIZAM
 *
 */

@Controller
@RequestMapping("/perpus/guest")
public class GuestController {

	@GetMapping(value = {"", "/", "/home", "/dasboard"})
	public String listBuku() {
		return "perpus/guest/home";
	}
	
	@GetMapping(value = "/detail/buku/{kdbuku}")
	public String detailBuku() {
		return "perpus/guest/bookDetail";
	}
}
