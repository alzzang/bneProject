package kr.co.bne.service;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.NoticeDetail;

public interface NoticeService {

	List<NoticeDetail> searchUnconfirmedNotice(Map<String, String> map);

	List<NoticeDetail> searchconfirmedNotice(Map<String, String> map);

}
