package com.bisaKhadayate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;

@Component
public class CheckUserSession extends HandlerInterceptorAdapter implements Constant  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		boolean isAllow=true;		
		if (userdetails == null) {
			isAllow=false;
			for (String url : URL_LIST) {
				if (request.getRequestURI().contains(url)) 
					isAllow= true;
			}
		}
		return isAllow;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, //
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
			Object handler, Exception ex) throws Exception {
	}
	

}
