package com.library.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.demo.model.Buku;
import com.library.demo.service.BukuService;

/**
 * @author ANIZAM
 *
 */

@Controller
public class IndexController {

	@Autowired
	private BukuService bukuService;
	
	@GetMapping(value = "/perpus/index")
	public String indexApp(ModelMap model) {
		List<Buku> listBuku = bukuService.findAll();
		model.addAttribute("buku", listBuku);
		return "/perpus/buku/listBuku";
	}
	
}
