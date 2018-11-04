package com.library.demo.service.impl;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.demo.model.Buku;
import com.library.demo.repository.BukuRepository;
import com.library.demo.service.BukuService;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {
	
	private BukuRepository bukuRepository;
	
	@Autowired
	public void setBukuRepository(BukuRepository bukuRepository) {
		// TODO Auto-generated method stub
		this.bukuRepository = bukuRepository;
	}
	
	@Override
	public Buku saveBuku(Buku buku) {
		// TODO Auto-generated method stub
		int totalData = bukuRepository.getTotalData();
		int seqId = 0;	
		if(totalData == 0) {
			seqId = 1;
		}else {
			seqId = bukuRepository.getSequenceId()+1;
		}
		buku.setId(seqId);
		buku.setKdbuku("BOOK"+seqId);
		buku.setStatus(true);		
		return bukuRepository.save(buku);
	}

	@Override
	public void updateBuku(Buku buku) {
		// TODO Auto-generated method stub
		Buku entity = bukuRepository.findByKdbuku(buku.getKdbuku());
		if(entity != null) {
			entity.setJudul(buku.getJudul());
			entity.setDeskripsi(buku.getDeskripsi());
			entity.setTahun(buku.getTahun());
			entity.setJumlah(buku.getJumlah());
			entity.setPengarang(buku.getPengarang());
			entity.setStatus(true);
		}
	}

	@Override
	public void deleteBuku(String kdbuku) {
		// TODO Auto-generated method stub
		Buku entity = bukuRepository.findByKdbuku(kdbuku);
		if(entity != null) {
			entity.setStatus(false);
		}
	}

//	@Override
//	public Buku findById(int id) {
//		// TODO Auto-generated method stub
//		return bukuRepository.findById(id);
//	}

	@Override
	public List<Buku> findAll() {
		// TODO Auto-generated method stub
		return bukuRepository.findAll();
	}

	@Override
	public Buku findByKode(String kdbuku) {
		// TODO Auto-generated method stub
		return bukuRepository.findByKdbuku(kdbuku);
	}

	@Override
	public void updateJumlahBuku(String kdbuku, int jumlah) {
		// TODO Auto-generated method stub
		Buku buku = bukuRepository.findByKdbuku(kdbuku);
		buku.setJumlah(jumlah);
	}

	@Override
	public Buku findByTahun(String tahun) {
		// TODO Auto-generated method stub
		return bukuRepository.findByTahun(tahun);
	}
	
}
