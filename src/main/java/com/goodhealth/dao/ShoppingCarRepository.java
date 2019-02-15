/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.goodhealth.pojo.Shoppingcar;

/**
 * @author 24663
 * @date 2019年1月3日
 * @Description
 */
public interface ShoppingCarRepository extends JpaRepository<Shoppingcar, Integer> 
,JpaSpecificationExecutor<Shoppingcar>{

	
	
}
