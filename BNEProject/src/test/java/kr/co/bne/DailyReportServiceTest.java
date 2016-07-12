package kr.co.bne;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.service.DailyReportService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class DailyReportServiceTest {

	@Autowired
	DailyReportService dailyReportService;
	
	@Test
	public void selectDailyReportList() {
		HashMap<String, Object> map = dailyReportService.selectDailyReportList("manager", "2", 1, 15);
		System.out.println("totalPageNum : " + map.get("totalPageNum"));
		
		List<DailyReportListElement> list = (List<DailyReportListElement>) map.get("dailyReportList");
		for(DailyReportListElement el : list) {
			System.out.println(el.getTitle());
		}
	}
	
	
}
