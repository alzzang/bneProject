package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dao.EmployeeDAO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;
import kr.co.bne.dto.EmployeeDTO;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	private DailyReportDAO dao;
	@Autowired
	private EmployeeDAO dao1;
	
	@Override
	public DailyReportEmployeeDTO searchPreSales(String employee_id) {
		// TODO Auto-generated method stub
		return dao.selectPreSales(employee_id);
	}

	@Override
	public void writeDailyReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		dao.insertDailyReport(dailyReportDTO);
	}

	@Override
	public DailyReportDetailDTO viewReport(String id) {
		// TODO Auto-generated method stub
		return dao.selectDailyReport(id);
	}

	@Override
	public int searchDailySales(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.selectDailySalesGoal(map);
	}

	@Override
	public EmployeeDTO searchImage(String employee_id) {
		// TODO Auto-generated method stub
		return dao1.selectImage(employee_id);
	}

	@Override
	public List<CounsellingRecordDTO> searchCounselRecord(String id) {
		// TODO Auto-generated method stub
		return dao.selectCounselList(id);
	}

	@Override
	public void approvalDailyReport(String daily_report_id) {
		// TODO Auto-generated method stub
		dao.updateApproval(daily_report_id);
	}

	@Override
	public DailyReportDTO searchDailyReport(String id) {
		// TODO Auto-generated method stub
		return dao.updateDailyReport(id);
	}

	@Override
	public void updateDailyReport(DailyReportDTO dailyReportDTO) {
		// TODO Auto-generated method stub
		dao.updateReport(dailyReportDTO);
	}

}
