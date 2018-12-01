package com.library.client.service.impl;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.client.model.Member;
import com.library.client.repository.MemberRepository;
import com.library.client.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	public void setmemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return memberRepository.findAll();
	}

	@Override
	public Member saveMember(Member member) {
		// TODO Auto-generated method stub		
		int totalData = memberRepository.getTotalData();
		int seqId = 0;	
		if(totalData == 0) {
			seqId = 1;
		}else {
			seqId = memberRepository.getSequenceId()+1;
		}
		member.setId(seqId);
		member.setIdmember("USR"+seqId);
		Date tgl = new Date();
		member.setStatus(true);
		member.setJoindate(tgl);
		return memberRepository.save(member);		
	}
	
	@Override
	public void editMember(Member member) {
		// TODO Auto-generated method stub
		Member entity = memberRepository.findByIdmember(member.getIdmember());
		if(entity != null) {
			entity.setName(member.getName());
			entity.setAddress(member.getAddress());
			entity.setPhone(member.getPhone());
			entity.setEmail(member.getEmail());
			entity.setStatus(true);
		}
	}

	@Override
	public void deleteMember(String idmember) {
		// TODO Auto-generated method stub
		Member entity = memberRepository.findByIdmember(idmember);
		if(entity != null) {
			entity.setStatus(false);
		}
	}
	
	@Override
	public Member findByIdmember(String idmember) {
		// TODO Auto-generated method stub
		return memberRepository.findByIdmember(idmember);
	}

}
