package kr.co.bne.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.dto.PlanDetailDTO;
import kr.co.bne.dto.WeeklyReportDTO;
import kr.co.bne.dto.WeeklyReportDetailDTO;
import kr.co.bne.service.UserService;
import kr.co.bne.service.WeeklyReportService;

@Controller
@RequestMapping("/weeklyReport")
public class WeeklyController {
	
	@Autowired
	WeeklyReportService weeklyReportService;
	@Autowired
	UserService userService;
	
	private JsonObject parseWeeklyReportDetailDTO(WeeklyReportDetailDTO result){
		JsonObject weeklyReportDetail = new JsonObject();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		
		JsonObject weeklyReportDTO = parser.parse(gson.toJson(result.getWeeklyReportDTO())).getAsJsonObject();
		JsonArray weeklyPlanDTOList = parser.parse(gson.toJson(result.getWeeklyPlanDTOList())).getAsJsonArray();
		JsonArray planDetailDTOList = parser.parse(gson.toJson(result.getPlanDetailDTOList())).getAsJsonArray();
		
		weeklyReportDetail.add("weeklyReportDTO", weeklyReportDTO);
		weeklyReportDetail.add("weeklyPlanDTOList", weeklyPlanDTOList);
		weeklyReportDetail.add("planDetailDTOList", planDetailDTOList);
		
		return weeklyReportDetail;
	}
	
	@RequestMapping("/writeForm")
	public String WeeklyWriteForm(Model model, HttpSession session) throws Exception{
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user");
		
		System.out.println("들어옴");
		if(loginEmployee == null) 
				return "main";
		
		String loginId = loginEmployee.getEmployee_id();
		
		//int salesGoal = weeklyReportService.getSalesGoal(loginId);
		//int monthlySales = weeklyReportService.getThisMonthlySales(loginId);
		//HashMap<String, String> dayList = weeklyReportService.getDayList(loginId);
		
		//model.addAttribute("salesGoal", salesGoal);
		//model.addAttribute("monthlySales", monthlySales);
		//model.addAttribute("dayList", dayList);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("currentDate", dateFormat.format(calendar.getTime()));
		
		System.out.println(loginEmployee.toString());
		
		return "weeklyWrite";
	}
	
	@RequestMapping("/detail/{id}")
	public ModelAndView WeeklyDetail(Model model,HttpServletRequest request,@PathVariable("id") String employeeId) throws Exception {
		ModelAndView mv  = new ModelAndView("weeklyDetail");
		/*EmployeeDTO eDTO = (EmployeeDTO)request.getSession().getAttribute("user");*/
		EmployeeDTO eDTO = userService.selectEmployee(employeeId);
		
		if(eDTO == null) {
			mv.setViewName("main");
			return mv;
		}
		List<String> reportId_list = weeklyReportService.selectAllReportId(eDTO.getEmployee_id());
		String lastWeeklyReportId = reportId_list.get(reportId_list.size()-1);
		WeeklyReportDetailDTO weeklyReportDetail = weeklyReportService.selectWeeklyReportDetail(lastWeeklyReportId);	
		

		JsonObject weeklyReportDetail = parseWeeklyReportDetailDTO(result);
		
		String employee_id = result.getWeeklyReportDTO().getEmployee_id();
		
		EmployeeDTO employeeDTO = userService.selectEmployee(employee_id);
		
		String department_name = employeeDTO.getDepartment_name();
		String employee_name = employeeDTO.getEmployee_name();
		
		System.out.println("주간계획 : " + weeklyReportDetail.toString());
		
		mv.addObject("weeklyReportDetail", weeklyReportDetail);
		mv.addObject("department_name", department_name);
		mv.addObject("employee_name", employee_name);
		mv.addObject("reportIdList", reportId_list);
		return mv;
	}
	
	@RequestMapping("/weeklywriteimpl")
	public String WeeklyWriteImpl(Model model, HttpServletRequest request) throws Exception{
		
		WeeklyReportDTO dto = new WeeklyReportDTO();

		return "main";
	}
	@RequestMapping("/write")
	public String WeeklyWrite(Model model,HttpServletResponse response, @RequestParam("report")String weeklyReport,@RequestParam("weeklyPlan")String weeklyPlan  ) throws Exception{
		JsonParser parser= new JsonParser();
		JsonArray json=(JsonArray)parser.parse(weeklyPlan);
		JsonObject jsonWeeklyReport = (JsonObject)parser.parse(weeklyReport);
/*		System.out.println(weeklyPlan);
		System.out.println(json);*/
		List<PlanDetailDTO> list = new ArrayList();
		WeeklyReportName WeeklyReportNameDto;
		WeeklyReportNameDto = (new Gson()).fromJson(jsonWeeklyReport, WeeklyReportName.class);
		
		
		
		for(int i=0;i<json.size();i++){
			PlanDetailDTO dto;
	        dto=(new Gson()).fromJson(json.get(i), PlanDetailDTO.class);
	        list.add(dto);
	        
	        //임시 테스트값 삽입
	        dto.setWeekly_report_id("1");
	        
	        
	        System.out.println(dto);
	      }
		
		// 작성 부분		
		int result = weeklyReportService.writeWeeklyReport(WeeklyReportNameDto, null, list);
		
		
		//System.out.println(json.get(0).);
		return "redirect:/weeklyReport/writeForm";
	}
	@RequestMapping("/getPlan")
	public @ResponseBody WeeklyReportDetailDTO getPlan(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("ReportId")String reportId )  throws Exception{
		System.out.println(reportId);
		WeeklyReportDetailDTO weeklyReportDetail;
		weeklyReportDetail = weeklyReportService.selectWeeklyReportDetail(reportId);
		PlanDetailDTO planDetail = new PlanDetailDTO();
		planDetail.setContent("공부");
		planDetail.setEnd_time("2016-07-21 15:00:00");
		planDetail.setStart_time("2016-07-21 11:00:00");
		List<PlanDetailDTO> planDetailDTOList = new ArrayList();
		planDetailDTOList.add(planDetail);
		weeklyReportDetail.setPlanDetailDTOList(planDetailDTOList);
		
		return weeklyReportDetail;		
	}
	
	
}
