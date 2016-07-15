package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
