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
import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dto.DailyReportDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class DailyReportDAOTest {
	
	@Autowired
	DailyReportDAO dailyReportDAO;
	
	/*@Test*/
	public void selectDailyReportList_Manager_Test() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		/*map.put("employee_id", "1");
		map.put("reg_date", "2016-06-27");*/
		
		List<DailyReportListElement> list = dailyReportDAO.selectDailyReportList("2", 1, 15, map);
		
		int cnt = 1;
		for(DailyReportListElement el : list) {
			System.out.println((cnt++) + ":" + el.getTitle());
		}
	}
	
	
	@Test
	public void insertTest() throws Exception {
		DailyReportDTO dailyReportDTO = new DailyReportDTO();
		dailyReportDTO.setAfter_gauge(10);
		dailyReportDTO.setApproval_flag(1);
		dailyReportDTO.setBefore_gauge(10);
		dailyReportDTO.setContent("Ff");
		dailyReportDTO.setReg_date("2016-07-05");
		dailyReportDTO.setDaily_report_id(333333);
		dailyReportDTO.setDepartment_id(1);
		dailyReportDTO.setEmployee_id("1");
		dailyReportDTO.setSales(1000);
		dailyReportDTO.setTitle("sssssssssssssssssssssssss");
		
		dailyReportDAO.insertDailyReport(dailyReportDTO);
	}
	
	
	//@Test
	public void getPagingNum_DailyReportList_Manager() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("employee_id", "1");
		/*map.put("reg_date", "2016-06-27");*/
		
		int num = dailyReportDAO.getPagingNum_DailyReportList("2", 25, map);
		System.out.println(num);
	}
	
	
	//@Test
	public void getPagingNum_DailyReportList_Member() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("reg_date", "2016-06-27");
		map.put("approval_flag", 0);
		
		int num = dailyReportDAO.getPagingNum_DailyReportList("1", 25, map);
		System.out.println(num);
	}


}
