/**
 * 
 */
package com.goodhealth.controller;



import static org.hamcrest.CoreMatchers.theInstance;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Shoppingcar;
import com.goodhealth.service.imp.DrugServiceImp;
import com.goodhealth.service.imp.ShoppingCarServiceImp;

/**
 * @author 24663
 * @date 2019年1月2日
 * @Description
 */
@Controller
@RequestMapping("drug")
public class DrugController {

	@Autowired
	private DrugServiceImp drugService;
	
	@Autowired
	private ShoppingCarServiceImp shoppingCarService;
	
	
	@RequestMapping("/findAll/{index}")
	public ModelAndView findDrug(@PathVariable(value = "index") int index) {
		ModelAndView model = new ModelAndView("Display/showDrug");
		try {
			Page<Drug> p = this.drugService.findAllByPage(index);
			List<Drug> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addObject("drugAllList", list);
			model.addObject("max", maxPages);
			model.addObject("index", index + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

	@RequestMapping("/intoPage")
	public String intoPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			showDrugFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/drug/findAll/" + (index - 1);
		} else {
			showDrugFail(request, response, i);
		}
		return "Display/showDrug";
	}
	
	public void showDrugFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/drug/findAll/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/searchDrug")
	public  String  search(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model,int index){
		try {
			Page<Drug>  page=this.drugService.findDrugListByLikeName(searchMess,index);
			List<Drug> list=page.getContent();
			if (list.isEmpty()) {
				searchFail(request,response);
			}else{
			int maxPages = page.getTotalPages();
			model.addAttribute("mess", searchMess);
			model.addAttribute("resultList", list);
			model.addAttribute("max", maxPages);
			model.addAttribute("index", index + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showSearch";
	}
	
	public void searchFail(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('搜索无结果，请输入其他关键字'); </script>");
			out.print("<script> window.location.href='http://localhost:8080/goodhealth/index.jsp'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/intoSearchPage")
	public String intoSearchPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			showResultFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/drug/searchDrug/" + (index - 1);
		} else {
			showResultFail(request, response, i);
		}
		return "Display/showSearch";
	}
	
	
	public void showResultFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/drug/searchDrug/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
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

	
	
	
	@RequestMapping("/showDetail")
	public String showDetail(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Drug drug = this.drugService.findById(id);
			model.addAttribute("drug", drug);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showDetail";
	}

	@RequestMapping("/intoShoppingCar")
	public String intoShoppingCar(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession(true);
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			needLoginFail(request, response);
		} else {
			try {
			int id = Integer.parseInt(request.getParameter("id"));
			Drug drug = this.drugService.findById(id);
			Shoppingcar  record=this.shoppingCarService.isHas(member, drug);
			int i = Integer.parseInt(request.getParameter("f"));
			Date   d=new   Date();
			SimpleDateFormat  sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String  time=sdf.format(d);
			if (record==null) {//用户member之前未将药品drug放进购物车
				
				/*新建一条记录，将drug放进member的购物车*/
				Shoppingcar re = new Shoppingcar();
				re.setDrug(drug);
				re.setMember(member);
				re.setRecordNumber(1);
				re.setRecordDate(time);
				this.shoppingCarService.addRecord(re);
			}else{
				
			/*	将原来记录的药品购买数目+1*/
			     int  old=record.getRecordNumber();
			     record.setRecordNumber(old+1);
			 	this.shoppingCarService.addRecord(record);
			}
				success(request, response,i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Display/showDrug";
	}

	/**
	 * @param request
	 * @param response
	 * @Description
	 */
	public void success(HttpServletRequest request, HttpServletResponse response ,int  i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('加入成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/drug/findAll/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping("/addDrug")
	public  String  addDrug(@Valid Drug  drug,BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response){
		// 获取校验错误信息
		try {
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("drug", drug);
			model.addAttribute("drugManger", 1);
			model.addAttribute("allErrors", allErrors);
			return "Manger/drug";
		}
			this.drugService.addDrug(drug);
			successAdd(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/drug";
	}
	
	public void successAdd(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('添加成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/addDrug'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/searchForUpdate")
	public  String  searchDrugForUpdate(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
		   Drug  drug=this.drugService.findDrugByName(searchMess);
			if (drug==null) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("drugManger", 2);
			model.addAttribute("drug", drug);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/drug";
	}
	
	@RequestMapping("/searchForDelete")
	public  String  searchDrugForDelete(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
		   Drug  drug=this.drugService.findDrugByName(searchMess);
			if (drug==null) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("drugManger", 3);
			model.addAttribute("drug", drug);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/drug";
	}
	
	public void searchFailManger(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('搜索无结果，请输入其他关键字'); </script>");
			out.print("<script> window.location.href='http://localhost:8080/page/drugManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/editDrug")
	public  String  editDrug(@Validated(value={ValidGroup1.class}) Drug  drug,BindingResult result,HttpServletRequest request, HttpServletResponse response,String  id,Model  model ){
        if (id==null|id=="") {
			model.addAttribute("N", 1);
			model.addAttribute("drugManger", 2);
			return "Manger/drug";
		}
		// 获取校验错误信息
			if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("drug", drug);
			model.addAttribute("drugManger", 2);
			model.addAttribute("allErrors", allErrors);
			return "Manger/drug";
		}
		try {
			Drug  d=this.drugService.findById(Integer.parseInt(id));		
			d.setDrugCharacter(drug.getDrugCharacter());
			d.setDrugComponent(drug.getDrugComponent());
			d.setDrugFunction(drug.getDrugFunction());
			d.setDrugIntegral(drug.getDrugIntegral());
			d.setDrugPic(drug.getDrugPic());
			d.setDrugPrice(drug.getDrugPrice());
			d.setDrugProductor(drug.getDrugProductor());
			d.setDrugStorage(drug.getDrugStorage());
			d.setDrugUsage(drug.getDrugUsage());
			this.drugService.addDrug(d);
			successEdit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Manger/drug";
	}
	@RequestMapping("/deleteDrug")
	public  String  deleteDrug(int  id,HttpServletRequest request, HttpServletResponse response){
		try {
			this.drugService.deleteDrugById(id);
			successDelete(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/drug";
	}
	public void successEdit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('修改成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/drugManger'  </script>");
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
			out.print("<script> window.location.href=' http://localhost:8080/page/drugManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
		
}
