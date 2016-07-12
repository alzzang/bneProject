package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.MailcapCommandMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.WeeklyReportDAO;

@Service
public class WeeklyReportServiceImpl  implements WeeklyReportService{
	
	@Autowired
	WeeklyReportDAO weeklyReportDAO;
	
	@Override
	public int getThisMonthlySales(String employee_id) throws Exception {
		int result  = weeklyReportDAO.selectThisMonthlySales(employee_id);
		System.out.println("매출액" + result);
		return result;
	}

	@Override
	public int getSalesGoal(String employee_id) throws Exception {
		int result  = weeklyReportDAO.selectSalesGoal(employee_id);
		System.out.println("매출목표" + result);
		return result;
	}

	@Override
	public HashMap<String, String> getDayList(String employee_id) throws Exception {
		List<Map<String, String>> resultList = weeklyReportDAO.selectDayList(employee_id);
		HashMap<String, String> result = new HashMap<String, String>();
		
		for (Map<String, String> map : resultList) {
			String weekDayName = map.get("WEEKDAYNAME");
			String reportPlanDays = map.get("REPORT_PLAN_DAYS");
			result.put(weekDayName, reportPlanDays);
		}
		return result;
	}

}
