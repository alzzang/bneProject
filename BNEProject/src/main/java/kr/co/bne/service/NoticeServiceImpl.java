package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bne.dao.NoticeDAO;
import kr.co.bne.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO noticeDAO;
	
	@Override
	public List<NoticeDTO> searchUnconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectUnconfirmedNotice(map);
	}

	@Override
	public List<NoticeDTO> searchconfirmedNotice(Map<String, String> map) {
		// TODO Auto-generated method stub
		return noticeDAO.selectconfirmedNotice(map);
	}

}
