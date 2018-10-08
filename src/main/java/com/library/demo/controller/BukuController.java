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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.demo.model.Buku;
import com.library.demo.model.Pengarang;
import com.library.demo.repository.PengarangRepository;
import com.library.demo.service.BukuService;

@Controller
@RequestMapping("/perpus/buku")
public class BukuController {

	@Autowired
	BukuService bukuService;

	@Autowired
	PengarangRepository pengarangRepository;

	@RequestMapping(value = "/listBuku", method = RequestMethod.GET)
	public String listBuku(ModelMap model) {
		List<Buku> buku = bukuService.findAll();
		model.addAttribute("buku", buku);
		return "perpus/buku/listBuku";
	}

	@RequestMapping(value = "/addBuku", method = RequestMethod.GET)
	public String addBuku(ModelMap model) {
		List<Pengarang> p = pengarangRepository.findAll();
		model.addAttribute("pengarang", p);
		return "perpus/buku/addBuku";
	}

	@RequestMapping(value = "/saveBuku", method = RequestMethod.POST)
	public String saveBuku(@Valid Buku buku, BindingResult result, ModelMap model) {
		if (result.getErrorCount() > 0) {
			System.out.println("ERROR MAS BRO!");
		}
		bukuService.saveBuku(buku);
		return "redirect:/perpus/buku/listBuku";
	}

	// Testing AJAX

	@RequestMapping(value = "/tahun", method = RequestMethod.GET)
	public String tahunBuku(@RequestParam(value = "tahun", required = false, defaultValue = "2018") String tahun,
			ModelMap model) {
		Buku buku = bukuService.findByTahun(tahun);
		model.addAttribute("buku", buku);
		return "/perpus/buku/tahun";
	}

	// END OF AJAX

	@RequestMapping(value = "/detail/{kdbuku}", method = RequestMethod.GET)
	public String detailBuku(@PathVariable String kdbuku, ModelMap model) {
		Buku buku = bukuService.findByKode(kdbuku);
		model.addAttribute("buku", buku);
		return "/perpus/buku/detailBuku";
	}

	@RequestMapping(value = "/edit/{kdbuku}", method = RequestMethod.GET)
	public String editBuku(@PathVariable String kdbuku, ModelMap model) {
		Buku buku = bukuService.findByKode(kdbuku);
		model.addAttribute("pengarang", pengarangRepository.findAll());
		model.addAttribute("buku", buku);
		return "/perpus/buku/editBuku";
	}

	@RequestMapping(value = "/saveEditBuku", method = RequestMethod.POST)
	public String saveEditBuku(@Valid Buku buku, ModelMap model) {
		bukuService.updateBuku(buku);
		return "redirect:/perpus/buku/listBuku";
	}

	@RequestMapping(value = "/delete/{kdbuku}", method = RequestMethod.GET)
	public String deleteBuku(@PathVariable String kdbuku, ModelMap model) {
		bukuService.deleteBuku(kdbuku);
		return "redirect:/perpus/buku/listBuku";
	}
	
}
