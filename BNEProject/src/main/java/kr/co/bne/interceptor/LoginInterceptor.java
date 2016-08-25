package kr.co.bne.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			if(request.getSession().getAttribute("user") == null ){
				response.sendRedirect("/user/goLoginForm");	
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}
