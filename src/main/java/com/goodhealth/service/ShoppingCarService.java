/**
 * 
 */
package com.goodhealth.service;


import org.springframework.data.domain.Page;
import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Shoppingcar;

import antlr.RecognitionException;

/**
 * @author 24663
 * @date 2019年1月3日
 * @Description
 */
public interface ShoppingCarService {
	
	
	void   addRecord(Shoppingcar   record)throws Exception;
	
	Shoppingcar  isHas(Member member, Drug drug)throws Exception;
	
	Page<Shoppingcar>  getOnesShoppingCarList(Member member,int index) throws Exception;
	
	Shoppingcar  getShoppingCarById(int id)throws Exception;
	
	void  deleteShoppingCar(int  id)throws Exception;

}
