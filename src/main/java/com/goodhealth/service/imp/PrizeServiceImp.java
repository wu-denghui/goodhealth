/**
 * 
 */
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
import com.goodhealth.dao.PrizeRepository;
import com.goodhealth.pojo.Prize;
import com.goodhealth.service.PrizeService;

/**
 * @author 24663
 * @date 2019年1月8日
 * @Description
 */
@Service
public class PrizeServiceImp implements  PrizeService {

	@Autowired
	private   PrizeRepository   prizeRepository;
	

	@Override
	public  Page<Prize> findAllByPage(int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 6);
		Page<Prize> p= (Page<Prize>) this.prizeRepository.findAll(pageable);
		return p;
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.PrizeService#findListByLikeName(java.lang.String, int)
	 */
	@Override
	public Page<Prize> findListByLikeName(final String name, int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 10);
		Specification<Prize>  spec=new  Specification<Prize>() {
			@Override
			public Predicate toPredicate(Root<Prize> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate  pre=cb.like(root.get("prizeName").as(String.class), "%"+name+"%");
				return pre;
			}
		};
		Page<Prize>  list=this.prizeRepository.findAll(spec,pageable);
		return list;
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.PrizeService#addPrize(com.goodhealth.pojo.Prize)
	 */
	@Override
	public void addPrize(Prize prize) throws Exception {
		this.prizeRepository.save(prize);
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.PrizeService#deletePrizeById(int)
	 */
	@Override
	public void deletePrizeById(int id) throws Exception {
		this.prizeRepository.delete(id);
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.PrizeService#findPrizeByName(java.lang.String)
	 */
	@Override
	public Prize findPrizeByName(String name) throws Exception {
		return this.prizeRepository.findByPrizeName(name);
	}



	/* (non-Javadoc)
	 * @see com.goodhealth.service.PrizeService#findById(int)
	 */
	@Override
	public Prize findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.prizeRepository.findOne(id);
	}

	
}
