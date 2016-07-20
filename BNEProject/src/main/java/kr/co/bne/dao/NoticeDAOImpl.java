package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.dto.NoticeDTO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<NoticeDTO> selectUnconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.Notice.selectUnconfirmedNotices", map);
	}

	@Override
	public List<NoticeDTO> selectconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.Notice.selectconfirmedNotices", map);
	}

}
