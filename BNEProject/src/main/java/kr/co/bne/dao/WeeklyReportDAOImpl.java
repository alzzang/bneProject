package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.WeeklyReportDTO;

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
		System.out.println(employee_id);
		int result = sqlSession.selectOne(namespace + "selectSalesGoal", employee_id);
		return result;
	}

	@Override
	public List<Map<String, String>> selectDayList(String employee_id) throws Exception {
		List<Map<String, String>> result = sqlSession.selectList(namespace + "selectDayList", employee_id);
		return result;
	}

	@Override
	public List<String> selectAllWeeklyReportId(String employee_id) throws Exception {
		List<String> result = sqlSession.selectList(namespace + "selectAllReportId", employee_id);
		return result;
	}

	@Override
	public WeeklyReportDTO selectWeeklyReport(String weekly_report_id) throws Exception {
		WeeklyReportDTO result = sqlSession.selectOne(namespace + "selectWeeklyReport", weekly_report_id);
		return result;
	}

	
}
