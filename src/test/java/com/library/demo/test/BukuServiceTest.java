/**
 * 
 */
package com.library.demo.test;

/**
 * @author ANIZAM
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.demo.model.Buku;
import com.library.demo.model.Pengarang;
import com.library.demo.repository.BukuRepository;
import com.library.demo.service.impl.BukuServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BukuServiceTest {
		
	private BukuServiceImpl bukuServiceImpl;
	
	@Mock
	private BukuRepository bukuRepository;
	
	@Mock
	private Buku buku;
		
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Pengarang pengarang = new Pengarang();
		pengarang.setId(1);
		pengarang.setNama_pengarang("Abror");
		buku = new Buku();
		buku.setId(1);
		buku.setKdbuku("BOOK1");
		buku.setJudul("Meraih Mimpi");
		buku.setDeskripsi("Desc Buku");
		buku.setTahun("2018");
		buku.setJumlah(10);
		buku.setPengarang(pengarang);
		buku.setStatus(true);
		bukuServiceImpl = new BukuServiceImpl();
		bukuServiceImpl.setBukuRepository(bukuRepository);
	}

	@Test
	public void shouldReturnBuku_whenSaveBukuValid() throws Exception {
		when(bukuRepository.save(buku)).thenReturn(buku);
		Buku bukuSaved = bukuServiceImpl.saveBuku(buku);
		System.out.println("*** SAVE : " + bukuSaved.getKdbuku());
		assertEquals(buku, bukuSaved);
	}
	
	@Test
	public void shouldReturnListBuku_whenMethodListBukuCalled() throws Exception {
		List<Buku> listBuku = new ArrayList<Buku>();
		listBuku.add(buku);
		when(bukuRepository.findAll()).thenReturn(listBuku);
		List<Buku> listBukuSaved = bukuServiceImpl.findAll();
		System.out.println("*** COUNT : " + listBukuSaved.size());
		assertEquals(1, listBukuSaved.size());
	}
	
	@Test
	public void shouldReturnBuku_whenKdBukuValid() throws Exception {
		when(bukuRepository.findByKdbuku("BOOK1")).thenReturn(buku);
		Buku bukuFound = bukuServiceImpl.findByKode("BOOK1");
		System.out.println("*** FOUND : " + bukuFound.getKdbuku());
		assertEquals(buku, bukuFound);
	}
	
	@Test
	public void shouldReturnBukuNotFound_whenKdBukuInvalid() throws Exception {
		when(bukuRepository.findByKdbuku("BOOK1")).thenReturn(buku);
		Buku bukuNotFound = bukuServiceImpl.findByKode("BOOK2");
		System.out.println("*** NOT FOUND : " + bukuNotFound);
		assertNull(bukuNotFound);
	}
	
	@Test
	public void shouldReturnBukuEdited_whenMethodEditBukuCalled() throws Exception {
		when(bukuRepository.findByKdbuku("BOOK1")).thenReturn(buku);
		buku.setKdbuku("BOOK2");
		Buku bukuEdited = bukuServiceImpl.findByKode("BOOK1");
		System.out.println("*** AFTER EDIT : " + bukuEdited.getKdbuku());
		assertEquals("BOOK2", bukuEdited.getKdbuku());
	}
	
	@Test
	public void shouldDeleteBuku_whenDeleteBukuValid() throws Exception {
		doNothing().when(bukuRepository).delete(buku);
		bukuServiceImpl.deleteBuku("BOOK1");
		Buku bukuDeleted = bukuServiceImpl.findByKode("BOOK1");
		System.out.println("*** DELETE : " + bukuDeleted);
		assertNull(bukuDeleted);
	}
	
	@Test
	public void sholdReturnTotalBuku_whenBukuBorrowed() throws Exception {
		int totalBuku = buku.getJumlah();
		int pinjamBuku = 2;
		int sisaBuku = totalBuku - pinjamBuku;
		when(bukuRepository.findByKdbuku("BOOK1")).thenReturn(buku);		
		bukuServiceImpl.updateJumlahBuku("BOOK1", sisaBuku);
		Buku bukuTotal = bukuServiceImpl.findByKode("BOOK1");
		System.out.println("*** TOTAL BUKU : " + bukuTotal.getJumlah());
		assertEquals(8, sisaBuku);
	}
}
