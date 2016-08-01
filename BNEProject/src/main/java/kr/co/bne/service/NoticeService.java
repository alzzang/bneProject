package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.common.NoticeHeader;
import kr.co.bne.common.type.NoticeType;

public interface NoticeService {
	
	List<NoticeDetail> searchUnconfirmedNotice(Map<String, String> map);
	
	List<NoticeHeader> selectNoticeList(String user_id, int startIdx, int perContentNum) throws Exception;	

	List<NoticeDetail> searchconfirmedNotice(Map<String, String> map);

	void click(int noticeId);
	
	List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception;

	int getUnreadNoticeCount(String object_id) throws Exception;
	
}
