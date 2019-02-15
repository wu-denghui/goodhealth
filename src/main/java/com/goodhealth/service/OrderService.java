/**
 * 
 */
package com.goodhealth.service;

import org.springframework.data.domain.Page;

import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Orders;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */
public interface OrderService {
  
	  void   addOrder(Orders  order)throws Exception;
	
	  
	  Page< Orders> findOnesOrder(Member  member,int index)throws Exception;
	  
	  
	  Page< Orders> findOnesCompletedOrder(Member  member,int index)throws Exception;
	  
	  Orders  findOrderById(int  id)throws  Exception;
	
}
