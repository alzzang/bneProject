package kr.co.bne.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.EmployeeDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/employee")
	public String goManageEmployeeView(Model model, HttpServletRequest request, HttpSession session, String employee_id) {
		return goManageEmployeeView(model, request, session, employee_id, 1);
	}


	@RequestMapping("/employee/{page}")
	public String goManageEmployeeView(Model model, HttpServletRequest request, HttpSession session, String employee_id, @PathVariable("page") int page) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		//admin이 아닌 직책이 접근하려 하면 막아주기 위함
		if(!"admin".equals(user.getPosition())) {
			return "redirect:/user/goLoginForm";
		}

		HashMap<String, Object> serviceParams = new HashMap<String, Object>(); //service에 요청할 파라미터

		Enumeration parameterNames = request.getParameterNames(); //request 파라미터 이름들 받아옴
		while(parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();

			if(!request.getParameter(parameterName).trim().equals("") && !request.getParameter(parameterName).trim().equals("*")) {
				serviceParams.put(parameterName, request.getParameter(parameterName));
				model.addAttribute(parameterName, (String)serviceParams.get(parameterName));
			}
		}

		model.addAttribute("page", page);

		System.out.println(serviceParams.toString());

		return "mainboard_admin";
	}

}
