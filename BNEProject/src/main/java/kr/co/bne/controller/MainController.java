package kr.co.bne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/main")
	public String goMain() {
		return "main";
	}
	
	@RequestMapping("/weeklywrite")
	public String WeeklyWriteForm(){
		return "weeklyWriteForm";
	}
}
