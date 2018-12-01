package com.library.client.repository;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.client.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

	@Query(value = "select * from member where status=true", nativeQuery = true)
	List<Member> findAll();
	
	Member findByIdmember(String idmember);
	
	@Query(value = "select count(id) from member", nativeQuery = true)
	int getTotalData();
	
	@Query(value = "select max(id) from member", nativeQuery = true)
	int getSequenceId();

}
