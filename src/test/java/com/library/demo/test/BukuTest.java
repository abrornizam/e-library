package com.library.demo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.demo.model.Buku;
import com.library.demo.service.BukuService;

public class BukuTest {

	@Autowired
	BukuService bukuService;
	
	public void saveBuku() {
		Buku buku = new Buku();
		buku.setJudul("Springfamework");
		buku.setDeskripsi("Dekripsi");
		buku.setJumlah(10);
		buku.setTahun("2017");
		buku.setStatus(true);
	}
	
}
