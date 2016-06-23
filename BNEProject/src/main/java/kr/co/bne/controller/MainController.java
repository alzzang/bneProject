package kr.co.bne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyReport")
public class MainController {
	@RequestMapping(value= "/main")
	public String goMain() {
		System.out.println("hello");
		return "dailyReportMain";
	}
	
	@RequestMapping("/write")
	public String goWriteform() {
		return "dailyReport_Writeform";
	}
	
	@RequestMapping("/detail")
	public String goViewmanager() {
		return "dailyReportDetail";
	}
	
	@RequestMapping("/WeeklyWrite")
	public String WeeklyWriteForm(){
		return "WeeklyWriteForm";
	}
	
}
