package com.walk.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("访问"+request.getServletPath());
		System.out.println("进入拦截器");
		HttpSession session = request.getSession();	
		System.out.println("用户"+session.getAttribute("user"));
		if(session.getAttribute("user") != null) {
			System.out.println("可以通过");
			return true;
		}
		
		//System.out.println("session失效，拦截器放回登录页面");
		response.sendRedirect("/MX/indexss");
		//request.getRequestDispatcher("login.htm").forward(request, response);
		return false;
	}


}
