/**
 * 
 */
package com.library.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.model.Anggota;
import com.library.demo.model.Buku;
import com.library.demo.model.Denda;
import com.library.demo.model.Peminjaman;
import com.library.demo.service.AnggotaService;
import com.library.demo.service.BukuService;
import com.library.demo.service.DendaService;
import com.library.demo.service.PeminjamanService;

/**
 * @author ANIZAM
 *
 */

@RestController
@RequestMapping("/rest/perpus/peminjaman")
public class PeminjamanRestController {

	@Autowired
	BukuService bukuService;
	
	@Autowired
	AnggotaService anggotaService;
	
	@Autowired
	PeminjamanService peminjamanService;
	
	@Autowired
	DendaService dendaService;
	
	@GetMapping(value = "/listPeminjaman")
	public @ResponseBody List<Peminjaman> listPeminjaman(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return peminjamanService.findAll();
	}
	
	@PostMapping(value = "/save")
	public @ResponseBody Peminjaman pinjamBuku(@RequestBody Peminjaman peminjaman, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		Anggota anggota = anggotaService.findByIdanggota(peminjaman.getAnggota().getIdanggota());
    	Buku buku = bukuService.findByKode(peminjaman.getBuku().getKdbuku());
    	peminjaman.setAnggota(anggota);
    	peminjaman.setBuku(buku);
    	Boolean isBorrow = peminjamanService.isAlreadyBorrow(peminjaman.getAnggota().getId());
		if(isBorrow == true) {	    	
			return new Peminjaman();
		}else {
			int totalDipinjam = peminjaman.getJumlah();
	    	int totalBuku = buku.getJumlah();
	    	totalBuku = totalBuku - totalDipinjam;
	    	bukuService.updateJumlahBuku(buku.getKdbuku(), totalBuku);
	    	return peminjamanService.savePeminjaman(peminjaman);
		}
	}
	
	@GetMapping(value = "/detail/{kdpeminjaman}")
	public @ResponseBody Peminjaman detail(@PathVariable String kdpeminjaman, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		return peminjamanService.findByKdpeminjaman(kdpeminjaman);
	}	
	
	@GetMapping(value = "/approve/{kdpeminjaman}")
	public @ResponseBody void approve(@PathVariable String kdpeminjaman, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		peminjamanService.acceptPeminjaman(kdpeminjaman);
		response.sendRedirect("/rest/perpus/peminjaman/detail/"+kdpeminjaman);
	}	
	
	@GetMapping(value = "/denda/{kdpeminjaman}")
	public @ResponseBody void denda(@PathVariable String kdpeminjaman, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		Peminjaman p = peminjamanService.findByKdpeminjaman(kdpeminjaman);
		int nominalDenda = peminjamanService.denda(kdpeminjaman);
		Denda denda = new Denda();
		denda.setPeminjaman(p);
    	denda.setNominal(nominalDenda);
    	dendaService.saveDenda(denda);
    	response.sendRedirect("/rest/perpus/peminjaman/detail/"+kdpeminjaman);
	}
	
	@GetMapping(value = "/fund/{kdpeminjaman}")
	public @ResponseBody void fund(@PathVariable String kdpeminjaman, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(200);
		peminjamanService.fundPeminjaman(kdpeminjaman);
		int lamaPinjam = peminjamanService.lamaPeminjaman(kdpeminjaman);
		if(lamaPinjam > 5) {
			response.sendRedirect("/rest/perpus/peminjaman/denda/"+kdpeminjaman);
		}else {
			response.sendRedirect("/rest/perpus/peminjaman/detail/"+kdpeminjaman);
		}
	}
	
	@DeleteMapping(value = "/delete/{kdpeminjaman}")
	public @ResponseBody void delete(@PathVariable String kdpeminjaman, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(200);
		peminjamanService.deletePeminjaman(kdpeminjaman);
	}
	
}
