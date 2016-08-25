package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.DepartmentTeamList;
import kr.co.bne.dto.DepartmentDTO;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<DepartmentTeamList> getDepartmentList(int startIdx, int perContentNum, HashMap<String, String> params) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = (HashMap<String, String>)params.clone();
		
		
		map.put("perContentNum", Integer.toString(perContentNum));
		map.put("startIdx", Integer.toString(startIdx));
		
		return sqlSession.selectList("kr.co.bne.mapper.Department.getDepartmentList", map);
	}

	@Override
	public int getPagingNum_DepartmentList(int perContentNum, HashMap<String, String> params) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = (HashMap<String, String>)params.clone();
		map.put("perContentNum", Integer.toString(perContentNum));
		return sqlSession.selectOne("kr.co.bne.mapper.Department.getPagingNum_DepartmentList", map);
	}
	
	@Override
	public List<DepartmentDTO> getDepartmentList() {
		return sqlSession.selectList("kr.co.bne.mapper.Department.getDepartmentList");
	}

}
