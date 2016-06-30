package kr.co.bne.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.EmployeeDTO;


@Repository
	
public class EmployeeDAOImpl implements EmployeeDAO {
	EmployeeDAO employeeDAO;
	EmployeeDTO employeeDTO; 
	
	
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public EmployeeDTO selectEmployee(String id) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("kr.co.bne.mapper.Employee.selectEmployee", id);
	}
	@Override
	public void updatePassword(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.Employee.updatePassword",info);
	}
	@Override
	public void updateFile(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.Employee.updateFile",info);
	}

}
