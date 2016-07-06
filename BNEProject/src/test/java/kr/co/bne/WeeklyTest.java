package kr.co.bne;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.bne.service.WeeklyReportService;
import kr.co.bne.service.WeeklyReportServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class WeeklyTest {

	@Autowired
	WeeklyReportService service;
	
	@Test
	public void test() {
	}

}
