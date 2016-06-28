package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;


@Repository("dailyReportDAO")
public class DailyReportDAOImpl implements DailyReportDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<DailyReportListElement> selectDailyReportList_All(int department_id, int startIdx, int perContentNum, String employee_id) throws RuntimeException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("department_id", department_id);
		params.put("employee_id", employee_id);
		params.put("startIdx", startIdx);
		params.put("perContentNum", perContentNum);
		
		List<DailyReportListElement> list = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectDailyReportList_All", params);
		
		return list;
	}
	
	
	
	@Override
	public int getPagingNum_All(int department_id, String employee_id, int perContentNum) throws RuntimeException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("department_id", department_id);
		params.put("employee_id", employee_id);
		params.put("perContentNum", perContentNum);
		int pageNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getPagingNum_All", params);
		
		return pageNum;
	}
	
	
	
	@Override
	public List<DailyReportTeamListElement> selectTeamMemberList(String user_id) throws RuntimeException {
		List<DailyReportTeamListElement> teamList = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectTeamMemberList", user_id);
	
		return teamList;
	}
	
	
	@Override
	public int getTotalUnapprovalNum(String user_id) throws RuntimeException {
		int TotalUnapprovalNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getTotalUnapprovalNum", user_id);
		
		return TotalUnapprovalNum;
	}

}
