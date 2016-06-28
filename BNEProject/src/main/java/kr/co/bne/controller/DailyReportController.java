package kr.co.bne.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


	@RequestMapping(value= "/main")
	public String goMain(Model model, HttpSession session) {
		return goMain(model, session, 1);
	}
	
	
	
	@RequestMapping("/main/{page}")
	public String goMain(Model model, HttpSession session, @PathVariable("page") int page) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
		HashMap<String, Object> dailyReportListMap = null;
		
		if(user == null) {
			return "redirect:/user/login";
		}
		
		String user_id = user.getEmployee_id();
		
		if(user.getPosition().equals("manager")) {
			HashMap<String, Object> TeamMemeberMenuList = dailyReportService.selectTeamMemberList(user_id);
			int totalUnapprovalNum = (Integer) TeamMemeberMenuList.get("totalUnapprovalNum");
			List<DailyReportTeamListElement> memberList = (List<DailyReportTeamListElement>) TeamMemeberMenuList.get("memberList");
			
			dailyReportListMap = dailyReportService.selectDailyReportList_All(user.getDepartment_id(), page, PER_CONTENT_NUM, null);
			
			model.addAttribute("totalUnapprovalNum", totalUnapprovalNum);
			model.addAttribute("memberList", memberList);
		} else {
			dailyReportListMap = dailyReportService.selectDailyReportList_All(user.getDepartment_id(), page, PER_CONTENT_NUM, user.getEmployee_id());
		}
		
		List<DailyReportListElement> reportList = (List<DailyReportListElement>) dailyReportListMap.get("reportList");
		int totalPageNum = (Integer) dailyReportListMap.get("totalPageNum");
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("currentPage", page);
		
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