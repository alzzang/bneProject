package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dto.NoticeDTO;
import kr.co.bne.common.NoticeDetail;


@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	SqlSession sqlSession;
	
	
	
	@Override
	public boolean insertNotice(NoticeType notice_type, int link) throws Exception {
		return insertNotice(notice_type, link, null, null);
	}
	
	
	@Override
	public boolean insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception {
		return insertNotice(notice_type, link, subject_id, null);
	}
	
	
	@Override
	public boolean insertNotice(NoticeType notice_type, int link, String subject_id, String object_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("notice_type", notice_type.toString());
		map.put("subject_id", subject_id);
		map.put("object_id", object_id);
		
		sqlSession.insert("kr.co.bne.mapper.Notice.create_notice", map);
		int result = (Integer)map.get("result");
		System.out.println(result);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@Override
	public List<NoticeDTO> selectNoticeList(String user_id, int perContentNum, int startIdx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		map.put("startIdx", startIdx);
		
		List<NoticeDTO> list = sqlSession.selectList("kr.co.bne.mapper.Notice.selectNoticeList", map);
		
		return list;
	}
	
	
	
	@Override
	public List<NoticeDetail> selectUnconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.Notice.selectUnconfirmedNotices", map);
	}

	@Override
	public List<NoticeDetail> selectconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.co.bne.mapper.Notice.selectconfirmedNotices", map);
	}
	
}