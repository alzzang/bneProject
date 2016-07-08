package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dao.EmployeeDAO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	private EmployeeDAO dao1;
	@Autowired
	private DailyReportDAO dailyReportDAO;
	
	
	
	@Override
	public HashMap<String, Object> selectTeamMemberList(String user_id) {
		int totalUnapprovalNum = dailyReportDAO.getTotalUnapprovalNum_Manager(user_id);
		
		List<DailyReportTeamListElement> memberList = dailyReportDAO.selectTeamMemberList(user_id);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("totalUnapprovalNum", totalUnapprovalNum);
		result.put("memberList", memberList);
		
		return result;
	}
	
	
	@Override
	public int getgetTotalUnapprovalNum(String position, String user_id) {
		if(position.equals("manager")) {
			return dailyReportDAO.getTotalUnapprovalNum_Manager(user_id);
		}else {
			return dailyReportDAO.getTotalUnapprovalNum_Member(user_id);
		}
	}
	
	
	@Override
	public HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();		
		return selectDailyReportList(position, user_id, startIdx, perContentNum, map);
	}
	
	
	@Override
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
	
	
	
	@Override
	public DailyReportEmployeeDTO searchPreSales(String employee_id) {
		// TODO Auto-generated method stub
		return dailyReportDAO.selectPreSales(employee_id);
	}

	@Override
	public void writeDailyReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		dailyReportDAO.insertDailyReport(dailyReportDTO);
	}

	@Override
	public DailyReportDetailDTO viewReport(String id) {
		// TODO Auto-generated method stub
		return dailyReportDAO.selectDailyReport(id);
	}

	@Override
	public int searchDailySales(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dailyReportDAO.selectDailySalesGoal(map);
	}

	@Override
	public EmployeeDTO searchImage(String employee_id) {
		// TODO Auto-generated method stub
		return dao1.selectImage(employee_id);
	}

	@Override
	public List<CounsellingRecordDTO> searchCounselRecord(String id) {
		// TODO Auto-generated method stub
		return dailyReportDAO.selectCounselList(id);
	}

	@Override
	public void approvalDailyReport(String daily_report_id) {
		// TODO Auto-generated method stub
		dailyReportDAO.updateApproval(daily_report_id);
	}

}
