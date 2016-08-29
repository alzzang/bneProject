package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.SecondaryClientSearchElement;
import kr.co.bne.dto.SecondaryClientDTO;

@Repository
public class SecondaryClientDAOImpl implements SecondaryClientDAO {

	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.co.bne.mapper.SecondaryClient" + ".";
	
	@Override
	public List<SecondaryClientDTO> selectSecondaryClient(int clientId) {
		return sqlSession.selectList("kr.co.bne.mapper.SecondaryClient.selectSecondClient", clientId);
	}

	@Override
	public int insertSecondaryClient(SecondaryClientDTO secondClient) throws Exception {
		int result = sqlSession.insert(namespace + "insertSecondaryClient", secondClient);
		return result;
	}

	@Override
	public int updateSecondaryClient(SecondaryClientDTO secondClient) throws Exception {
		int result = sqlSession.update(namespace + "updateSecondaryClient", secondClient);
		return result;
	}

	@Override
	public int deleteSecondaryClient(int sec_client_id) throws Exception {
		int result = sqlSession.insert(namespace + "deleteSecondaryClient", sec_client_id);
		return result;
	}

	@Override
	public List<SecondaryClientSearchElement> searchSecondaryClient(Map<String, Object> parameterMap) throws Exception {
		List<SecondaryClientSearchElement> result = sqlSession.selectList(namespace + "searchSecondaryClient", parameterMap);
		return result;
	}

	@Override
	public int searchSecondaryClientCount(Map<String, Object> parameterMap) throws Exception {
		int result = sqlSession.selectOne(namespace + "searchSecondaryClientCount", parameterMap);
		return result;
	}

}
