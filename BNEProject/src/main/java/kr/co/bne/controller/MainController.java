package kr.co.bne.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bne.service.DailyReportService;

@Controller
public class MainController {
	
	@Autowired
	DailyReportService dailyReportService;
	
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@RequestMapping("/main")
	public String goMain() {

		return "mainboard";

	}
		
	@RequestMapping(value= "/editor")
	public String goEditor(HttpServletResponse res,HttpServletRequest req){
		return "aaa";
	}
	@RequestMapping(value="/smarteditor")
	public String getSmartEditor(HttpServletResponse res,HttpServletRequest req) throws ServletException, IOException
	{
		return "edit";
	}


	@RequestMapping("/WeeklyWrite")
	public String WeeklyWriteForm(){
		return "WeeklyWriteForm";
	}

	

	
	
}
