package kr.co.bne.service;

import java.util.List;

public interface WeeklyReportService {
	public int getThisMonthlySales(String employee_id) throws Exception;
	public int getSalesGoal(String employee_id) throws Exception;
	public List<String> getDayList(String employee_id) throws Exception;
}
