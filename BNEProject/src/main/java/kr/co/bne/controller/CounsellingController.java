package kr.co.bne.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/counselling")
public class CounsellingController {

	@RequestMapping(value = "/write", method = { RequestMethod.POST })
	public String writeCounselling(HttpServletResponse response,@Param("content")String content) {
		
		System.out.println(content);
	return "redirect:/main";
	}
	
}
