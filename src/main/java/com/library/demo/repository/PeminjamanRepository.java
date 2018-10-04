package com.library.demo.repository;

/**
 * @author ANIZAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.demo.model.Peminjaman;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer>{

	Peminjaman findByKdpeminjaman(String kdpeminjaman);

	//	Peminjaman findByBuku(String kdbuku);
}
