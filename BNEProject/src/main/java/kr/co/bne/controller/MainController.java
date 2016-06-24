package kr.co.bne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyReport")
public class MainController {
	@RequestMapping("/WeeklyWrite")
	public String WeeklyWriteForm(){
		return "WeeklyWriteForm";
	}
	
}
