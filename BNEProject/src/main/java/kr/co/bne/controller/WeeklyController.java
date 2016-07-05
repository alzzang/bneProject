package kr.co.bne.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bne.service.WeeklyReportService;

@Controller
public class WeeklyController {
	@Autowired
	WeeklyReportService weeklyReportService;
	
	@RequestMapping("/weeklywrite")
	public String WeeklyWriteForm(Model model, HttpServletRequest request){
		/*
		 * 1. 로그인 한 정보를 받고
		 * 2. 
		 */
		
		HttpSession session = request.getSession();
		
		
		return "weeklyWriteForm";
	}
}
