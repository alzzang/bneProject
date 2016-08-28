package kr.co.bne.controller;

import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bne.dto.DepartmentDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.DepartmentService;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;

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
				model.addAttribute(parameterName, request.getParameter(parameterName));
				System.out.println(request.getParameter(parameterName));
				System.out.println("parameterName  :"+parameterName);
			}
		}
		System.out.println("서비스파람:" + serviceParams.toString());
		
		String optionQuery = request.getQueryString();
		if(optionQuery != null) {
			model.addAttribute("optionQuery", "?" + optionQuery);
		}
		
		HashMap<String, Object> resultMap = userService.pagingEmployeeSearchResultList(page, 10, serviceParams);
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
		
		List<DepartmentDTO> departmentList = departmentService.getDepartmentList();
		
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("endIdx", endIdx);
		model.addAttribute("startIdx", startIdx);

		return "mainboard_admin";
	}
	
	
	
	@RequestMapping("/employee/delete/{employee_id}")
	public String goManageEmployeeView(Model model, HttpServletRequest request, @PathVariable String employee_id) {
		userService.deleteEmployee(employee_id);
		
		return "redirect:/admin/employee/1";
	}
	
	
	@RequestMapping("/employee/add")
	public String addEmployees(Model model, HttpServletRequest request) {
		String employeeList_jsonStr = request.getParameter("employee_add_info");
		System.out.println(employeeList_jsonStr);
		Type listType = new TypeToken<List<EmployeeDTO>>() {}.getType();
		List<EmployeeDTO> employeeList =  (new Gson()).fromJson(employeeList_jsonStr, listType);
		
		userService.signUp(employeeList);
		
		return "redirect:/admin/employee/1";
	}
	
	@RequestMapping("/employee/existCheck/{id}")
	public @ResponseBody HashMap<String, String> Employee(@PathVariable String id) {
		Boolean result = userService.isExistEmployee(id);
		HashMap<String, String> resultmap = new HashMap<String, String>();
		resultmap.put("result", result.toString());
		
		return resultmap;
	}
	
	
	@RequestMapping("/employee/update/{id}")
	public String updateEmployee(@PathVariable String id, HttpServletRequest request) {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployee_id(id);
		employee.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		employee.setEmail(request.getParameter("email"));
		employee.setEmployee_name(request.getParameter("employee_name"));
		employee.setMobile_phone(request.getParameter("mobile_phone"));
		
		Boolean result = userService.updateEmploye(employee);
		 
		return "redirect:/admin/employee/1";
	}

}
