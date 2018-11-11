/**
 * 
 */
package com.library.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.model.Buku;
import com.library.demo.repository.PengarangRepository;
import com.library.demo.service.BukuService;

/**
 * @author ANIZAM
 *
 */

@RestController
@RequestMapping("/rest/perpus/buku")
public class BukuRestController {

	@Autowired
	BukuService bukuService;
	
	@Autowired
	PengarangRepository pengarangRepository;
	
	@GetMapping(value = "/listBuku")
	public @ResponseBody List<Buku> listBuku(HttpServletResponse response) {
		response.setContentType("application/json");
    	response.setStatus(200);
		return bukuService.findAll();
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody Buku saveBuku(@RequestBody Buku buku, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return bukuService.saveBuku(buku);
	}
	
	@GetMapping(value = "/detail/{kdbuku}")
	public @ResponseBody Buku detailBuku(@PathVariable String kdbuku, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return bukuService.findByKode(kdbuku);
	}
	
	@PutMapping(value = "/edit/{kdbuku}")
	public @ResponseBody void editBuku(@RequestBody Buku buku, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		bukuService.updateBuku(buku);
	}
	
	@GetMapping(value = "/delete/{kdbuku}")
	public @ResponseBody void deleteBuku(@PathVariable String kdbuku, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		bukuService.deleteBuku(kdbuku);
	}
	
}
