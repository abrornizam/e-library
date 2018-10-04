package com.library.demo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.demo.model.Buku;
import com.library.demo.repository.BukuRepository;
import com.library.demo.service.BukuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BukuTest {

//	@TestConfiguration
//	static class BukuTestConfiguration {
//		
//		@Bean
//		public BukuService bukuService() {
//			return new BukuServiceImpl();
//		}
//	}
	
	@MockBean
	private BukuRepository bukuRepository;
	
	@Autowired
	private BukuService bukuService;
	
	@Before
	public void setUp() {
		Buku buku = new Buku();
		buku.setKdbuku("B001");
		buku.setJudul("Springfamework");
		buku.setDeskripsi("Dekripsi");
		buku.setJumlah(10);
		buku.setTahun("2017");
		buku.setStatus(true);
		
		Mockito.when(bukuRepository.findByKdbuku(buku.getKdbuku()))
			.thenReturn(buku);
	}
		
	@Test
	public void findBukuByKodeBuku() {
		String kdBuku = "B001";		
		Buku buku = bukuRepository.findByKdbuku(kdBuku);
		assertEquals(buku.getKdbuku(), kdBuku); 
	}
	
}
