package kr.co.bne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@RequestMapping("/detail")
	public String main() {
		return "alarmDetail";
	}
	
}
