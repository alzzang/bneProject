package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.bne.dao.WeeklyReportDAO;

public class WeeklyReportServiceImpl  implements WeeklyReportService{
	
	@Autowired
	WeeklyReportDAO weeklyReportDAO;
	
	@Override
	public int getThisMonthlySales(String employee_id) throws Exception {
		int result  = weeklyReportDAO.selectThisMonthlySales(employee_id);
		return result;
	}

	@Override
	public int getSalesGoal(String employee_id) throws Exception {
		int result  = weeklyReportDAO.selectSalesGoal(employee_id);
		return result;
	}

	@Override
	public List<String> getDayList(String employee_id) throws Exception {
		List<String> result = weeklyReportDAO.selectDayList(employee_id);
		return result;
	}

}
