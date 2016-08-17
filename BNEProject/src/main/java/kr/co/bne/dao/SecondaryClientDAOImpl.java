package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.SecondaryClientDTO;

@Repository
public class SecondaryClientDAOImpl implements SecondaryClientDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SecondaryClientDTO> selectSecondaryClient(int clientId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.SecondaryClient.selectSecondClient", clientId);
	}

}
