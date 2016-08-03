package kr.co.bne.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
import kr.co.bne.service.DailyReportService;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmployeeDAO employeeDAO;


	private DailyReportService dailyReportService;

	
	public void setFileName(String fileName, HttpServletRequest req){
		HttpSession session = req.getSession();
		session.setAttribute("fileName", fileName);
	}
	
	@RequestMapping(value = "/defaultFile", method = RequestMethod.POST)
	public void defaultFile(HttpServletResponse response, HttpServletRequest req,
			@RequestParam("id") String id) throws IOException {
		userService.modifyFilePosition(id);
		setFileName("default.png",req);
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadFiles(HttpServletResponse response, MultipartHttpServletRequest req,
			@RequestParam("id") String id) throws IOException {
		String fileName = null;
		Map<String, MultipartFile> fileMap = req.getFileMap();
		for (MultipartFile multipartFile : fileMap.values()) {
			fileName=saveFileToLocalDisk(multipartFile, id);
			userService.modifyFilePosition(id, fileName);
		}
		setFileName(fileName,req);
		
	}

	private String saveFileToLocalDisk(MultipartFile multipartFile, String id)
			throws IOException, FileNotFoundException {
		String temp[] = multipartFile.getContentType().split("/");
		String outputFileName = getDestinationLocation() + id + "." + temp[1];
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
		return id + "." + temp[1];
	}

	@RequestMapping(value = "/download/{fileName}", method = { RequestMethod.GET })
	public void showFile(@PathVariable String fileName, HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		byte[] buffer = new byte[1024];
		int byteRead = -1;
		FileInputStream inputStream;
		OutputStream outStream;

		File f = new File("c:/uploaded-files/" + fileName);

		inputStream = new FileInputStream(f);
		outStream = res.getOutputStream();

		while ((byteRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, byteRead);
		}
		inputStream.close();
		outStream.close();
		
	}

	private String getDestinationLocation() {
		String savedir = "c:/uploaded-files";
		File saveDirFile = new File(savedir);

		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		return "c:/uploaded-files/";
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

		HttpSession session = req.getSession();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = userService.validCheck(id, rawPassword);
		String newpassword = req.getParameter("newpassword");
		
		if (newpassword == null) {
			if (employeeDTO != null) {
				session.setAttribute("user", employeeDTO);
				session.setAttribute("fileName", employeeDTO.getFile_position());

				DailyReportEmployeeDTO employee=dailyReportService.searchPreSales(employeeDTO.getEmployee_id());
				session.setAttribute("employee", employee);
				
				return "redirect:/main";
			}
			return "redirect:/user/login";
		}

		else {
			if (employeeDTO != null) {
				userService.modifyPassword(id, newpassword);
				return "redirect:/main";
			}
			return "redirect:/user/changeProfile";
		}
	}
	

	@RequestMapping(value = "/empSearch", method = { RequestMethod.POST })
	public @ResponseBody List<EmployeeDTO> getEmpSearch(@RequestParam("empSearch") String empSearch ,HttpServletRequest req) {
		System.out.println("empSearch :"+empSearch);
		
			String temp="%"+empSearch+"%";
			List<EmployeeDTO> list =userService.getEmpSearch(temp);
			return list;
	}

	
	@RequestMapping(value = "/searchUser/{empId}", method = { RequestMethod.GET })
	public String showSearchUser(Model model,@PathVariable String empId, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
				
		EmployeeDTO ed = employeeDAO.selectEmployee(empId);
		model.addAttribute("emp",ed);
		return "searchUser";
	}
}
