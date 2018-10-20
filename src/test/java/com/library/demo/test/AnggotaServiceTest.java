/**
 * 
 */
package com.library.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.demo.model.Anggota;
import com.library.demo.repository.AnggotaRepository;
import com.library.demo.service.impl.AnggotaServiceImpl;

/**
 * @author ANIZAM
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnggotaServiceTest {

	private AnggotaServiceImpl anggotaServiceImpl;
	
	@Mock
	private AnggotaRepository anggotaRepository;
	
	@Mock
	private Anggota anggota;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		anggota = new Anggota(1, "USR1", "Abror Nizam", "Jakarta", "08151xxx", "abror@email.com", new Date(), true);
		anggotaServiceImpl = new AnggotaServiceImpl();
		anggotaServiceImpl.setAnggotaRepository(anggotaRepository);
	}
	
	@Test
	public void shouldReturnBukuSaved_whenCalledMethodSaveWithValidAnggota() throws Exception {
		when(anggotaRepository.save(anggota)).thenReturn(anggota);
		Anggota saved = anggotaServiceImpl.saveAnggota(anggota);
		System.out.println("*** SAVED : " + saved.getIdanggota());
		assertEquals(anggota, saved);
	}
	
	@Test
	public void shouldReturnListAnggota_whenMethodListAnggotaCalled() {		
		List<Anggota> listAnggota = new ArrayList<Anggota>();
		listAnggota.add(anggota);
		when(anggotaRepository.findAll()).thenReturn(listAnggota);
		List<Anggota> listAnggotaSaved = anggotaServiceImpl.findAll();
		System.out.println("*** COUNT : " + listAnggotaSaved.size());
		assertEquals(1, listAnggotaSaved.size());
	}
	
	@Test
	public void shouldReturnAnggota_whenAnggotaIdValid() throws Exception {
		when(anggotaRepository.findByIdanggota("USR1")).thenReturn(anggota);
		Anggota found = anggotaServiceImpl.findByIdanggota("USR1");
		System.out.println("*** FOUND : " + found.getIdanggota());
		assertEquals(anggota, found);
	}
	
	@Test
	public void shouldReturnNull_whenAnggotaIdInvalid() throws Exception {
		Anggota notFound = anggotaServiceImpl.findByIdanggota("USR2");
		System.out.println("*** NOT FOUND : " + notFound);
		assertNull(notFound);
	}
	
	@Test
	public void shouldReturnAnggotaEdited_whenMethodEditAnggotaCalled() throws Exception {
		when(anggotaRepository.findByIdanggota("USR1")).thenReturn(anggota);
		anggota.setIdanggota("USR2");
		Anggota edit = anggotaServiceImpl.findByIdanggota("USR1");
		System.out.println("*** EDIT : " + edit.getIdanggota());
		assertEquals("USR2", edit.getIdanggota());
	}
	
	@Test
	public void shouldReturnAnggotaDeleted_whenDeleteBukuValid() throws Exception {
		doNothing().when(anggotaRepository).delete(anggota);
		anggotaServiceImpl.deleteAnggota("USR1");
		Anggota delete = anggotaServiceImpl.findByIdanggota("USR1");
		System.out.println("*** DELETE : " + delete);
		assertNull(delete);
	}
	
}
