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
		return sqlSession.selectList("kr.co.bne.mapper.Department.getDepartmentList_all");
	}

	@Override
	public boolean deleteDepartment(int department_id) {
		// TODO Auto-generated method stub
		
		int rows=sqlSession.delete("kr.co.bne.mapper.Department.deleteDepartment",department_id);
		return rows > 0 ? true : false;
	}

	@Override
	public boolean updateDepartment(DepartmentTeamList deptlist) {
		// TODO Auto-generated method stub
		int rows=sqlSession.update("kr.co.bne.mapper.Department.updateDepartment",deptlist);
		return rows > 0 ? true : false;
	}

	@Override
	public int selectDeptCount(String department_name) {
		// TODO Auto-generated method stub
		try {
			return sqlSession.selectOne("kr.co.bne.mapper.Department.selectCount",department_name);
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
		
	}
	@Override
	public boolean updateManager(DepartmentTeamList deptlist) {
		// TODO Auto-generated method stub
		int rows=sqlSession.update("kr.co.bne.mapper.Department.updateManager",deptlist);
		return rows > 0 ? true : false;
	}
	

	@Override
	public int getManagerCount(String manager_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.Department.selectManager", manager_id);
	}

	@Override
	public boolean addDepartment(DepartmentDTO ddto) {
		try {
            int a = sqlSession.insert("kr.co.bne.mapper.Department.insertDepartmentName", ddto);
            
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   
		return true;
	}

}
