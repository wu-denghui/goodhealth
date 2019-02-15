/**
 * 
 */
package com.goodhealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author 24663
 * @date 2019年1月1日
 * @Description
 */
@Controller
@RequestMapping("page")
public class PageController {
	
	@RequestMapping("Admin")
	public   String  manger(){
		
		return "Manger/login";
	}
	
	@RequestMapping("login")
	public   String  login(){
		return "Display/login";
	}

	@RequestMapping("register")
	public  String  register(){
		return "Display/register";
	}
	
	
	@RequestMapping("drugManger")
	public  String  drugManger(Model model ){
		model.addAttribute("mangerStatus", 2);
		return "Manger/adminManger";
	}

	@RequestMapping("prizeManger")
	public  String  prizeManger(Model model ){
		model.addAttribute("mangerStatus", 3);
		return "Manger/adminManger";
	}
	
	@RequestMapping("newsManger")
	public  String  newsManger(Model model ){
		model.addAttribute("mangerStatus", 4);
		return "Manger/adminManger";
	}
	
	@RequestMapping("addDrug")
	public  String  addDrug(Model model ){
		model.addAttribute("drugManger", 1);
		return "Manger/drug";
	}
	
	@RequestMapping("addNews")
	public  String  addNews(Model model ){
		model.addAttribute("newsManger", 1);
		return "Manger/news";
	}
	
	@RequestMapping("addPrize")
	public  String  addPrize(Model model ){
		model.addAttribute("prizeManger", 1);
		return "Manger/prize";
	}
}
