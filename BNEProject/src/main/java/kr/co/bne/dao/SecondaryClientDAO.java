package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.dto.SecondaryClientDTO;

public interface SecondaryClientDAO {
	
	public List<SecondaryClientDTO> selectSecondaryClient(int clientId);
}
