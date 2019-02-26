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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.goodhealth.pojo.Prize;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.Orders;
import com.goodhealth.service.imp.MemberServiceImp;
import com.goodhealth.service.imp.OrderServiceImp;
import com.goodhealth.service.imp.PrizeServiceImp;

/**
 * @author 24663
 * @date 2019年1月7日
 * @Description
 */
@Controller
@RequestMapping("prize")
public class PrizeController {

	@Autowired
	private PrizeServiceImp prizeService;
	
	@Autowired
	private  OrderServiceImp  orderService;

	@Autowired
	private MemberServiceImp memberService;

	@RequestMapping("/findAll/{index}")
	public ModelAndView showAllPrize(@PathVariable(value = "index") int index) {
		ModelAndView model = new ModelAndView("Display/prizeHome");
		try {
			Page<Prize> p = this.prizeService.findAllByPage(index);
			List<Prize> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addObject("prizeList", list);
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
			jumpFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/prize/findAll/" + (index - 1);
		} else {
			jumpFail(request, response, i);
		}
		return "Display/prizeHome";
	}

	public void jumpFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/prize/findAll/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/exchange")
	public String exchange(HttpServletRequest request, HttpServletResponse response, 
		String name,	int value, int from,Model  model,String orderId) {
		Member member = (Member) request.getSession(true).getAttribute("member");
		int old = member.getMemberIntegral();
		try {
			if (old < value) {
				exchangeFail(request, response, from);
			} else {
				member.setMemberIntegral(old - value);
				this.memberService.addMember(member);
				Orders  orders=this.orderService.findOrderById(orderId);
				orders.setOrderAdditional(name);
				this.orderService.addOrder(orders);
				exchangeSuccess(request, response, orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return    "Display/showPrize";
	}



	public void exchangeSuccess(HttpServletRequest request, HttpServletResponse response, String i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('兑换成功，积分商品将和您的订单一起送达'); </script>");
			out.print(
					"<script> window.location.href=' http://localhost:8080/order/payOrder/" + i + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	public void exchangeFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('积分不够'); </script>");
			out.print(
					"<script> window.location.href=' http://localhost:8080/prize/showPrize/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/addPrize")
	public  String  addPrize(@Valid Prize  prize,BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response){
		// 获取校验错误信息
		try {
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("prize", prize);
			model.addAttribute("prizeManger", 1);
			model.addAttribute("allErrors", allErrors);
			return "Manger/prize";
		}
			this.prizeService.addPrize(prize);
			successAdd(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/prize";
	}
	
	public void successAdd(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('添加成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/prizeManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/searchForUpdate")
	public  String  searchPrizeForUpdate(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
		   Prize  prize=this.prizeService.findPrizeByName(searchMess);
			if (prize==null) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("prizeManger", 2);
			model.addAttribute("prize", prize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/prize";
	}
	
	@RequestMapping("/searchForDelete")
	public  String  searchPrizeForDelete(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model){
		try {
		   Prize  prize=this.prizeService.findPrizeByName(searchMess);
			if (prize==null) {
				searchFailManger(request,response);
			}else{
			model.addAttribute("prizeManger", 3);
			model.addAttribute("prize", prize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/prize";
	}
	
	public void searchFailManger(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('搜索无结果，请输入其他关键字'); </script>");
			out.print("<script> window.location.href='http://localhost:8080/page/prizeManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/editPrize")
	public  String  editPrize(@Valid Prize  prize,BindingResult result,HttpServletRequest request, HttpServletResponse response,int   id,Model  model ){
		// 获取校验错误信息
			if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("prize", prize);
			model.addAttribute("prizeManger", 2);
			model.addAttribute("allErrors", allErrors);
			return "Manger/prize";
		}
		try {
			Prize  d=this.prizeService.findById(id);
			d.setPrizeName(prize.getPrizeName());
			d.setPrizeValue(prize.getPrizeValue());
			d.setPrizePic(prize.getPrizePic());
			this.prizeService.addPrize(d);
			successEdit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Manger/prize";
	}
	@RequestMapping("/deletePrize")
	public  String  deletePrize(int  id,HttpServletRequest request, HttpServletResponse response){
		try {
			this.prizeService.deletePrizeById(id);
			successDelete(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/prize";
	}
	public void successEdit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('修改成功'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/page/prizeManger'  </script>");
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
			out.print("<script> window.location.href=' http://localhost:8080/page/prizeManger'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	
	@RequestMapping("/showPrize/{index}")
	public ModelAndView showPrize(@PathVariable(value = "index") int index,int  orderId) {
		ModelAndView model = new ModelAndView("Display/showPrize");
		try {
			Page<Prize> p = this.prizeService.findAllByPage(index);
			List<Prize> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addObject("prizeList", list);
			model.addObject("max", maxPages);
			model.addObject("orderId", orderId);
			model.addObject("index", index + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/into")
	public String into(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			jumpFai(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/prize/showPrize/" + (index - 1);
		} else {
			jumpFai(request, response, i);
		}
		return "Display/showPrize";
	}

	public void jumpFai(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/prize/showPrize/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
