package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;

public interface DailyReportService {
	public DailyReportEmployeeDTO searchPreSales(String employee_id);
	public void writeDailyReport(DailyReportDTO dailyReportDTO);
	public DailyReportDetailDTO viewReport(String id);
	public int searchDailySales(HashMap<String, String> map);
	public EmployeeDTO searchImage(String employee_id);
	public List<CounsellingRecordDTO> searchCounselRecord(String id);
	public void approvalDailyReport(String daily_report_id);
=======
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
		int totalUnapprovalNum = dailyReportDAO.getTotalUnapprovalNum_Manager(user_id);
		
		List<DailyReportTeamListElement> memberList = dailyReportDAO.selectTeamMemberList(user_id);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("totalUnapprovalNum", totalUnapprovalNum);
		result.put("memberList", memberList);
		
		return result;
	}
	
	
	
	public int getgetTotalUnapprovalNum(String position, String user_id) {
		if(position.equals("manager")) {
			return dailyReportDAO.getTotalUnapprovalNum_Manager(user_id);
		}else {
			return dailyReportDAO.getTotalUnapprovalNum_Member(user_id);
		}
	}
	
	
	
	public HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();		
		return selectDailyReportList(position, user_id, startIdx, perContentNum, map);
	}
	
	
	
	public HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum, HashMap<String, Object> params) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<DailyReportListElement> dailyReportList = null;
		int totalPageNum = 0;
		
		
		dailyReportList = dailyReportDAO.selectDailyReportList(user_id, startIdx, perContentNum, params);
		totalPageNum = dailyReportDAO.getPagingNum_DailyReportList(user_id, perContentNum, params);
		
		
		result.put("dailyReportList", dailyReportList);
		result.put("totalPageNum", totalPageNum);
		
		return result;
	}
	
>>>>>>> refs/remotes/origin/Jiyeon
}
