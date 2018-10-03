package com.abror.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.abror.demo.model.Buku;

public interface BukuService {

	List<Buku> findAll();
	
	void saveBuku(Buku buku);
	
	void updateBuku(Buku buku);
	
	void deleteBuku(String kdbuku);
	
	Buku findByKode(String kdbuku);
	
	void updateJumlahBuku(String kdbuku, int jumlah);
	
//	Testing Ajax
	
	Buku findByTahun(String tahun);
}