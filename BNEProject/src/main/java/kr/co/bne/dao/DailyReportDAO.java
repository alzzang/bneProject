package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;

public interface DailyReportDAO {

	List<DailyReportTeamListElement> selectTeamMemberList(String user_id) throws RuntimeException ;

	int getTotalUnapprovalNum(String user_id) throws RuntimeException;

	List<DailyReportListElement> selectDailyReportList_All(int department_id, int startIdx, int perContentNum,
			String employee_id) throws RuntimeException;

	int getPagingNum_All(int department_id, String employee_id, int perContentNum) throws RuntimeException;

}
