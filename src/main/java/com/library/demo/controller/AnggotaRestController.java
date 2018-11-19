package com.library.demo.controller;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.model.Anggota;
import com.library.demo.service.AnggotaService;

@RestController
@RequestMapping("/rest/perpus/anggota")
public class AnggotaRestController {

	@Autowired
	AnggotaService anggotaService;
	
	@GetMapping(value = "/listAnggota")
	public @ResponseBody List<Anggota> listAnggota(HttpServletResponse response)  {
    	response.setContentType("application/json");
    	response.setStatus(200);
    	return anggotaService.findAll();
    }
	
	@PostMapping(value = "/save")
	public @ResponseBody void saveAnggota(@RequestBody Anggota anggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		anggotaService.saveAnggota(anggota);
	}
	
	@GetMapping(value = "/detail/{idanggota}")
	public @ResponseBody Anggota detailAnggota(@PathVariable String idanggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return anggotaService.findByIdanggota(idanggota);
	}
	
	@PutMapping(value = "/edit/{idanggota}")
	public @ResponseBody void editAnggota(@RequestBody Anggota anggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		anggotaService.editAnggota(anggota);
	}
	
	@DeleteMapping(value = "/delete/{idanggota}")
	public @ResponseBody void deleteAnggota(@PathVariable String idanggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		anggotaService.deleteAnggota(idanggota);
	}
	
}
