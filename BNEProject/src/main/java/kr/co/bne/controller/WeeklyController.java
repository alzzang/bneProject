package kr.co.bne.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.WeeklyReportService;

@Controller
public class WeeklyController {
	
	@Autowired
	WeeklyReportService weeklyReportService;
	
	@RequestMapping("/weeklywrite")
	public String WeeklyWriteForm(Model model, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user"); 
		String loginId = loginEmployee.getEmployee_id();
		
		int salesGoal = weeklyReportService.getSalesGoal(loginId);
		int monthlySales = weeklyReportService.getThisMonthlySales(loginId);
		List<String> dayList = weeklyReportService.getDayList(loginId);
		
		model.addAttribute("salesGoal", salesGoal);
		model.addAttribute("monthlySales", monthlySales);
		model.addAttribute("dayList", dayList);
		
		return "weeklyWriteForm";
	}
}
