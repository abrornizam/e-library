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
//		String idpeminjam = "";
//		String peminjam = peminjaman.getPeminjam().getIdpeminjam();
//		List<Peminjaman> p = peminjamanRepository.findAll();
//		for (Peminjaman peminjaman2 : p) {
//			idpeminjam = peminjaman2.getPeminjam().getIdpeminjam();
//			if(idpeminjam.equals(peminjam)) {
//				idpeminjam = peminjam; 
//				break;
//			}
//		}
//
//		if (idpeminjam.equals(peminjam)) {
//			peminjamanRepository.hashCode();
//			System.out.println("PRINT AJALAH : " + peminjamanRepository.hashCode());
//			return peminjamanRepository.
//		}
		Date tgl_pinjam = new Date();
		peminjaman.setStatus(true);
		peminjaman.setTgl_pinjam(tgl_pinjam);
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

}
