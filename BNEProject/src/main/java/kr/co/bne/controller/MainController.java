package kr.co.bne.controller;

import java.io.IOException;

import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.dto.WeeklyReportDetailDTO;
import kr.co.bne.service.DailyReportService;
import kr.co.bne.service.UserService;
import kr.co.bne.service.WeeklyReportService;

@Controller
public class MainController {
	@Autowired
	WeeklyReportService weeklyReportService;
	@Autowired
	DailyReportService dailyReportService;
	@Autowired
	UserService userService;
	
	private JsonObject parseWeeklyReportDetailDTO(WeeklyReportDetailDTO result){
		JsonObject weeklyReportDetail = new JsonObject();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String employee_id = result.getWeeklyReportDTO().getEmployee_id();
		
		JsonElement weeklyReportDTO = parser.parse(gson.toJson(result.getWeeklyReportDTO())).getAsJsonObject();
		JsonArray weeklyPlanDTOList = parser.parse(gson.toJson(result.getWeeklyPlanDTOList())).getAsJsonArray();
		JsonArray planDetailDTOList = parser.parse(gson.toJson(result.getPlanDetailDTOList())).getAsJsonArray();
		JsonElement department_name = parser.parse(userService.selectEmployee(employee_id).getDepartment_name());
		JsonElement employee_name = parser.parse(userService.selectEmployee(employee_id).getEmployee_name());
		
		weeklyReportDetail.add("weeklyReportDTO", weeklyReportDTO);
		weeklyReportDetail.add("weeklyPlanDTOList", weeklyPlanDTOList);
		weeklyReportDetail.add("planDetailDTOList", planDetailDTOList);
		weeklyReportDetail.add("department_name", department_name);
		weeklyReportDetail.add("employee_name", employee_name);
		
		return weeklyReportDetail;
	}
	
	
	@RequestMapping("/main")
	public String goMain(HttpServletRequest request,HttpSession session) throws Exception {
		//주간테이블
		EmployeeDTO loginEmployee = (EmployeeDTO)session.getAttribute("user");
		if(loginEmployee == null) {
			return "redirect:/user/goLoginForm";
		}
		
		Calendar calendar = Calendar.getInstance();
		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR); 
		int year = calendar.get(Calendar.YEAR);
		String weekly_report_id = year+"_"+week_of_year+"_"+loginEmployee.getEmployee_id();
		System.out.println("weeklyReportId:"+weekly_report_id);
		WeeklyReportDetailDTO reportDetail = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);
		JsonObject weeklyReportDetail = null;
		if(reportDetail.getWeeklyReportDTO() != null)
			weeklyReportDetail = parseWeeklyReportDetailDTO(reportDetail);
		
		request.setAttribute("weeklyReportDetail", weeklyReportDetail);
		request.setAttribute("employee_Id", loginEmployee.getEmployee_id());
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

	

	
	
}
