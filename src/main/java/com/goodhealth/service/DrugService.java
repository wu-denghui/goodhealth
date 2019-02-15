/**
 * 
 */
package com.goodhealth.service;



import org.springframework.data.domain.Page;
import com.goodhealth.pojo.Drug;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
public interface DrugService {
	
	Drug findById(int id) throws Exception;
	
	Page<Drug>   findAllByPage(int index) throws Exception;
	
	Page<Drug>   findNewDrugPage(int index) throws Exception;
	
	Page<Drug>  findDrugListByLikeName(String name,int index) throws Exception;
	
	void   addDrug(Drug  drug) throws  Exception;
	
	Drug findDrugByName(String name)throws Exception;
	
	void  deleteDrugById(int   id)throws  Exception;

}
