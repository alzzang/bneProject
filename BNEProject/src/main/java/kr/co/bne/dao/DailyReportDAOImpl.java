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
	

}
