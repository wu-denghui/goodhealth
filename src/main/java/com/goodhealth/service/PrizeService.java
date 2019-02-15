/**
 * 
 */
package com.goodhealth.service;

import org.springframework.data.domain.Page;
import com.goodhealth.pojo.Prize;

/**
 * @author 24663
 * @date 2019年1月8日
 * @Description
 */
public interface PrizeService {
	
	  Page<Prize>   findAllByPage(int index) throws Exception;
	  
	 Page<Prize>  findListByLikeName(String name,int index) throws Exception;
	 
	 void   addPrize(Prize  prize) throws  Exception;
	 
	 void  deletePrizeById(int   id)throws  Exception;
	 
	 Prize  findById(int   id)throws  Exception;
	 
	 Prize  findPrizeByName(String  name)throws  Exception;

}
