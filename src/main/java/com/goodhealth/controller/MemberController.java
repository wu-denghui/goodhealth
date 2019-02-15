/**
 * 
 */
package com.goodhealth.controller;



import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.goodhealth.pojo.Drug;
import com.goodhealth.pojo.Member;
import com.goodhealth.service.imp.MemberServiceImp;

/**
 * @author 24663
 * @date 2018年12月31日
 * @Description
 */
@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private   MemberServiceImp  memberService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model,String name, String password) {
		String error = null;
		Member member = null;
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		if (name==null|password==null|name==""|password=="") {
			error = "请输入正确的用户名及密码";
			model.addAttribute("error", error);
			return "Display/login";
		}
		try {
			 member = memberService.getMemberByName(name);
			if (member == null) {
				error = "用户名错误或该用户不存在";
				model.addAttribute("error", error);
				return "Display/login";
			} else {
				if (password .equals(member.getMemberPassword())){
					HttpSession session = request.getSession(true);
					session.setAttribute("member", member);
					return "redirect:/goodhealth/index.jsp";
				} else {
					error = "密码错误，请重新输入";
					model.addAttribute("error", error);
					return "Display/login";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/goodhealth/index.jsp";
	}
	
	@RequestMapping("/mangerLogin")
	public String mangerLogin(HttpServletRequest request, Model model,String name, String password) {
		String error = null;
		Member member = null;
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		if (name==null|password==null|name==""|password=="") {
			error = "请输入正确的账号及密码";
			model.addAttribute("error", error);
			return "Manger/login";
		}
		try {
			 member = memberService.getMemberByName(name);
			 if (member.getMemberStatus()==0) {
					error = "请输入正确的账号及密码";
					model.addAttribute("error", error);
					return "Manger/login";
		    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (member == null) {
			error = "账号错误";
			model.addAttribute("error", error);
			return "Manger/login";
		} else {
			if (password .equals(member.getMemberPassword())){
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", member);
				return "Manger/manger";
			} else {
				error = "密码错误，请重新输入";
				model.addAttribute("error", error);
				return "Manger/login";
			}
		}
	}
	
	public void success(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
//			window.location='../index '
			out = response.getWriter();
			out.print("<script> alert('注册成功,您已成功登录'); </script>");
//			window.location.href=' http://localhost:8080/MyStore/drug/showIndexPage/" '+i+' " '  
		    out.print("<script> window.location.href=' http://localhost:8080/goodhealth/index.jsp'  </script>");
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response,Model model,
			@Valid Member member,BindingResult  result) {
		Member m = null;
		try {
			 m=memberService.getMemberByName(member.getMemberName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (m!=null) {
			model.addAttribute("member", member);
			model.addAttribute("duplicateName", "该用户名已经被注册");
			return "Display/register";
		}
		// 获取校验错误信息
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("member", member);
			model.addAttribute("allErrors", allErrors);
			return "Display/register";
		}
		try {
			memberService.addMember(member);
			HttpSession session = request.getSession(true);
			session.setAttribute("member", member);
			success(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"redirect:/goodhealth/index.jsp";

	}
	
	
	@RequestMapping("/home")
	public  String  home(HttpServletRequest request,HttpServletResponse  response,Model  model){
		Member member=(Member) request.getSession().getAttribute("member");
		if (member==null) {
			needLoginFail(request, response);
		}
		if (member.getMemberStatus()==1) {
			return  "Manger/adminManger";
		}else{
			model.addAttribute("member", member);
			return  "Manger/MManger";
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
	@RequestMapping("/showMember")
	public  String   showMember(HttpServletRequest request, Model  model){
		Member member=(Member) request.getSession().getAttribute("member");
		try {//重新加载更新过的用户
			Member mem=this.memberService.getMemberById(member.getMemberId());
			model.addAttribute("searchStatus", 1);
			model.addAttribute("member", mem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/MManger";
	}
	
	
	@RequestMapping("/editMember")
	public  String  editMember(HttpServletRequest request,HttpServletResponse response,Model model,int  id,
			@Valid Member member,BindingResult  result){
		// 获取校验错误信息
		if (result.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			// 将错误信息传到页面
			model.addAttribute("searchStatus", 1);
			Member mem=(Member) request.getSession().getAttribute("member");
			model.addAttribute("member", mem);
			model.addAttribute("allErrors", allErrors);
			return "Manger/MManger";
		}
		try {
			Member  m=this.memberService.getMemberById(id);
			m.setMemberAddress(member.getMemberAddress());
			m.setMemberBirthday(member.getMemberBirthday());
			m.setMemberEmail(member.getMemberEmail());
			m.setMemberName(member.getMemberName());
			m.setMemberPassword(member.getMemberPassword());
			m.setMemberTell(member.getMemberTell());
			this.memberService.addMember(m);
			successEdit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/MManger";
		
	}
	
	public void successEdit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('修改成功'); </script>");
		    out.print("<script> window.location.href=' http://localhost:8080/member/showMember'  </script>");
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/showAdvice")
	public  String  showAdvice(Model  model){
		model.addAttribute("searchStatus", 3);
		return "Manger/MManger";
	}
	
	
	@RequestMapping("memberManger/{index}")
	public  String  memberManger(@PathVariable(value = "index") int index,Model model ){
		try {
			Page<Member>  page=this.memberService.findAllByPage(index);
			List<Member> list=page.getContent();
			int maxPages = page.getTotalPages();
			model.addAttribute("memberList", list);
			model.addAttribute("max", maxPages);
			model.addAttribute("index", index + 1);
			model.addAttribute("memberManger", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mangerStatus", 1);
		return "Manger/member";
	}
	
	@RequestMapping("/intoPage")
	public String intoPage(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("into")==""|request.getParameter("into")==null) {
			showMemberFail(request, response, 1);
		}
		int index = Integer.parseInt(request.getParameter("into"));
		int max = Integer.parseInt(request.getParameter("max"));
		int i = Integer.parseInt(request.getParameter("index"));
		if (max >= index&&index>0) {
			return "redirect:/member/memberManger/" + (index - 1);
		} else {
			showMemberFail(request, response, i);
		}
		return "Manger/member";
	}
	
	public void showMemberFail(HttpServletRequest request, HttpServletResponse response, int i) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('页码错误'); </script>");
			out.print("<script> window.location.href=' http://localhost:8080/member/memberManger/" + (i - 1) + "'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
    @RequestMapping("/logout")
	public String exit(HttpSession session) {

		// 清除session
		session.invalidate();

		// 重定向到首页
		return "redirect:/goodhealth/index.jsp";
	}
    
    
    
    @RequestMapping("/search")
	public  String  search(HttpServletRequest request, HttpServletResponse response,String  searchMess,Model model,int index){
		try {
			Page<Member>  page=this.memberService.findListByLikeName(searchMess,index);
			List<Member> list=page.getContent();
			if (list.isEmpty()) {
				model.addAttribute("memberManger",2);
				searchFail(request,response);
			}else{
			model.addAttribute("memberList", list);
			model.addAttribute("memberManger",2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Manger/member";
	}
	
	public void searchFail(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script> alert('搜索无结果，请输入其他关键字'); </script>");
			out.print("<script> window.location.href='http://localhost:8080/member/memberManger/0'  </script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
