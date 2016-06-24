package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.common.DailyReportListElement;

public interface DailyReportDAO {

	List<DailyReportListElement> selectDailyReportList_All(int department_id, int startIdx, String employee_id)
			throws RuntimeException;

	int getPagingNum_All(int department_id, String employee_id) throws RuntimeException;


}
