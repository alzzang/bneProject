package kr.co.bne.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.DailyReportService;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	DailyReportService dailyReportService;
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/getTeamMemberList")
	public @ResponseBody List<EmployeeDTO> getTeamMemberList(HttpSession session) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
		List<EmployeeDTO> teamMemberList = (List<EmployeeDTO>) userService.selectTeamMember_menu(user.getEmployee_id());
		
		return teamMemberList;
	}
	
}
