package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;


public interface DailyReportService {
	public DailyReportEmployeeDTO searchPreSales(String employee_id);
	public void writeDailyReport(DailyReportDTO dailyReportDTO, List<CounsellingRecordDTO> list);
	public DailyReportDetailDTO viewReport(String id);
	public int searchDailySales(HashMap<String, String> map);
	public EmployeeDTO searchImage(String employee_id);
	public List<CounsellingDetailDTO> searchCounselRecord(String id);
	public void approvalDailyReport(String daily_report_id);
	public DailyReportDTO searchDailyReport(String id);
	public void updateDailyReport(DailyReportDTO dailyReportDTO);
	public void writeCounsellingRecord(List<CounsellingRecordDTO> result);
	public void updateDailyReport(DailyReportDTO dailyReportDTO, List<CounsellingRecordDTO> list);
		HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum,
			HashMap<String, Object> params);

	HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum);

	int getgetTotalUnapprovalNum(String position, String user_id);

	HashMap<String, Object> selectTeamMemberList(String user_id);
	
	public HashMap<String,Integer> selectMonthlyGoal(String id);
	
}
