package kr.co.bne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bne.service.NoticeService;

@Controller
@RequestMapping("/alarm")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/detail")
	public String main() {
		return "alarmDetail";
	}
	
}
