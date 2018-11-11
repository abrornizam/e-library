package com.library.demo.controller;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.model.Anggota;
import com.library.demo.service.AnggotaService;

@RestController
@RequestMapping("/rest/perpus/anggota")
public class AnggotaController {

	@Autowired
	AnggotaService anggotaService;
	
	@RequestMapping(value = "/listAnggota", method = RequestMethod.GET)
    public @ResponseBody List<Anggota> listAnggota(HttpServletResponse response) {
    	response.setContentType("application/json");
    	response.setStatus(200);
    	return anggotaService.findAll();
    }
	
	@RequestMapping(value = "/saveAnggota", method = RequestMethod.POST)
	public @ResponseBody void saveAnggota(@RequestBody Anggota anggota, HttpServletResponse response) {
		response.setContentType("application/json");
    	response.setStatus(200);
		anggotaService.saveAnggota(anggota);
	}
	
	@RequestMapping(value = "/detail/{idanggota}", method = RequestMethod.GET)
	public @ResponseBody void detailAnggota(@PathVariable String idanggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		anggotaService.findByIdanggota(idanggota);
	}
	
	@RequestMapping(value = "/delete/{idanggota}", method = RequestMethod.GET)
	public @ResponseBody void deleteAnggota(@Valid Anggota anggota, @PathVariable String idanggota, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		anggotaService.deleteAnggota(anggota.getIdanggota());
	}
	
}
