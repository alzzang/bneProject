package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dto.NoticeDTO;
import kr.co.bne.common.NoticeDetail;

public interface NoticeDAO {
	
	boolean insertNotice(NoticeType notice_type, String subject_id, String object_id) throws Exception;

	boolean insertNotice(NoticeType notice_type, String subject_id) throws Exception;

	List<NoticeDTO> selectNoticeList(String user_id, int perContentNum, int startIdx);
	
	List<NoticeDetail> selectUnconfirmedNotice(Map<String, String> map);

	List<NoticeDetail> selectconfirmedNotice(Map<String, String> map);
	

}
