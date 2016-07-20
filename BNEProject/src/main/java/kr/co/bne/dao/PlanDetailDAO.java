package kr.co.bne.dao;

import org.springframework.transaction.annotation.Transactional;

import kr.co.bne.dto.PlanDetailDTO;

public interface PlanDetailDAO {
	@Transactional
	public int insertPlanDetail(PlanDetailDTO planDetailDTO) throws Exception;
}
