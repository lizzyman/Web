package com.ktds.lizzy.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * �̷��� �����صθ� response�� request�� �ϳ��ϳ� ���ڵ� ������ �ʿ� X
		 */
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response); // Servlet�� ��û�ϱ� or �����ϱ�
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
