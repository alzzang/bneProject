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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.dto.PlanDetailDTO;
import kr.co.bne.dto.WeeklyPlanDTO;
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
		String employee_id = result.getWeeklyReportDTO().getEmployee_id();
		
		JsonObject weeklyReportDTO = parser.parse(gson.toJson(result.getWeeklyReportDTO())).getAsJsonObject();
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
	
	
	@RequestMapping("/writeForm")
	public String WeeklyWriteForm(Model model, HttpSession session) throws Exception{
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user");
		
		System.out.println("들어옴");
		if(loginEmployee == null) 
				return "main";
		
		String loginId = loginEmployee.getEmployee_id();
		
/*		int salesGoal = weeklyReportService.getSalesGoal(loginId);
		int monthlySales = weeklyReportService.getThisMonthlySales(loginId);*/
		//HashMap<String, String> dayList = weeklyReportService.getDayList(loginId);
		
/*		model.addAttribute("salesGoal", salesGoal);
		model.addAttribute("monthlySales", monthlySales);*/
		//model.addAttribute("dayList", dayList);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("currentDate", dateFormat.format(calendar.getTime()));
		
		System.out.println(loginEmployee.toString());
		
		return "weeklyWrite";
	}
	
	@RequestMapping("/detail")
	public ModelAndView WeeklyDetail(Model model,HttpServletRequest request) throws Exception {
		ModelAndView mv  = new ModelAndView("weeklyDetail");
		EmployeeDTO loginEmployeeDTO = (EmployeeDTO)request.getSession().getAttribute("user");

		if(loginEmployeeDTO == null) {
			mv.setViewName("main");
			return mv;
		}
		
		List<String> reportId_list = weeklyReportService.selectAllReportId(loginEmployeeDTO.getEmployee_id());
		
//		System.out.println("리스트가비어있니? : " + reportId_list.isEmpty());
		String lastWeeklyReportId = reportId_list.get(reportId_list.size()-1);
//		System.out.println("그럼 마지막인덱스가 뭐니?? : " + lastWeeklyReportId);
		WeeklyReportDetailDTO result = weeklyReportService.selectWeeklyReportDetail(lastWeeklyReportId);	
		
		JsonObject weeklyReportDetail = parseWeeklyReportDetailDTO(result);
		
		System.out.println("주간계획 : " + weeklyReportDetail.toString());
		
		mv.addObject("weeklyReportDetail", weeklyReportDetail.toString());
		mv.addObject("reportIdList", reportId_list);
		
		return mv;
	}
	
	@RequestMapping("/weeklywriteimpl")
	public String WeeklyWriteImpl(Model model, HttpServletRequest request) throws Exception{
		WeeklyReportDTO dto = new WeeklyReportDTO();
		return "main";
	}
	@RequestMapping("/write")
	public String WeeklyWrite(Model model,HttpServletResponse response,HttpServletRequest request, @RequestParam("report")String weeklyReport, @RequestParam("sales")String sales ) throws Exception{
		String weeklyPlan =  request.getParameter("weeklyPlan");
		
		JsonParser parser= new JsonParser();
		
		JsonArray json=(JsonArray)parser.parse(weeklyPlan);
		JsonObject jsonWeeklyReport = (JsonObject)parser.parse(weeklyReport);
/*		System.out.println(weeklyPlan);
		System.out.println(json);*/
		List<PlanDetailDTO> planDetailList = new ArrayList();
		
		WeeklyReportDTO weeklyReportDTO;
		weeklyReportDTO = (new Gson()).fromJson(jsonWeeklyReport, WeeklyReportDTO.class);
		
		//System.out.println(json.get(0));
		for(int i=0;i<json.size();i++){
			PlanDetailDTO dto;
	        dto=(new Gson()).fromJson(json.get(i), PlanDetailDTO.class);
	        planDetailList.add(dto);
	        
	        dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
	        System.out.println(dto);
	      }
		
		System.out.println("-----------------------------------------------------------------");
		JsonArray json1 = (JsonArray)parser.parse(sales);
		List<WeeklyPlanDTO> weeklyPlanList = new ArrayList<WeeklyPlanDTO>();
		System.out.println("여기까진들어옴");
		
		for(int i = 0; i<json1.size(); i++){
			WeeklyPlanDTO dto = new WeeklyPlanDTO();

			dto = (new Gson()).fromJson(json1.get(i), WeeklyPlanDTO.class);
			dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
			System.out.println(dto);
			weeklyPlanList.add(dto);
		}
		
		
		WeeklyReportDetailDTO weeklyReportDetail = new WeeklyReportDetailDTO();
		weeklyReportDetail.setWeeklyReportDTO(weeklyReportDTO);
		weeklyReportDetail.setPlanDetailDTOList(planDetailList);
		weeklyReportDetail.setWeeklyPlanDTOList(weeklyPlanList);
		// 작성 부분		
		int result = weeklyReportService.writeWeeklyReport(weeklyReportDetail);
		
		
		//System.out.println(json.get(0).);
		return "redirect:/weeklyReport/writeForm";
	}
	@RequestMapping("/getPlan")
	public @ResponseBody WeeklyReportDetailDTO getPlan(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("ReportId")String reportId ){
		WeeklyReportDetailDTO reportDetail = new WeeklyReportDetailDTO();
		
		
		try {
			WeeklyReportDetailDTO result = weeklyReportService.selectWeeklyReportDetail(reportId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		WeeklyReportDTO report = new WeeklyReportDTO();
		report.setTitle("helloWorld");
		report.setDepartment_id(1);
		report.setEmployee_id("1");
		report.setSaleGoal(100000);
		report.setSales(10000);
		report.setWeekly_report_id(10);
		
		reportDetail.setWeeklyReportDTO(report);*/
		return reportDetail;
		
	}
	
	
}
