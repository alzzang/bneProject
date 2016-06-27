package kr.co.bne.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.bne.dto.EmployeeDTO;
import kr.co.bne.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/*
	 * @RequestMapping(value = { "/list" }) public String listBooks(Map<String,
	 * Object> map) {
	 * 
	 * map.put("fileList", uploadService.listFiles());
	 * 
	 * // will be resolved to /views/listFiles.jsp return "/listFiles"; }
	 */

	/*
	 * @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
	 * public void getFile(HttpServletResponse response, @PathVariable Long
	 * fileId) {
	 * 
	 * UploadedFile dataFile = uploadService.getFile(fileId);
	 * 
	 * File file = new File(dataFile.getLocation(), dataFile.getName());
	 * 
	 * try { response.setContentType(dataFile.getType());
	 * response.setHeader("Content-disposition", "attachment; filename=\"" +
	 * dataFile.getName() + "\"");
	 * 
	 * FileCopyUtils.copy(FileUtils.readFileToByteArray(file),
	 * response.getOutputStream());
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletResponse response, MultipartHttpServletRequest request) throws IOException {

		/* MultipartHttpServletRequest request=req; */

		// Getting uploaded files from the request object
		
		Map<String, MultipartFile> fileMap = request.getFileMap();

		// Maintain a list to send back the files info. to the client side
	//	List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

		// Iterate through the map
		for (MultipartFile multipartFile : fileMap.values()) {

			// Save the file to local disk
			saveFileToLocalDisk(multipartFile);
	//		UploadedFile fileInfo = getUploadedFileInfo(multipartFile);

			// Save the file info to database
			/* fileInfo = saveFileToDatabase(fileInfo); */

			// adding the file info to the list
		//	uploadedFiles.add(fileInfo);
		}

		/* return uploadedFiles; */
	}

	private void saveFileToLocalDisk(MultipartFile multipartFile) throws IOException, FileNotFoundException {

		String outputFileName = getOutputFilename(multipartFile);
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));

	}

	/*
	 * private UploadedFile saveFileToDatabase(UploadedFile uploadedFile) {
	 * return uploadService.saveFile(uploadedFile); }
	 */

	private String getOutputFilename(MultipartFile multipartFile) {

/*		HttpServletRequest req = null;
		HttpSession session = req.getSession();
		EmployeeDTO employee = (EmployeeDTO)session.getAttribute("user");
		
		String[] temp = multipartFile.getContentType().split("/");*/
		
		//return getDestinationLocation() + employee.getEmployee_id()+ "." + temp[1];
		return getDestinationLocation() + multipartFile.getOriginalFilename();
		
		 
	}

/*	private UploadedFile getUploadedFileInfo(MultipartFile multipartFile) throws IOException {

		UploadedFile fileInfo = new UploadedFile();
		fileInfo.setName(multipartFile.getOriginalFilename());
		fileInfo.setSize(multipartFile.getSize());
		fileInfo.setType(multipartFile.getContentType());
		fileInfo.setLocation(getDestinationLocation());

		return fileInfo;
	}
*/
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
		EmployeeDTO sessionid = (EmployeeDTO) session.getAttribute("user");
	
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = userService.validCheck(id, rawPassword);

		if (sessionid == null) {
			if (employeeDTO != null) {
				session.setAttribute("user", employeeDTO);
				return "redirect:/main";
			}
			return "redirect:/user/login";
		}

		else {
			if (employeeDTO != null) {
				userService.modifyPassword(id, req.getParameter("newpassword"));
				return "redirect:/main";
			}
			return "redirect:/user/changeProfile";
		}
	}

}
