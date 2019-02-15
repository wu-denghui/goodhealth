/**
 * 
 */
package com.goodhealth.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.News;
import com.goodhealth.service.imp.NewsServiceImp;

/**
 * @author 24663
 * @date 2019年1月10日
 * @Description
 */

@Controller
@RequestMapping("news")
public class NewController {
	
	@Autowired
	private   NewsServiceImp newService;
	
	@RequestMapping("/findAll/{index}")
	public ModelAndView findAllDrug(@PathVariable(value = "index") int index) {
		ModelAndView model = new ModelAndView("Display/showNews");
		try {
			Page<News> p = this.newService.findAllByPage(index);
			List<News> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addObject("newsList", list);
			model.addObject("max", maxPages);
			model.addObject("index", index + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/intoPage")
	public String intoPage(HttpServletRequest request, HttpServletResponse response) {
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/news/findAll/" + (index - 1);
		} else {
			showDrugFail(request, response, i);
		}
		return "Display/showNews";
	}
	
	public void showDrugFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/news/findAll/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/addNews")
	public  String  addNews(@Valid News  news,BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response){
		// 获取校验错误信息
		try {
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("news", news);
			model.addAttribute("newsManger", 1);
			model.addAttribute("allErrors", allErrors);
			return "Manger/news";
		}
			this.newService.addNews(news);
			successAdd(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/news";
	}
	
	public void successAdd(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('添加成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/drugManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/searchForUpdate")
	public  String  searchNewsForUpdate(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
			Page<News>  page=this.newService.findListByLikeName(searchMess,0);
			List<News>  list=page.getContent();
			if (list.isEmpty()) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("newsManger", 2);
			model.addAttribute("newsList", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/news";
	}
	
	@RequestMapping("/searchForDelete")
	public  String  searchNewsForDelete(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
			Page<News>  page=this.newService.findListByLikeName(searchMess,0);
			List<News>  list=page.getContent();
			if (list.isEmpty()) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("newsManger", 3);
			model.addAttribute("newsList", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/news";
	}
	
	public void searchFailManger(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('搜索无结果，请输入其他关键字'); </script>");
			out.print("<script> window.location.href='http://localhost:8080/page/newsManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/editNews")
	public  String  editNews(@Valid News  news,BindingResult result,HttpServletRequest request, HttpServletResponse response,String   id,Model  model ){
		// 获取校验错误信息
			if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("news", news);
			model.addAttribute("newsManger", 2);
			model.addAttribute("allErrors", allErrors);
			return "Manger/news";
		}
		try {
			News  d=this.newService.findById(Integer.parseInt(id));
			d.setNewAuthor(news.getNewAuthor());
			d.setNewDate(news.getNewDate());
			d.setNewDetail(news.getNewDetail());
			d.setNewTitle(news.getNewTitle());
			this.newService.addNews(d);
			successEdit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Manger/news";
	}
	@RequestMapping("/deleteNews")
	public  String  deleteDrug(int  id,HttpServletRequest request, HttpServletResponse response){
		try {
			this.newService.deleteNewsById(id);
			successDelete(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/news";
	}
	public void successEdit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('更新成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/newsManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	public void successDelete(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('删除成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/newsManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

}
