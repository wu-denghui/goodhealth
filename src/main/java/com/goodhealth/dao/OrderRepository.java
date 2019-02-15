/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.goodhealth.pojo.Orders;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */
public interface OrderRepository extends  JpaRepository<Orders, Integer>
,JpaSpecificationExecutor<Orders>{

	Orders  findByOrderId(int  id);
	
}
