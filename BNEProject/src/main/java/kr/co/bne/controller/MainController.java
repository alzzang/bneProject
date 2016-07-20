package kr.co.bne.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bne.dto.DailyReportChartDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.service.DailyReportService;

@Controller
public class MainController {
	
	@Autowired
	DailyReportService dailyReportService;
	
	@RequestMapping("/main")
	public String goMain() {

		return "mainboard";

	}
	
	


	@RequestMapping(value= "/editor")
	public String goEditor(HttpServletResponse res,HttpServletRequest req){
		return "aaa";
	}
	@RequestMapping(value="/smarteditor")
	public String getSmartEditor(HttpServletResponse res,HttpServletRequest req) throws ServletException, IOException
	{
		return "edit";
	}


	@RequestMapping("/WeeklyWrite")
	public String WeeklyWriteForm(){
		return "WeeklyWriteForm";
	}

	
	@RequestMapping("/monthlySales")
	public @ResponseBody HashMap<String,Integer> getSales(HttpServletResponse res, @RequestParam("employee_id")String id) {
		
		return dailyReportService.selectMonthlyGoal(id);
		
	}
	
	@RequestMapping("/teamMonthlySales")
	public @ResponseBody List<DailyReportEmployeeDTO> getTeamSales(HttpServletResponse res, @RequestParam("department_id")int id) {
		List<DailyReportEmployeeDTO> temp = dailyReportService.selectTeamMonthlyGoal(id);
		System.out.println(temp);
		return temp;
		
	}
	
	
	@RequestMapping(value = "/morrisChartLine", method = { RequestMethod.POST })
	public @ResponseBody HashMap<String,List<?>> getDailyReportChart(HttpServletResponse res, @RequestParam("employee_id") String employee_id)
			throws JSONException, IOException {
		
	//	List<DailyReportChartDTO> dailyRepoartChart = dailyReportService.searchDailyChartLine(employee_id);
	//	System.out.println("!!"+dailyRepoartChart);
		
		
		return dailyReportService.searchDailyChartLine(employee_id);
	}
	
	
}
