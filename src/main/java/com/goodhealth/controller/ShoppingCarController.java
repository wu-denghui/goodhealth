/**
 * 
 */
package com.goodhealth.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Shoppingcar;
import com.goodhealth.service.imp.ShoppingCarServiceImp;

/**
 * @author 24663
 * @date 2019年1月3日
 * @Description
 */
@Controller
@RequestMapping("shoppingcar")
public class ShoppingCarController {
	
	
	@Autowired
	private  ShoppingCarServiceImp    shoppingCarService;
	
	@RequestMapping("/showMyShoppingCar/{index}")
	public  String  showMyShoppingCar(HttpServletRequest request,HttpServletResponse response,Model model,@PathVariable("index")int index){
		HttpSession  session=request.getSession(true);
		Member member=(Member) session.getAttribute("member");
		if (member==null) {
			needLoginFail(request, response);
		}
		try {
			Page<Shoppingcar>   page=this.shoppingCarService.getOnesShoppingCarList(member, index);
            List<Shoppingcar> recordList=page.getContent();
            int  max=page.getTotalPages();
            model.addAttribute("max",max);
            model.addAttribute("size",recordList.size());
            model.addAttribute("recordList",recordList);
            model.addAttribute("index", index+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
		
	}
	public void needLoginFail(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('请先登录'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/login'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/intoPage")
	public String intoPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			fail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/shoppingcar/showMyShoppingCar/" + (index - 1);
		} else {
			fail(request, response, i);
		}
		return "Display/showShoppingCar";
	}

	public void fail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/shoppingcar/showMyShoppingCar/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/countAdd/{id}")
	public String  countAdd(@PathVariable("id")int id,int i,HttpServletRequest request,HttpServletResponse response){
		  try {
			Shoppingcar  record=this.shoppingCarService.getShoppingCarById(id);
			int  old=record.getRecordNumber();
			record.setRecordNumber(old+1);
			this.shoppingCarService.addRecord(record);
			successAR(request, response, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
	}
	@RequestMapping("/countReduce/{id}")
	public String  countReduce(@PathVariable("id")int id,int i,HttpServletRequest request,HttpServletResponse response){
		  try {
			Shoppingcar  record=this.shoppingCarService.getShoppingCarById(id);
			int  old=record.getRecordNumber();
			if (old==1) {
	            this.shoppingCarService.deleteShoppingCar(id);
	            successAR(request, response, i);
			}else{
			record.setRecordNumber(old-1);
			this.shoppingCarService.addRecord(record);
			successAR(request, response, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
	}
	
	@RequestMapping("/countDelete/{id}")
	public String  countDelete(@PathVariable("id")int id,int i,int size,   HttpServletRequest request,HttpServletResponse response){
		  try {
            this.shoppingCarService.deleteShoppingCar(id);
			successD(request, response, i,size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
	}
	public void successD(HttpServletRequest request, HttpServletResponse response,int i,int size) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if ((size-1)==0) {//此页没有数据了
				
				if (i-1>0) {//上一页有数据
					out.print("<script> alert('操作成功'); </script>");
					out.print("<script> window.location.href=' http://localhost:8080/shoppingcar/showMyShoppingCar/"+(i-2)+"'   </script>");
				}else{
					out.print("<script> alert('操作成功'); </script>");
					out.print("<script> window.location.href=' http://localhost:8080/shoppingcar/showMyShoppingCar/0'   </script>");
				}
			}else{
				out.print("<script> alert('操作成功'); </script>");
				out.print("<script> window.location.href=' http://localhost:8080/shoppingcar/showMyShoppingCar/"+(i-1)+"'   </script>");
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	public void successAR(HttpServletRequest request, HttpServletResponse response,int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
				out.print("<script> alert('操作成功'); </script>");
				out.print("<script> window.location.href=' http://localhost:8080/shoppingcar/showMyShoppingCar/"+(i-1)+"'   </script>");
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
