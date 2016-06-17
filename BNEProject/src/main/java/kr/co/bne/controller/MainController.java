package kr.co.bne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/main")
	public String goMain() {
		System.out.println("aaa");
		return "main";
	}
	public String goMen(){
		return "manin1";
	}
}
