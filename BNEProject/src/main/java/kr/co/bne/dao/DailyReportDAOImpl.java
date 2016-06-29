package kr.co.bne.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

@Repository
public class DailyReportDAOImpl implements DailyReportDAO {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public DailyReportEmployeeDTO selectPreSales(String employee_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectEmployee", employee_id);
	}
	@Override
	public void insertDailyReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		sqlSession.insert("kr.co.bne.mapper.DailyReport.insertDailyReport", dailyReportDTO);
		
	}
	@Override
	public DailyReportDTO selectDailyReport(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectDailyReport", id);
	}
	@Override
	public int selectDailySalesGoal(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectDailySalesGoal", map);
	}

}
