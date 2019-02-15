/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.goodhealth.pojo.Prize;

/**
 * @author 24663
 * @date 2019年1月8日
 * @Description
 */
public interface PrizeRepository extends  JpaSpecificationExecutor<Prize> 
,JpaRepository<Prize, Integer>{

	Prize  findByPrizeName(String  name);
	
}
