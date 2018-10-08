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
import com.library.demo.model.Buku;
import com.library.demo.model.Peminjaman;
import com.library.demo.repository.PengarangRepository;
import com.library.demo.service.AnggotaService;
import com.library.demo.service.BukuService;
import com.library.demo.service.PeminjamanService;

@Controller
@RequestMapping("/perpus/peminjaman")
public class PeminjamanController {

	@Autowired
	BukuService bukuService;
	
	@Autowired
	AnggotaService anggotaService;
	
	@Autowired
	PeminjamanService peminjamanService;
	
	@Autowired
	PengarangRepository pengarangRepository;
	
	@RequestMapping(value = "/listPeminjaman", method = RequestMethod.GET)
    public String listPeminjaman(ModelMap model) {
    	List<Peminjaman> peminjaman = peminjamanService.findAll();
    	model.addAttribute("peminjaman", peminjaman);
    	return "/perpus/peminjaman/listPeminjaman";
    }
    
    @RequestMapping(value = "/pinjamBuku/{kdbuku}", method = RequestMethod.GET)
    public String pinjamBuku(@PathVariable String kdbuku, ModelMap model) {
    	Buku buku = bukuService.findByKode(kdbuku);
    	List<Peminjaman> peminjaman = peminjamanService.findAll();
    	List<Anggota> anggota = anggotaService.findAll();
    	model.addAttribute("buku", buku);
    	model.addAttribute("anggota", anggota);
    	model.addAttribute("peminjaman", peminjaman);
    	return "/perpus/buku/pinjamBuku";
    }
    
    @RequestMapping(value = "/savePinjamBuku", method = RequestMethod.POST)
    public String savePinjamBuku(@Valid Peminjaman peminjaman, ModelMap model) {
    	Peminjaman p = peminjamanService.savePeminjaman(peminjaman);
    	Buku b = bukuService.findByKode(peminjaman.getBuku().getKdbuku());
    	int totalDipinjam = p.getJumlah();
    	int totalBuku = b.getJumlah();
    	totalBuku = totalBuku - totalDipinjam;
    	bukuService.updateJumlahBuku(b.getKdbuku(), totalBuku);
    	return "redirect:/perpus/buku/listBuku";
    } 
    
    @RequestMapping(value = "/detailPeminjaman/{kdpeminjaman}", method = RequestMethod.GET)
    public String detailPeminjaman(@PathVariable String kdpeminjaman, ModelMap model) {
    	Peminjaman p = peminjamanService.findByKdpeminjaman(kdpeminjaman);
    	model.addAttribute("peminjaman", p);
    	return "/perpus/peminjaman/detailPeminjaman";
    }
	
    @RequestMapping(value = "/deletePeminjaman/{kdpeminjaman}", method = RequestMethod.GET)
    public String deletePeminjaman(@PathVariable String kdpeminjaman, ModelMap model) {
    	peminjamanService.deletePeminjaman(kdpeminjaman);
    	return "redirect:/perpus/peminjaman/listPeminjaman";
    }
    
}
