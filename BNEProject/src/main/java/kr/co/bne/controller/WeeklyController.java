package kr.co.bne.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.common.WeeklyReportSearchElement;
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
		
		System.out.println(weeklyReportDetail.get("weeklyReportDTO").isJsonNull());
		
		return weeklyReportDetail;
	}
	
	
	@RequestMapping("/writeForm")
	public String WeeklyWriteForm(Model model, HttpSession session) throws Exception{
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user");
		
		System.out.println("들어옴");
		if(loginEmployee == null) 
				return "main";
		
		String loginId = loginEmployee.getEmployee_id();
		
		int salesGoal = weeklyReportService.getSalesGoal(loginId);
		int monthlySales = weeklyReportService.getThisMonthlySales(loginId);
		//HashMap<String, String> dayList = weeklyReportService.getDayList(loginId);
		
		model.addAttribute("salesGoal", salesGoal);
		model.addAttribute("monthlySales", monthlySales);
		//model.addAttribute("dayList", dayList);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("currentDate", dateFormat.format(calendar.getTime()));
		
		System.out.println(loginEmployee.toString());
		
		return "weeklyWrite";
	}
	
	@RequestMapping("/detail/{employeeId}")
	public ModelAndView WeeklyDetail(Model model,HttpServletRequest request,@PathVariable("employeeId") String employeeId, String weeklyReportId) throws Exception {
		ModelAndView mv  = new ModelAndView("weeklyDetail");
		EmployeeDTO eDTO = userService.selectEmployee(employeeId);
		
		System.out.println("넘어왔니?? " + weeklyReportId);
		
		
		if(eDTO == null) {
			mv.setViewName("main");
			return mv;
		}
		List<String> reportId_list = weeklyReportService.selectAllReportId(eDTO.getEmployee_id());
		
		String weekly_report_id = "";
		
		if(weeklyReportId == null || "".equals(weeklyReportId))
			weekly_report_id = reportId_list.get(reportId_list.size()-1);
		else{
			weekly_report_id = weeklyReportId;
		}
		WeeklyReportDetailDTO result = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);	

		JsonObject weeklyReportDetail = parseWeeklyReportDetailDTO(result);
		
		System.out.println("주간계획 : " + weeklyReportDetail.toString());
		
		mv.addObject("weeklyReportDetail", weeklyReportDetail);
		mv.addObject("reportIdList", reportId_list);
		mv.addObject("employee_Id", employeeId);
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
		List<PlanDetailDTO> planDetailList = new ArrayList();
		
		WeeklyReportDTO weeklyReportDTO;
		weeklyReportDTO = (new Gson()).fromJson(jsonWeeklyReport, WeeklyReportDTO.class);

		for(int i=0;i<json.size();i++){
			PlanDetailDTO dto;
	        dto=(new Gson()).fromJson(json.get(i), PlanDetailDTO.class);
	        planDetailList.add(dto);
	        
	        dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
	      }
		
		JsonArray json1 = (JsonArray)parser.parse(sales);
		List<WeeklyPlanDTO> weeklyPlanList = new ArrayList<WeeklyPlanDTO>();
		
		for(int i = 0; i<json1.size(); i++){
			WeeklyPlanDTO dto = new WeeklyPlanDTO();

			dto = (new Gson()).fromJson(json1.get(i), WeeklyPlanDTO.class);
			dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
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
		WeeklyReportDetailDTO reportDetail = null;
		System.out.println(reportId);
		try {
			reportDetail = weeklyReportService.selectWeeklyReportDetail(reportId);
			
			System.out.println(reportDetail.toString());
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
	
	@RequestMapping("/modifyWeeklyReport")
	public ModelAndView modifyWeeklyReport(Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
		ModelAndView mv = new ModelAndView("weeklyModify");
		String weeklyReportId = (String)request.getParameter("weekly_report_id");
		System.out.println("weeklyReportId"+weeklyReportId);
		WeeklyReportDetailDTO result = weeklyReportService.selectWeeklyReportDetail(weeklyReportId);
		
		JsonObject weeklyReportDetail = parseWeeklyReportDetailDTO(result);
		
		String employee_id = result.getWeeklyReportDTO().getEmployee_id();
		
		EmployeeDTO employeeDTO = userService.selectEmployee(employee_id);
		
		String department_name = employeeDTO.getDepartment_name();
		String employee_name = employeeDTO.getEmployee_name();
		
		mv.addObject("weeklyReportDetail", weeklyReportDetail);
		mv.addObject("department_name", department_name);
		mv.addObject("employee_name", employee_name);
		mv.addObject("employee_Id", employee_id);
		System.out.println("여기까지옴");
		return mv;
	}
	
	@RequestMapping("/modify")
	public void modify(HttpServletResponse response,HttpServletRequest request, @RequestParam("report")String weeklyReport, @RequestParam("sales")String sales )throws Exception{
		System.out.println("Modify");
		String weeklyPlan =  request.getParameter("weeklyPlan");
		
		JsonParser parser= new JsonParser();
		
		JsonArray json=(JsonArray)parser.parse(weeklyPlan);
		JsonObject jsonWeeklyReport = (JsonObject)parser.parse(weeklyReport);
		List<PlanDetailDTO> planDetailList = new ArrayList();
		
		WeeklyReportDTO weeklyReportDTO;
		weeklyReportDTO = (new Gson()).fromJson(jsonWeeklyReport, WeeklyReportDTO.class);

		for(int i=0;i<json.size();i++){
			PlanDetailDTO dto;
	        dto=(new Gson()).fromJson(json.get(i), PlanDetailDTO.class);
	        planDetailList.add(dto);
	        
	        dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
	      }
		
		JsonArray json1 = (JsonArray)parser.parse(sales);
		List<WeeklyPlanDTO> weeklyPlanList = new ArrayList<WeeklyPlanDTO>();
		
		for(int i = 0; i<json1.size(); i++){
			WeeklyPlanDTO dto = new WeeklyPlanDTO();

			dto = (new Gson()).fromJson(json1.get(i), WeeklyPlanDTO.class);
			dto.setWeekly_report_id(weeklyReportDTO.getWeekly_report_id());
			weeklyPlanList.add(dto);
		}
		
		
		WeeklyReportDetailDTO weeklyReportDetail = new WeeklyReportDetailDTO();
		weeklyReportDetail.setWeeklyReportDTO(weeklyReportDTO);
		weeklyReportDetail.setPlanDetailDTOList(planDetailList);
		weeklyReportDetail.setWeeklyPlanDTOList(weeklyPlanList);
		// 작성 부분		
		int result = weeklyReportService.modifyWeeklyReport(weeklyReportDetail);
		
		System.out.println("redirect:/weeklyReport/detail/"+weeklyReportDTO.getEmployee_id());
		//return "redirect:/weeklyReport/detail/"+weeklyReportDTO.getEmployee_id()+"/";
	}
	
	@RequestMapping("/search")
	public ModelAndView searchWeeklyReport(HttpSession session, HttpServletRequest request,HttpServletResponse response)throws Exception {
		ModelAndView mv = new ModelAndView("weeklySearch");
		
		EmployeeDTO loginUser = (EmployeeDTO) session.getAttribute("user");
		
		String employee_id = loginUser.getEmployee_id();
		String department_id = loginUser.getDepartment_id() + "";
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		
		parameterMap.put("employee_id", employee_id);
		parameterMap.put("department_id", department_id);
		
		System.out.println(loginUser.toString());

		// 내 주간계획 목록
		List<WeeklyReportSearchElement> myWeeklyReportList = weeklyReportService.selectWeeklyReportSearch(parameterMap);
		mv.addObject("myWeeklyReportList", myWeeklyReportList);
		
		// 내 부서의 주간계획 목록
		parameterMap.replace("employee_id", null);
		List<WeeklyReportSearchElement> myDeptWeeklyReportList = weeklyReportService.selectWeeklyReportSearch(parameterMap);
		mv.addObject("myDeptWeeklyReportList", myDeptWeeklyReportList);

		// 전체 주간계획 목록
		parameterMap.replace("department_id", null);
		List<WeeklyReportSearchElement> allDeptWeeklyReportList = weeklyReportService.selectWeeklyReportSearch(parameterMap);
		mv.addObject("allDeptWeeklyReportList", allDeptWeeklyReportList);
		
		return mv;
	}
	
	
}
