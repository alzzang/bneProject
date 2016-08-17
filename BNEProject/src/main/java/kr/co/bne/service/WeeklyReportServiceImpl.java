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
import kr.co.bne.dto.WeeklyReportDetailDTO;

@Service
public class WeeklyReportServiceImpl implements WeeklyReportService{
	
	@Autowired
	WeeklyReportDAO weeklyReportDAO;
	@Autowired
	WeeklyPlanDAO weeklyPlanDAO;
	@Autowired
	PlanDetailDAO planDetailDAO;

	@Override
	public int writeWeeklyReport(WeeklyReportDetailDTO weeklyReportDetail) throws Exception  {
		int result = 0;
		int planDetailResult = 0;
		int weeklyPlanResult =0;
		// 1. WeeklyReportDAO insert
		//weeklyReportDAO.
		int WeeklyReportResult = weeklyReportDAO.insertWeeklyReport(weeklyReportDetail.getWeeklyReportDTO());
		System.out.println(WeeklyReportResult);
		// 2. WeeklyPlanDAO insert * (mon, tue, wed, thu, fri)
		for (WeeklyPlanDTO weeklyPlan : weeklyReportDetail.getWeeklyPlanDTOList()) {
			weeklyPlanResult = weeklyPlanDAO.insertWeeklyPlan(weeklyPlan);
		}
		// 3-1. PlanDetailDAO insert 
		for (PlanDetailDTO planDetail : weeklyReportDetail.getPlanDetailDTOList()) {
			planDetailResult += planDetailDAO.insertPlanDetail(planDetail);
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
	public List<String> selectAllReportId(String employee_id) throws Exception{
		List<String> result = weeklyReportDAO.selectAllWeeklyReportId(employee_id);
		return result;
	}
	
	@Override
	public WeeklyReportDetailDTO selectWeeklyReportDetail(String weekly_report_id) throws Exception {
		WeeklyReportDTO weeklyReportDTO = weeklyReportDAO.selectWeeklyReport(weekly_report_id);
		List<WeeklyPlanDTO> weeklyPlanDTOList = weeklyPlanDAO.selectWeeklyPlanList(weekly_report_id);
		List<PlanDetailDTO> planDetailDTOList = planDetailDAO.selectPlanDetailList(weekly_report_id);
		
		WeeklyReportDetailDTO result = new WeeklyReportDetailDTO(weeklyReportDTO, weeklyPlanDTOList, planDetailDTOList);
		
		return result;
	}
	@Override
	public int modifyWeeklyReport(WeeklyReportDetailDTO weeklyReportDetail) throws Exception {
		
		int planDetailResult = 0;
		int weeklyPlanResult =0;
		
		for (WeeklyPlanDTO weeklyPlan : weeklyReportDetail.getWeeklyPlanDTOList()) {
			weeklyPlanResult = weeklyPlanDAO.updateWeeklyPlan(weeklyPlan);
		}
		
		planDetailDAO.deletePlanDetail(weeklyReportDetail.getWeeklyReportDTO().getWeekly_report_id());
		for (PlanDetailDTO planDetail : weeklyReportDetail.getPlanDetailDTOList()) {
			planDetailResult += planDetailDAO.insertPlanDetail(planDetail);
		}
		return 0;
	}
	

}
