package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.CounsellingRecordDTO;
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
	public List<CounsellingRecordDTO> searchCounselRecord(String id);
	public void approvalDailyReport(String daily_report_id);
	public DailyReportDTO searchDailyReport(String id);
	public void updateDailyReport(DailyReportDTO dailyReportDTO);
	public void writeCounsellingRecord(List<CounsellingRecordDTO> result);
}
