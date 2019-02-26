/**
 * 
 */
package com.goodhealth.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodhealth.dao.OrderItemRepository;
import com.goodhealth.pojo.OrderItem;
import com.goodhealth.service.OrderItemService;

/**
 * @author 24663
 * @date 2019年2月25日
 * @Description
 */
@Service
public class OrderItemServiceImp  implements OrderItemService{

	
	@Autowired
	private  OrderItemRepository  itemRepository;
	
	/* (non-Javadoc)
	 * @see com.goodhealth.service.OrderItemService#saveItem()
	 */
	@Override
	public void saveItem(OrderItem item) throws Exception {
		itemRepository.save(item);
	}
	
	

}
