package kr.co.bne.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.CounsellingRecordDTO;

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
	
}
