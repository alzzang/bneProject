package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println(dailyReportDTO);
		dailyReportService.writeDailyReport(dailyReportDTO);
		
		
		System.out.println("JSON"+req.getParameter("counsellingJSON"));
		return model;
	}
	
	
	@RequestMapping("/detail")
	public ModelAndView goViewmanager() {
		String id="60";
		DailyReportDetailDTO dailyReport=dailyReportService.viewReport(id);
		List<CounsellingRecordDTO> counsellingRecord=dailyReportService.searchCounselRecord(id);
		System.out.println(counsellingRecord);
		ModelAndView model=new ModelAndView("dailyReportDetail");
		model.addObject("dailyReport", dailyReport);
		model.addObject("counselList",counsellingRecord);
		System.out.println(dailyReport);

		
		return model;
	}
	
	@RequestMapping("/inputarea")
	public String goInputarea(){
		return "inputarea";
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
		System.out.println("aa"+aa);
	}

}
