package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.WeeklyReportMemberInfo;
import kr.co.bne.common.WeeklyReportSearchElement;
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
		int weeklyPlanResult = 0;
		
		for (WeeklyPlanDTO weeklyPlan : weeklyReportDetail.getWeeklyPlanDTOList()) {
			weeklyPlanResult = weeklyPlanDAO.updateWeeklyPlan(weeklyPlan);
		}
		
		planDetailDAO.deletePlanDetail(weeklyReportDetail.getWeeklyReportDTO().getWeekly_report_id());
		for (PlanDetailDTO planDetail : weeklyReportDetail.getPlanDetailDTOList()) {
			planDetailResult += planDetailDAO.insertPlanDetail(planDetail);
		}
		return 0;
	}
	
	@Override
	public List<WeeklyReportSearchElement> selectWeeklyReportSearch(Map<String, Object> parameterMap) throws Exception {
		List<WeeklyReportSearchElement> result = null; 
		result = weeklyReportDAO.selectWeeklyReportList(parameterMap);
		return result;
	}

	@Override
	public List<WeeklyReportMemberInfo> selectDeptMember(int department_id) throws Exception {
		List<WeeklyReportMemberInfo> result = weeklyReportDAO.selectDeptMember(department_id);
		return result;
	}

	@Override
	public int selectTotalRecordNum(Map<String, Object> parameterMap) throws Exception {
		int result = 0;
		result = weeklyReportDAO.selectTotalRecordNum(parameterMap);
		return result;
	}
	@Override
	public WeeklyReportDTO selectWeeklyReport(int link_id) {
		// TODO Auto-generated method stub
		return weeklyReportDAO.getWeeklyReport(link_id);
	}

}
