package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.PlanDetailDAO;
import kr.co.bne.dao.WeeklyPlanDAO;
import kr.co.bne.dao.WeeklyReportDAO;
import kr.co.bne.dto.PlanDetailDTO;
import kr.co.bne.dto.WeeklyPlanDTO;
import kr.co.bne.dto.WeeklyReportDTO;

@Service
public class WeeklyReportServiceImpl implements WeeklyReportService{
	
	@Autowired
	WeeklyReportDAO weeklyReportDAO;
	@Autowired
	WeeklyPlanDAO weeklyPlanDAO;
	@Autowired
	PlanDetailDAO planDetailDAO;

	@Override
	public int writeWeeklyReport(WeeklyReportDTO wrDTO, List<WeeklyPlanDTO> wpDTOList, List<PlanDetailDTO> pdDTOList) throws Exception  {
		int result = 0;
		int planDetailResult = 0;
		// 1. WeeklyReportDAO insert
		// 2. WeeklyPlanDAO insert * (mon, tue, wed, thu, fri)
		// 3-1. PlanDetailDAO insert 
		for (PlanDetailDTO planDetailDTO : pdDTOList) {
			planDetailResult += planDetailDAO.insertPlanDetail(planDetailDTO);
		}
		
		result += planDetailResult;
		// 3-2. PlanDetailDAO 리스트 째로
		return result;
	}
	@Override
	public int getThisMonthlySales(String employee_id) throws Exception {
		int result = weeklyReportDAO.selectThisMonthlySales(employee_id);
		System.out.println("매출액 : " + result);
		return result;
	}

	@Override
	public int getSalesGoal(String employee_id) throws Exception {
		int result  = weeklyReportDAO.selectSalesGoal(employee_id);
		System.out.println("매출목표 : " + result);
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
	@Override
	public List<Integer> selectAllReportId(String employee_id) {
		
		List<Integer> result = weeklyReportDAO.selectAllWeeklyReportId(employee_id);
		return result;
	}
	

}
