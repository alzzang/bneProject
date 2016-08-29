package kr.co.bne.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bne.common.ClientSearchElement;
import kr.co.bne.dto.ClientDTO;
import kr.co.bne.dto.DepartmentDTO;
import kr.co.bne.service.ClientService;
import kr.co.bne.service.DepartmentService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private DepartmentService departmentService; 
	
	
	@RequestMapping(value = "/getClient", method = { RequestMethod.GET })
	public void getClient(HttpServletResponse res, HttpServletRequest req, ModelAndView mv) {

		req.setAttribute("client", clientService.getClient());

	}

	@RequestMapping(value = "/list")
	public ModelAndView getFirstClientlist(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("clientList");
		
		// 부서 목록
		List<DepartmentDTO> deptList = departmentService.getDepartmentList();
		
		Map<String, Object> parameterMap = getParamMap(request);
		
		List<ClientSearchElement> clientResult = clientService.searchClient(parameterMap);
		int totalRecordNum = clientService.searchClientCount(parameterMap);
		
		mv.addObject("deptList", deptList);
		mv.addObject("clientList", clientResult);
		mv.addObject("totalRecordNum", totalRecordNum);
		mv.addObject("ListCondition", parameterMap);
		
		return mv;
	}

	private Map<String, Object> getParamMap(HttpServletRequest request){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String client_name = request.getParameter("client_name");
		String representative = request.getParameter("representative");
		String department_id_Str = request.getParameter("department_id");
		String pageStr = request.getParameter("pageStr");
		System.out.println("department_id_Str");
		int department_id = 0;
		if(department_id_Str != null && !"".equals(department_id_Str)){
			department_id = Integer.parseInt(department_id_Str);
		}
		
		
		// 검색 결과 출력
		ClientDTO client = new ClientDTO();
		client.setClient_name(client_name);
		client.setRepresentative(representative);
		client.setDepartment_id(department_id);
		
		int pageSize = 5;
		
		int page = 0;
		if("".equals(pageStr) || pageStr == null)
			page = 1;
		else								
			page = Integer.parseInt(pageStr);

		parameterMap.put("client", client);
		parameterMap.put("pageSize", pageSize);
		parameterMap.put("page", page);
		Iterator<String> iterator = parameterMap.keySet().iterator();
	    while (iterator.hasNext()) {
	        String key = (String) iterator.next();
	        System.out.print("key="+key);
	        System.out.println(" value="+parameterMap.get(key));
	    }
		return parameterMap;
	}
	
	@RequestMapping(value="getClientList")
	public List<ClientSearchElement> getClientList(HttpServletRequest request) throws Exception{
		
		return clientService.searchClient(getParamMap(request));
	}
	
	@RequestMapping(value="/add", method={ RequestMethod.POST })
	public String addClient(HttpServletRequest request) throws Exception{
	  String clientList_jsonStr = request.getParameter("client_add_info");
      System.out.println(clientList_jsonStr);
      Type listType = new TypeToken<List<ClientDTO>>() {}.getType();
      List<ClientDTO> clientList =  (new Gson()).fromJson(clientList_jsonStr, listType);
		
      int result = clientService.insertClient(clientList);
      
      return "redirect:/client/list";
	}
	
	@RequestMapping(value="/remove/{client_id}", method={ RequestMethod.GET })
	public String removeClient(HttpServletRequest request, @PathVariable("client_id") String client_idStr) throws Exception{
		
		int client_id = 0;
		int result = 0;
		if(client_idStr != null && !"".equals(client_idStr)){
			client_id = Integer.parseInt(client_idStr);
			result = clientService.deleteClient(client_id);
		}
		
		return "redirect:/client/list";
	}
	@RequestMapping(value="/modify", method={ RequestMethod.POST })
	public String modifyClient(HttpServletRequest request, @ModelAttribute ClientDTO client) throws Exception{
		
		int result = clientService.updateClient(client);
		
		return "redirect:/client/list";
	}
}
