package kr.co.bne;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dto.DailyReportChartDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class ChartTest {
	
	@Autowired
	DailyReportDAO dao;
	
/*	@Test
	public void test() throws Exception {
		List<DailyReportChartDTO> list = dao.selectDailyReportChartLineList("1");
		
		for(DailyReportChartDTO dto : list) {
			System.out.println(dto.getEmployee_id() + ":" + dto.getReg_date());
		}
	}*/

}
