package kr.co.bne.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.bne.dto.PlanDetailDTO;

public interface PlanDetailDAO {
	@Transactional
	public int insertPlanDetail(PlanDetailDTO planDetailDTO) throws Exception;
	public List<PlanDetailDTO> selectPlanDetailList(String weekly_report_id) throws Exception;
	public int deletePlanDetail(String ReportId) throws Exception;
}
