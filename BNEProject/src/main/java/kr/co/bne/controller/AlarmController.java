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
import org.springframework.web.bind.annotation.RequestParam;
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
		String type=req.getParameter("notice_type");
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("employee_id", employee.getEmployee_id());
		map.put("start", "1");
		map.put("end", "10");
		map.put("position", position);
		
		
		if(type==null){
			type="%%";
		}else{
			type="%"+type+"%";
		}
		map.put("type", type);
		
		unList=noticeService.searchUnconfirmedNotice(map);
		cnList=noticeService.searchconfirmedNotice(map);
		
		for(int i=0;i<unList.size();i++){
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
		model.addObject("type", type);
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
		String type=req.getParameter("notice_type");
		map.put("employee_id", employee.getEmployee_id());
		Integer start=Integer.parseInt(req.getParameter("start"));
		start=(start-1)*10+1;
		map.put("start", start.toString());
		Integer end=start+9;
		map.put("end", end.toString());
		map.put("position", position);
		
		if(type==null){
			type="%%";
		}else{
			type="%"+type+"%";
		}
		map.put("type", type);
		
		unList=noticeService.searchUnconfirmedNotice(map);
		
		
		for(int i=0;i<unList.size();i++){
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
		String type=req.getParameter("notice_type");
		map.put("employee_id", employee.getEmployee_id());
		Integer start=Integer.parseInt(req.getParameter("start"));
		start=(start-1)*10+1;
		map.put("start", start.toString());
		Integer end=start+9;
		map.put("end", end.toString());
		map.put("position", position);
		
		if(type==null){
			type="%%";
		}else{
			type="%"+type+"%";
		}
		map.put("type", type);
		
		unList=noticeService.searchconfirmedNotice(map);
		
		
		for(int i=0;i<unList.size();i++){
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
		Gson gson=new  Gson();
		String confirmedList=gson.toJson(unList);
		
		
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.print(confirmedList);
		pw.flush();
		pw.close();
	}
	@RequestMapping(value="/click",method=RequestMethod.POST)
	public void clickUnconfirmed(@RequestParam("noticeId") int noticeId,HttpServletRequest req, HttpServletResponse res)
	{
		noticeService.click(noticeId);
	}
}
