package com.library.demo.controller;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.demo.model.Anggota;
import com.library.demo.service.AnggotaService;

@Controller
@RequestMapping("/perpus/anggota")
public class PerpusController {

	@Autowired
	private AnggotaService anggotaService;

	@RequestMapping(value = "/listAnggota", method = RequestMethod.GET)
	public String listAnggota(ModelMap model) {
		List<Anggota> anggota = anggotaService.findAll();
		model.addAttribute("anggota", anggota);
		return "/perpus/anggota/listAnggota";
	}

	@RequestMapping(value = "/addAnggota", method = RequestMethod.GET)
	public String addAnggota() {
		return "/perpus/anggota/addAnggota";
	}

	@RequestMapping(value = "/saveAnggota", method = RequestMethod.POST)
	public String saveAnggota(@Valid Anggota anggota, ModelMap model) {
		anggotaService.saveAnggota(anggota);		
		return "redirect:/perpus/anggota/listAnggota";
	}
	
	@RequestMapping(value = "/detail/{idanggota}", method = RequestMethod.GET)
	public String detailAnggota(@PathVariable String idanggota, ModelMap model) {
		Anggota anggota = anggotaService.findByIdanggota(idanggota);
		model.addAttribute("anggota", anggota);
		return "/perpus/anggota/detailAnggota";
	}
	
	@RequestMapping(value = "/edit/{idanggota}", method = RequestMethod.GET)
	public String editAnggota(@PathVariable String idanggota, ModelMap model) {
		Anggota anggota = anggotaService.findByIdanggota(idanggota);
		model.addAttribute("anggota", anggota);
		return "/perpus/anggota/editAnggota";
	}
	
	@RequestMapping(value = "/saveEditAnggota", method = RequestMethod.POST)
	public String saveEditAnggota(@Valid Anggota anggota, ModelMap model) {
		anggotaService.editAnggota(anggota);
		return "redirect:/perpus/anggota/listAnggota";
	}
	
	@RequestMapping(value = "/delete/{idanggota}", method = RequestMethod.GET)
	public String deleteAnggota(@PathVariable String idanggota) {
		anggotaService.deleteAnggota(idanggota);
		return "redirect:/perpus/anggota/listAnggota";
	}

}
