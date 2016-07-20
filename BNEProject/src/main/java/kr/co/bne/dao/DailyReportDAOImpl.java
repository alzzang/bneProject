package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import oracle.net.aso.s;

@Repository
public class DailyReportDAOImpl implements DailyReportDAO {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public DailyReportEmployeeDTO selectPreSales(String employee_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectEmployee", employee_id);
	}
	@Override
	public int insertDailyReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("kr.co.bne.mapper.DailyReport.insertDailyReport", dailyReportDTO);
		
	}
	@Override
	public DailyReportDetailDTO selectDailyReport(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectDailyReport", id);
	}
	@Override
	public int selectDailySalesGoal(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.selectDailySalesGoal", map);
	}
	@Override
	public List<CounsellingDetailDTO> selectCounselList(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectCounselRecordList", id);
	}
	@Override
	public void updateApproval(String daily_report_id) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.DailyReport.updateApproval",daily_report_id);
		
	}
	@Override
	public DailyReportDTO updateDailyReport(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.DailyReport.DailyReport", id);
	}
	@Override
	public void updateReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.DailyReport.updateReport", dailyReportDTO);
	}
	@Override
	public void insertCounsellingRecord(CounsellingRecordDTO result) {
		// TODO Auto-generated method stub
		sqlSession.insert("kr.co.bne.mapper.DailyReport.insertCounsellingList", result);
	}
	@Override
	public void updateCounsellingRecord(CounsellingRecordDTO counsellingRecordDTO) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.DailyReport.updateCounsellingList", counsellingRecordDTO);
	}
	@Override
	public void deleteCounsellingRecord(int counsel_id) {
		// TODO Auto-generated method stub
		sqlSession.delete("kr.co.bne.mapper.DailyReport.deleteCounsellingList", counsel_id);
	}
	@Override
	public List<DailyReportListElement> selectDailyReportList(String user_id, int startIdx, int perContentNum, HashMap<String, Object> params) throws RuntimeException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("startIdx", startIdx);
		map.put("perContentNum", perContentNum);
		
		List<DailyReportListElement> list = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectDailyReportList", map);
	
		return list;
	}
	
	
	
	@Override
	public int getPagingNum_DailyReportList(String user_id, int perContentNum, HashMap<String, Object> params) throws RuntimeException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(params != null) {
			map.putAll(params);
		}
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		
		int pageNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getPagingNum_DailyReportList", map);
		
		return pageNum;
	}
	

	@Override
	public List<DailyReportTeamListElement> selectTeamMemberList(String user_id) throws RuntimeException {
		List<DailyReportTeamListElement> teamList = sqlSession.selectList("kr.co.bne.mapper.DailyReport.selectTeamMemberList", user_id);
	
		return teamList;
	}
	
	
	@Override
	public int getTotalUnapprovalNum_Manager(String user_id) throws RuntimeException {
		int TotalUnapprovalNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getTotalUnapprovalNum_Manager", user_id);
		
		return TotalUnapprovalNum;
	}
	
	
	
	@Override
	public int getTotalUnapprovalNum_Member(String user_id) throws RuntimeException {
		int TotalUnapprovalNum = sqlSession.selectOne("kr.co.bne.mapper.DailyReport.getTotalUnapprovalNum_Member", user_id);
		
		return TotalUnapprovalNum;
	}
	
	@Override
	public void insertComment(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.DailyReport.insertComment", map);
	}
	@Override
	public void deleteComment(String daily_report_id) {
		// TODO Auto-generated method stub
		sqlSession.update("kr.co.bne.mapper.DailyReport.deleteComment", daily_report_id);
	}

}
