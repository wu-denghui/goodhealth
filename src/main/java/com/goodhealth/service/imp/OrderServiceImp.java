/**
 * 
 */
package com.goodhealth.service.imp;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.goodhealth.dao.OrderRepository;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Orders;
import com.goodhealth.service.OrderService;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */
@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private  OrderRepository   orderRepository;

	/* (non-Javadoc)
	 * @see com.goodhealth.service.OrderService#addOrder()
	 */
	@Override
	public void addOrder(Orders order) throws Exception {
		this.orderRepository.save(order);
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.OrderService#findOnesOrder(com.goodhealth.pojo.Member)
	 */
	@Override
	public Page<Orders> findOnesOrder(final Member member,int index) throws Exception {
		Sort sort=new Sort(Direction.DESC, "orderId");
		Pageable pageable=new PageRequest(index, 6,sort);
		Specification< Orders>  spec=new Specification<Orders>() {
			@Override
			public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre=cb.and(cb.equal(root.get("orderStatus"), 0),cb.equal(root.get("memberId"), member.getMemberId()));
				return pre;
			}
		};
		Page< Orders> page=this.orderRepository.findAll(spec,pageable);
		return  page ;
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.OrderService#findOrderById(int)
	 */
	@Override
	public Orders findOrderById(String id) throws Exception {
		Orders  orders=this.orderRepository.findByOrderId(id);
		return orders;
	}

	/* (non-Javadoc)
	 * @see com.goodhealth.service.OrderService#findOnesCompletedOrder(com.goodhealth.pojo.Member, int)
	 */
	@Override
	public Page<Orders> findOnesCompletedOrder(final Member member, int index) throws Exception {
		Sort sort=new Sort(Direction.DESC, "orderId");
		Pageable pageable=new PageRequest(index, 6,sort);
		Specification< Orders>  spec=new Specification<Orders>() {
			@Override
			public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre=cb.and(cb.equal(root.get("orderStatus"), 1),cb.equal(root.get("memberId"), member.getMemberId()));
				return pre;
			}
		};
		Page< Orders> page=this.orderRepository.findAll(spec,pageable);
		return  page ;
	}
}
