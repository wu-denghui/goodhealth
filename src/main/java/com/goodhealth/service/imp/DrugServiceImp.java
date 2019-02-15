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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.goodhealth.dao.DrugRepository;
import com.goodhealth.pojo.Drug;
import com.goodhealth.service.DrugService;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
@Service
public class DrugServiceImp implements DrugService {
	
	@Autowired
	private   DrugRepository drugRepository;
	

	@Override
	public  Page<Drug> findAllByPage(int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 6);
		Page<Drug> p= (Page<Drug>) this.drugRepository.findAll(pageable);
		return p;
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#findById(int)
	 */
	@Override
	public Drug findById(int id) throws Exception {
		Drug  drug=this.drugRepository.findOne(id);
		return drug;
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#findDrugListByLikeName(java.lang.String)
	 */
	@Override
	public   Page<Drug>  findDrugListByLikeName(final String name,int index) throws Exception {
		Pageable   pageable= new PageRequest(index, 15);
		Specification<Drug>  spec=new  Specification<Drug>() {
			@Override
			public Predicate toPredicate(Root<Drug> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate  pre=cb.like(root.get("drugName").as(String.class), "%"+name+"%");
				return pre;
			}
		};
		Page<Drug>  list=this.drugRepository.findAll(spec,pageable);
		return list;
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#findNewDrugPage(int)
	 */
	@Override
	public Page<Drug> findNewDrugPage(int index) throws Exception {
		Sort  sort=new Sort(Direction.DESC,"drugId");
		Pageable   pageable= new PageRequest(index, 6,sort);
		Page<Drug> p= (Page<Drug>) this.drugRepository.findAll(pageable);
		return p;
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#addDrug(com.goodhealth.pojo.Drug)
	 */
	@Override
	public void addDrug(Drug drug) throws Exception {
		this.drugRepository.save(drug);
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#findDrugLikeName(java.lang.String)
	 */
	@Override
	public Drug findDrugByName(String name) throws Exception {
		return   this.drugRepository.findByDrugName(name);
	}


	/* (non-Javadoc)
	 * @see com.goodhealth.service.DrugService#deleteDrugById(int)
	 */
	@Override
	public void deleteDrugById(int id) throws Exception {
		this.drugRepository.delete(id);
	}

}
