package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.ClientSearchElement;
import kr.co.bne.dto.ClientDTO;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	SqlSession sqlSession;
	
	private String namespace = "kr.co.bne.mapper.Client" + ".";	
	@Override
	public List<ClientDTO> selectAll() {
		return sqlSession.selectList("kr.co.bne.mapper.Client.selectClient");
	}

	@Override
	public List<ClientSearchElement> searchClient(Map<String, Object> parameterMap) throws Exception {
		List<ClientSearchElement> result = sqlSession.selectList(namespace + "searchClient", parameterMap);
		return result;
	}

	@Override
	public int insertClient(ClientDTO client) throws Exception {
		int result = sqlSession.insert(namespace + "insertClient", client);
		return result;
	}

	@Override
	public int updateClient(ClientDTO client) throws Exception {
		int result = sqlSession.update(namespace + "updateClient", client);
		return result;
	}

	@Override
	public int deleteClient(int client_id) throws Exception {
		int result = sqlSession.insert(namespace + "deleteClient", client_id);
		return result;
	}

	@Override
	public int searchClientCount(Map<String, Object> parameterMap) throws Exception {
		int result = sqlSession.selectOne(namespace + "searchClientCount", parameterMap);
		return result;
	}


}
