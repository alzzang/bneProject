package kr.co.bne.controller;



import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
			
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value = "/goLoginForm", method = { RequestMethod.GET })
	public String goLoginForm() {
		
		return "login";
	}

	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login() {
	
		return "redirect:/user/goLoginForm";
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:/main";
	}
	
	
	@RequestMapping(value = "/changeProfile", method = { RequestMethod.GET})
	public String chagePassword(HttpServletRequest req) {
				
		return "password";
	}
	
	@RequestMapping(value="/authcheck", method={ RequestMethod.POST })
	public String authcheck(@RequestParam ("id") String id,@RequestParam ("password") String password, HttpServletRequest req ){
		
		HttpSession session = req.getSession();
		session.setAttribute("user", id);
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println(id);
		System.out.println(encodedPassword);
	
		 
		return "redirect:/main";
	}
	


}
