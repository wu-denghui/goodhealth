/**
 * 
 */
package com.goodhealth.controller;


import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goodhealth.pojo.Member;
import com.goodhealth.pojo.OrderItem;
import com.goodhealth.pojo.Orders;
import com.goodhealth.pojo.Shoppingcar;
import com.goodhealth.service.imp.MemberServiceImp;
import com.goodhealth.service.imp.OrderItemServiceImp;
import com.goodhealth.service.imp.OrderServiceImp;
import com.goodhealth.service.imp.ShoppingCarServiceImp;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */
@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderServiceImp orderService;
	
	@Autowired
	private MemberServiceImp memberService;
	
	@Autowired
	private OrderItemServiceImp   orderItemServic;
	

	@Autowired
	private ShoppingCarServiceImp shoppingCarService;

	@RequestMapping("/generateOrderMany")
	public String generateOrderMany(HttpServletRequest request,HttpServletResponse response,int  form,int  size) {
		String[] id = request.getParameterValues("intoOrder");
		int  reduce=id.length;
		Orders order = new Orders();
		order.setMemberId(((Member)request.getSession(true).getAttribute("member")).getMemberId());
         java.util.Date  date=new  java.util.Date();
     	SimpleDateFormat  sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     	SimpleDateFormat  oDateFormat=new  SimpleDateFormat("yyyyMMddHHmmss");
     	String  time=sdf.format(date);
     	double  a=Math.floor(Math.random()*100);
     	String  orderId=oDateFormat.format(date)+String.valueOf(a);
     	order.setOrderDate(time);
		try {
			int  sum=0;
			int prizesum=0;
			int recordId;
			Shoppingcar record ;
			int  recordCount;
			int  prize;
			double  b;
			OrderItem item;
			Date da;
			String  itemId;
			StringBuffer  sBuffer = new StringBuffer();
			for (int i = 0; i < id.length; i++) {
				recordId = Integer.parseInt(id[i]);
				System.out.println(recordId);
				record = this.shoppingCarService.getShoppingCarById(recordId);
		     	item=new  OrderItem();
		     	da=new Date();
		     	b=Math.floor(Math.random()*100);
		     	itemId=oDateFormat.format(da)+String.valueOf(b);
		     	System.out.println(itemId);
				item.setId(itemId);
				item.setDrugId(record.getDrug().getDrugId());
				item.setNum(record.getRecordNumber());
				System.out.println(orderId);
				item.setOrderId(orderId);
				this.orderItemServic.saveItem(item);
				recordCount=(record.getRecordNumber())*(record.getDrug().getDrugPrice().intValue());
				prize=(record.getRecordNumber())*(record.getDrug().getDrugIntegral());
			   sBuffer.append( record.getDrug().getDrugName()+"*"+record.getRecordNumber());
			   sBuffer.append("\r\n"); 
				sum+=recordCount;
				prizesum+=prize;
				this.shoppingCarService.deleteShoppingCar(recordId);
			}
			order.setOrderId(orderId);
			order.setOrderCount(new  BigDecimal(sum));
			order.setOrderAward(prizesum);
			order.setOrderDetail(sBuffer.toString());
			order.setOrderStatus(0);
			this.orderService.addOrder(order);
			success(request, response, form,size,reduce);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
	}
	
	@RequestMapping("/generateOrder")
	public String generateOrder(HttpServletRequest request,HttpServletResponse response,int  id,int  form,int  size) {
		Orders order = new Orders();
		order.setMemberId(((Member)request.getSession(true).getAttribute("member")).getMemberId());
         java.util.Date  date=new  java.util.Date();
     	SimpleDateFormat  sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     	SimpleDateFormat  oDateFormat=new  SimpleDateFormat("yyyyMMddHHmmss");
     	String  time=sdf.format(date);
     	String  itemId=oDateFormat.format(date)+String.valueOf(Math.floor(Math.random()*100));
     	String  orderId=oDateFormat.format(date)+String.valueOf(Math.floor(Math.random()*100));
     	order.setOrderDate(time);
     	OrderItem item=new  OrderItem();
		try {
			StringBuffer  sBuffer = new StringBuffer();
			Shoppingcar  record = this.shoppingCarService.getShoppingCarById(id);
			item.setId(itemId);
			item.setDrugId(record.getDrug().getDrugId());
			item.setNum(record.getRecordNumber());
			item.setOrderId(orderId);
		    int recordCount=(record.getRecordNumber())*(record.getDrug().getDrugPrice().intValue());
			int prize=(record.getRecordNumber())*(record.getDrug().getDrugIntegral());
		    sBuffer.append( record.getDrug().getDrugName()+"*"+record.getRecordNumber());
			sBuffer.append("\r\n"); 
			this.shoppingCarService.deleteShoppingCar(id);
			order.setOrderId(orderId);
			order.setOrderCount(new  BigDecimal(recordCount));
			order.setOrderAward(prize);
			order.setOrderDetail(sBuffer.toString());
			order.setOrderStatus(0);
			this.orderService.addOrder(order);
			this.orderItemServic.saveItem(item);
			success(request, response, form,size,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showShoppingCar";
	}
	
	@RequestMapping("/payOrder/{id}")
	public   String  payOrder(HttpServletRequest  request ,HttpServletResponse response,Model  model, @PathVariable(value="id")String  id){
		Orders  orders;
			try {
				orders=this.orderService.findOrderById(id);
				model.addAttribute("order",orders);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "Display/showPayOrders";
	}
	
	
/*	@RequestMapping("/pay")
	public   String  pay(HttpServletRequest  request ,HttpServletResponse response,Model  model, int  id){
		Member  member=(Member) request.getSession(true).getAttribute("member");
		int  old=member.getMemberIntegral();
		String  contacts=request.getParameter("orderContacts");
		String  tell=request.getParameter("orderTell");
		String  address=request.getParameter("orderAddress");
		if (contacts==null|tell==null|address==null) {
			 String  error="请将信息填写完整";
			 model.addAttribute("error", error);
		}
			try {
				Orders orders=this.orderService.findOrderById(id);
				//赠送奖励积分
				int xin=old+orders.getOrderAward();
				member.setMemberIntegral(xin);
				this.memberService.addMember(member);
				//更新订单地址、联系人、联系电话
				orders.setOrderAddress(address);
				orders.setOrderContacts(contacts);
				orders.setOrderTell(tell);
				orders.setOrderStatus(1);			
				this.orderService.addOrder(orders);
				successPay(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "showPayOrders";
	}*/
	
	
	
	@RequestMapping("/pay")
	public   String  pay(HttpServletRequest  request ,HttpServletResponse response,Model  model, 
			String  id,@Validated(value={ValidGroup1.class}) Orders od,BindingResult  result){
		// 获取校验错误信息
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("order", od);
			model.addAttribute("allErrors", allErrors);
			return "Display/showPayOrders";
		}
		Member  member=(Member) request.getSession(true).getAttribute("member");
		int  old=member.getMemberIntegral();
		String  contacts=request.getParameter("orderContacts");
		String  tell=request.getParameter("orderTell");
		String  address=request.getParameter("orderAddress");
		if (contacts==null|tell==null|address==null) {
			 String  error="请将信息填写完整";
			 model.addAttribute("error", error);
		}
			try {
		         java.util.Date  date=new  java.util.Date();
		      	SimpleDateFormat  sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      	String  time=sdf.format(date);
				Orders orders=this.orderService.findOrderById(id);
				//赠送奖励积分
				int xin=old+orders.getOrderAward();
				member.setMemberIntegral(xin);
				this.memberService.addMember(member);
				//更新订单地址、联系人、联系电话、订单支付时间
				orders.setOrderAddress(od.getOrderAddress());
				orders.setOrderContacts(od.getOrderContacts());
				orders.setOrderTell(od.getOrderTell());
				orders.setOrderStatus(1);			
				orders.setOrderDate(time);
				this.orderService.addOrder(orders);
				successPay(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "Display/showPayOrders";
	}
	public void successPay(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('支付成功，可在个人中心-已完成订单中查询'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/order/showOrder/0'   </script>");
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/showOrder/{index}")
	public   String  showOnesOrder(@PathVariable(value="index")int index,Model model,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession(true);
		Member member=(Member) session.getAttribute("member");
		if (member == null) {
			needLoginFail(request, response);
		}
		try {
			Page<Orders> p = this.orderService.findOnesOrder(member, index);
			List<Orders> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addAttribute("orderList", list);
			model.addAttribute("max", maxPages);
			model.addAttribute("index", index + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Display/showOrder";
	}
	
	@RequestMapping("/showCompletedOrder/{index}")
	public  String  showCompletedOrder(@PathVariable(value="index")int index,HttpServletRequest request, HttpServletResponse response,Model model){
		HttpSession session=request.getSession(true);
		Member member=(Member) session.getAttribute("member");
		try {
			Page<Orders> p = this.orderService.findOnesCompletedOrder(member, index);
			List<Orders> list = p.getContent();
			int maxPages = p.getTotalPages();
			model.addAttribute("completedOrderList", list);
			model.addAttribute("searchStatus", 2);
			model.addAttribute("max", maxPages);
			model.addAttribute("index", index + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/MManger";
		
	}
	
	@RequestMapping("/intoCPage")
	public String intoCPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			showCOrderFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/order/showCompletedOrder/" + (index - 1);
		} else {
			showCOrderFail(request, response, i);
		}
		return "Manger/MManger";
	}
	
	public void showCOrderFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/order/showCompletedOrder/" + (i - 1) + "'  </script>");
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
	
	@RequestMapping("/intoPage")
	public String intoPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			showOrderFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/order/showOrder/" + (index - 1);
		} else {
			showOrderFail(request, response, i);
		}
		return "Display/showOrder";
	}
	
	
	public void showOrderFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/order/showOrder/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	
	public void success(HttpServletRequest request, HttpServletResponse response,int i,int size,int reduce) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if ((size-reduce)==0) {//此页没有数据了
				
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
}
