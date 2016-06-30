package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeeklyReportDAOImpl implements WeeklyReportDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.co.bne.mapper.WeeklyReport" + "."; 

	@Override
	public int selectThisMonthlySales(String employee_id) throws Exception {
		int result = sqlSession.selectOne(namespace + "selectThisMonthlySales", employee_id);
		return result;
	}

	@Override
	public int selectSalesGoal(String employee_id) throws Exception {
		int result = sqlSession.selectOne(namespace + "selectSalesGoal", employee_id);
		return result;
	}

	@Override
	public List<String> selectDayList(String employee_id) throws Exception {
		List<String> result = sqlSession.selectList(namespace + "selectDayList", employee_id);
		return result;
	}

}
