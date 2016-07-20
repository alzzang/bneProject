package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> searchUnconfirmedNotice(Map<String, String> map);

	List<NoticeDTO> searchconfirmedNotice(Map<String, String> map);

}
