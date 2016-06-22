package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

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

	@RequestMapping(value = "/changeProfile", method = { RequestMethod.GET })
	public String chagePassword(HttpServletRequest req) {

		return "password";
	}


	@RequestMapping(value = "/validCheck", method = { RequestMethod.POST })
	public String validCheck(@RequestParam("id") String id, @RequestParam("password") String rawPassword,
			HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession();
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = userService.validCheck(id, rawPassword);

		if (sessionid == null) {
			if (employeeDTO != null) {
				session.setAttribute("user", employeeDTO);
				return "redirect:/main";
			}
			return "redirect:/user/login";
		}

		else {
			if (employeeDTO != null) {
				
				userService.modifyPassword(id, req.getParameter("newpassword"));
				return "redirect:/main";
			}
			return "redirect:/user/changeProfile";
		}

	}

}
