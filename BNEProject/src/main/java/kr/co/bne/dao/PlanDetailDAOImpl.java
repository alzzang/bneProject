package kr.co.bne.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.PlanDetailDTO;

@Repository
public class PlanDetailDAOImpl implements PlanDetailDAO {

	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.co.bne.mapper.PlanDetail" + "."; 
	
	@Override
	public int insertPlanDetail(PlanDetailDTO planDetailDTO) throws Exception {
		int result = 0;
		result = sqlSession.insert(namespace + "insertPlanDetail", planDetailDTO);
		return result;
	}

}
