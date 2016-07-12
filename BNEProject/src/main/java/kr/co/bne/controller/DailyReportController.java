package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.DailyReportService;

@Controller
@RequestMapping("/dailyReport")
public class DailyReportController {
	
	@Autowired
	private DailyReportService dailyReportService;
	
	@RequestMapping(value= "/main")
	public String goMain() {
			return "dailyReportMain";
		
	}
	@RequestMapping(value="/update")
	public ModelAndView goUpdate(@RequestParam("daily_report_id")String id,HttpServletRequest req,HttpServletResponse res){
		DailyReportDTO dailyreport=dailyReportService.searchDailyReport(id);
		List<CounsellingDetailDTO> counsellingRecord=dailyReportService.searchCounselRecord(id);
		String counsellingJson=new Gson().toJson(counsellingRecord);
		
		String[] tt=dailyreport.getReg_date().split(" ");
		dailyreport.setReg_date(tt[0]);
		ModelAndView model=new ModelAndView("updateDailyReport");
		model.addObject("dailyReport",dailyreport);
		model.addObject("counsellingJson", counsellingJson);
		return model;
	}

	
	@RequestMapping("/write")
	public ModelAndView goWriteform(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=req.getSession();
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
		System.out.println(sessionid.getEmployee_id());
		DailyReportEmployeeDTO employee=dailyReportService.searchPreSales(sessionid.getEmployee_id());
		ModelAndView model=new ModelAndView("dailyReport_Writeform");
		model.addObject("employee", employee);
		
		
		System.out.println(employee);
		return model;
	}
	@RequestMapping("/writeform")
	public ModelAndView goWrite(@ModelAttribute DailyReportDTO dailyReportDTO  ,HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model=new ModelAndView("dailyReport_Writeform");
		JsonParser parser=new JsonParser();
		JsonArray json=(JsonArray) parser.parse(req.getParameter("counsellingJSON"));
		List<CounsellingRecordDTO> list= new ArrayList<CounsellingRecordDTO>();
		for(int i=0;i<json.size();i++){
			CounsellingRecordDTO dto=(new Gson()).fromJson(json.get(i), CounsellingRecordDTO.class);
			list.add(dto);
			System.out.println(dto);
		}
		dailyReportService.writeDailyReport(dailyReportDTO,list);
		
		return model;
	}
	
	
	@RequestMapping("/detail")
	public ModelAndView goViewmanager() {
		String id="164";
		DailyReportDetailDTO dailyReport=dailyReportService.viewReport(id);
		List<CounsellingDetailDTO> counsellingRecord=dailyReportService.searchCounselRecord(id);
		System.out.println(counsellingRecord);
		ModelAndView model=new ModelAndView("dailyReportDetail");
		model.addObject("dailyReport", dailyReport);
		model.addObject("counselList",counsellingRecord);
		
		/*String aa=new Gson().toJson(counsellingRecord);
		model.addObject("aa", aa);
		System.out.println(":"+aa);*/
		return model;
	}
	
	@RequestMapping("/inputarea")
	public String goInputarea(){
		return "inputarea";
	}
	
	@RequestMapping("/updateform")
	public  ModelAndView  goUpdateForm(@ModelAttribute DailyReportDTO dailyReportDTO ,HttpServletRequest req,HttpServletResponse res){
		ModelAndView model=new ModelAndView("dailyReport_Writeform");
		JsonParser parser=new JsonParser();
		JsonArray json=(JsonArray) parser.parse(req.getParameter("counsellingJSON"));
		
		List<CounsellingRecordDTO> list= new ArrayList<CounsellingRecordDTO>();
		for(int i=0;i<json.size();i++){
			CounsellingRecordDTO dto=(new Gson()).fromJson(json.get(i), CounsellingRecordDTO.class);
			System.out.println(dto);
			list.add(dto);
		}
		dailyReportService.updateDailyReport(dailyReportDTO,list);
		
		return model;
	}
	
	@RequestMapping("/dailysales")
	public void goDailysales(@RequestParam("reg_date")String reg_date, HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		HttpSession session=req.getSession();
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
		String employee_id=sessionid.getEmployee_id();
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("reg_date", reg_date);
		map.put("employee_id", employee_id);
		int daily_sales=0;
		try{
			daily_sales=dailyReportService.searchDailySales(map);
			PrintWriter pw = res.getWriter();
			pw.print(daily_sales);
			pw.flush();
			pw.close();
		}catch(Exception e){
			daily_sales=-1;
			PrintWriter pw = res.getWriter();
			pw.print(daily_sales);
			pw.flush();
			pw.close();
		}
		
		
		/*PrintWriter pw = res.getWriter();
		pw.print(daily_sales);
		pw.flush();
		pw.close();*/
	}
	
	@RequestMapping("/approval")
	public void goApproval(@RequestParam("report_id")String daily_report_id,HttpServletRequest req,HttpServletRequest res){
		dailyReportService.approvalDailyReport(daily_report_id);
	}
	
	@RequestMapping("/jsontest")
	public void goJSON(HttpServletRequest req,HttpServletResponse res){
		String aa=req.getParameter("dd");
	}

}
