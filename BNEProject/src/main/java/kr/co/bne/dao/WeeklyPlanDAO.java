package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.dto.WeeklyPlanDTO;

public interface WeeklyPlanDAO {

	public List<WeeklyPlanDTO> selectWeeklyPlanList(String weekly_report_id) throws Exception;
	public int insertWeeklyPlan(WeeklyPlanDTO weeklyPlan);
	public int updateWeeklyPlan(WeeklyPlanDTO weeklyPlan);
}
