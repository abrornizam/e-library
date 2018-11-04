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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.demo.model.Anggota;
import com.library.demo.model.Buku;
import com.library.demo.model.Denda;
import com.library.demo.model.Peminjaman;
import com.library.demo.repository.PeminjamanRepository;
import com.library.demo.repository.PengarangRepository;
import com.library.demo.service.AnggotaService;
import com.library.demo.service.BukuService;
import com.library.demo.service.DendaService;
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
	DendaService dendaService;
	
	@Autowired
	PeminjamanRepository peminjamanRepository;
	
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
    	List<Anggota> anggota = anggotaService.findAll();
    	model.addAttribute("buku", buku);
    	model.addAttribute("anggota", anggota);
    	return "/perpus/buku/pinjamBuku";
    }
    
    @RequestMapping(value = "/savePinjamBuku", method = RequestMethod.POST)
    public String savePinjamBuku(@Valid Peminjaman peminjaman, BindingResult result, RedirectAttributes red, ModelMap model) {
    	Anggota anggota = anggotaService.findByIdanggota(peminjaman.getAnggota().getIdanggota());
    	Buku buku = bukuService.findByKode(peminjaman.getBuku().getKdbuku());
    	peminjaman.setAnggota(anggota);
    	peminjaman.setBuku(buku);
    	Boolean isBorrow = peminjamanService.isAlreadyBorrow(peminjaman.getAnggota().getId());
		if(result.getErrorCount() > 0 && isBorrow == true) {
	    	red.addFlashAttribute("msg", isBorrow);
	    	model.addAttribute("msg", isBorrow);
			return "redirect:/perpus/peminjaman/pinjamBuku/"+buku.getKdbuku();
		}else {
			peminjamanService.savePeminjaman(peminjaman);
			int totalDipinjam = peminjaman.getJumlah();
	    	int totalBuku = buku.getJumlah();
	    	totalBuku = totalBuku - totalDipinjam;
	    	bukuService.updateJumlahBuku(buku.getKdbuku(), totalBuku);	
		}
		return "redirect:/perpus/buku/listBuku";		
    }
    
    @RequestMapping(value = "/detailPeminjaman/{kdpeminjaman}", method = RequestMethod.GET)
    public String detailPeminjaman(@PathVariable String kdpeminjaman, ModelMap model) {
    	Peminjaman p = peminjamanService.findByKdpeminjaman(kdpeminjaman);
    	model.addAttribute("peminjaman", p);
    	return "/perpus/peminjaman/detailPeminjaman";
    }
	
    @RequestMapping(value = "/deletePeminjaman/{kdpeminjaman}", method = RequestMethod.GET)
    public String deletePeminjaman(@PathVariable String kdpeminjaman) {
    	peminjamanService.deletePeminjaman(kdpeminjaman);
    	return "redirect:/perpus/peminjaman/listPeminjaman";
    }
    
    @RequestMapping(value = "/approve/{kdpeminjaman}", method = RequestMethod.GET)
    public String approvePeminjaman(@PathVariable String kdpeminjaman) {
    	peminjamanService.acceptPeminjaman(kdpeminjaman);
    	return "redirect:/perpus/peminjaman/listPeminjaman";
    }
    
    @RequestMapping(value = "/fund/{kdpeminjaman}", method = RequestMethod.GET)
    public String fundPeminjaman(@PathVariable String kdpeminjaman) {
    	peminjamanService.fundPeminjaman(kdpeminjaman);
    	int lamaPinjam = peminjamanService.lamaPeminjaman(kdpeminjaman);
    	if(lamaPinjam > 5) {
    		return "redirect:/perpus/peminjaman/denda/"+kdpeminjaman;
    	}
    	return "redirect:/perpus/peminjaman/listPeminjaman";
    }
    
    @RequestMapping(value = "/denda/{kdpeminjaman}", method = RequestMethod.GET)
    public String dendaPeminjaman(@Valid Denda denda, @PathVariable String kdpeminjaman) {
    	Peminjaman p = peminjamanService.findByKdpeminjaman(kdpeminjaman);
    	int nominalDenda = peminjamanService.denda(kdpeminjaman);
    	denda.setPeminjaman(p);
    	denda.setNominal(nominalDenda);
    	dendaService.saveDenda(denda);
    	return "redirect:/perpus/peminjaman/listPeminjaman";
    }
    
}
