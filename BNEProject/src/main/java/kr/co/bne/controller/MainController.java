package kr.co.bne.controller;

import java.io.IOException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
	
	@Autowired
	UserController usercontroller;
	
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
	public String goMain(HttpServletRequest request,HttpServletResponse res,HttpSession session) throws Exception {
		//주간테이블
		EmployeeDTO loginEmployee = (EmployeeDTO)session.getAttribute("user");
		
					
		if(loginEmployee.getDepartment_id() != 0) { //일반 사용자
		
			return goMain_Normal(request, res, loginEmployee);
			
		}else {//admin
			return "redirect:/admin/employee/1";
		}
	}

	
	private String goMain_Normal(HttpServletRequest request,HttpServletResponse res, EmployeeDTO loginEmployee) throws Exception {
		JsonObject weeklyReportDetail = getWekelyTable(loginEmployee);

		if(weeklyReportDetail != null)
			request.setAttribute("weeklyReportDetail", weeklyReportDetail);
		else{
			boolean result = false;
			request.setAttribute("weeklyReportDetail",result);
		}
		request.setAttribute("employee_Id", loginEmployee.getEmployee_id());
		request.setAttribute("unapproval", unapproval(request,res));
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

	public JsonObject getWekelyTable(EmployeeDTO loginEmployee) throws Exception{
		Calendar calendar = Calendar.getInstance();
		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR); 
		int year = calendar.get(Calendar.YEAR);
		String weekly_report_id = year+"_"+week_of_year+"_"+loginEmployee.getEmployee_id();
		WeeklyReportDetailDTO reportDetail = weeklyReportService.selectWeeklyReportDetail(weekly_report_id);
		JsonObject weeklyReportDetail = null;
		if(reportDetail.getWeeklyReportDTO() != null)
			weeklyReportDetail = parseWeeklyReportDetailDTO(reportDetail);
		return weeklyReportDetail;
	}
	
	public Map<String,Object> unapproval(HttpServletRequest req, HttpServletResponse res) {
	      
	      
	      HttpSession session=req.getSession();
	      EmployeeDTO employee=(EmployeeDTO) session.getAttribute("user");
	   
	      HashMap<String, Object> dailyReportListMap = null;
	      HashMap<String, Object> params=new HashMap<String, Object>();
	      
	      int startIdx=1;
	      int perContentNum=4;
	      
	      if(!employee.getPosition().equals("manager")){
	      params.put("employee_id", employee.getEmployee_id());
	      }
	      
	      params.put("approval_flag", 0);
	      
	      
	      dailyReportListMap = dailyReportService.selectDailyReportList(employee.getEmployee_id(), startIdx, perContentNum, params);
	      
	      //System.out.println(dailyReportListMap);
	      
	      //unList=noticeService.searchUnconfirmedNotice(map);
	      
	      
	   /*   for(int i=0;i<unList.size();i++){
	         String tempTime[]=unList.get(i).getPasstime().split(" ");
	         String setTime="";
	         if(tempTime[0].charAt(0)!='0'){
	            setTime=tempTime[0];
	         }else if(tempTime[1].charAt(0)!='0'){
	            setTime=tempTime[1];
	         }else if(tempTime[2].charAt(0)!='0'){
	            setTime=tempTime[2];
	         }else{
	         for(int j=3;j<tempTime.length;j++){
	            if(tempTime[j].charAt(0)!='0')
	               setTime+=tempTime[j]+" ";
	         }
	         }
	         unList.get(i).setPasstime(setTime);
	      }
	      
	      for(int i=0;i<cnList.size();i++){
	         String tempTime[]=cnList.get(i).getPasstime().split(" ");
	         String setTime="";
	         if(tempTime[0].charAt(0)!='0'){
	            setTime=tempTime[0];
	         }else if(tempTime[1].charAt(0)!='0'){
	            setTime=tempTime[1];
	         }else if(tempTime[2].charAt(0)!='0'){
	            setTime=tempTime[2];
	         }else{
	         for(int j=3;j<tempTime.length;j++){
	            if(tempTime[j].charAt(0)!='0')
	               setTime+=tempTime[j]+" ";
	         }
	         }
	         cnList.get(i).setPasstime(setTime);
	      }
	      
	      ModelAndView model=new ModelAndView("alarmDetail");
	         model.addObject("unList", unList);
	      model.addObject("cnList", cnList);
	      model.addObject("position",position);
	      model.addObject("type", type);*/
	      return dailyReportListMap;
	   }

	
	
}
