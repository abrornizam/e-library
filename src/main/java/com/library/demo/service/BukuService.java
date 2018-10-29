package com.library.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.library.demo.model.Buku;

public interface BukuService {

	List<Buku> findAll();
	
	Buku saveBuku(Buku buku);
	
	void updateBuku(Buku buku);
	
	void deleteBuku(String kdbuku);
	
	Buku findByKode(String kdbuku);
	
	void updateJumlahBuku(String kdbuku, int jumlah);
	
//	Testing Ajax
	
	Buku findByTahun(String tahun);
}