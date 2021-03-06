package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dao.EmployeeDAO;
import kr.co.bne.dto.CounsellingDetailDTO;
import kr.co.bne.dto.CounsellingRecordDTO;




import kr.co.bne.dto.DailyReportChart2DTO;




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
	public HashMap<String, Object> selectTeamMemberList(String user_id) {
		int totalUnapprovalNum = dao.getTotalUnapprovalNum_Manager(user_id);
		
		List<DailyReportTeamListElement> memberList = dao.selectTeamMemberList(user_id);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("totalUnapprovalNum", totalUnapprovalNum);
		result.put("memberList", memberList);
		
		return result;
	}
	
	
	@Override
	public int getgetTotalUnapprovalNum(String position, String user_id) {
		if(position.equals("manager")) {
			return dao.getTotalUnapprovalNum_Manager(user_id);
		}else {
			return dao.getTotalUnapprovalNum_Member(user_id);
		}
	}
	
	
	@Override
	public HashMap<String, Object> selectDailyReportList(String user_id, int startIdx, int perContentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();		
		return selectDailyReportList(user_id, startIdx, perContentNum, map);
	}
	
	
	@Override
	public HashMap<String, Object> selectDailyReportList(String user_id, int startIdx, int perContentNum, HashMap<String, Object> params) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<DailyReportListElement> dailyReportList = null;
		int totalPageNum = 0;
		
		dailyReportList = dao.selectDailyReportList(user_id, startIdx, perContentNum, params);
		totalPageNum = dao.getPagingNum_DailyReportList(user_id, perContentNum, params);
		
		result.put("dailyReportList", dailyReportList);
		result.put("totalPageNum", totalPageNum);
		
		return result;
	}
	
	
	@Override
	public DailyReportEmployeeDTO searchPreSales(String employee_id) {
		// TODO Auto-generated method stub
		
		return dao.selectPreSales(employee_id);
	}

	@Override
	public void writeDailyReport(DailyReportDTO dailyReportDTO,List<CounsellingRecordDTO> list) {
		// TODO Auto-generated method stub
		dao.insertDailyReport(dailyReportDTO);
		int seq=dailyReportDTO.getDaily_report_id();
		for(int i=0;i<list.size();i++){
			list.get(i).setDaily_report_id(seq);
			dao.insertCounsellingRecord(list.get(i));
		}
	}

	@Override
	public DailyReportDetailDTO viewReport(String id) {
		// TODO Auto-generated method stub
		DailyReportDetailDTO dto=dao.selectDailyReport(id);
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("reg_date", dto.getReg_date());
		map.put("employee_id", dto.getEmployee_id());
		System.out.println(map.toString());
		int wpsales=0;
		try {
			dto.setWpsales(dao.selectDailySalesGoal(map));
		} catch (Exception e) {
			dto.setWpsales(wpsales);
		}

		return dto;  
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
	public List<CounsellingDetailDTO> searchCounselRecord(String id) {
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

	@Override
	public void writeCounsellingRecord(List<CounsellingRecordDTO> result) {
		// TODO Auto-generated method stub
		for(int i=0;i<result.size();i++){
			dao.insertCounsellingRecord(result.get(i));
		}
		
	}

	@Override
	public void updateDailyReport(DailyReportDTO dailyReportDTO, List<CounsellingRecordDTO> list) {
		// TODO Auto-generated method stub
		dao.updateReport(dailyReportDTO);
		int seq=dailyReportDTO.getDaily_report_id();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getCounsel_id()==0){
				list.get(i).setDaily_report_id(seq);
				dao.insertCounsellingRecord(list.get(i));
			}
			else if(list.get(i).getCounsel_id()<0){
				dao.deleteCounsellingRecord(list.get(i).getCounsel_id()*-1);
			}
			else{
				dao.updateCounsellingRecord(list.get(i));
			}
			
			
		}
	}

	@Override




	public HashMap<String, Integer> selectMonthlyGoal(String id,String position) {


	


		// TODO Auto-generated method stub

		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		if(position.equals("manager")){
			result.put("monthlyGoal", dao.selectMonthlyGoalManager(id));
			result.put("sumofMonthlyGoal", dao.selectSumofMonthlyGoalManager(id));
		}
		else{
			result.put("monthlyGoal", dao.selectMonthlyGoal(id)); //목표액
			result.put("sumofMonthlyGoal", dao.selectSumofMonthlyGoal(id)); //달성액
		}
		return result;


	}


		

	@Override
	public void writeComment(HashMap<String, String> map) {

		// TODO Auto-generated method stub
			dao.insertComment(map);
	}


	@Override
	public List<DailyReportEmployeeDTO> selectTeamMonthlyGoal(String employeeId) {
		// TODO Auto-generated method stub
		return dao.selectTeamMonthlyGoal(employeeId);
	}
	@Override
	public List<?> selectVehicleGauge(String id) {
		// TODO Auto-generated method stub
		return dao.selectVehicleGauge(id);
	}


	@Override
	public List<DailyReportChart2DTO> selectCustomersSales(int departmentId) {
		return dao.selectCustomerSales(departmentId);

	}
	
	
	@Override
	public void delete(String id) {

		// TODO Auto-generated method stub
		dao.deleteReport(id);

	}


	@Override
	public void removeComment(String daily_report_id) {
		// TODO Auto-generated method stub
		dao.deleteComment(daily_report_id);
	}

	@Override
	   public HashMap<String, List<?>> searchDailyChartLine(String employee_id,int department_id) {
	      // TODO Auto-generated method stub
	      HashMap<String, List<?>> result = new HashMap<String,List<?>>();
	      result.put("List1", dao.selectDailyReportChartLineList(employee_id));
	      result.put("List2", dao.selectDailyReportChartLine2List(department_id));
	      return result;
	   }

	@Override
	public int confirmDuplicate(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.selectDailyCount(map);
	}
	
	@Override
	public int checkReport(String date,String employeeId) {
		int result = dao.checkDailyReport(date,employeeId);
		return result;
	}
}
