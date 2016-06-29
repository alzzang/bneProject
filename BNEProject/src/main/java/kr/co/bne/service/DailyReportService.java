package kr.co.bne.service;

import java.util.HashMap;

import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

public interface DailyReportService {
	public DailyReportEmployeeDTO searchPreSales(String employee_id);
	public void writeDailyReport(DailyReportDTO dailyReportDTO);
	public DailyReportDTO viewReport(String id);
	public int searchDailySales(HashMap<String, String> map);
	
}
