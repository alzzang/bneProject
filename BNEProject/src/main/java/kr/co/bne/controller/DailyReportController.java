package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.bne.dto.ClientDTO;
import kr.co.bne.dto.CounsellingDetailDTO;

import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.ClientService;
import kr.co.bne.service.DailyReportService;

@Controller
@RequestMapping("/dailyReport")
public class DailyReportController {

	private static final int PER_CONTENT_NUM = 15;

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/main")
	public String goMain(Model model, HttpServletRequest request, HttpSession session) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		String position = user.getPosition();

		if ("manager".equals(position)) { // manager이면
			return "redirect:/dailyReport/main/all/1";
		} else {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id() + "/1";
		}
	}

	@RequestMapping(value = "/main/all")
	public String goMain_Manager(Model model, HttpServletRequest request, HttpSession session) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		// employee인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if (!"manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id();
		}

		return goMain_Manager(model, request, session, 1);
	}

	@RequestMapping(value = "/main/all/{page}")
	public String goMain_Manager(Model model, HttpServletRequest request, HttpSession session,
			@PathVariable("page") int page) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		HashMap<String, Object> dailyReportListMap = null;
		HashMap<String, Object> serviceParams = new HashMap<String, Object>();
		int totalUnapprovalNum = 0;
		List<DailyReportTeamListElement> memberList = null;

		// employee인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if (!"manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/employee/" + user.getEmployee_id();
		}

		Enumeration parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();

			if ("employee_id".equals(parameterName)) {
				serviceParams.put("employee_id", request.getParameter(parameterName));
				model.addAttribute("currentEmployee_id", (String) serviceParams.get("employee_id"));
			} else if ("reg_date".equals(parameterName)) {
				serviceParams.put("reg_date", request.getParameter(parameterName));
				model.addAttribute("currentReg_date", (String) serviceParams.get("reg_date"));
			} else if ("approval_flag".equals(parameterName)) {
				serviceParams.put("approval_flag", Integer.parseInt(request.getParameter(parameterName)));

				if ((Integer) serviceParams.get("approval_flag") == 0) {
					model.addAttribute("currentApproval_flag", "미승인 목록");
				} else if ((Integer) serviceParams.get("approval_flag") == 1) {
					model.addAttribute("currentApproval_flag", "승인 목록");
				}
			} else if ("search_text".equals(parameterName)) {

				serviceParams.put("search_text", request.getParameter(parameterName));
				model.addAttribute("search_text", (String) serviceParams.get("search_text"));
			} else if ("search_type".equals(parameterName)) {
				serviceParams.put("search_type", request.getParameter("search_type"));
				model.addAttribute("search_type", (String) serviceParams.get("search_type"));
			}
		}
		System.out.println(serviceParams.toString());
		Gson gson = new Gson();
		String serviceParamsStr = gson.toJson(serviceParams);

		dailyReportListMap = dailyReportService.selectDailyReportList(user.getEmployee_id(), page, PER_CONTENT_NUM,
				serviceParams);

		HashMap<String, Object> TeamMemeberMenuList = dailyReportService.selectTeamMemberList(user.getEmployee_id());
		totalUnapprovalNum = (Integer) TeamMemeberMenuList.get("totalUnapprovalNum");
		memberList = (List<DailyReportTeamListElement>) TeamMemeberMenuList.get("memberList");

		model.addAttribute("dailyReportList", dailyReportListMap.get("dailyReportList"));
		model.addAttribute("totalPageNum", dailyReportListMap.get("totalPageNum"));
		model.addAttribute("totalUnapprovalNum", totalUnapprovalNum);
		model.addAttribute("memberList", memberList);
		model.addAttribute("serviceParamsStr", serviceParamsStr);

		model.addAttribute("currentPage", page);

		model.addAttribute("url", "/dailyReport/main/all");

		return "dailyReportMain";
	}

	@RequestMapping(value = "/main/employee/{id}")
	public String goMain_Employee(Model model, HttpServletRequest request, HttpSession session,
			@PathVariable("id") String employee_id) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		// manager인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if ("manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/all/1";
		}

		return goMain_Employee(model, request, session, employee_id, 1);
	}

	@RequestMapping(value = "/main/employee/{id}/{page}")
	public String goMain_Employee(Model model, HttpServletRequest request, HttpSession session,
			@PathVariable("id") String employee_id, @PathVariable("page") int page) {
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");

		HashMap<String, Object> dailyReportListMap = null;
		HashMap<String, Object> serviceParams = new HashMap<String, Object>();
		int totalUnapprovalNum = 0;
		List<DailyReportTeamListElement> memberList = null;

		// manager인 사람이 이 url로 접근 하려고 할 때 막아주기 위함
		if ("manager".equals(user.getPosition())) {
			return "redirect:/dailyReport/main/all/1";
		}

		serviceParams.put("employee_id", employee_id);
		model.addAttribute("employee_id", (String) serviceParams.get("employee_id"));

		Enumeration parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();

			if ("reg_date".equals(parameterName)) {
				serviceParams.put("reg_date", request.getParameter(parameterName));
				model.addAttribute("currentReg_date", (String) serviceParams.get("reg_date"));
			} else if ("approval_flag".equals(parameterName)) {
				serviceParams.put("approval_flag", Integer.parseInt(request.getParameter(parameterName)));

				if ((Integer) serviceParams.get("approval_flag") == 0) {
					model.addAttribute("currentApproval_flag", "미승인 목록");
				} else if ((Integer) serviceParams.get("approval_flag") == 1) {
					model.addAttribute("currentApproval_flag", "승인 목록");
				}
			} else if ("search_text".equals(parameterName)) {

				serviceParams.put("search_text", request.getParameter(parameterName));
				model.addAttribute("search_text", (String) serviceParams.get("search_text"));
			} else if ("search_type".equals(parameterName)) {
				serviceParams.put("search_type", request.getParameter("search_type"));
				model.addAttribute("search_type", (String) serviceParams.get("search_type"));
			}
		}
		System.out.println(serviceParams.toString());
		Gson gson = new Gson();
		String serviceParamsStr = gson.toJson(serviceParams);

		dailyReportListMap = dailyReportService.selectDailyReportList(user.getEmployee_id(), page, PER_CONTENT_NUM,
				serviceParams);
		totalUnapprovalNum = dailyReportService.getgetTotalUnapprovalNum("member", employee_id);

		model.addAttribute("dailyReportList", dailyReportListMap.get("dailyReportList"));
		model.addAttribute("totalPageNum", dailyReportListMap.get("totalPageNum"));
		model.addAttribute("totalUnapprovalNum", totalUnapprovalNum);
		model.addAttribute("serviceParamsStr", serviceParamsStr);
		model.addAttribute("currentPage", page);
		model.addAttribute("url", "/dailyReport/main/employee/" + employee_id);

		return "dailyReportMain";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String goUpdate(HttpServletRequest req,HttpServletResponse res) {
		return "redirect:/dailyReport/main";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView goUpdate(@RequestParam("daily_report_id") String id, HttpServletRequest req,
			HttpServletResponse res) {
		DailyReportDTO dailyreport = dailyReportService.searchDailyReport(id);
		List<CounsellingDetailDTO> counsellingRecord = dailyReportService.searchCounselRecord(id);
		String counsellingJson = new Gson().toJson(counsellingRecord);
		String[] tt = dailyreport.getReg_date().split(" ");
		dailyreport.setReg_date(tt[0]);

		List<ClientDTO> clietns = clientService.getClient();

		ModelAndView model = new ModelAndView("updateDailyReport");
		model.addObject("dailyReport", dailyreport);
		System.out.println(dailyreport+"나와라");
		model.addObject("counsellingJson", counsellingJson);
		model.addObject("clients", clietns);
		return model;
	}

	@RequestMapping("/write")
	public ModelAndView goWriteform(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
		if (sessionid.getPosition().equals("manager")) {
			ModelAndView model = new ModelAndView("redirect:/dailyReport/main");
			return model;
		}
		DailyReportEmployeeDTO employee = dailyReportService.searchPreSales(sessionid.getEmployee_id());
		List<ClientDTO> clietns = clientService.getClient();

		ModelAndView model = new ModelAndView("dailyReport_Writeform");
		model.addObject("employee", employee);
		model.addObject("clients", clietns);
		model.addObject("reg_date", req.getParameter("reg_date"));
		return model;
	}

	@RequestMapping("/writeform")
	public ModelAndView goWrite(@ModelAttribute DailyReportDTO dailyReportDTO, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("redirectDetail");
		JsonParser parser = new JsonParser();
		JsonArray json = null;
		List<CounsellingRecordDTO> list = new ArrayList<CounsellingRecordDTO>();
		try {
			json = (JsonArray) parser.parse(req.getParameter("counsellingJSON"));
			for (int i = 0; i < json.size(); i++) {
				CounsellingRecordDTO dto = (new Gson()).fromJson(json.get(i), CounsellingRecordDTO.class);
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dailyReportService.writeDailyReport(dailyReportDTO, list);
		}
		model.addObject("dailyReportId", dailyReportDTO.getDaily_report_id());

		return model;
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String goViewmanager(HttpServletRequest req,
			HttpServletResponse res) {
		return "redirect:/dailyReport/main";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView goViewmanager(@RequestParam("dailyReportId") String id, HttpServletRequest req,
			HttpServletResponse res) {
		
		DailyReportDetailDTO dailyReport = dailyReportService.viewReport(id);

		List<CounsellingDetailDTO> counsellingRecord = dailyReportService.searchCounselRecord(id);

		ModelAndView model = new ModelAndView("dailyReportDetail");
		model.addObject("dailyReport", dailyReport);
		model.addObject("counselList", counsellingRecord);
		System.out.println(dailyReport);
		HttpSession session = req.getSession();
		EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
		DailyReportEmployeeDTO employee = dailyReportService.searchPreSales(dailyReport.getEmployee_id());
		session.setAttribute("employee", employee);

		Enumeration parameterNames = req.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();

			if ("params".equals(parameterName)) {
				model.addObject("params", req.getParameter("params"));
			} else if ("page".equals(parameterName)) {
				model.addObject("page", req.getParameter("page"));
			} else if ("url".equals(parameterName)) {
				model.addObject("url", req.getParameter("url"));
			}
		}
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteDailyReport(@RequestParam("dailyReportId") String id, HttpServletRequest req,
			HttpServletResponse res) {
		dailyReportService.delete(id);
		return "redirect:/dailyReport/write";
	}

	@RequestMapping("/inputarea")
	public String goInputarea() {
		return "inputarea";
	}

	@RequestMapping("/updateform")
	public ModelAndView goUpdateForm(@ModelAttribute DailyReportDTO dailyReportDTO, HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView model = new ModelAndView("redirectDetail");
		JsonParser parser = new JsonParser();
		JsonArray json = (JsonArray) parser.parse(req.getParameter("counsellingJSON"));

		List<CounsellingRecordDTO> list = new ArrayList<CounsellingRecordDTO>();
		for (int i = 0; i < json.size(); i++) {
			CounsellingRecordDTO dto = (new Gson()).fromJson(json.get(i), CounsellingRecordDTO.class);
			list.add(dto);
		}
		dailyReportService.updateDailyReport(dailyReportDTO, list);
		model.addObject("dailyReportId", dailyReportDTO.getDaily_report_id());
		return model;
	}

	@RequestMapping("/dailysales")
	public void goDailysales(@RequestParam("reg_date") String reg_date,@RequestParam("flag") int flag, HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		HttpSession session = req.getSession();
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
		String employee_id = sessionid.getEmployee_id();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("reg_date", reg_date);
		map.put("employee_id", employee_id);
		int daily_sales = 0;

		HashMap<String, Integer> salesMap = new HashMap<String, Integer>();
		System.out.println(map.toString());
		if(flag!=1){
			daily_sales = dailyReportService.confirmDuplicate(map);
		}
		Gson gson = new Gson();
		if (daily_sales >= 1) {
			salesMap.put("flag", -2);
			salesMap.put("daily_report_id", daily_sales);
			String salesParam = gson.toJson(salesMap);
			PrintWriter pw = res.getWriter();
			pw.print(salesParam);
			pw.flush();
			pw.close();
			return;
		}
		try {
			daily_sales = dailyReportService.searchDailySales(map);
			salesMap.put("flag", 0);
			salesMap.put("daily_sales", daily_sales);
			String salesParam = gson.toJson(salesMap);
			PrintWriter pw = res.getWriter();
			pw.print(salesParam);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			salesMap.put("flag", -1);
			salesMap.put("daily_sales", 0);
			String salesParam = gson.toJson(salesMap);
			PrintWriter pw = res.getWriter();
			pw.print(salesParam);
			pw.flush();
			pw.close();
		}
	}

	@RequestMapping(value = "/approval", method = RequestMethod.POST)
	public void goApproval(@RequestParam("report_id") String daily_report_id, HttpServletRequest req,
			HttpServletResponse res) {
		dailyReportService.approvalDailyReport(daily_report_id);

	}

	@RequestMapping(value = "/writecomment", method = RequestMethod.POST)
	public void writeComment(@RequestParam("report_id") String daily_report_id, @RequestParam("comment") String comment,
			HttpServletRequest req, HttpServletResponse res) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("daily_report_id", daily_report_id);
		map.put("comment", comment);
		dailyReportService.writeComment(map);

	}

	@RequestMapping(value = "/deletecomment", method = RequestMethod.POST)
	public void writeComment(@RequestParam("report_id") String daily_report_id, HttpServletRequest req,
			HttpServletResponse res) {

		dailyReportService.removeComment(daily_report_id);

	}

	@RequestMapping("/checkReport")
	public @ResponseBody int checkReport(@RequestParam("date") String date, HttpServletRequest req,
			HttpServletRequest res) {
		HttpSession session = req.getSession();
		EmployeeDTO employeeDto = (EmployeeDTO) session.getAttribute("user");

		return dailyReportService.checkReport(date, employeeDto.getEmployee_id());
	}

}
