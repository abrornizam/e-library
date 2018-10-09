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

import com.library.demo.model.Peminjaman;
import com.library.demo.repository.AnggotaRepository;
import com.library.demo.repository.BukuRepository;
import com.library.demo.repository.PeminjamanRepository;
import com.library.demo.service.AnggotaService;
import com.library.demo.service.PeminjamanService;

@Service
@Transactional
public class PeminjamanServiceImpl implements PeminjamanService {

	@Autowired
	PeminjamanRepository peminjamanRepository;

	@Autowired
	BukuRepository bukuRepository;

	@Autowired
	AnggotaRepository peminjamRepository;

	@Autowired
	AnggotaService peminjamService;

	@Override
	public List<Peminjaman> findAll() {
		// TODO Auto-generated method stub
		return peminjamanRepository.findAll();
	}

	@Override
	public Peminjaman savePeminjaman(Peminjaman peminjaman) {
		// TODO Auto-generated method stub
		peminjaman.setKdpeminjaman("TRX"+peminjaman.getId());
		Date tgl_pinjam = new Date();		
		peminjaman.setTgl_pinjam(tgl_pinjam);
		peminjaman.setStatus_peminjaman("IN PROGRESS");
		peminjaman.setStatus(true);
		return peminjamanRepository.save(peminjaman);
	}
	

	@Override
	public Peminjaman findByKdpeminjaman(String kdpeminjaman) {
		// TODO Auto-generated method stub
		return peminjamanRepository.findByKdpeminjaman(kdpeminjaman);
	}

	@Override
	public void deletePeminjaman(String kdpeminjaman) {
		// TODO Auto-generated method stub
		Peminjaman entity = peminjamanRepository.findByKdpeminjaman(kdpeminjaman);
		if(entity != null) {
			Date tgl_kembali = new Date();
			entity.setStatus(false);
			entity.setTgl_kembali(tgl_kembali);			
		}
	}

	@Override
	public void acceptPeminjaman(String kdpeminjaman) {
		// TODO Auto-generated method stub
		Peminjaman entity = peminjamanRepository.findByKdpeminjaman(kdpeminjaman);
		if(entity.getStatus_peminjaman().equals("IN PROGRESS")) {
			entity.setStatus_peminjaman("APPROVED");
		}
	}

	@Override
	public void fundPeminjaman(String kdpeminjaman) {
		// TODO Auto-generated method stub
		Peminjaman entity = peminjamanRepository.findByKdpeminjaman(kdpeminjaman);
		if(entity.getStatus_peminjaman().equals("APPROVED")) {
			entity.setStatus_peminjaman("FINISH");
		}
	}

	@Override
	public int denda(String kdpeminjaman) {
		// TODO Auto-generated method stub
		int denda = 0;
		int lamaPinjam = 5;
		if(lamaPinjam > 3) {
			denda = 5000;
		}
		return denda;
	}

}
