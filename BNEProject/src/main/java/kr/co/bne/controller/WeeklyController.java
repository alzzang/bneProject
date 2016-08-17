package kr.co.bne.controller;

import java.io.PrintWriter;
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
	
	
	@RequestMapping("/writeForm")
	public String WeeklyWriteForm(Model model,HttpServletResponse response,HttpServletRequest request, HttpSession session) throws Exception{
		EmployeeDTO loginEmployee = (EmployeeDTO) session.getAttribute("user");

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
		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR) +1; 
		int year = calendar.get(Calendar.YEAR);
		String weekly_report_id = year+"_"+week_of_year+"_"+loginId;
		
		WeeklyReportDetailDTO reportDetail = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);
		if(reportDetail.getWeeklyReportDTO() != null){
			String o = request.getHeader("ORIGIN");
			String referer =  request.getHeader("referer");
			String urlArr[] = referer.split("http://");
			urlArr = urlArr[1].split("/");
			String splitUrl = "/"+urlArr[1];
			String origin[] = referer.split(splitUrl);
			
			String url = referer.replace(origin[0], "");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>var flag = confirm('작성된 계획이 존재합니다. 계획을 보시겠습니까?\\n취소 시 전의 있던 페이지로 이동합니다.'); if(flag == 1){window.location.href='/weeklyReport/detail/"+loginId+"'}else {window.location.href='"+url+"'}</script>");
			out.flush();
			out.close();

			/*if(choice == 0)*/
				//return "redirect:/weeklyReport/detail/"+loginId;
		}
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
		List<String> reportId_list = weeklyReportService.selectAllReportId(eDTO.getEmployee_id());
		
		String weekly_report_id = "";
		if(reportId_list.size()!=0){
			if(weeklyReportId == null || "".equals(weeklyReportId)){
				weekly_report_id = reportId_list.get(reportId_list.size()-1);
				System.out.println(weekly_report_id);
			}
			else{
				weekly_report_id = weeklyReportId;
			}
		}
		WeeklyReportDetailDTO result = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);	
		JsonObject weeklyReportDetail = null;
		if(result.getWeeklyReportDTO()!=null)
			weeklyReportDetail = parseWeeklyReportDetailDTO(result);
		
		//System.out.println("주간계획 : " + weeklyReportDetail.toString());
		
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
	}
}
