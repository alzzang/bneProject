package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dto.NoticeDTO;

public interface NoticeService {
	
	List<NoticeDetail> searchUnconfirmedNotice(Map<String, String> map);
	
	List<NoticeDTO> selectNoticeList(String user_id, int startIdx, int perContentNum) throws Exception;	

	List<NoticeDetail> searchconfirmedNotice(Map<String, String> map);

	boolean insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception;
	
}
