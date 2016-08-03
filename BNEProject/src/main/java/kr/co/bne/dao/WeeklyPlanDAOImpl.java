package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.WeeklyPlanDTO;

@Repository
public class WeeklyPlanDAOImpl implements WeeklyPlanDAO {
	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.co.bne.mapper.WeeklyPlan" + ".";
	
	@Override
	public List<WeeklyPlanDTO> selectWeeklyPlanList(String weekly_report_id) throws Exception {
		List<WeeklyPlanDTO> resultList = sqlSession.selectList(namespace + "selectWeeklyPlanList", weekly_report_id);
		return resultList;
	}
	@Override
	public int insertWeeklyPlan(WeeklyPlanDTO weeklyPlan) {
		int result = sqlSession.insert(namespace+"insertWeeklyPlan", weeklyPlan);
		
		return result;
	}
	@Override
	public int updateWeeklyPlan(WeeklyPlanDTO weeklyPlan) {
		int result = sqlSession.update(namespace+"updateWeeklyPlan",weeklyPlan);
		return result;
	}

}
