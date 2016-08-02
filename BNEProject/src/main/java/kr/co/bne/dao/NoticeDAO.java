package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.NoticeDetail;
import kr.co.bne.common.NoticeHeader;
import kr.co.bne.common.type.NoticeType;

public interface NoticeDAO {

	List<NoticeHeader> selectNoticeList(String user_id, int perContentNum, int startIdx);
	
	List<NoticeDetail> selectUnconfirmedNotice(Map<String, String> map);

	List<NoticeDetail> selectconfirmedNotice(Map<String, String> map);

	Object updateUnconfirmed(int noticeId);

	List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id) throws Exception;

	List<NoticeHeader> insertNotice(NoticeType notice_type, int link, String subject_id, String object_id) throws Exception;

	List<NoticeHeader> insertNotice(NoticeType notice_type, int link) throws Exception;

	int getUnreadNoticeCount(String object_id) throws Exception;
	
}
