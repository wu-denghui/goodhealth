package com.goodhealth.service.imp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.goodhealth.dao.MemberRepository;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.News;
import com.goodhealth.service.MemberService;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
@Service
public class MemberServiceImp  implements MemberService {
	
	@Autowired
	private MemberRepository  memberRepository;


	/* (non-Javadoc)
	 * @see com.goodhealth.service.MemberService#getMemberByName(java.lang.String)
	 */
	@Override
	public Member getMemberByName(String name) throws Exception {
		Member  member=this.memberRepository.findByMemberName(name);
		return member;
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.MemberService#addMember(com.goodhealth.pojo.Member)
	 */
	@Override
	public void addMember(Member member) throws Exception {
		this.memberRepository.save(member);
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.MemberService#getMemberById(int)
	 */
	@Override
	public Member getMemberById(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.memberRepository.findOne(id);
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.MemberService#findAllByPage(int)
	 */
	@Override
	public Page<Member> findAllByPage(int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 6);
		Page<Member> p= (Page<Member>) this.memberRepository.findAll(pageable);
		return p;
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.MemberService#findListByLikeName(java.lang.String, int)
	 */
	@Override
	public Page<Member> findListByLikeName(final String name, int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 20);
		Specification<Member>  spec=new  Specification<Member>() {
			@Override
			public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate  pre=cb.like(root.get("memberName").as(String.class), "%"+name+"%");
				return pre;
			}
		};
		Page<Member>  list=this.memberRepository.findAll(spec,pageable);
		return list;
	}



}
