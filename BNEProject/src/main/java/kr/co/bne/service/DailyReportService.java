package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;

@Service("dailyReportService")
public class DailyReportService {

	@Autowired
	private DailyReportDAO dailyReportDAO;
	
	
	public HashMap<String, Object> selectTeamMemberList(String user_id) {
		int totalUnapprovalNum = dailyReportDAO.getTotalUnapprovalNum(user_id);
		List<DailyReportTeamListElement> memberList = dailyReportDAO.selectTeamMemberList(user_id);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("totalUnapprovalNum", totalUnapprovalNum);
		result.put("memberList", memberList);
		
		return result;
	}
	
	
	
	public HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();		
		return selectDailyReportList(position, user_id, startIdx, perContentNum, map);
	}
	
	
	
	public HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum, HashMap<String, Object> params) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<DailyReportListElement> dailyReportList = null;
		int totalPageNum = 0;
		
		if(("manager").equals(position)) {
			//매니저일 때 쿼리
			dailyReportList = dailyReportDAO.selectDailyReportList_Manager(user_id, startIdx, perContentNum, params);
			totalPageNum = dailyReportDAO.getPagingNum_DailyReportList_Manager(user_id, perContentNum, params);
		}else {
			//팀원일 때 쿼리
			dailyReportList = dailyReportDAO.selectDailyReportList_Member(user_id, perContentNum, params);
			totalPageNum = dailyReportDAO.getPagingNum_DailyReportList_Member(user_id, perContentNum, params);
		}
		
		result.put("dailyReportList", dailyReportList);
		result.put("totalPageNum", totalPageNum);
		
		return result;
	}
	
}
