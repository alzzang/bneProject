package kr.co.bne.service;

import java.util.HashMap;
import java.util.List;

import kr.co.bne.dto.PlanDetailDTO;
import kr.co.bne.dto.WeeklyPlanDTO;
import kr.co.bne.dto.WeeklyReportDTO;
import kr.co.bne.dto.WeeklyReportDetailDTO;

public interface WeeklyReportService {

	// 작성시 필요한 정보
	public int getThisMonthlySales(String employee_id) throws Exception;
	public int getSalesGoal(String employee_id) throws Exception;
	public int writeWeeklyReport(WeeklyReportDTO wrDTO, List<WeeklyPlanDTO> wpDTOList, List<PlanDetailDTO> pdDTOList) throws Exception ;
	
	//음뭘까??
	public List<Integer> selectAllReportId(String employee_id) throws Exception;
	public WeeklyReportDetailDTO selectWeeklyReportDetail(int weekly_report_id) throws Exception;
	
	// 날짜 자동으로 불러오는건데 일단 안씀
	public HashMap<String, String> getDayList(String employee_id) throws Exception;
}
