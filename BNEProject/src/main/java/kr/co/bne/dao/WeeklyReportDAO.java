package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.dto.WeeklyReportName;

public interface WeeklyReportDAO {
	public int selectThisMonthlySales(String employee_id) throws Exception;
	public int selectSalesGoal(String employee_id) throws Exception;
	public List<Map<String, String>> selectDayList(String employee_id) throws Exception;
	public WeeklyReportName selectWeeklyReport(String weekly_report_id) throws Exception;
	public List<String> selectAllWeeklyReportId(String employee_id) throws Exception;
}
