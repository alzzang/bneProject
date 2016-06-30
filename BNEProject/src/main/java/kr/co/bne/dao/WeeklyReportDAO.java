package kr.co.bne.dao;

import java.util.List;

public interface WeeklyReportDAO {
	public int selectThisMonthlySales(String employee_id) throws Exception;
	public int selectSalesGoal(String employee_id) throws Exception;
	public List<String> selectDayList(String employee_id) throws Exception;
}
