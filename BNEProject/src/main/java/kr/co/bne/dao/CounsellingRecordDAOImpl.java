package kr.co.bne.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.CounsellingManageSales;
import kr.co.bne.common.CounsellingSalesGoal;
import kr.co.bne.dto.CounsellingManageSalesDTO;
import kr.co.bne.dto.CounsellingRecordDTO;
import kr.co.bne.dto.EmployeeDTO;

@Repository

public class CounsellingRecordDAOImpl implements CounsellingRecordDAO {
	
	@Autowired 
	private SqlSession sqlSession;

	@Override
	public void insertCounsellingRecord(CounsellingRecordDTO counsellingRecord) {
		// TODO Auto-generated method stub
		sqlSession.insert("kr.co.bne.mapper.CounsellingRecord.insertCounsellingRecord",counsellingRecord);
	}

	@Override
	public CounsellingRecordDTO selectCounsellingRecord(int counsellingId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("kr.co.bne.mapper.CounsellingRecord.selectCounsellingRecord",counsellingId);
	}
	
	@Override
	public List<EmployeeDTO> selectManageSales(int departmentId){
		return sqlSession.selectList("kr.co.bne.mapper.CounsellingRecord.selectManageUser",departmentId);
	}

	@Override
	public List<CounsellingManageSalesDTO> selectManageSalesList(CounsellingManageSales cm) {
		return sqlSession.selectList("kr.co.bne.mapper.CounsellingRecord.selectManageSalesList",cm);
	}

	@Override
	public void insertSalesGoal(CounsellingSalesGoal csg) {
		sqlSession.insert("kr.co.bne.mapper.CounsellingRecord.insertSalesGoal",csg);
		
		
	}

	@Override
	public void updateSalesGoal(CounsellingSalesGoal csg) {
		sqlSession.update("kr.co.bne.mapper.CounsellingRecord.updateSalesGoal",csg);
		
		
	}
	
}
