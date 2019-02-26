/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.goodhealth.pojo.OrderItem;

/**
 * @author 24663
 * @date 2019年2月25日
 * @Description
 */
public interface OrderItemRepository   extends  JpaRepository<OrderItem, String>,
JpaSpecificationExecutor<OrderItem>{
	

}
