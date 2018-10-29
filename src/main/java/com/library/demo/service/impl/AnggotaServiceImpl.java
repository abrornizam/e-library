package com.library.demo.service.impl;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.demo.model.Anggota;
import com.library.demo.repository.AnggotaRepository;
import com.library.demo.service.AnggotaService;

@Service
@Transactional
public class AnggotaServiceImpl implements AnggotaService {

	private AnggotaRepository anggotaRepository;
	
	@Autowired
	public void setAnggotaRepository(AnggotaRepository anggotaRepository) {
		this.anggotaRepository = anggotaRepository;
	}
	
	@Override
	public List<Anggota> findAll() {
		// TODO Auto-generated method stub
		return anggotaRepository.findAll();
	}

	@Override
	public Anggota saveAnggota(Anggota anggota) {
		// TODO Auto-generated method stub
		anggota.setIdanggota("USR"+anggota.getId());
		Date tgl = new Date();
		anggota.setStatus(true);
		anggota.setTgl_join(tgl);
		return anggotaRepository.save(anggota);		
	}
	
	@Override
	public void editAnggota(Anggota anggota) {
		// TODO Auto-generated method stub
		Anggota entity = anggotaRepository.findByIdanggota(anggota.getIdanggota());
		if(entity != null) {
			entity.setNama_anggota(anggota.getNama_anggota());
			entity.setAlamat(anggota.getAlamat());
			entity.setNo_telp(anggota.getNo_telp());
			entity.setEmail(anggota.getEmail());
			entity.setStatus(true);
		}
	}

	@Override
	public void deleteAnggota(String idanggota) {
		// TODO Auto-generated method stub
		Anggota entity = anggotaRepository.findByIdanggota(idanggota);
		if(entity != null) {
			entity.setStatus(false);
		}
	}
	
	@Override
	public Anggota findByIdanggota(String idanggota) {
		// TODO Auto-generated method stub
		return anggotaRepository.findByIdanggota(idanggota);
	}

}
