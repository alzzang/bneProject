package kr.co.bne.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bne.common.SecondaryClientSearchElement;
import kr.co.bne.dto.ClientDTO;
import kr.co.bne.dto.SecondaryClientDTO;
import kr.co.bne.service.ClientService;
import kr.co.bne.service.SecondaryClientService;

@Controller
@RequestMapping("/2ndclient")
public class SecondaryClientController {

	@Autowired
	private SecondaryClientService secondaryClientService;
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/list")
	public ModelAndView getFirstClientlist(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("secondaryClientList");
		Map<String, Object> parameterMap = getParamMap(request);
		List<ClientDTO> clientList = clientService.getClient();
		List<SecondaryClientSearchElement> secondaryClientList = secondaryClientService.searchSecondaryClient(parameterMap);
		int totalRecordNum = secondaryClientService.searchClientCount(parameterMap);
		
		mv.addObject("clientList", clientList);
		mv.addObject("secClientList", secondaryClientList);
		mv.addObject("totalRecordNum", totalRecordNum);
		mv.addObject("ListCondition", parameterMap);
		return mv;
	}

	private Map<String, Object> getParamMap(HttpServletRequest request){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String sec_client_name = request.getParameter("sec_client_name");
		String client_name = request.getParameter("client_name");
		String pageStr = request.getParameter("pageStr");
		
		
		// 검색 결과 출력
		SecondaryClientSearchElement client = new SecondaryClientSearchElement();
		client.setClient_name(client_name);
		client.setSec_client_name(sec_client_name);
		
		int pageSize = 5;
		
		int page = 0;
		if("".equals(pageStr) || pageStr == null)
			page = 1;
		else								
			page = Integer.parseInt(pageStr);

		parameterMap.put("sec_client", client);
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
	
	@RequestMapping(value="get2ndClientList")
	public List<SecondaryClientSearchElement> getClientList(HttpServletRequest request) throws Exception{
		
		return secondaryClientService.searchSecondaryClient(getParamMap(request));
	}
	
	@RequestMapping(value="/add", method={ RequestMethod.POST })
	public String addClient(HttpServletRequest request) throws Exception{
	  String secClientList_jsonStr = request.getParameter("sec_client_add_info");
      System.out.println(secClientList_jsonStr);
      Type listType = new TypeToken<List<SecondaryClientDTO>>() {}.getType();
      List<SecondaryClientDTO> clientList =  (new Gson()).fromJson(secClientList_jsonStr, listType);
		
      int result = secondaryClientService.insertSecondaryClient(clientList);
      
      return "redirect:/2ndclient/list";
	}
	
	@RequestMapping(value="/remove/{sec_client_id}", method={ RequestMethod.GET })
	public String removeClient(HttpServletRequest request,@PathVariable("sec_client_id") String sec_client_idStr) throws Exception{
		
		int sec_client_id = 0;
		int result = 0;
		if(sec_client_idStr != null && !"".equals(sec_client_idStr)){
			sec_client_id = Integer.parseInt(sec_client_idStr);
			result = secondaryClientService.deleteSecondaryClient(sec_client_id);
		}
		
		return "redirect:/2ndclient/list";
	}
	@RequestMapping(value="/modify", method={ RequestMethod.POST })
	public String modifyClient(HttpServletRequest request, @ModelAttribute SecondaryClientDTO secondClient) throws Exception{
		
		int result = secondaryClientService.updateSecondaryClient(secondClient);
		
		return "redirect:/2ndclient/list";
	}
}
