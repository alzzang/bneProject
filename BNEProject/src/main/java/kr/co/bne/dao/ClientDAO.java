package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.ClientSearchElement;
import kr.co.bne.dto.ClientDTO;

public interface ClientDAO {
	public List<ClientDTO> selectAll();
	
	public int insertClient(ClientDTO client) throws Exception;
	public int updateClient(ClientDTO client) throws Exception;
	public int deleteClient(int client_id) throws Exception;
	public List<ClientSearchElement> searchClient(Map<String, Object> parameterMap) throws Exception;
	public int searchClientCount(Map<String, Object> parameterMap) throws Exception;
}
