package kr.co.bne.service;

import java.util.List;

import kr.co.bne.dto.SecondaryClientDTO;

public interface SecondaryClientService {
	public List<SecondaryClientDTO> getSecondaryClient(int clientId);
}
