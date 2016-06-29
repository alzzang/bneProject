package kr.co.bne.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportEmployeeDTO;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	private DailyReportDAO dao;
	
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
	public DailyReportDTO viewReport(String id) {
		// TODO Auto-generated method stub
		return dao.selectDailyReport(id);
	}

	@Override
	public int searchDailySales(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.selectDailySalesGoal(map);
	}

}
