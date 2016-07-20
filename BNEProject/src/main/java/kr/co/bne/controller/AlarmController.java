package kr.co.bne.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.NoticeService;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@Autowired
	NoticeService noticeService;
		
	@RequestMapping("/detail")
	public ModelAndView main(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=req.getSession();
		EmployeeDTO employee=(EmployeeDTO) session.getAttribute("user");
		String position=employee.getPosition();
		List<NoticeDetail> unList=null;
		List<NoticeDetail> cnList=null;
		Map<String, String> map=new HashMap<String, String>();
		map.put("employee_id", employee.getEmployee_id());
		map.put("start", "1");
		map.put("end", "10");
		if(position.equals("manager")){
			unList=noticeService.searchUnconfirmedNotice(map);
			cnList=noticeService.searchconfirmedNotice(map);
		}
		
		for(int i=0;i<unList.size();i++){
			String tempTime[]=unList.get(i).getPasstime().split(" ");
			//System.out.println(tempTime);
			String setTime="";
			for(int j=0;j<tempTime.length;j++){
				if(tempTime[j].charAt(0)!='0')
					setTime+=tempTime[j]+" ";
			}
			//System.out.println(setTime);
			unList.get(i).setPasstime(setTime);
		}
		
		for(int i=0;i<cnList.size();i++){
			String tempTime[]=cnList.get(i).getPasstime().split(" ");
			//System.out.println(tempTime);
			String setTime="";
			for(int j=0;j<tempTime.length;j++){
				if(tempTime[j].charAt(0)!='0')
					setTime+=tempTime[j]+" ";
			}
			//System.out.println(setTime);
			cnList.get(i).setPasstime(setTime);
		}
		
		ModelAndView model=new ModelAndView("alarmDetail");
		model.addObject("unList", unList);
		model.addObject("cnList", cnList);
		return model;
	}
	
	
	
	
	@RequestMapping(value="/unconfirmed", method = RequestMethod.POST)
	public void addUnConfirmed(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession session=req.getSession();
		EmployeeDTO employee=(EmployeeDTO) session.getAttribute("user");
		String position=employee.getPosition();
		List<NoticeDetail> unList=null;
		Map<String, String> map=new HashMap<String, String>();
		map.put("employee_id", employee.getEmployee_id());
		Integer start=Integer.parseInt(req.getParameter("start"));
		start=(start-1)*10+1;
		map.put("start", start.toString());
		Integer end=start+9;
		map.put("end", end.toString());
		if(position.equals("manager")){
			unList=noticeService.searchUnconfirmedNotice(map);
		}
		
		for(int i=0;i<unList.size();i++){
			String tempTime[]=unList.get(i).getPasstime().split(" ");
			System.out.println(tempTime);
			String setTime="";
			for(int j=0;j<tempTime.length;j++){
				if(tempTime[j].charAt(0)!='0')
					setTime+=tempTime[j]+" ";
			}
			System.out.println(setTime);
			unList.get(i).setPasstime(setTime);
		}
		Gson gson=new  Gson();
		String unconfirmedList=gson.toJson(unList);
		
		
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.print(unconfirmedList);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/confirmed", method = RequestMethod.POST)
	public void addConfirmed(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession session=req.getSession();
		EmployeeDTO employee=(EmployeeDTO) session.getAttribute("user");
		String position=employee.getPosition();
		List<NoticeDetail> unList=null;
		Map<String, String> map=new HashMap<String, String>();
		map.put("employee_id", employee.getEmployee_id());
		Integer start=Integer.parseInt(req.getParameter("start"));
		start=(start-1)*10+1;
		map.put("start", start.toString());
		Integer end=start+9;
		map.put("end", end.toString());
		if(position.equals("manager")){
			unList=noticeService.searchconfirmedNotice(map);
		}
		
		for(int i=0;i<unList.size();i++){
			String tempTime[]=unList.get(i).getPasstime().split(" ");
			System.out.println(tempTime);
			String setTime="";
			for(int j=0;j<tempTime.length;j++){
				if(tempTime[j].charAt(0)!='0')
					setTime+=tempTime[j]+" ";
			}
			System.out.println(setTime);
			unList.get(i).setPasstime(setTime);
		}
		Gson gson=new  Gson();
		String confirmedList=gson.toJson(unList);
		
		
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.print(confirmedList);
		pw.flush();
		pw.close();
	}
}
