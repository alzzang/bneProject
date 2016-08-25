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

import kr.co.bne.common.WeeklyReportMemberInfo;
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
	JsonParser parser = new JsonParser();
	Calendar calendar = Calendar.getInstance();
	
	private JsonObject parseWeeklyReportDetailDTO(WeeklyReportDetailDTO result){
		JsonObject weeklyReportDetail = new JsonObject();
		Gson gson = new Gson();
		
		String employee_id = result.getWeeklyReportDTO().getEmployee_id();
		
		System.out.println("hello"+gson.toJson(result.getPlanDetailDTOList()));
		JsonElement weeklyReportDTO = parser.parse(gson.toJson(result.getWeeklyReportDTO())).getAsJsonObject();
		JsonArray weeklyPlanDTOList = parser.parse(gson.toJson(result.getWeeklyPlanDTOList())).getAsJsonArray();
		JsonArray planDetailDTOList = parser.parse(gson.toJson(result.getPlanDetailDTOList())).getAsJsonArray();
		JsonElement department_name = parser.parse(userService.selectEmployee(employee_id).getDepartment_name());
		JsonElement employee_name = parser.parse(userService.selectEmployee(employee_id).getEmployee_name());
		
		System.out.println("planDetailDTOList:"+planDetailDTOList);
		
		weeklyReportDetail.add("weeklyReportDTO", weeklyReportDTO);
		weeklyReportDetail.add("weeklyPlanDTOList", weeklyPlanDTOList);
		weeklyReportDetail.add("planDetailDTOList", planDetailDTOList);
		weeklyReportDetail.add("department_name", department_name);
		weeklyReportDetail.add("employee_name", employee_name);
		
		//System.out.println(weeklyReportDetail.get("planDetailDTOList"));
		return weeklyReportDetail;
	}
	
	
	@RequestMapping("/writeForm")
	public String WeeklyWriteForm(Model model,HttpServletResponse response,HttpServletRequest request, HttpSession session) throws Exception{
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user");

		if(loginEmployee == null) 
				return "main";
		
		String loginId = loginEmployee.getEmployee_id();
		
		int salesGoal = weeklyReportService.getSalesGoal(loginId);
		int monthlySales = weeklyReportService.getThisMonthlySales(loginId);
		//HashMap<String, String> dayList = weeklyReportService.getDayList(loginId);
		
		model.addAttribute("salesGoal", salesGoal);
		model.addAttribute("monthlySales", monthlySales);
		//model.addAttribute("dayList", dayList);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("currentDate", dateFormat.format(calendar.getTime()));
		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR) +1; 
		int year = calendar.get(Calendar.YEAR);
		String weekly_report_id = year+"_"+week_of_year+"_"+loginId;
		
		boolean result = true;
		
		
		String referer =  request.getHeader("referer");
		String urlArr[] = referer.split("http://");
		urlArr = urlArr[1].split("/");
		String splitUrl = "/"+urlArr[1];
		String origin[] = referer.split(splitUrl);
		
		String url = referer.replace(origin[0], "");
		WeeklyReportDetailDTO reportDetail = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);
		if(reportDetail.getWeeklyReportDTO() != null)
			result = false;
		
		request.setAttribute("employee_Id", loginEmployee.getEmployee_id());
		request.setAttribute("result", result);
		request.setAttribute("beforeUrl",url);
		return "weeklyWrite";
	}
	
	@RequestMapping("/detail/{employeeId}")
	public ModelAndView WeeklyDetail(Model model,HttpServletRequest request,@PathVariable("employeeId") String employeeId, String weeklyReportId) throws Exception {
		ModelAndView mv  = new ModelAndView("weeklyDetail");
		EmployeeDTO eDTO = userService.selectEmployee(employeeId);

		if(eDTO == null) {
			mv.setViewName("main");
			return mv;
		}

		String weekly_report_id = "";
			if(weeklyReportId == null || "".equals(weeklyReportId)){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				model.addAttribute("currentDate", dateFormat.format(calendar.getTime()));
				int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR); 
				int year = calendar.get(Calendar.YEAR);
				weekly_report_id = year+"_"+week_of_year+"_"+eDTO.getEmployee_id();
			}
			else
				weekly_report_id = weeklyReportId;
			
		WeeklyReportDetailDTO weeklyReportDetailDTO = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);

		JsonObject weeklyReportDetail = null;
		if(weeklyReportDetailDTO.getWeeklyReportDTO()!=null)
			weeklyReportDetail = parseWeeklyReportDetailDTO(weeklyReportDetailDTO);
		
		if(weeklyReportDetail != null)
			mv.addObject("weeklyReportDetail", weeklyReportDetail);
		else{
			boolean result = false;
			mv.addObject("weeklyReportDetail",result);
		}
			
		//mv.addObject("reportIdList", reportId_list);
		mv.addObject("employee_Id", employeeId);
		return mv;
	}
	
	@RequestMapping("/weeklywriteimpl")
	public String WeeklyWriteImpl(Model model, HttpServletRequest request) throws Exception{
		return "main";
	}
	
	@RequestMapping("/write")
	public void WeeklyWrite(Model model,HttpServletResponse response,HttpServletRequest request, @RequestParam("report")String weeklyReport, @RequestParam("sales")String sales ) throws Exception{
		String weeklyPlan =  request.getParameter("weeklyPlan");
		
		JsonParser parser= new JsonParser();
		
		JsonArray json=(JsonArray)parser.parse(weeklyPlan);
		JsonObject jsonWeeklyReport = (JsonObject)parser.parse(weeklyReport);
		List<PlanDetailDTO> planDetailList = new ArrayList<PlanDetailDTO>();
		
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
		weeklyReportService.writeWeeklyReport(weeklyReportDetail);
	}
	@RequestMapping("/getPlan")
	public @ResponseBody WeeklyReportDetailDTO getPlan(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("ReportId")String reportId ){
		WeeklyReportDetailDTO reportDetail = null;
		try {
			reportDetail = weeklyReportService.selectWeeklyReportDetail(reportId);
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
	public ModelAndView modifyWeeklyReport(Model model,HttpServletRequest request,HttpServletResponse responsel)throws Exception {
		ModelAndView mv = new ModelAndView("weeklyModify");
		String weeklyReportId = (String)request.getParameter("weekly_report_id");
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
		return mv;
	}
	
	@RequestMapping("/modify")
	public void modify(HttpServletResponse response,HttpServletRequest request, @RequestParam("report")String weeklyReport, @RequestParam("sales")String sales )throws Exception{
		System.out.println("Modify");
		String weeklyPlan =  request.getParameter("weeklyPlan");
		
		JsonParser parser= new JsonParser();
		
		JsonArray json=(JsonArray)parser.parse(weeklyPlan);
		JsonObject jsonWeeklyReport = (JsonObject)parser.parse(weeklyReport);
		List<PlanDetailDTO> planDetailList = new ArrayList<PlanDetailDTO>();
		
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
	}

	
	@RequestMapping("/list")
	public ModelAndView getFirstWeeklyList(HttpServletRequest request) throws Exception {
		ModelAndView mv = getWeeklyList(null, request);
		return mv;
	}
	
	@RequestMapping("/list/{employee_id}")
	public ModelAndView getWeeklyList(@PathVariable String employee_id, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("weeklyList");
		HttpSession session = request.getSession();
		EmployeeDTO loginUser = (EmployeeDTO) session.getAttribute("user");
		
		if(loginUser == null){
			mv.setViewName("weeklyList");
			return mv;
		}

		// keyword
		String keyword = request.getParameter("keyword");
		
		// plan_date
		String planDate = request.getParameter("planDate");
		
		// department_id
		String department_id = loginUser.getDepartment_id() + "";
		
		// pageSize
		int pageSize = 5;

		// page
		String pageStr = request.getParameter("pageStr");

		int page = 0;
		if("".equals(pageStr) || pageStr == null)
			page = 1;
		else								
			page = Integer.parseInt(pageStr);
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("employee_id", employee_id);
		parameterMap.put("department_id", department_id);
		parameterMap.put("keyword", keyword);
		parameterMap.put("pageSize", pageSize);
		parameterMap.put("page", page);
		parameterMap.put("plan_date", planDate);
		
		// 내 부서의 주간계획 목록
		List<WeeklyReportSearchElement> myDeptWeeklyReportList = weeklyReportService.selectWeeklyReportSearch(parameterMap);
		for (WeeklyReportSearchElement weeklyReportSearchElement : myDeptWeeklyReportList) {
			System.out.println(weeklyReportSearchElement);
		}
		
		List<WeeklyReportMemberInfo> myDeptMemberList = weeklyReportService.selectDeptMember(department_id);
//		System.out.println("myDeptMemberList : " + myDeptMemberList.size());
		
		int allReportNum = 0;
		for (WeeklyReportMemberInfo weeklyReportMemberInfo : myDeptMemberList) {
			allReportNum += weeklyReportMemberInfo.getPost_num();
		}
		
		int totalRecordNum = weeklyReportService.selectTotalRecordNum(parameterMap);

		mv.addObject("myDeptWeeklyReportList", myDeptWeeklyReportList);
		mv.addObject("selectedMemberId", employee_id);
		mv.addObject("myDeptMemberList", myDeptMemberList);
		mv.addObject("totalRecordNum", totalRecordNum);
		mv.addObject("allReportNum", allReportNum);
		mv.addObject("keyword", keyword);
		mv.addObject("pageSize", pageSize);
		mv.addObject("page", page);
		mv.addObject("planDate", planDate);
		
		return mv;
	}
	
}
