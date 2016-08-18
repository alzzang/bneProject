package kr.co.bne.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAspect {
	
	public LoginAspect() {
		// TODO Auto-generated constructor stub
		System.out.println("loginaspect 생성!!");
	}
	
	@After("execution(public * kr.co.bne.controller.*.*(..))")
	public void checkLoginState(JoinPoint joinPoint) throws Throwable {
		
		System.out.println("loginaspect!!!!!");
		
	}

}
