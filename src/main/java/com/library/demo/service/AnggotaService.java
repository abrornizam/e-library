package com.library.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.library.demo.model.Anggota;

public interface AnggotaService {

	List<Anggota> findAll();
	
	Anggota saveAnggota(Anggota anggota);
	
	void editAnggota(Anggota anggota);
	
	void deleteAnggota(String idanggota);
	
	Anggota findByIdanggota(String idanggota);
	
}
