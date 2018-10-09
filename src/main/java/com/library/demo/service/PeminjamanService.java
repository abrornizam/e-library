package com.library.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.library.demo.model.Peminjaman;

public interface PeminjamanService {

	List<Peminjaman> findAll();
	
	Peminjaman savePeminjaman(Peminjaman peminjaman);
	
	Peminjaman findByKdpeminjaman(String kdpeminjaman);
	
	void acceptPeminjaman(String kdpeminjaman);
	
	void fundPeminjaman(String kdpeminjaman);
	
	int denda(String kdpeminjaman);
		
	void deletePeminjaman(String kdpeminjaman);
	
}
