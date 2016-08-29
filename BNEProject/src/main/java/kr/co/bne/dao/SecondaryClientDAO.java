package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.SecondaryClientSearchElement;
import kr.co.bne.dto.SecondaryClientDTO;

public interface SecondaryClientDAO {
	
	public List<SecondaryClientDTO> selectSecondaryClient(int clientId);
	
	public int insertSecondaryClient(SecondaryClientDTO secondClient) throws Exception;
	public int updateSecondaryClient(SecondaryClientDTO secondClient) throws Exception;
	public int deleteSecondaryClient(int client_id) throws Exception;
	public List<SecondaryClientSearchElement> searchSecondaryClient(Map<String, Object> parameterMap) throws Exception;
	public int searchSecondaryClientCount(Map<String, Object> parameterMap) throws Exception;
	
}
