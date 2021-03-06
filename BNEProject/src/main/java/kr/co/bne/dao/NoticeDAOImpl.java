package kr.co.bne.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.common.NoticeHeader;
import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dto.NoticeDTO;


@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	SqlSession sqlSession;
	
	
	
	@Override
	public List<NoticeHeader> insertNotice(NoticeType notice_type, int link) throws Exception {
		return insertNotice(notice_type, link, null, null);
	}
	
	
	@Override
	public List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception {
		return insertNotice(notice_type, link, subject_id, null);
	}
	
	
	@Override
	public int getUnreadNoticeCount(String object_id) throws Exception {
		int count = sqlSession.selectOne("kr.co.bne.mapper.Notice.getUnreadNoticeCount", object_id);
		return count;
	}
	
	
	
	@Override
	public List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id, String object_id) throws Exception {
		List<NoticeHeader> result = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("notice_type", notice_type.toString());
		map.put("subject_id", subject_id);
		map.put("object_id", object_id);
		
		sqlSession.insert("kr.co.bne.mapper.Notice.create_notice", map);
		result = (List<NoticeHeader>)map.get("result");

		return result;
	}
	
	
	@Override
	public List<NoticeHeader> selectNoticeList(String user_id, int perContentNum, int startIdx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("perContentNum", perContentNum);
		map.put("startIdx", startIdx);
		
		List<NoticeHeader> list = sqlSession.selectList("kr.co.bne.mapper.Notice.selectNoticeList", map);
		
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

	@Override
	public Object updateUnconfirmed(int noticeId) {
		// TODO Auto-generated method stub
		return sqlSession.update("kr.co.bne.mapper.Notice.updateUnconfirmed", noticeId);
	}

}
