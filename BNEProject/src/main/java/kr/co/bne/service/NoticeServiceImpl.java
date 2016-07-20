package kr.co.bne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dao.NoticeDAO;
import kr.co.bne.dto.NoticeDTO;


@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDAO noticeDAO;
	
	@Override
	public boolean insertNotice(NoticeType notice_type, String subject_id) throws Exception {
		boolean result = noticeDAO.insertNotice(notice_type, subject_id);
		return result;
	}
	
	@Override
	public boolean insertNotice(NoticeType notice_type, String subject_id, String object_id) throws Exception {
		boolean result = noticeDAO.insertNotice(notice_type, subject_id, object_id);
		return result;
	}
	
	
	@Override
	public List<NoticeDTO> selectNoticeList(String user_id, int startIdx, int perContentNum) throws Exception {
		List<NoticeDTO> list = noticeDAO.selectNoticeList(user_id, perContentNum, startIdx);
		return list;
	}

}
