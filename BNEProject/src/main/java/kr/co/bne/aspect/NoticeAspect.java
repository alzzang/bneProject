package kr.co.bne.aspect;

import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.bne.common.NoticeDeliver;
import kr.co.bne.common.NoticeHeader;
import kr.co.bne.common.type.NoticeType;
import kr.co.bne.dao.DailyReportDAO;
import kr.co.bne.dao.NoticeDAO;
import kr.co.bne.dto.DailyReportDTO;
import kr.co.bne.dto.DailyReportDetailDTO;
import kr.co.bne.dto.NoticeDTO;


@Component
@Aspect
public class NoticeAspect {
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private DailyReportDAO dailyReportDAO;
	@Autowired
	private NoticeDeliver deliver;
	
	
	
	public NoticeAspect() {
		System.out.println("aspect 생성!!");
	}
	
	
	
	@AfterReturning("execution(public int kr.co.bne.dao.DailyReportDAO.insertDailyReport(..))")
	public void execInsertNotice_DAILY_POST(JoinPoint joinPoint) throws Throwable {
		System.out.println("일일보고 등록 aop 실행!!");
		
		DailyReportDTO dailyReport = (DailyReportDTO)joinPoint.getArgs()[0];
		List<NoticeHeader> result = noticeDAO.insertNotice(NoticeType.DAILY_POST, dailyReport.getDaily_report_id(), dailyReport.getEmployee_id());	
		
		//node.js에 보내는 코드도 작성
		for(NoticeHeader dto : result) {
			deliver.sendNotice(dto.getSubject_name(), dto.getObject_id(), dto.getLink_id(), dto.getNotice_type());
		}
	}
	
	
	
	@AfterReturning("execution(public * kr.co.bne.dao.DailyReportDAO.updateApproval(..))")
	public void execInsertNotice_APPROVAL(JoinPoint joinPoint) throws Throwable {
		System.out.println("승인 aop 실행!!");
		
		int daily_report_id = Integer.parseInt(joinPoint.getArgs()[0].toString());
		List<NoticeHeader> result = noticeDAO.insertNotice(NoticeType.APPROVAL, daily_report_id);	
		
		//node.js에 보내는 코드도 작성
		for(NoticeHeader dto : result) {
			deliver.sendNotice(dto.getSubject_name(), dto.getObject_id(), dto.getLink_id(), dto.getNotice_type());
		}
	}
	
	
	
	@AfterReturning("!execution(public * kr.co.bne.dao.DailyReportDAO.deleteComment(..)) && execution(public * kr.co.bne.dao.DailyReportDAO.*Comment(..))") //댓글 수정, 댓글 등록 모두다
	public void execInsertNotice_COMMENT(JoinPoint joinPoint) throws Throwable {
		System.out.println("comment aop 실행!!");
		
		HashMap<String, String> map = (HashMap<String, String>)joinPoint.getArgs()[0];
		int daily_report_id = Integer.parseInt(map.get("daily_report_id"));
		
		List<NoticeHeader> result = noticeDAO.insertNotice(NoticeType.COMMENT, daily_report_id);
		
		//node.js에 보내는 코드도 작성
		for(NoticeHeader dto : result) {
			deliver.sendNotice(dto.getSubject_name(), dto.getObject_id(), dto.getLink_id(), dto.getNotice_type());
		}
	}
	
	
	
	@AfterReturning("execution(public * kr.co.bne.service.DailyReportService.updateDailyReport(..))")
	public void execInsertNotice_DAILY_CORRECT(JoinPoint joinPoint) throws Throwable {
		System.out.println("일일보고 수정 aop 실행!!");
		
		
		
		int daily_report_id = ((DailyReportDTO)(joinPoint.getArgs()[0])).getDaily_report_id();
		DailyReportDetailDTO dailyReport = dailyReportDAO.selectDailyReport(Integer.toString(daily_report_id));
		
		List<NoticeHeader> result = noticeDAO.insertNotice(NoticeType.DAILY_CORRECT, daily_report_id, dailyReport.getEmployee_id());
		
		//node.js에 보내는 코드도 작성
		for(NoticeHeader dto : result) {
			deliver.sendNotice(dto.getSubject_name(), dto.getObject_id(), dto.getLink_id(), dto.getNotice_type());
		}
	}

}
