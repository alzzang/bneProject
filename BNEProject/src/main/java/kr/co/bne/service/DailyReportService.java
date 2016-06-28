package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;

@Service
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
	
	
	public HashMap<String, Object> selectDailyReportList_All(int department_id, int startIdx, int perContentNum, String user_id) {
		List<DailyReportListElement> reportList = dailyReportDAO.selectDailyReportList_All(department_id, startIdx, perContentNum, user_id);
		int totalPageNum = dailyReportDAO.getPagingNum_All(department_id, user_id, perContentNum);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("reportList", reportList);
		result.put("totalPageNum", totalPageNum);
		
		return result;
	}
	
}
