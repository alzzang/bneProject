package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.ClientSearchElement;
import kr.co.bne.dao.ClientDAO;
import kr.co.bne.dto.ClientDTO;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDAO clientDAO;
	
	@Override
	public List<ClientDTO> getClient() {
		return clientDAO.selectAll();
	}

	@Override
	public List<ClientSearchElement> searchClient(Map<String, Object> parameterMap) throws Exception {
		List<ClientSearchElement> result = clientDAO.searchClient(parameterMap);
		return result;
	}

	@Override
	public int insertClient(List<ClientDTO> clientList) throws Exception {
		int result = 0;
		
		for (ClientDTO clientDTO : clientList) {
			result += clientDAO.insertClient(clientDTO);
		}
		
		return result;
	}

	@Override
	public int updateClient(ClientDTO client) throws Exception {
		int result = clientDAO.updateClient(client);
		return result;
	}

	@Override
	public int deleteClient(int client_id) throws Exception {
		int result = clientDAO.deleteClient(client_id);
		return result;
	}

	@Override
	public int searchClientCount(Map<String, Object> parameterMap) throws Exception {
		int result = clientDAO.searchClientCount(parameterMap);
		return result;
	}

}
