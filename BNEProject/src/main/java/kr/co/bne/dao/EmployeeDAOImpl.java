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
		System.out.println("44");
		return sqlSession.selectOne("kr.co.bne.mapper.Employee.selectEmployee", id);
	}

	@Override
	public void updatePassword(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.Employee.updatePassword",info);
	}

}
