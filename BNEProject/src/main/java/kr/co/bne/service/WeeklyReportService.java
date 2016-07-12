package kr.co.bne.service;

import java.util.HashMap;

public interface WeeklyReportService {
	public int getThisMonthlySales(String employee_id) throws Exception;
	public int getSalesGoal(String employee_id) throws Exception;
	public HashMap<String, String> getDayList(String employee_id) throws Exception;
}
