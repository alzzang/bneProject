package kr.co.bne.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import kr.co.bne.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@RequestMapping(value = "/getClient", method = { RequestMethod.GET })
	public void getClient(HttpServletResponse res,HttpServletRequest req, ModelAndView mv) {
		
		System.out.println(clientService.getClient());
		req.setAttribute("client", clientService.getClient());
		
		
	}
	
}
