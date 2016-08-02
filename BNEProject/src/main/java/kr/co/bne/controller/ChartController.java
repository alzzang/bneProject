package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.bne.dto.DailyReportChart2DTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.DailyReportService;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/chart")

public class ChartController {

	@Autowired
	DailyReportService dailyReportService;
	@Autowired
	UserService userService;

	@RequestMapping("/page")
	public String goPage(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		EmployeeDTO employee = (EmployeeDTO) session.getAttribute("user");

		if (employee != null) {
			List<EmployeeDTO> teamMember = userService.getTeamMember(employee.getPosition(),employee.getDepartment_id());
			if (teamMember != null) {
				
				req.setAttribute("member", teamMember);
			}
		}
	
		return "chartPage";
	}

	@RequestMapping("/monthlySales")
	public @ResponseBody HashMap<String, Integer> getSales(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("employee_id") String id) {
		HttpSession session = req.getSession();
		EmployeeDTO employee = (EmployeeDTO) session.getAttribute("user");
		return dailyReportService.selectMonthlyGoal(id, employee.getPosition());
	}

	@RequestMapping("/teamMonthlySales")
	public @ResponseBody List<DailyReportEmployeeDTO> getTeamSales(HttpServletResponse res,
			@RequestParam("department_id") String id) {
		return dailyReportService.selectTeamMonthlyGoal(id);
	}

	@RequestMapping("/customersSales")
	public @ResponseBody List<DailyReportChart2DTO> getMemberSales(HttpServletRequest req, HttpServletResponse res, @RequestParam("department_id") int departmentId) {
		
		return dailyReportService.selectCustomersSales(departmentId);
	}
	@RequestMapping("/vehicleGauge")
	public void getVehicleGauge(HttpServletRequest req, HttpServletResponse res, @RequestParam("employee_id") String id) throws IOException {
		Gson gson = new Gson();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println(gson.toJson(dailyReportService.selectVehicleGauge(id)));
		pw.close();
	}

	@RequestMapping(value = "/morrisChartLine", method = { RequestMethod.POST })
	public @ResponseBody HashMap<String, List<?>> getDailyReportChart(HttpServletResponse res,
			@RequestParam("employee_id") String employee_id) throws JSONException, IOException {
		return dailyReportService.searchDailyChartLine(employee_id);
	}

}
