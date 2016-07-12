package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;


import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;


public interface DailyReportService {

	void approvalDailyReport(String daily_report_id);

	List<CounsellingRecordDTO> searchCounselRecord(String id);

	EmployeeDTO searchImage(String employee_id);

	int searchDailySales(HashMap<String, String> map);

	DailyReportDetailDTO viewReport(String id);

	void writeDailyReport(DailyReportDTO dailyReportDTO);

	DailyReportEmployeeDTO searchPreSales(String employee_id);

	HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum,
			HashMap<String, Object> params);

	HashMap<String, Object> selectDailyReportList(String position, String user_id, int startIdx, int perContentNum);

	int getgetTotalUnapprovalNum(String position, String user_id);

	HashMap<String, Object> selectTeamMemberList(String user_id);

	
	
}
