package com.spring.project.security.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.spring.project.security.UserService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private String loginidname;
	private String defaultUrl;
	
	@Resource(name="userSer")
	private UserService userSer;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String username = request.getParameter(loginidname);
		
		userSer.resetFailureCnt(username);
		
		clearAuthenticationAttributes(request);
		
		resultRedirectStrategy(request, response, authentication);
		
		
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest!=null) {
			useSessionUrl(request, response);
		} else {
			useDefaultUrl(request, response);
		}
		
	}
	
	protected void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		redirectStratgy.sendRedirect(request, response, targetUrl);
	}
	
	protected void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectStratgy.sendRedirect(request, response, defaultUrl);
	}

	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

}
