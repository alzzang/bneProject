package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.WeeklyReportMemberInfo;
import kr.co.bne.common.WeeklyReportSearchElement;
import kr.co.bne.dto.WeeklyReportDTO;

@Repository
public class WeeklyReportDAOImpl implements WeeklyReportDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.co.bne.mapper.WeeklyReport" + "."; 

	@Override
	public int selectThisMonthlySales(String employee_id) throws Exception {
		int result = 0;
		if(sqlSession.selectOne(namespace + "selectThisMonthlySales", employee_id) != null){
			result = sqlSession.selectOne(namespace + "selectThisMonthlySales", employee_id);
		}
		return result;
	}

	@Override
	public int selectSalesGoal(String employee_id) throws Exception {
		int result = 0;
		if(sqlSession.selectOne(namespace + "selectSalesGoal", employee_id) != null){
			result = sqlSession.selectOne(namespace + "selectSalesGoal", employee_id);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> selectDayList(String employee_id) throws Exception {
		List<Map<String, String>> result = sqlSession.selectList(namespace + "selectDayList", employee_id);
		return result;
	}

	@Override
	public List<String> selectAllWeeklyReportId(String employee_id) throws Exception {
		List<String> result = sqlSession.selectList(namespace + "selectAllReportId", employee_id);
		return result;
	}

	@Override
	public WeeklyReportDTO selectWeeklyReport(String weekly_report_id) throws Exception {
		WeeklyReportDTO result = sqlSession.selectOne(namespace + "selectWeeklyReport", weekly_report_id);
		return result;
	}

	@Override
	public int insertWeeklyReport(WeeklyReportDTO weeklyReport) throws Exception {
		int result = sqlSession.insert(namespace + "insertWeeklyReport",weeklyReport);
		return result;
	}

	@Override
	public  List<WeeklyReportSearchElement> selectWeeklyReportList(Map<String, Object> parameterMap) throws Exception {
		List<WeeklyReportSearchElement> result = null;
		result = sqlSession.selectList(namespace+"selectWeeklyReportList", parameterMap);
		return result;
	}

	@Override
	public List<WeeklyReportMemberInfo> selectDeptMember(int department_id) throws Exception {
		List<WeeklyReportMemberInfo> result = null;
		result = sqlSession.selectList(namespace+"selectDeptMember", department_id);
		return result;
	}

	@Override
	public int selectTotalRecordNum(Map<String, Object> parameterMap) throws Exception {
		int result = 0;
		result = sqlSession.selectOne(namespace + "selectTotalRecordNum", parameterMap);
		return result;
	}

	
}
