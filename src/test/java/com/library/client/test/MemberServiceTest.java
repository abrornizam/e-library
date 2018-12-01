/**
 * 
 */
package com.library.client.test;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.client.model.Member;
import com.library.client.repository.MemberRepository;
import com.library.client.service.impl.MemberServiceImpl;

/**
 * @author ANIZAM
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberServiceImpl memberServiceImpl;
	
	@Mock
	private MemberRepository memberRepository;
	
	@Mock
	private Member member;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		member = new Member();
		member.setId(1);
		member.setIdmember("USR1");
		member.setName("Abror Nizam");
		member.setAddress("Jakarta");
		member.setPhone("08151xxx");
		member.setEmail("abror@email.com");
		member.setJoindate(new Date());
		member.setStatus(true);
		memberServiceImpl = new MemberServiceImpl();
		memberServiceImpl.setmemberRepository(memberRepository);
	}
	
	@Test
	public void shouldReturnBukuSaved_whenCalledMethodSaveWithValidAnggota() throws Exception {
		when(memberRepository.save(member)).thenReturn(member);
		Member saved = memberServiceImpl.saveMember(member);
		System.out.println("*** SAVED : " + saved.getIdmember());
		assertEquals(member, saved);
	}
	
	@Test
	public void shouldReturnListAnggota_whenMethodListAnggotaCalled() {		
		List<Member> listMember = new ArrayList<Member>();
		listMember.add(member);
		when(memberRepository.findAll()).thenReturn(listMember);
		List<Member> listAnggotaSaved = memberServiceImpl.findAll();
		System.out.println("*** COUNT : " + listAnggotaSaved.size());
		assertEquals(1, listAnggotaSaved.size());
	}
	
	@Test
	public void shouldReturnAnggota_whenAnggotaIdValid() throws Exception {
		when(memberRepository.findByIdmember("USR1")).thenReturn(member);
		Member found = memberServiceImpl.findByIdmember("USR1");
		System.out.println("*** FOUND : " + found.getIdmember());
		assertEquals(member, found);
	}
	
	@Test
	public void shouldReturnNull_whenAnggotaIdInvalid() throws Exception {
		Member notFound = memberServiceImpl.findByIdmember("USR2");
		System.out.println("*** NOT FOUND : " + notFound);
		assertNull(notFound);
	}
	
	@Test
	public void shouldReturnAnggotaEdited_whenMethodEditAnggotaCalled() throws Exception {
		when(memberRepository.findByIdmember("USR1")).thenReturn(member);
		member.setIdmember("USR2");
		Member edit = memberServiceImpl.findByIdmember("USR1");
		System.out.println("*** EDIT : " + edit.getIdmember());
		assertEquals("USR2", edit.getIdmember());
	}
	
	@Test
	public void shouldReturnAnggotaDeleted_whenDeleteBukuValid() throws Exception {
		doNothing().when(memberRepository).delete(member);
		memberServiceImpl.deleteMember("USR1");
		Member delete = memberServiceImpl.findByIdmember("USR1");
		System.out.println("*** DELETE : " + delete);
		assertNull(delete);
	}
	
}
