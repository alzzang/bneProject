package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportChart2DTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;



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

	public void writeComment(HashMap<String, String> map);
	public void removeComment(String daily_report_id);
	public void delete(String id);
	

	public HashMap<String, List<?>> searchDailyChartLine(String employee_id,int department_id);
	public HashMap<String,Integer> selectMonthlyGoal(String id, String position);
	public List<DailyReportEmployeeDTO> selectTeamMonthlyGoal(String employeeId);
	public List<?> selectVehicleGauge(String id);
	public List<DailyReportChart2DTO> selectCustomersSales(int departmentid);
	public int checkReport(String date,String employeeId);




}
