package kr.co.bne.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.bne.dao.EmployeeDAO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.CounsellingRecordService;
import kr.co.bne.service.DailyReportService;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	CounsellingRecordService counsellingRecordService;
	
	@Autowired
	MessageChannel ftpChannel;

	public void setFileName(String fileName, HttpServletRequest req){
		HttpSession session = req.getSession();
		EmployeeDTO employee=(EmployeeDTO)session.getAttribute("user");
		
		if(!employee.getFile_position().equals(employee.getEmployee_id()+".png")){
			userService.modifyFilePosition(employee.getEmployee_id(), fileName);
		}
		
		employee.setFile_position(fileName);
		session.setAttribute("user", employee);
		
		
		
		
	}
	
	@RequestMapping(value = "/defaultFile", method = RequestMethod.POST)
	public void defaultFile(HttpServletResponse response, HttpServletRequest req,
			@RequestParam("id") String id) throws IOException {
		

		File uploadFile = new File("C:\\Users\\bit-user\\git\\bneProject\\BNEProject\\src\\main\\resources\\default.png");
		final Message<?> message = MessageBuilder.withPayload(uploadFile).setHeader("fileName", id+".png").build();
		ftpChannel.send(message);
		setFileName(id+".png",req);
		
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadFiles(HttpServletResponse response, MultipartHttpServletRequest req,
			@RequestParam("id") String id) throws IOException {
		
		
		String fileName = null;
		Map<String, MultipartFile> fileMap = req.getFileMap();
		for (MultipartFile multipartFile : fileMap.values()) {
			
			fileName=saveFileToRemoteDisk(multipartFile, id);
			
			

		}
				
		
		setFileName(fileName,req);
		
	}


	
	private String saveFileToRemoteDisk(MultipartFile multipartFile, String id)
			throws IOException, FileNotFoundException {


		String outputFileName =  id + "." + "png";			
		File uploadFile = new File(outputFileName);
		multipartFile.transferTo(uploadFile);
		
		
	
		final Message<?> message = MessageBuilder.withPayload(uploadFile).build();
		ftpChannel.send(message);
		
		uploadFile.delete();
	
		return outputFileName;
		
	}
	

	@RequestMapping(value = "/goLoginForm", method = { RequestMethod.GET })
	public String goLoginForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login() {
		return "redirect:/user/goLoginForm";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/main";
	}

	@RequestMapping(value = "/changeProfile", method = { RequestMethod.GET })
	public String chagePassword(HttpServletRequest req) {
		return "password";
	}

	@RequestMapping(value = "/validCheck", method = { RequestMethod.POST })
	public String validCheck(@RequestParam("id") String id, @RequestParam("password") String rawPassword,
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter outs = res.getWriter();
		
		HttpSession session = req.getSession();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = userService.validCheck(id, rawPassword);
		String newpassword = req.getParameter("newpassword");
		
		
		if (newpassword == null) {
			if (employeeDTO != null) {
				session.setAttribute("user", employeeDTO);
				session.setAttribute("fileName", employeeDTO.getFile_position());

				DailyReportEmployeeDTO employee= dailyReportService.searchPreSales(employeeDTO.getEmployee_id());

				session.setAttribute("employee", employee);
				return "redirect:/main";
			}
	     
			
			return "redirect:/user/login";
		}

		else {
			if (employeeDTO != null) {
				userService.modifyPassword(id, newpassword);
		
				outs.println("<script type='text/javascript'>");
				outs.println(" location.href = '/main'"); 
	            outs.println(" alert('비밀번호변경 완료'); ");
	            outs.println("</script>");
	            outs.flush();
	            outs.close();
	            return "mainboard";
			}
			
			outs.println("<script type='text/javascript'>");
            outs.println(" location.href = '/user/changeProfile'"); 
            outs.println(" alert('비밀번호 변경 실패'); ");
            outs.println("</script>");
            outs.flush();
            outs.close();
            
			return "password";
		}
	}
	

	/* javascript에서 필요한 정보를 json 으로 변환 return */
	@RequestMapping(value = "/empSearch", method = { RequestMethod.POST })
	public @ResponseBody List<EmployeeDTO> getEmpSearch(@RequestParam("empSearch") String empSearch ,HttpServletRequest req) {

	
			String temp="%"+empSearch+"%";
			List<EmployeeDTO> list =userService.getEmpSearch(temp);
			return list;
	}

	
	
	@RequestMapping(value = "/searchUser/{empId}", method = { RequestMethod.GET })
   public String showSearchUser(Model model,@PathVariable String empId, HttpServletRequest req, HttpServletResponse res)
         throws IOException {

      res.setCharacterEncoding("UTF-8");
      res.setContentType("text/html; charset=UTF-8");
      HttpSession session = req.getSession();
      EmployeeDTO employeeDTO = (EmployeeDTO)session.getAttribute("user");
      PrintWriter out = res.getWriter();
      EmployeeDTO edto = employeeDAO.selectEmployee(empId);
      if(edto.getPosition().equals("manager")){
         System.out.println("manager search!");
         out.println("<script language='javascript'>"); 
         out.println("alert('manager의 url 검색은 할 수 없습니다.');"); 
         out.println("location.href='/main'");
         out.println("</script>"); 
         out.close();
      }
      model.addAttribute("emp",edto);
      List<EmployeeDTO> depUserList=employeeDAO.selectEmployeeListA(edto.getDepartment_id());
      
      model.addAttribute("depUserList", depUserList);
      model.addAttribute("employeeDTO", employeeDTO);
      
      return "searchUser";
	}
	
	@RequestMapping(value = "/empSearch2", method = { RequestMethod.GET })
	public String getEmpSearch2(Model model,@RequestParam("empSearch") String empSearch ,HttpServletRequest req) {
		System.out.println("empSearch2 @@ : "+empSearch);
		
			String temp="%"+empSearch+"%";
			List<EmployeeDTO> list2 =userService.getEmpSearch(temp);
			model.addAttribute("list", list2);
			System.out.println(list2);
			return "searchUserList";
	}
	
	
	
}
