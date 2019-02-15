/**
 * 
 */
package com.goodhealth.service.imp;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.goodhealth.dao.ShoppingCarRepository;
import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Shoppingcar;
import com.goodhealth.service.ShoppingCarService;

/**
 * @author 24663
 * @date 2019年1月3日
 * @Description
 */
@Service
public class ShoppingCarServiceImp implements ShoppingCarService {
	
	@Autowired
     private   ShoppingCarRepository   shoppingCarRepository;
	
	/* (non-Javadoc)
	 * @see com.goodhealth.service.ShoppingCarService#addRecord(com.goodhealth.pojo.Shoppingcar)
	 */
	@Override
	public void addRecord(Shoppingcar record) throws Exception {
		this.shoppingCarRepository.save(record);
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.ShoppingCarService#isHas(int, int)
	 */
	@Override
	public Shoppingcar isHas(final Member member, final Drug drug) throws Exception {
		Specification<Shoppingcar>  spec=new Specification<Shoppingcar>(){

			@Override
			public Predicate toPredicate(Root<Shoppingcar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate  pre=cb.and(cb.equal(root.get("member"), member),cb.equal(root.get("drug"), drug));
				return pre;
			}
		};
		Shoppingcar record=this.shoppingCarRepository.findOne(spec);
		return record;
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.ShoppingCarService#getOnesShoppingCarList(com.goodhealth.pojo.Member)
	 */
	@Override
	public   Page<Shoppingcar> getOnesShoppingCarList(final Member member,int index) throws Exception {
		Sort sort=new Sort(Direction.DESC, "recordId");
		Pageable pageable=new PageRequest(index, 6,sort);
		Specification<Shoppingcar> spec=new  Specification<Shoppingcar>() {
			@Override
			public Predicate toPredicate(Root<Shoppingcar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return  cb.and(cb.isNotNull(root.get("drug")),cb.equal(root.get("member"), member));
			}
		};
		Page<Shoppingcar> p= (Page<Shoppingcar>) this.shoppingCarRepository.findAll(spec,pageable);
		return p;
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.ShoppingCarService#getShoppingCarById(int)
	 */
	@Override
	public Shoppingcar getShoppingCarById(int id) throws Exception {
		Shoppingcar  record=this.shoppingCarRepository.findOne(id);
		return record;
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.ShoppingCarService#deleteShoppingCar(int)
	 */
	@Override
	public void deleteShoppingCar(int id) throws Exception {
		this.shoppingCarRepository.delete(id);
	}

}
