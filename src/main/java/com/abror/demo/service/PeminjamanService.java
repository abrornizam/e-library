package com.abror.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;
import com.abror.demo.model.Peminjaman;

public interface PeminjamanService {

	List<Peminjaman> findAll();
	
	Peminjaman savePeminjaman(Peminjaman peminjaman);
	
	Peminjaman findByKdpeminjaman(String kdpeminjaman);
	
	void deletePeminjaman(String kdpeminjaman);
	
}
