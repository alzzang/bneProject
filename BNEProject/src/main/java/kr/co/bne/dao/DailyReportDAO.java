package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;

public interface DailyReportDAO {

	List<DailyReportListElement> selectDailyReportList(String user_id, int startIdx, int perContentNum,
			HashMap<String, Object> params) throws RuntimeException;

	int getPagingNum_DailyReportList(String user_id, int perContentNum, HashMap<String, Object> params)
			throws RuntimeException;

	List<DailyReportTeamListElement> selectTeamMemberList(String user_id) throws RuntimeException;

	int getTotalUnapprovalNum_Manager(String user_id) throws RuntimeException;

	int getTotalUnapprovalNum_Member(String user_id) throws RuntimeException;

}
