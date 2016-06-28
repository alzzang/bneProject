package kr.co.bne;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import kr.co.bne.common.DailyReportListElement;
import kr.co.bne.common.DailyReportTeamListElement;
import kr.co.bne.dao.DailyReportDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class DailyReportDAOTest {
	
	@Autowired
	DailyReportDAO dailyReportDAO;
	
	
	
	/*public void selectDailyReportList_All_Test() throws Exception {
		List<DailyReportListElement> list = dailyReportDAO.selectDailyReportList_All(1, 1, "1");
		
		for(DailyReportListElement el : list) {
			System.out.println(el.getIdx() + ":" + el.getEmployee_name() + ":" + el.getTitle() );
		}
	}
	
	
	
	public void getPagingNum_All_Test() throws Exception {
		int num = dailyReportDAO.getPagingNum_All(1, "1");
		
		System.out.println(num);
	}
	
	
	
	@Test
	public void selectTeamMemberList() throws Exception {
		List<DailyReportTeamListElement> list = dailyReportDAO.selectTeamMemberList("2");
		
		for(DailyReportTeamListElement el : list) {
			System.out.println(el.getEmployee_name());
		}
	}
	
	
	
	
	public void getTotalUnapprovalNum() throws Exception {
		int num = dailyReportDAO.getTotalUnapprovalNum("2");

		System.out.println(num);
	}*/

}
