package kr.co.bne.dao;

import java.util.List;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dto.NoticeDTO;

public interface NoticeDAO {
	
	boolean insertNotice(NoticeType notice_type, String subject_id, String object_id) throws Exception;

	boolean insertNotice(NoticeType notice_type, String subject_id) throws Exception;

	List<NoticeDTO> selectNoticeList(String user_id, int perContentNum, int startIdx);
	
	

}
