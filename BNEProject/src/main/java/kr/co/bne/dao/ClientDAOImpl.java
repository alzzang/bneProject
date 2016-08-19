package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.ClientDTO;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ClientDTO> selectAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.Client.selectClient");
	}


}
