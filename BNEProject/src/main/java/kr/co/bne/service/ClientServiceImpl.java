package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.ClientDAO;
import kr.co.bne.dto.ClientDTO;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDAO clientDAO;
	
	@Override
	public List<ClientDTO> getClient() {
		// TODO Auto-generated method stub
		return clientDAO.selectAll();
	}

}
