package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.common.NoticeDetail;
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
	public List<NoticeDTO> selectNoticeList(String user_id, int startIdx, int perContentNum) throws Exception {
		List<NoticeDTO> list = noticeDAO.selectNoticeList(user_id, perContentNum, startIdx);
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

}
