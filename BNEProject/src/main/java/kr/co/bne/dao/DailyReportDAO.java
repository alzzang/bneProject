package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportChart2DTO;
import kr.co.bne.dto.DailyReportChartDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

public interface DailyReportDAO {
	public DailyReportEmployeeDTO selectPreSales(String employee_id);
	public int insertDailyReport(DailyReportDTO dailyReportDTO);
	public DailyReportDetailDTO selectDailyReport(String id);
	public int selectDailySalesGoal(HashMap<String, String> map);
	public List<CounsellingDetailDTO> selectCounselList(String id);
	public void updateApproval(String daily_report_id);
	public DailyReportDTO updateDailyReport(String id);
	public void updateReport(DailyReportDTO dailyReportDTO);
	public void insertCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO);
	public void updateCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO);
	public void deleteCounsellingRecord(int i);

	public int selectMonthlyGoal(String id);
	public int selectSumofMonthlyGoal(String id);
	
	public int selectMonthlyGoalManager(String id);
	public int selectSumofMonthlyGoalManager(String id);
	public List<DailyReportEmployeeDTO> selectTeamMonthlyGoal(String id);

	
	
	List<DailyReportListElement> selectDailyReportList(String user_id, int startIdx, int perContentNum,
			HashMap<String, Object> params) throws RuntimeException;

	int getPagingNum_DailyReportList(String user_id, int perContentNum, HashMap<String, Object> params)
			throws RuntimeException;

	List<DailyReportTeamListElement> selectTeamMemberList(String user_id) throws RuntimeException;

	int getTotalUnapprovalNum_Manager(String user_id) throws RuntimeException;
	int getTotalUnapprovalNum_Member(String user_id) throws RuntimeException;


	public List<DailyReportChartDTO> selectDailyReportChartLineList(String id);
	public List<DailyReportChart2DTO> selectDailyReportChartLine2List(int department_id);
	public List<?> selectVehicleGauge(String id);
	public List<DailyReportChart2DTO> selectCustomerSales(int departmentId);


	public void insertComment(HashMap<String, String> map);
	public void deleteComment(String daily_report_id);
	public void deleteReport(String id);

	public int selectDailyCount(HashMap<String, String> map);
}
