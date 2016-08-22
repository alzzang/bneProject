package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.WeeklyReportMemberInfo;
import kr.co.bne.common.WeeklyReportSearchElement;
import kr.co.bne.dto.WeeklyReportDTO;

public interface WeeklyReportDAO {
	public int selectThisMonthlySales(String employee_id) throws Exception;
	public int selectSalesGoal(String employee_id) throws Exception;
	public WeeklyReportDTO selectWeeklyReport(String weekly_report_id) throws Exception;
	public List<String> selectAllWeeklyReportId(String employee_id) throws Exception;
	public int insertWeeklyReport(WeeklyReportDTO weeklyReport) throws Exception;
	
	// 목록 ////////////////////////////////////////
	
	// 조건에 맞는 주간 계획 목록
	public List<WeeklyReportSearchElement> selectWeeklyReportList(Map<String, Object> parameterMap) throws Exception;
	// 내 부서 사람들 정보 
	public List<WeeklyReportMemberInfo> selectDeptMember(String department_id) throws Exception;
	// 조건에 맞는 작성된 주간 계획의 레코드 수   
	public int selectTotalRecordNum(Map<String, Object> parameterMap) throws Exception;
}
