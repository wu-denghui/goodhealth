/**
 * 
 */
package com.goodhealth.service;

import org.springframework.data.domain.Page;

import com.goodhealth.pojo.News;

/**
 * @author 24663
 * @date 2019年1月10日
 * @Description
 */
public interface NewsService {
	
	Page<News>   findAllByPage(int index) throws Exception;
	
	Page<News>  findListByLikeName(String name,int index) throws Exception;

	void   addNews(News  news) throws  Exception;
	
	void  deleteNewsById(int   id)throws  Exception;
	
	News    findById(int   id)throws  Exception;
	
	News  findNewsByName(String  name)throws  Exception;

}
