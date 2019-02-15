/**
 * 
 */
package com.goodhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.goodhealth.pojo.Drug;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
public interface DrugRepository  extends JpaRepository<Drug, Integer>,
JpaSpecificationExecutor<Drug> {

      Drug   findByDrugName(String  name);


}
