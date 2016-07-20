package kr.co.bne.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dao.NoticeDAO;
import kr.co.bne.dto.DailyReportDTO;

@Aspect
public class NoticeAspect {
	
	@Autowired
	NoticeDAO noticeDAO;
	
	
	public NoticeAspect() {
		System.out.println("aspect 생성!!");
	}
	
	
	@AfterReturning("execution(public int kr.co.bne.dao.DailyReportDAOImpl.insertDailyReport(*))")
	public void measure(JoinPoint joinPoint) throws Throwable {
		DailyReportDTO dailyReport = (DailyReportDTO)joinPoint.getArgs()[0];
		noticeDAO.insertNotice(NoticeType.DAILY_POST, dailyReport.getEmployee_id());
		
		System.out.println("실행!!");
		//node.js에 보내는 코드도 작성
	}

}
