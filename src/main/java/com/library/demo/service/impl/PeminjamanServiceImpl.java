package com.library.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.library.demo.service.PeminjamanService;

@Service
@Transactional
public class PeminjamanServiceImpl implements PeminjamanService {

	@Autowired
	PeminjamanRepository peminjamanRepository;

	@Autowired
	BukuRepository bukuRepository;

	@Autowired
	AnggotaRepository anggotaRepository;

	@Override
	public List<Peminjaman> findAll() {
		// TODO Auto-generated method stub
		return peminjamanRepository.findAll();
	}

	@Override
	public Peminjaman savePeminjaman(Peminjaman peminjaman) {
		// TODO Auto-generated method stub
		int totalData = peminjamanRepository.getTotalData();
		int seqId = 0;	
		if(totalData == 0) {
			seqId = 1;
		}else {
			seqId = peminjamanRepository.getSequenceId()+1;
		}
		peminjaman.setId(seqId);
    	String kdpeminjaman = Integer.valueOf(seqId).toString();
    	peminjaman.setKdpeminjaman("TRX".concat(kdpeminjaman));
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
			Date tgl_kembali = new Date();
			entity.setTgl_kembali(tgl_kembali);
		}
	}

	@Override
	public boolean isAlreadyBorrow(int idanggota) {
		// TODO Auto-generated method stub
		int idfound = 0;
		String status = "";
		List<Peminjaman> listPeminjaman = peminjamanRepository.findAll();
		for(Peminjaman peminjaman : listPeminjaman) {
			idfound = peminjaman.getAnggota().getId();
			status = peminjaman.getStatus_peminjaman();
			if(idanggota == idfound && !status.equalsIgnoreCase("FINISH")) {
				break;
			}
		}
		
		if(idanggota == idfound && !status.equalsIgnoreCase("FINISH")) {
			return true;
		}else {
			return false;
		}
	}	
	
	@Override
	public int lamaPeminjaman(String kdpeminjaman) {
		// TODO Auto-generated method stub
		Peminjaman p = peminjamanRepository.findByKdpeminjaman(kdpeminjaman);
		Date tglPinjam = p.getTgl_pinjam();
		Date tglKembali = p.getTgl_kembali();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String tglPeminjaman = dateFormat.format(tglPinjam);
		String tglPengembalian = dateFormat.format(tglKembali);
		int tglP = Integer.parseInt(tglPeminjaman.substring(8, 10));
		int bulanP = Integer.parseInt(tglPeminjaman.substring(5, 7));
		int tahunP = Integer.parseInt(tglPeminjaman.substring(0, 4));
		int tglK = Integer.parseInt(tglPengembalian.substring(8, 10));
		int bulanK = Integer.parseInt(tglPengembalian.substring(5, 7));
		int tahunK = Integer.parseInt(tglPengembalian.substring(0, 4));
		int hari = tglK - tglP;
		int bulan = (bulanK - bulanP) * 30;
		int tahun = (tahunK - tahunP) * 365;
		int lamaPinjam = hari + bulan + tahun;
		return lamaPinjam;
	}
	
	@Override
	public int denda(String kdpeminjaman) {
		// TODO Auto-generated method stub
		int denda = 0;
		int lamaPinjam = lamaPeminjaman(kdpeminjaman);
		if(lamaPinjam > 5) {			
			denda = 5000;
		}else if(lamaPinjam > 10) {
			denda = 10000;
		}else {
			denda = 25000;
		}
		return denda;
	}

}
