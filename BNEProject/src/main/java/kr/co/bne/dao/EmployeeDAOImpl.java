package kr.co.bne.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
	
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SqlSession sqlSession;
		
	
	@Override
	public int validUserCheck() {
		// TODO Auto-generated method stub
		return 0;
	}

}
