package kr.co.bne.dao;

import java.util.HashMap;

import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

public interface DailyReportDAO {
	public DailyReportEmployeeDTO selectPreSales(String employee_id);
	public void insertDailyReport(DailyReportDTO dailyReportDTO);
	public DailyReportDTO selectDailyReport(String id);
	public int selectDailySalesGoal(HashMap<String, String> map);
}
