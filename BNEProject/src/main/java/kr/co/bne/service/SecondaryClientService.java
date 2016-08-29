package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.SecondaryClientSearchElement;
import kr.co.bne.dto.SecondaryClientDTO;

public interface SecondaryClientService {
	public List<SecondaryClientDTO> getSecondaryClient(int clientId);
	
	public int insertSecondaryClient(List<SecondaryClientDTO> secondClientList) throws Exception;
	public int updateSecondaryClient(SecondaryClientDTO secondClient) throws Exception;
	public int deleteSecondaryClient(int sec_client_id) throws Exception;
	public List<SecondaryClientSearchElement> searchSecondaryClient(Map<String, Object> parameterMap) throws Exception;
	public int searchClientCount(Map<String, Object> parameterMap) throws Exception;
}


