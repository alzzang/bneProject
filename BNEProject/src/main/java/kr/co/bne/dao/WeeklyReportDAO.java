package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.dto.WeeklyReportDTO;

public interface WeeklyReportDAO {
	public int selectThisMonthlySales(String employee_id) throws Exception;
	public int selectSalesGoal(String employee_id) throws Exception;
	public List<Map<String, String>> selectDayList(String employee_id) throws Exception;
	public WeeklyReportDTO selectWeeklyReport(int weekly_report_id) throws Exception;
	public List<Integer> selectAllWeeklyReportId(String employee_id) throws Exception;
}
