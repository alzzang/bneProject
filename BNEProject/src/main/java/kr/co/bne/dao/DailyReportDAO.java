package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
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
}
