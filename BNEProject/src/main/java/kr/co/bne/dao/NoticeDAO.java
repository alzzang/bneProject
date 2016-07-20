package kr.co.bne.dao;

import java.util.List;
import java.util.Map;

import kr.co.bne.common.NoticeDetail;

public interface NoticeDAO {

	List<NoticeDetail> selectUnconfirmedNotice(Map<String, String> map);

	List<NoticeDetail> selectconfirmedNotice(Map<String, String> map);

}
