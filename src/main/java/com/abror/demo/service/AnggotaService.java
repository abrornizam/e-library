package com.abror.demo.service;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import com.abror.demo.model.Anggota;

public interface AnggotaService {

	List<Anggota> findAll();
	
	void saveAnggota(Anggota anggota);
	
	void editAnggota(Anggota anggota);
	
	void deleteAnggota(String idanggota);
	
	Anggota findByIdanggota(String idanggota);
	
}
