package kr.co.bne.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
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

		HashMap<String, String> serviceParams = new HashMap<String, String>(); //service에 요청할 파라미터

		Enumeration parameterNames = request.getParameterNames(); //request 파라미터 이름들 받아옴
		while(parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();

			if(!request.getParameter(parameterName).trim().equals("") && !request.getParameter(parameterName).trim().equals("*")) {
				serviceParams.put(parameterName, request.getParameter(parameterName));
				model.addAttribute(parameterName, (String)serviceParams.get(parameterName));
			}
		}
		System.out.println("서비스파람:" + serviceParams.toString());
		
		String optionQuery = request.getQueryString();
		if(optionQuery != null) {
			model.addAttribute("optionQuery", "?" + optionQuery);
		}
		
		HashMap<String, Object> resultMap = userService.pagingEmployeeSearchResultList(page, 15, serviceParams);
		List<EmployeeDTO> employeeList = (List<EmployeeDTO>)resultMap.get("employeeList");
		int totalPageNum = (Integer)resultMap.get("totalPageNum");
		
		int startIdx = 0;
		int endIdx = 0;
		for(int i=1; i <= Math.ceil((double)totalPageNum/4); i++) {
			startIdx = 1 + (i-1)*4;
			endIdx = i*4;
			
			if((page >= startIdx) && (page <= endIdx)) {
				if(endIdx >= totalPageNum) {
					endIdx = totalPageNum;
				}
				break;		
			}
		}
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("endIdx", endIdx);
		model.addAttribute("startIdx", startIdx);

		return "mainboard_admin";
	}
	
	@RequestMapping("/teamList")
	public @ResponseBody List<EmployeeDTO> getEmpOfDept(@RequestParam("departmentId") int departmentId ,HttpServletRequest req, HttpServletResponse res) {

		List<EmployeeDTO> list =userService.getEmpOfDept(departmentId);
		return list;
	}
}

	

