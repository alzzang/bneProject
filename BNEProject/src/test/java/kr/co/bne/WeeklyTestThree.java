package kr.co.bne;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import kr.co.bne.service.WeeklyReportService;
import kr.co.bne.service.WeeklyReportServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class WeeklyTestThree extends TestCase {
	@Test
	public void test() {
		try {
			WeeklyReportService service = new WeeklyReportServiceImpl();
			//System.out.println(service.getSalesGoal("1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
