package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bne.common.CounsellingManageSales;
import kr.co.bne.common.CounsellingSalesGoal;
import kr.co.bne.dto.CounsellingManageSalesDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.dto.SecondaryClientDTO;
import kr.co.bne.service.CounsellingRecordService;
import kr.co.bne.service.SecondaryClientService;

@Controller
@RequestMapping("/counselling")
public class CounsellingController {

	@Autowired
	SecondaryClientService secondaryClientService;
	@Autowired
	CounsellingRecordService counsellingRecordService;

	@RequestMapping(value = "/writeCounsellingRecord", method = { RequestMethod.POST })
	public String setCounsellingRecord(HttpServletResponse res, HttpServletRequest req,
			@ModelAttribute CounsellingRecordDTO counsellingRecordDTO) {

		counsellingRecordService.setCounsellingRecord(counsellingRecordDTO);

		return "main2";
	}

	@RequestMapping(value = "/readCounsellingRecord/{counsellingId}", method = { RequestMethod.GET })
	public ModelAndView getCounsellingRecord(@PathVariable int counsellingId) {

		CounsellingRecordDTO counsellingRecord = counsellingRecordService.getCounsellingRecord(counsellingId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main2");
		mv.addObject("counsellingrecord", counsellingRecord);

		return mv;
	}

	@RequestMapping(value = "/secondaryClient", method = { RequestMethod.POST })
	public void getSecondaryClient(HttpServletResponse res, @RequestParam("client_id") int clientId)
			throws JSONException, IOException {

		JSONArray jarr = new JSONArray();
		JSONObject jobj;

		List<SecondaryClientDTO> secondaryClient = secondaryClientService.getSecondaryClient(clientId);

		
		for (SecondaryClientDTO temp : secondaryClient) {
			jobj = new JSONObject();
			jobj.put("client_id", temp.getClient_id());
			jobj.put("representative", temp.getRepresentative());
			jobj.put("cliend_name", temp.getClient_name());
			jobj.put("sec_client_id", temp.getSec_client_id());
			jobj.put("address", temp.getAddress());
			jobj.put("sec_client_name", temp.getSec_client_name());
			jarr.put(jobj);
		}

		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println(jarr.toString());
		pw.flush();
		pw.close();

	}
	
	
	/* 매출목표관리 에서 department 에 맞는 사원 list를 뿌려주기 위한 method */
	@RequestMapping(value="/manageSales", method={RequestMethod.GET})
	public String manageSales(Model model, HttpServletResponse res, HttpServletRequest req){
		
		HttpSession session = req.getSession();
		EmployeeDTO employeeDTO = (EmployeeDTO)session.getAttribute("user");
		
		//여기서 department user list를 받아오기
		List<EmployeeDTO> manageSales=counsellingRecordService.getManageSales(employeeDTO.getDepartment_id());
		model.addAttribute("manageSales", manageSales);
		
		
		return "manageSales";
	}
	
	/*  */
	@RequestMapping(value="/manageSalesList",method={RequestMethod.POST})
	public @ResponseBody List<CounsellingManageSalesDTO> getManageSalesList(@RequestParam("department_id") int id,@RequestParam("year") int year,@RequestParam("month") int month,HttpServletResponse res, HttpServletRequest req){
	
		CounsellingManageSales cm = new CounsellingManageSales();
		cm.setDepartment_id(id);
		cm.setYear(year);
		cm.setMonth(month);
		
		List<CounsellingManageSalesDTO> list =counsellingRecordService.getManageSalesList(cm);
		
		return list;
	}
	
	@RequestMapping(value="/insertSalesGoal",method={RequestMethod.POST})
	public void insertSalesGoal(HttpServletResponse res, HttpServletRequest req, @ModelAttribute("csg") CounsellingSalesGoal csg){
	
		counsellingRecordService.insertUpdateSalesGoal(csg);
		
	}
	
	
}
