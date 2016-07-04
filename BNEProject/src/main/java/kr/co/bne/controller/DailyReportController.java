package kr.co.bne.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.DailyReportService;

@Controller
@RequestMapping("/dailyReport")
public class DailyReportController {
	
	private static final int PER_CONTENT_NUM = 15;


	@Autowired
	private DailyReportService dailyReportService;

	
	@RequestMapping(value="/main") 
	public String goMain(Model model, HttpServletRequest request, HttpSession session){
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
		
		if(user == null) {
			return "redirect:/user/login";
		}
		
		String position = user.getPosition();

		if("manager".equals(position)){ //manager이면
			return "redirect:/dailyReport/main/all/1";
		}else {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id() + "/1";
		}
	}
	
	
	
	
	@RequestMapping(value="/main/all") 
	public String goMain_Manager(Model model, HttpServletRequest request, HttpSession session){
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");		

		if(user == null) {
			return "redirect:/user/login";
		}

		//employee인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if(!"manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id();
		}
		
		return goMain_Manager(model, request, session, 1);
	}


	
	
	@RequestMapping(value="/main/all/{page}") 
	public String goMain_Manager(Model model, HttpServletRequest request, HttpSession session, @PathVariable("page") int page){
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");		
		
		if(user == null) {
			return "redirect:/user/login";
		}
		
		HashMap<String, Object> dailyReportListMap = null;		
		HashMap<String, Object> serviceParams = new HashMap<String, Object>();
		int totalUnapprovalNum = 0;
		List<DailyReportTeamListElement> memberList = null;
		
		//employee인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if(!"manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id();
		}
		
		Enumeration parameterNames = request.getParameterNames();
				
		while(parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();
			
			if("employee_id".equals(parameterName)) {
				serviceParams.put("employee_id", request.getParameter(parameterName));
				model.addAttribute("currentEmployee_id", (String)serviceParams.get("employee_id"));
			}else if("reg_date".equals(parameterName)) {
				serviceParams.put("reg_date", request.getParameter(parameterName));
				model.addAttribute("currentReg_date", (String)serviceParams.get("reg_date"));
			}else if("approval_flag".equals(parameterName)) {
				serviceParams.put("approval_flag", Integer.parseInt(request.getParameter(parameterName)));
				
				if((Integer)serviceParams.get("approval_flag") == 0) {
					model.addAttribute("currentApproval_flag", "미승인 목록");
				}else if((Integer)serviceParams.get("approval_flag") == 1) {
					model.addAttribute("currentApproval_flag", "승인 목록");
				}
			}
		}
		System.out.println(serviceParams.toString());
		Gson gson = new Gson();
		String serviceParamsStr = gson.toJson(serviceParams);
		
		
		dailyReportListMap = dailyReportService.selectDailyReportList("manager", user.getEmployee_id(), page, PER_CONTENT_NUM, serviceParams);
		
		
		HashMap<String, Object> TeamMemeberMenuList = dailyReportService.selectTeamMemberList(user.getEmployee_id());
		totalUnapprovalNum = (Integer) TeamMemeberMenuList.get("totalUnapprovalNum");
		memberList = (List<DailyReportTeamListElement>) TeamMemeberMenuList.get("memberList");
		
		
		model.addAttribute("dailyReportList", dailyReportListMap.get("dailyReportList"));
		model.addAttribute("totalPageNum", dailyReportListMap.get("totalPageNum"));
		model.addAttribute("totalUnapprovalNum", totalUnapprovalNum);
		model.addAttribute("memberList", memberList);
		model.addAttribute("serviceParamsStr", serviceParamsStr);
		
		model.addAttribute("currentPage", page);		
		
		
		model.addAttribute("url", "/dailyReport/main/all");
		
		return "dailyReportMain";
	}
	
	
	
	
	
	
	@RequestMapping(value="/main/employee/{id}") 
	public String goMain_Employee(Model model, HttpServletRequest request, HttpSession session, @PathVariable("id") String employee_id){
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		if(user == null) {
			return "redirect:/user/login";
		}

		//manager인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if("manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/all/1";
		}

		return goMain_Employee(model, request, session, employee_id, 1);
	}
	
	
	@RequestMapping(value="/main/employee/{id}/{page}") 
	public String goMain_Employee(Model model, HttpServletRequest request, HttpSession session, @PathVariable("id") String employee_id, @PathVariable("page") int page){
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
		
		if(user == null) {
			return "redirect:/user/login";
		}
		
		HashMap<String, Object> dailyReportListMap = null;		
		HashMap<String, Object> serviceParams = new HashMap<String, Object>();
		int totalUnapprovalNum = 0;
		List<DailyReportTeamListElement> memberList = null;
		
		
		//manager인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if("manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/all/1";
		}
		
		
		Enumeration parameterNames = request.getParameterNames();
		
		if(parameterNames.hasMoreElements()) { //파라미터가 존재하면
			while(parameterNames.hasMoreElements()) {
				String parameterName = (String)parameterNames.nextElement();
				
				if("reg_date".equals(parameterName)) {
					serviceParams.put("reg_date", request.getParameter(parameterName));
				}else if("approval_flag".equals(parameterName)) {
					serviceParams.put("approval_flag", request.getParameter(parameterName));
				}
			}
		}else {//파라미터가 존재하지 않으면
			
		}
				
		dailyReportListMap = dailyReportService.selectDailyReportList("employee", user.getEmployee_id(), page, PER_CONTENT_NUM, serviceParams);
		
		
		HashMap<String, Object> TeamMemeberMenuList = dailyReportService.selectTeamMemberList(user.getEmployee_id());
		totalUnapprovalNum = (Integer) TeamMemeberMenuList.get("totalUnapprovalNum");
		memberList = (List<DailyReportTeamListElement>) TeamMemeberMenuList.get("memberList");
		
		
		model.addAttribute("dailyReportList", dailyReportListMap.get("dailyReportList"));
		model.addAttribute("totalPageNum", dailyReportListMap.get("totalPageNum"));
		model.addAttribute("memberList", memberList);
		model.addAttribute("currentPage", page);
		model.addAttribute("serviceParams", serviceParams);
		model.addAttribute("url", "/dailyReport/main/employee/" + user.getEmployee_id());
		
		return "dailyReportMain";
	}

		
	
	

	@RequestMapping("/write")
	public String goWriteform() {
		return "dailyReport_Writeform";
	}

	@RequestMapping("/detail")
	public String goViewmanager() {
		return "dailyReportDetail";
	}
	

	

}