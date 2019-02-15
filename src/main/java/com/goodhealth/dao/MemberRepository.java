package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.goodhealth.pojo.Member;
/**
 * 
 *JpaSpecificationExecutor不能单独使用，需要配合着 jpa 中的
其他接口一起使用
 */
public interface MemberRepository extends JpaRepository<Member, Integer>, 
JpaSpecificationExecutor<Member> {
	
	     Member  findByMemberName(String  name);
	     
	       




	
}
