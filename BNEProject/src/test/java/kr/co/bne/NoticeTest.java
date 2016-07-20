package kr.co.bne;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dao.NoticeDAO;
import kr.co.bne.dto.NoticeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class NoticeTest {
	
	@Autowired
	NoticeDAO dao;
	
	
	public void test() throws Exception {
		boolean ret = dao.insertNotice(NoticeType.DAILY_CORRECT, "3");
		System.out.println(ret);
	}
	
	@Test
	public void test1() throws Exception {
		List<NoticeDTO> list = dao.selectNoticeList("3", 5, 1);
		
		for(NoticeDTO dto : list) { 
			System.out.println(dto.getNotice_id());
		}
	}

}
