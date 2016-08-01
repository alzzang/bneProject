package kr.co.bne.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAspect {
	
	@Before("execution(public * kr.co.bne.controller.*.*(..))")
	public void checkDidLogin(JoinPoint joinPoint) {
		
		System.out.println("joinPoint: " + joinPoint.toString());
		
	}
	
}
