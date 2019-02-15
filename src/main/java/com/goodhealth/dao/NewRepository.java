/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.News;

/**
 * @author 24663
 * @date 2019年1月10日
 * @Description
 */
public interface NewRepository  extends  JpaRepository<News, Integer>
,JpaSpecificationExecutor<News>{
	
    News   findByNewTitle(String  name);

}
