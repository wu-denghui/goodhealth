/**
 * 
 */
package com.goodhealth.service.imp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.goodhealth.dao.NewRepository;
import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.News;
import com.goodhealth.service.NewsService;

/**
 * @author 24663
 * @date 2019年1月10日
 * @Description
 */
@Service
public class NewsServiceImp implements  NewsService{
		
		@Autowired
		private   NewRepository newRepository;
		

		@Override
		public  Page<News> findAllByPage(int index) throws Exception {
			Sort  sort =new  Sort(Direction.DESC, "newId");
			Pageable   pageable= new PageRequest(index, 6,sort);
			Page<News> p= (Page<News>) this.newRepository.findAll(pageable);
			return p;
		}



		/* (non-Javadoc)
		 * @see com.goodhealth.service.NewsService#findListByLikeName(java.lang.String, int)
		 */
		@Override
		public Page<News> findListByLikeName(final String name, int index) throws Exception {
			Pageable   pageable= new PageRequest(index, 10);
			Specification<News>  spec=new  Specification<News>() {
				@Override
				public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate  pre=cb.like(root.get("newTitle").as(String.class), "%"+name+"%");
					return pre;
				}
			};
			Page<News>  list=this.newRepository.findAll(spec,pageable);
			return list;
		}



		/* (non-Javadoc)
		 * @see com.goodhealth.service.NewsService#addNews(com.goodhealth.pojo.News)
		 */
		@Override
		public void addNews(News news) throws Exception {
			this.newRepository.save(news);
			
		}



		/* (non-Javadoc)
		 * @see com.goodhealth.service.NewsService#deleteNewsById(int)
		 */
		@Override
		public void deleteNewsById(int id) throws Exception {
			this.newRepository.delete(id);
		}



		/* (non-Javadoc)
		 * @see com.goodhealth.service.NewsService#findNewsByName(java.lang.String)
		 */
		@Override
		public News findNewsByName(String name) throws Exception {
			return this.newRepository.findByNewTitle(name);
		}



		/* (non-Javadoc)
		 * @see com.goodhealth.service.NewsService#findById(int)
		 */
		@Override
		public News findById(int id) throws Exception {
			// TODO Auto-generated method stub
			return   this.newRepository.findOne(id);
		}
}
