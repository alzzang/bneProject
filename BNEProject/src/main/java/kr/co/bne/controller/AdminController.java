package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.common.DepartmentTeamList;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DepartmentDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.UserService;
import kr.co.bne.service.DepartmentService;

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
	@RequestMapping("/employee/delete/{employee_id}")
	public String goManageEmployeeView(Model model, HttpServletRequest request, @PathVariable String employee_id) {
		userService.deleteEmployee(employee_id);
		return "redirect:/admin/employee/1";
	}
	
	@RequestMapping("/department")
	public String goManageDepartmentView(Model model, HttpServletRequest request, HttpSession session) {
		return  goManageDepartmentView(model, request, session,  1);
	}


	@RequestMapping("/department/{page}")
	public String goManageDepartmentView(Model model, HttpServletRequest request, HttpSession session,  @PathVariable("page") int page) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		//admin이 아닌 직책이 접근하려 하면 막아주기 위함
		if(!"admin".equals(user.getPosition())) {
			return "redirect:/user/goLoginForm";
		}

		HashMap<String, String> serviceParams = new HashMap<String, String>(); //service에 요청할 파라미터

		Enumeration parameterNames = request.getParameterNames(); //request 파라미터 이름들 받아옴
		while(parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();

			if(!request.getParameter(parameterName).trim().equals("")) {
				serviceParams.put(parameterName, request.getParameter(parameterName));
				model.addAttribute(parameterName, (String)serviceParams.get(parameterName));
			}
		}
		
		System.out.println("서비스파람:" + serviceParams.toString());
		
		String optionQuery = request.getQueryString();
		if(optionQuery != null) {
			model.addAttribute("optionQuery", "?" + optionQuery);
		}
		
		HashMap<String, Object> resultMap = userService.pagingDepartmentSearchResultList(page, 15, serviceParams);
		List<DepartmentTeamList> departmentList = (List<DepartmentTeamList>)resultMap.get("departmentList");
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
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("endIdx", endIdx);
		model.addAttribute("startIdx", startIdx);
		System.out.println(model.toString());
		return "department_admin";
	}
	
	@RequestMapping("/teamList")
	public @ResponseBody List<EmployeeDTO> getEmpOfDept(@RequestParam("departmentId") int departmentId ,HttpServletRequest req, HttpServletResponse res) {

		List<EmployeeDTO> list =userService.getEmpOfDept(departmentId);
		return list;
	}
	
	@RequestMapping("/department/delete/{department_id}")
	public String goDeleteDepartment(Model model, HttpServletRequest request, @PathVariable int department_id) {
		userService.deleteDepartment(department_id);
		
		return "redirect:/admin/department/1";
	}
	
	@RequestMapping("/department/update/{department_id}")
	public String goUpdateDepartment(Model model, HttpServletRequest request, @PathVariable Integer department_id,@ModelAttribute("DepartmentTeamList")DepartmentTeamList deptlist) {
		System.out.println("뭐가"+deptlist);
		/*HashMap<String, String> map=new HashMap<String, String>();
		map.put("department_id",department_id.toString());
		map.put("department_name", request.getParameter("department_name"));
		map.put("employee_name", request.getParameter("employee_name"));
		map.put("telephone", request.getParameter("telephone"));
		map.put("manager_id", request.getParameter("manager_id"));
		System.out.println(map.toString());*/
		userService.updateDepartment(deptlist);
		
		return "redirect:/admin/department/1";
	}
	@RequestMapping("/department/departmentSearch")
	public void goSearchDepartment(Model model, HttpServletRequest request, HttpServletResponse res) throws IOException {


		int department_id=userService.searchDepartment(request.getParameter("department_name"));
		PrintWriter pw = res.getWriter();
		pw.print(department_id);
		pw.flush();
		pw.close();
	}
	@RequestMapping("/department/updateall")
	public String goUpdateDepartmentAll(Model model, HttpServletRequest request, HttpServletResponse res) throws IOException {


		JsonParser parser = new JsonParser();
		JsonArray json = (JsonArray) parser.parse(request.getParameter("departmentJson"));

		List<DepartmentTeamList> list = new ArrayList<DepartmentTeamList>();
		for (int i = 0; i < json.size(); i++) {
			DepartmentTeamList dto = (new Gson()).fromJson(json.get(i), DepartmentTeamList.class);
			userService.updateDepartment(dto);
		}
		return "redirect:/admin/department/1";
	}
	
	 @RequestMapping("/department/add")
	   public String addDepartment(Model model, HttpServletRequest request, HttpServletResponse res) throws IOException {
	      
	      String a = request.getParameter("department_add").toString();
	      int idxS = a.indexOf(":");
	      int idxE = a.indexOf("}");
	      String deptName=a.substring(idxS+2, idxE-1);
	      departmentService.addDepartment(deptName);
	      return "redirect:/admin/department/1";
	   }
	 @RequestMapping("/department/searchManager")
	 public void goSearchManager(Model model, HttpServletRequest request, HttpServletResponse res) throws IOException {


			int cnt=departmentService.searchManager(request.getParameter("manager_id"));
			PrintWriter pw = res.getWriter();
			pw.print(cnt);
			pw.flush();
			pw.close();
		}
}

	

