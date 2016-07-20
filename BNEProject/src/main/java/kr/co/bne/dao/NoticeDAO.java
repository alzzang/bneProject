package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.dto.NoticeDTO;

public interface NoticeDAO {

	List<NoticeDTO> selectUnconfirmedNotice(Map<String, String> map);

	List<NoticeDTO> selectconfirmedNotice(Map<String, String> map);

}
