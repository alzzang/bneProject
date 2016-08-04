
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import kr.co.bne.dao.DailyReportDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class test {
   
   @Autowired
   DailyReportDAO dao;
   
   @Test
   public void selectDailyReportList_All_Test() throws Exception {
	   
   }

}