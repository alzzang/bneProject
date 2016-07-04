package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import oracle.net.aso.s;


@Repository("dailyReportDAO")
public class DailyReportDAOImpl implements DailyReportDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public List<DailyReportListElement> selectDailyReportList_Manager(String user_id, int startIdx, int perContentNum, HashMap<String, Object> params) throws RuntimeException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("startIdx", startIdx);
		map.put("perContentNum", perContentNum);
		
		List<DailyReportListElement> list = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectDailyReportList_Manager", map);
	
		return list;
	}
	
	
	
	@Override
	public int getPagingNum_DailyReportList_Manager(String user_id, int perContentNum, HashMap<String, Object> params) throws RuntimeException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		
		int pageNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getPagingNum_DailyReportList_Manager", map);
		
		return pageNum;
	}
	
	
	
	
	@Override
	public List<DailyReportListElement> selectDailyReportList_Member(String user_id, int perContentNum, HashMap<String, Object> params) throws RuntimeException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		
		List<DailyReportListElement> list = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectDailyReportList_Member", map);
		
		return list;
	}
	
	
	
	
	@Override
	public int getPagingNum_DailyReportList_Member(String user_id, int perContentNum, HashMap<String, Object> params) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		
		int pageNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getPagingNum_DailyReportList_Member", map);
		
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
