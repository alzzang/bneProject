package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.WeeklyReportMemberInfo;
import kr.co.bne.common.WeeklyReportSearchElement;
import kr.co.bne.dto.WeeklyReportDetailDTO;

public interface WeeklyReportService {

	// 작성시 필요한 정보
	public int getThisMonthlySales(String employee_id) throws Exception;
	public int getSalesGoal(String employee_id) throws Exception;
	public int writeWeeklyReport(WeeklyReportDetailDTO weeklyReportDetail) throws Exception ;
	public int modifyWeeklyReport(WeeklyReportDetailDTO weeklyReportDetail) throws Exception;
	
	//음뭘까??
	public List<String> selectAllReportId(String employee_id) throws Exception;
	public WeeklyReportDetailDTO selectWeeklyReportDetail(String weekly_report_id) throws Exception;
	
	//목록
	public List<WeeklyReportSearchElement> selectWeeklyReportSearch(Map<String, Object> parameterMap) throws Exception;
	public List<WeeklyReportMemberInfo> selectDeptMember(String department_id) throws Exception;
	public int selectTotalRecordNum(Map<String, Object> parameterMap) throws Exception;
}
