package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.common.NoticeHeader;
import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dao.NoticeDAO;


@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDAO noticeDAO;
	
	@Override
	public List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception {
		List<NoticeHeader> result = noticeDAO.insertNotice(notice_type, link, subject_id);
		return result;
	}
	
	
	@Override
	public int getUnreadNoticeCount(String object_id) throws Exception {
		return noticeDAO.getUnreadNoticeCount(object_id);
	}
	
	
	
	@Override
	public List<NoticeHeader> selectNoticeList(String user_id, int startIdx, int perContentNum) throws Exception {
		List<NoticeHeader> list = noticeDAO.selectNoticeList(user_id, perContentNum, startIdx);
		return list;
	}
	
	
	@Override
	public List<NoticeDetail> searchUnconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectUnconfirmedNotice(map);
	}

	@Override
	public List<NoticeDetail> searchconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectconfirmedNotice(map);
	}

	@Override
	public void click(int noticeId) {
		// TODO Auto-generated method stub
		noticeDAO.updateUnconfirmed(noticeId);
	}

}
