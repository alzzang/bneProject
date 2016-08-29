package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.SecondaryClientSearchElement;
import kr.co.bne.dao.SecondaryClientDAO;
import kr.co.bne.dto.SecondaryClientDTO;

@Service
public class SecondaryClientServiceImpl implements SecondaryClientService {

	@Autowired
	SecondaryClientDAO secondaryClientDAO;

	@Override
	public List<SecondaryClientDTO> getSecondaryClient(int clientId) {
		// TODO Auto-generated method stub
		return secondaryClientDAO.selectSecondaryClient(clientId);
	}

	@Override
	public int insertSecondaryClient(List<SecondaryClientDTO> secondClientList) throws Exception {
		int result = 0;
		for (SecondaryClientDTO secondaryClientDTO : secondClientList) {
			result += secondaryClientDAO.insertSecondaryClient(secondaryClientDTO);
		}
		return result;
	}

	@Override
	public int updateSecondaryClient(SecondaryClientDTO secondClient) throws Exception {
		int result = secondaryClientDAO.updateSecondaryClient(secondClient);
		return result;
	}

	@Override
	public int deleteSecondaryClient(int sec_client_id) throws Exception {
		int result = secondaryClientDAO.deleteSecondaryClient(sec_client_id);
		return result;
	}

	@Override
	public List<SecondaryClientSearchElement> searchSecondaryClient(Map<String, Object> parameterMap) throws Exception {
		List<SecondaryClientSearchElement> result =  secondaryClientDAO.searchSecondaryClient(parameterMap);
		return result;
	}

	@Override
	public int searchClientCount(Map<String, Object> parameterMap) throws Exception {
		int result =secondaryClientDAO.searchSecondaryClientCount(parameterMap);
		return result;
	}

}
