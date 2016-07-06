package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.dto.ClientDTO;

public interface ClientDAO {
	public List<ClientDTO> selectAll();
}
