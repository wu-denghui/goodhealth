/**
 * 
 */
package com.goodhealth.service;

import org.springframework.data.domain.Page;

import com.goodhealth.pojo.Member;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
public interface MemberService {

	
	public  Member getMemberByName(String  name) throws Exception;
	
	
	public  void  addMember(Member member)throws Exception;

	
	public  Member getMemberById(int  id) throws Exception;
	
	Page<Member>   findAllByPage(int index) throws Exception;
	
	Page<Member>  findListByLikeName(String name,int index) throws Exception;
}
