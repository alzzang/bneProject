package kr.co.bne.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value= "/main")
	public String goMain() {
			return "dailyReport_Writeform";
		
	}
	@RequestMapping(value= "/editor")
	public String goEditor(HttpServletResponse res,HttpServletRequest req){

		return "aaa";
	}
	@RequestMapping(value="/smarteditor")
	public String getSmartEditor(HttpServletResponse res,HttpServletRequest req) throws ServletException, IOException
	{
		System.out.println("aaaa");
		return "edit";
	}

}
